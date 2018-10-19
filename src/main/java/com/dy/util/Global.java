package com.dy.util;

public class Global {
    public String fileArticleUploadPath;
    public String fileUserUploadPath;
    public String fileServer;
    public String wxToken;
    public String wxAppid;
    public String wxAppsecret;
    public String dbBackupCommand;
    public String dbBackupFilePath;
    public String dbBackupFileName;

    public String getDbBackupCommand() {
        return dbBackupCommand;
    }

    public void setDbBackupCommand(String dbBackupCommand) {
        this.dbBackupCommand = dbBackupCommand;
    }

    public String getDbBackupFilePath() {
        return dbBackupFilePath;
    }

    public void setDbBackupFilePath(String dbBackupFilePath) {
        this.dbBackupFilePath = dbBackupFilePath;
    }

    public String getDbBackupFileName() {
        return dbBackupFileName;
    }

    public void setDbBackupFileName(String dbBackupFileName) {
        this.dbBackupFileName = dbBackupFileName;
    }

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

    public String getFileArticleUploadPath() {
        return fileArticleUploadPath;
    }

    public void setFileArticleUploadPath(String fileArticleUploadPath) {
        this.fileArticleUploadPath = fileArticleUploadPath;
    }

    public String getFileUserUploadPath() {
        return fileUserUploadPath;
    }

    public void setFileUserUploadPath(String fileUserUploadPath) {
        this.fileUserUploadPath = fileUserUploadPath;
    }

    public String getFileServer() {
        return fileServer;
    }

    public void setFileServer(String fileServer) {
        this.fileServer = fileServer;
    }
}
