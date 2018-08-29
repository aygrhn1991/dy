package com.dy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeCtrl {
    @RequestMapping(value = "/test.do",method = RequestMethod.GET)
    public String index() {

        return "index";
    }
}
