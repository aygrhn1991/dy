package com.dy.controller;

import com.dy.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/home")
public class HomeCtrl {
    @Autowired
    @Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;

    //<editor-fold desc="页面">
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/ask")
    public String ask() {
        return "ask";
    }

    @RequestMapping("/askfinish")
    public String askfinish() {
        return "askfinish";
    }

    @RequestMapping("/questions")
    public String questions() {
        return "questions";
    }

    @RequestMapping("/articles")
    public String articles() {
        return "articles";
    }

    @RequestMapping("/question")
    public String question() {
        return "question";
    }

    @RequestMapping("/article")
    public String article() {
        return "article";
    }

    //</editor-fold>

    //<editor-fold desc="首页">
    @RequestMapping("/addquestion")
    @ResponseBody
    public boolean addquestion(@RequestBody Question question) {
        String sql = "insert into t_question(t_type_id, t_title, t_user_id, t_time, t_scan, t_sort, t_top, t_solved) values (0,?,?,?,0,0,0,0)";
        int count = this.jdbcTemplate.update(sql, new Object[]{question.t_title, this.getUserId(), new Date().getTime()});
        return count == 1;
    }
    //</editor-fold>

    private int getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userid")) {
                return Integer.parseInt(cookie.getValue());
            }
        }
        return 0;
    }
}
