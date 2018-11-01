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
@ContextConfiguration(locations = {"classpath*:applicationContext.xml", "classpath*:connectionContext.xml"})
public class OAuthCtrlTest {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("global")
    private Global global;

    @Test
    public void log() {

        String keyword = "试管";
        int pageIndex = 1;
        int pageSize = 5;
        List<Map<String, Object>> list = null;
        String sql = "select * from t_question where t_solved=1";
        if (keyword != null && !keyword.equals("")) {
            sql += " and t_title like '%" + keyword + "%' ";
        }
        sql += " order by t_top desc,t_sort desc,t_scan desc,t_id desc ";
        sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
        list = this.jdbcTemplate.queryForList(sql);

        Object o=list.get(0).get("t_visitors");
        boolean b=o==null;
        String visitors=String.valueOf(list.get(0).get("t_visitors"));
        boolean r=visitors.contains("-14-");
        //处理搜索次数
        if (keyword != null && !keyword.equals("")) {
            for (Map<String, Object> m : list) {
                sql = "update t_question set t_search=t_search+1 where t_id=?";
                System.out.println(this.jdbcTemplate.update(sql, new Object[]{m.get("t_id")}));
                System.out.println("--------");
            }
        }
        //System.out.println(list);
    }
}