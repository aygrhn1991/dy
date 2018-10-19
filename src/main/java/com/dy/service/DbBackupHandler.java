package com.dy.service;

import com.dy.controller.OAuthCtrl;
import com.dy.util.Global;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class DbBackupHandler {

    @Autowired
    @Qualifier("global")
    private Global global;

    private static final Logger logger = LogManager.getLogger(OAuthCtrl.class.getName());

    public boolean backup() {
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(global.dbBackupFilePath + global.dbBackupFileName), "utf8"));
            Process process = Runtime.getRuntime().exec(global.dbBackupCommand);
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
