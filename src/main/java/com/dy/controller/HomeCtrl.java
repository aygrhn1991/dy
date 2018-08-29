package com.dy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/home"})
public class HomeCtrl {

    @Autowired
    HomeDao dao;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index() {
        int i = this.dao.getNum();
        return "index";
    }
}
