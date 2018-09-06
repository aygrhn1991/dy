package com.dy.util;

public class Global {
    public String articleUploadPath;
    public String userUploadPath;
    public String fileServer;
    public String wxToken;
    public String wxAppid;
    public String wxAppsecret;

    public String getWxAppid() {
        return wxAppid;
    }

    public void setWxAppid(String wxAppid) {
        this.wxAppid = wxAppid;
    }

    public String getWxAppsecret() {
        return wxAppsecret;
    }

    public void setWxAppsecret(String wxAppsecret) {
        this.wxAppsecret = wxAppsecret;
    }

    public String getWxToken() {
        return wxToken;
    }

    public void setWxToken(String wxToken) {
        this.wxToken = wxToken;
    }

    public String getArticleUploadPath() {
        return articleUploadPath;
    }

    public void setArticleUploadPath(String articleUploadPath) {
        this.articleUploadPath = articleUploadPath;
    }

    public String getUserUploadPath() {
        return userUploadPath;
    }

    public void setUserUploadPath(String userUploadPath) {
        this.userUploadPath = userUploadPath;
    }

    public String getFileServer() {
        return fileServer;
    }

    public void setFileServer(String fileServer) {
        this.fileServer = fileServer;
    }
}
