package com.dy.service;

import org.springframework.stereotype.Service;

@Service
public class DbBackupHandler {
    public void backup() {
        System.out.println("定时任务执行：hhh");
    }
}
