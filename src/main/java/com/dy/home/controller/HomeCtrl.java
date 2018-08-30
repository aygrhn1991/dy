package com.dy.home.controller;

import com.dy.home.dao.HomeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/home")
public class HomeCtrl {
    @Autowired
    @Qualifier("homeDao")
    HomeDao homeDao;

    @RequestMapping("/index")
    public String index() {
        int count = this.homeDao.queryUserCount();
        return "index";
    }
}
