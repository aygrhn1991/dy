package com.dy.util;

import com.dy.model.wx.AccessToken;
import com.google.gson.Gson;

import java.security.MessageDigest;
import java.util.Arrays;

public class WxUtil {

    public static boolean checkConfig(String token, String timestamp, String nonce, String signature) {
        String[] strArray = new String[]{token, timestamp, nonce};
        Arrays.sort(strArray);
        String strResult = getSha1(strArray[0] + strArray[1] + strArray[2]);
        return strResult.equals(signature);
    }
    public static String getAccesstToken(String appid,String appsecret) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appid, appsecret);
        String response = HttpUtil.Get(url);
        Gson gson = new Gson();
        AccessToken accessToken = gson.fromJson(response, AccessToken.class);
        return accessToken.access_token;
    }
    private static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
