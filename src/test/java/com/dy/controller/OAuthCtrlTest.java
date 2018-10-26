package com.dy.controller;

import com.dy.util.Global;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

//        String accesstoken = WxUtil.getAccesstToken(global.wxAppid, global.wxAppsecret);
//
//        System.out.println(this.global.wxAppid);
//        String sql = "select * from t_type";
//        List<Map<String,Object>> list= this.jdbcTemplate.queryForList(sql);
//        for(Map<String,Object> m:list){
//            System.out.println(m.get("t_type_name"));
//        }
//
//        Map<String, Object> map = new HashMap<>();
//        sql = "select t_title,t_scan_origin from t_article order by t_scan_origin desc limit 0,10";
//        List<Map<String, Object>> article_scan = this.jdbcTemplate.queryForList(sql);
//        sql = "select t_title,t_search from t_article order by t_search desc limit 0,10";
//        List<Map<String, Object>> article_search = this.jdbcTemplate.queryForList(sql);
//        sql = "select t_title,t_scan_origin from t_question order by t_scan_origin desc limit 0,10";
//        List<Map<String, Object>> question_scan = this.jdbcTemplate.queryForList(sql);
//        map.put("article_scan", article_scan);
//        map.put("article_search", article_search);
//        map.put("question_scan", question_scan);
//        System.out.println(map);


        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<root>"
                + "<data ret_code=\"0\" error_message=\"失败原因\">"
                + "<refund_no>那就這样</refund_no>"
                + "</data>"
                + "<sign>xxxxxxxxxxxxxxxxxxx</sign>"
                + "</root>";
        try {
            Document document = null;
            document = DocumentHelper.parseText(xmlString);

            Element root = document.getRootElement();
            Element data = root.element("data");
            String  text=  data.elementText("refund_no");//获取节点的值
            String sign=  root.elementText("data");
            String ss="sf";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}