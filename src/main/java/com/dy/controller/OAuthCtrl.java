package com.dy.controller;

import com.dy.model.wx.OAuthUserAccessToken;
import com.dy.model.wx.OAuthUserInfo;
import com.dy.util.Global;
import com.dy.util.HttpUtil;
import com.dy.util.WxUtil;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/oauth")
public class OAuthCtrl {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("global")
    private Global global;

    private static final Logger logger = LogManager.getLogger(OAuthCtrl.class.getName());

    @RequestMapping("/config")
    @ResponseBody
    public String config(HttpServletRequest request) {
        if (request.getMethod().toLowerCase().equals("get")) {
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String signature = request.getParameter("signature");
            String echostr = request.getParameter("echostr");
            return WxUtil.checkConfig(this.global.wxToken, timestamp, nonce, signature) ? echostr : null;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/requestcode", method = RequestMethod.GET)
    public String auth(HttpServletRequest request) throws UnsupportedEncodingException {
        String baseUrl = HttpUtil.getBaseUrl(request);
        String encodeUrl = URLEncoder.encode(baseUrl + "/oauth/getcode", "utf-8");
        String url = String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect", this.global.wxAppid, encodeUrl, "snsapi_userinfo", "dy");
        return "redirect:" + url;
    }

    @RequestMapping(value = "/getcode", method = RequestMethod.GET)
    public String getcode(HttpServletRequest request, HttpServletResponse response) {
        String state = request.getParameter("state");
        String code = request.getParameter("code");
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", this.global.wxAppid, this.global.wxAppsecret, code);
        String rsp = HttpUtil.Get(url);
        logger.info("微信授权，code换取accesstoken：" + rsp);
        Gson gson = new Gson();
        OAuthUserAccessToken oAuthUserAccessToken = gson.fromJson(rsp, OAuthUserAccessToken.class);
        String sql = "select * from t_user where w_openid=?";
        List<Map<String, Object>> userList = this.jdbcTemplate.queryForList(sql, new Object[]{oAuthUserAccessToken.openid});
        if (userList.size() == 1) {
            //统计登陆次数
            try {
                long current = System.currentTimeMillis();
                long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
                sql = "select count(*) from t_scan where t_time=?";
                int count = this.jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{zero});
                if (count == 0) {
                    sql = "insert into t_scan(t_time,t_scan) values(" + zero + ",1)";
                } else {
                    sql = "update t_scan set t_scan=t_scan+1 where t_time=" + zero;
                }
                count = this.jdbcTemplate.update(sql);
            } catch (Exception e) {
                logger.error("统计登陆次数异常：" + e.getMessage());
            }
            //登陆
            Cookie cookie = new Cookie("userid", userList.get(0).get("t_id").toString());
            cookie.setDomain(request.getServerName());
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/home/index";
        } else {
            url = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN", oAuthUserAccessToken.access_token, oAuthUserAccessToken.openid);
            rsp = HttpUtil.Get(url);
            logger.info("微信授权，accesstoken换取userinfo（授权专用accesstoken）：" + rsp);
            OAuthUserInfo oAuthUserInfo = gson.fromJson(rsp, OAuthUserInfo.class);
            sql = "insert into t_user(w_openid,w_nickname,w_sex,w_province,w_city,w_country,w_headimgurl,t_time) values (?,?,?,?,?,?,?,?)";
            int count = this.jdbcTemplate.update(sql, new Object[]{oAuthUserInfo.openid,
                    oAuthUserInfo.nickname,
                    oAuthUserInfo.sex,
                    oAuthUserInfo.province,
                    oAuthUserInfo.city,
                    oAuthUserInfo.country,
                    oAuthUserInfo.headimgurl,
                    new Date().getTime()});
            return "redirect:/oauth/requestcode";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("userid", "14");
        cookie.setDomain(request.getServerName());
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/home/index";
    }

    @RequestMapping("/jssdkconfig")
    @ResponseBody
    public Map<String, Object> jssdkconfig(HttpServletRequest request) {
        String appId = global.wxAppid;
        long timestamp = new Date().getTime();
        String nonceStr = global.wxToken;
        String accesstoken = WxUtil.getAccesstToken(global.wxAppid, global.wxAppsecret);
        String jsapiticket = WxUtil.getJsapiTicket(accesstoken);
        String url = request.getParameter("url");
        String signature = WxUtil.getJsapiSignature(jsapiticket, timestamp, nonceStr, url);
        Map<String, Object> map = new HashMap<>();
        map.put("appId", appId);
        map.put("timestamp", timestamp);
        map.put("nonceStr", nonceStr);
        map.put("signature", signature);
        return map;
    }

}
