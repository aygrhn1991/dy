package com.dy.controller;

import com.dy.model.wx.OAuthUserAccessToken;
import com.dy.model.wx.UserInfoModel;
import com.dy.util.Global;
import com.dy.util.HttpUtil;
import com.dy.util.WxUtil;
import com.google.gson.Gson;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        String baseUrl = HttpUtil.getBaseUrlWithoutPort(request);
        String encodeUrl = URLEncoder.encode(baseUrl + "/oauth/getcode", "utf-8");
        String url = String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect", this.global.wxAppid, encodeUrl, "snsapi_base", "dy");
        return "redirect:" + url;
    }

    @RequestMapping(value = "/getcode", method = RequestMethod.GET)
    public String getcode(HttpServletRequest request, HttpServletResponse response) {
        String state = request.getParameter("state");
        String code = request.getParameter("code");
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", this.global.wxAppid, this.global.wxAppsecret, code);
        String rsp = HttpUtil.Get(url);
        Gson gson = new Gson();
        OAuthUserAccessToken oAuthUserAccessToken = gson.fromJson(rsp, OAuthUserAccessToken.class);
        String sql = "select * from t_user where w_openid=?";
        List<Map<String, Object>> userList = this.jdbcTemplate.queryForList(sql, new Object[]{oAuthUserAccessToken.openid});
        if (userList.size() == 1) {
            Cookie cookie = new Cookie("userid", userList.get(0).get("t_id").toString());
            cookie.setDomain(request.getServerName());
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/home/index";
        } else {
            String accessToken = WxUtil.getAccesstToken(this.global.wxAppid, this.global.wxAppsecret);
            url = String.format("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN", accessToken, oAuthUserAccessToken.openid);
            rsp = HttpUtil.Get(url);
            UserInfoModel userInfoModel = gson.fromJson(rsp, UserInfoModel.class);
            sql = "insert into t_user(w_openid,w_nickname,w_sex,w_province,w_city,w_country,w_headimgurl,t_time) values (?,?,?,?,?,?,?,?)";
            int count = this.jdbcTemplate.update(sql, new Object[]{userInfoModel.openid,
                    userInfoModel.nickname.replaceAll("[\ue000-\uefff]", ""),
                    userInfoModel.sex,
                    userInfoModel.province,
                    userInfoModel.city,
                    userInfoModel.country,
                    userInfoModel.headimgurl,
                    new Date().getTime()});
            return "redirect:/oauth/requestcode";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("userid", "10");
        cookie.setDomain(request.getServerName());
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/home/index";
    }
}
