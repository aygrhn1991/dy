package com.dy.controller;

import com.dy.util.Global;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml","classpath*:connectionContext.xml"})
public class OAuthCtrlTest {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("global")
    private Global global;

    @Test
    public void log() {
        System.out.println(this.global.wxAppid);
        String sql = "select * from t_type";
        List<Map<String,Object>> list= this.jdbcTemplate.queryForList(sql);
        for(Map<String,Object> m:list){
            System.out.println(m.get("t_type_name"));
        }
    }
}