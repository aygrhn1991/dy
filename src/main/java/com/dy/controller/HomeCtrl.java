package com.dy.controller;

import com.dy.model.Answer;
import com.dy.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        int count = this.jdbcTemplate.update(sql, new Object[]{question.t_title, question.t_user_id, new Date().getTime()});
        return count == 1;
    }

    @RequestMapping("/queryquestionsbytop")
    @ResponseBody
    public List<Map<String, Object>> queryquestionsbytop() {
        String sql = "select * from t_question ";
        sql += " order by t_top desc,t_sort desc,t_scan desc,t_id desc ";
        sql += " limit 0,5";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        return list;
    }

    @RequestMapping("/queryarticlesbytop")
    @ResponseBody
    public List<Map<String, Object>> queryarticlesbytop() {
        String sql = "select * from t_article ";
        sql += " order by t_top desc,t_sort desc,t_scan desc,t_id desc ";
        sql += " limit 0,4";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        return list;
    }
    //</editor-fold>

    //<editor-fold desc="图文内容">
    @RequestMapping("/queryarticle/{id}")
    @ResponseBody
    public Map<String, Object> queryarticle(@PathVariable("id") int id) {
        String sql = "update t_article set t_scan=t_scan+1 where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{id});
        sql = "select * from t_article where t_id=?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{id});
        return list.get(0);
    }
    //</editor-fold>

    //<editor-fold desc="问提回答">
    @RequestMapping("/queryuser/{id}")
    @ResponseBody
    public Map<String, Object> queryuser(@PathVariable("id") int id) {
        String sql = "select * from t_user where t_id=?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{id});
        return list.get(0);
    }

    @RequestMapping("/queryquestion/{id}")
    @ResponseBody
    public Map<String, Object> queryquestion(@PathVariable("id") int id) {
        String sql = "select * from t_question where t_id=?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{id});
        return list.get(0);
    }

    @RequestMapping("/queryallanswers/{id}")
    @ResponseBody
    public List<Map<String, Object>> queryallanswers(@PathVariable("id") int id) {
        String sql = "select * from t_answer where t_question_id=?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{id});
        return list;
    }

    @RequestMapping("/addanswer")
    @ResponseBody
    public boolean addanswer(@RequestBody Answer answer) {
        String sql = "insert into t_answer(t_question_id, t_user_id, t_time, t_content) values (?,?,?,?)";
        int count = this.jdbcTemplate.update(sql, new Object[]{answer.t_question_id, answer.t_user_id, new Date().getTime(), answer.t_content});
        if (count == 1) {
            sql = "update t_question set t_solved=0 where t_id=" + answer.t_question_id;
            count = this.jdbcTemplate.update(sql);
            return count == 1;
        }
        return false;
    }
    //</editor-fold>

    //<editor-fold desc="图文列表">
    @RequestMapping(value = {"/queryarticles/{pageIndex}/{pageSize}/{orderby}/{id}/{keyword}",
            "/queryarticles/{pageIndex}/{pageSize}/{orderby}/{id}"})
    @ResponseBody
    public List<Map<String, Object>> queryarticles(
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize,
            @PathVariable("orderby") int orderby,//1.最新 2.热门
            @PathVariable("id") int id,
            @PathVariable(value = "keyword", required = false) String keyword) {
        List<Map<String, Object>> list;
        String sql = "select t_id,t_title,t_cover from t_article ";
        if (keyword == null || keyword.equals("")) {
            sql += " where t_type_id=? ";
            if (orderby == 1) {
                sql += " order by t_time desc ";
            } else {
                sql += " order by t_top desc,t_sort desc,t_scan desc,t_id desc ";
            }
            sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
            list = this.jdbcTemplate.queryForList(sql, new Object[]{id});
        } else {
            sql += " where t_title like '%" + keyword + "%' ";
            sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
            list = this.jdbcTemplate.queryForList(sql);
        }
        return list;
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
