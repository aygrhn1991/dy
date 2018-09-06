package com.dy.controller;

import com.dy.model.wx.OAuthUserAccessToken;
import com.dy.model.wx.OAuthUserInfo;
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

import javax.servlet.http.HttpServletRequest;

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
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth(HttpServletRequest request) {
        String currentBaseUrl = HttpUtil.getCurrentBaseUrlWithoutPort(request);
        //String encodeUrl = URLEncoder.encode(currentBaseUrl + "/c/wxhome/login", "utf-8");
        //String url = OAuthUtil.getRedirectUrl(encodeUrl, OAuthScopeEnum.snsapi_base, "state");
        return "redirect:" + currentBaseUrl;
    }
    @RequestMapping(value = "/getcode", method = RequestMethod.GET)
    public @ResponseBody
    String getcode(HttpServletRequest request) {
        String state = request.getParameter("state");
        String code = request.getParameter("code");
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", this.global.wxAppid, this.global.wxAppsecret, code);
        String response = HttpUtil.Get(url);
        Gson gson = new Gson();
        OAuthUserAccessToken oAuthUserAccessToken = gson.fromJson(response, OAuthUserAccessToken.class);
        url = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN", oAuthUserAccessToken.access_token, oAuthUserAccessToken.openid);
        response = HttpUtil.Get(url);
        OAuthUserInfo oAuthUserInfo = gson.fromJson(response, OAuthUserInfo.class);
        return "result";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/querytypescount")
    @ResponseBody
    public int querytypescount() {
        String sql = "select count(*) from t_type";
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }
}
