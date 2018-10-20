package com.dy.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HttpUtil {
    public static String Get(String url) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity, "UTF-8");
            httpResponse.close();
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String Post(String url, List<NameValuePair> params) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            httpPost.setEntity(entity);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity, "UTF-8");
            httpResponse.close();
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String getBaseUrl(HttpServletRequest request) {
        try {
            return request.getScheme() + "://" + request.getServerName() + request.getContextPath();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String getFullUrl(HttpServletRequest request) {
        try {
            return request.getScheme() + "://" + request.getServerName() + request.getContextPath() + request.getServletPath() + "?" + request.getQueryString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
