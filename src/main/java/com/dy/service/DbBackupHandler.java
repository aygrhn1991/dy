package com.dy.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbBackupHandler {

    private String dbBackupFilePath;
    private String dbBackupCommand;

    private static final Logger logger = LogManager.getLogger(DbBackupHandler.class.getName());

    public String getDbBackupFilePath() {
        return dbBackupFilePath;
    }

    public void setDbBackupFilePath(String dbBackupFilePath) {
        this.dbBackupFilePath = dbBackupFilePath;
    }

    public String getDbBackupCommand() {
        return dbBackupCommand;
    }

    public void setDbBackupCommand(String dbBackupCommand) {
        this.dbBackupCommand = dbBackupCommand;
    }

    public boolean backup() {
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String time = format.format(new Date());
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(dbBackupFilePath + time + ".sql"), "utf8"));
            Process process = Runtime.getRuntime().exec(dbBackupCommand);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            printWriter.flush();
            if (process.waitFor() == 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error("数据库备份异常：" + e.getMessage());
        } finally {
            try {
                bufferedReader.close();
                printWriter.close();
            } catch (Exception e) {
                logger.error("数据库备份流关闭异常：" + e.getMessage());
            }
        }
        return false;
    }
}
