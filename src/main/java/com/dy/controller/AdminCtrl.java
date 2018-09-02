package com.dy.controller;

import com.dy.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/admin")
public class AdminCtrl {
    @Autowired
    @Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    //<editor-fold desc="科室">
    @RequestMapping("/querytypescount")
    @ResponseBody
    public int querytypescount() {
        String sql = "select count(*) from t_type";
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    @RequestMapping("/queryalltypes")
    @ResponseBody
    public List<Map<String, Object>> queryalltypes() {
        String sql = "select * from t_type";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        return list;
    }
    @RequestMapping("/querytypes/{pageIndex}/{pageSize}")
    @ResponseBody
    public List<Map<String, Object>> querytypes(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
        String sql = "select * from t_type";
        sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        return list;
    }

    @RequestMapping("/addtype")
    @ResponseBody
    public boolean addtype(@RequestBody Type type) {
        String sql = "insert into t_type(t_type_name) values(?)";
        int count = this.jdbcTemplate.update(sql, new Object[]{type.t_type_name});
        return count == 1;
    }

    @RequestMapping("/edittype")
    @ResponseBody
    public boolean edittype(@RequestBody Type type) {
        String sql = "update t_type set t_type_name=? where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{type.t_type_name, type.t_id});
        return count == 1;
    }

    @RequestMapping("/deletetype/{id}")
    @ResponseBody
    public boolean deletetype(@PathVariable("id") int id) {
        String sql = "delete from t_type where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{id});
        return count == 1;
    }

    //</editor-fold>
    //<editor-fold desc="图文">
    @RequestMapping("/queryarticlescount")
    @ResponseBody
    public int queryarticlescount() {
        String sql = "select count(*) from t_article";
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    @RequestMapping("/queryarticles/{pageIndex}/{pageSize}")
    @ResponseBody
    public List<Map<String, Object>> queryarticles(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
        String sql = "select t_article.t_id,t_type_id,t_type_name,t_title,t_author,t_time,t_cover,t_scan,t_sort,t_top from t_article left join t_type on t_type.t_id=t_article.t_type_id";
        sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        return list;
    }

    @RequestMapping("/addarticle")
    @ResponseBody
    public boolean addarticle(@RequestBody Article article) {
        String sql = "insert into t_article(t_type_id, t_title, t_author, t_time, t_cover, t_content, t_scan, t_sort, t_top) values (?,?,?,?,?,?,0,0,0)";
        int count = this.jdbcTemplate.update(sql, new Object[]{article.t_type_id, article.t_title, article.t_author, article.t_time, article.t_cover, article.t_content});
        return count == 1;
    }

    @RequestMapping("/editarticle")
    @ResponseBody
    public boolean editarticle(@RequestBody Article article) {
        String sql = "update t_article set t_type_id=?,t_title=?,t_author=?,t_time=?,t_cover=?,t_content=?,t_scan=?,t_sort=?,t_top=? where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{article.t_type_id, article.t_title, article.t_author, article.t_time, article.t_cover, article.t_content, article.t_scan, article.t_sort, article.t_top, article.t_id});
        return count == 1;
    }

    @RequestMapping("/deletearticle/{id}")
    @ResponseBody
    public boolean deletearticle(@PathVariable("id") int id) {
        String sql = "delete from t_article where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{id});
        return count == 1;
    }

    //</editor-fold>
}
