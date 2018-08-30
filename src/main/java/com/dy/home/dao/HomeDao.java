package com.dy.home.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("homeDao")
public class HomeDao {
    @Autowired
    @Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;

    public int queryUserCount() {
        String sql = "select count(*) from t_user";
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }
}
