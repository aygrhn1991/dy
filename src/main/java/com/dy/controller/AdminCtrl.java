package com.dy.controller;

import com.dy.model.Type;
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

    //<editor-fold desc="类型">
    @RequestMapping("/querytypescount")
    @ResponseBody
    public int querytypescount() {
        String sql = "select count(*) from t_type";
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
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
}
