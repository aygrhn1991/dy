package com.dy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class HomeDao {
    @Autowired
    @Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;

    public int getNum() {
        String sql = "select count(*) from t_user";
        int i = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return i;
    }
}
