package com.dy.controller;

import com.dy.model.Answer;
import com.dy.model.Code;
import com.dy.model.Question;
import com.dy.util.FileUtil;
import com.dy.util.Global;
import com.dy.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/home")
public class HomeCtrl {

    @Autowired
    @Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("global")
    private Global global;

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

    //<editor-fold desc="提问">
    @RequestMapping("/addquestion")
    @ResponseBody
    public boolean addquestion(@RequestBody final Question question) {
        final String sql = "insert into t_question(t_title, t_user_id, t_time, t_scan, t_sort, t_top, t_solved, t_scan_origin) values (?,?,?,0,0,0,0,0)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, question.t_title);
                ps.setInt(2, question.t_user_id);
                ps.setLong(3, new Date().getTime());
                return ps;
            }
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        String sql2 = "select * from t_tag";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql2);
        List<Object[]> objs = new ArrayList<>();
        int counter = 0;
        for (Map<String, Object> m : list) {
            if (question.t_title.contains(String.valueOf(m.get("t_tag_name")))) {
                objs.add(new Object[]{id, m.get("t_id")});
                counter++;
            }
        }
        sql2 = "insert into t_question_tag (t_question_id,t_tag_id) values(?,?)";
        if (counter != 0) {
            int[] counts = this.jdbcTemplate.batchUpdate(sql2, objs);
            return counts.length == counter;
        }
        return id > 0;
    }

    //</editor-fold>

    //<editor-fold desc="追问">
    @RequestMapping("/queryallanswers/{id}")
    @ResponseBody
    public List<Map<String, Object>> queryallanswers(@PathVariable("id") int id) {
        String sql = "select * from t_answer where t_question_id=?";
        return this.jdbcTemplate.queryForList(sql, new Object[]{id});
    }

    @RequestMapping("/addanswer")
    @ResponseBody
    public boolean addanswer(@RequestBody Answer answer) {
        String sql = "insert into t_answer(t_question_id, t_user_id, t_isimg, t_time, t_content) values (?,?,0,?,?)";
        int count = this.jdbcTemplate.update(sql, new Object[]{answer.t_question_id, answer.t_user_id, new Date().getTime(), answer.t_content});
        if (count == 1) {
            sql = "update t_question set t_solved=0 where t_id=" + answer.t_question_id;
            count = this.jdbcTemplate.update(sql);
            return count == 1;
        }
        return false;
    }

    @RequestMapping(value = {"/addimg"}, method = RequestMethod.POST)
    @ResponseBody
    public boolean addimg(@RequestParam("file") MultipartFile file,
                          @RequestParam("t_question_id") int t_question_id,
                          @RequestParam("t_user_id") int t_user_id) throws IOException {
        String fileName = UUID.randomUUID() + FileUtil.getFileExtensionName(file.getOriginalFilename());
        String savePath = this.global.userUploadPath + fileName;
        File localFile = new File(savePath);
        file.transferTo(localFile);
        String sql = "insert into t_answer(t_question_id, t_user_id, t_isimg, t_time, t_content) values (?,?,1,?,?)";
        int count = this.jdbcTemplate.update(sql, new Object[]{t_question_id, t_user_id, new Date().getTime(), fileName});
        if (count == 1) {
            sql = "update t_question set t_solved=0 where t_id=" + t_question_id;
            count = this.jdbcTemplate.update(sql);
            return count == 1;
        }
        return false;
    }

    //</editor-fold>

    //<editor-fold desc="图文">
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
        String sql = "select t_id,t_title,t_cover,t_url,t_mode from t_article ";
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
            sql += " order by t_id desc ";
            sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
            list = this.jdbcTemplate.queryForList(sql);
        }
        //处理搜索次数
        if (keyword != null && !keyword.equals("")) {
            for (Map<String, Object> m : list) {
                sql = "update t_article set t_search=t_search+1 where t_id=?";
                this.jdbcTemplate.update(sql, new Object[]{m.get("t_id")});
            }
        }
        return list;
    }

    @RequestMapping("/queryarticlesbytop")
    @ResponseBody
    public List<Map<String, Object>> queryarticlesbytop() {
        String sql = "select t_id,t_title,t_cover,t_url,t_mode from t_article ";
        sql += " order by t_top desc,t_sort desc,t_scan desc,t_id desc ";
        sql += " limit 0,4";
        return this.jdbcTemplate.queryForList(sql);
    }

    @RequestMapping("/queryarticlesbytag/{t_title}")
    @ResponseBody
    public List<Map<String, Object>> queryarticlesbytag(@PathVariable("t_title") String t_title) {
        String sql = "select * from t_tag";
        List<Map<String, Object>> tagList = this.jdbcTemplate.queryForList(sql);
        List<String> tagNames = new ArrayList<>();
        for (Map<String, Object> m : tagList) {
            if (t_title.contains(String.valueOf(m.get("t_tag_name")))) {
                tagNames.add(String.valueOf(m.get("t_tag_name")));
            }
        }
        Set questionIds = new HashSet();
        for (String m : tagNames) {
            sql = "select t_id from t_article where t_title like '%" + m + "%'";
            List<Map<String, Object>> questionList = this.jdbcTemplate.queryForList(sql);
            for (Map<String, Object> n : questionList) {
                questionIds.add(n.get("t_id"));
            }
        }
        List<Map<String, Object>> result = new ArrayList<>();
        if (questionIds.size() != 0) {
            StringBuilder ids = new StringBuilder();
            for (Object i : questionIds) {
                ids.append(i).append(",");
            }
            ids = new StringBuilder(ids.substring(0, ids.length() - 1));
            sql = "select t_id,t_title,t_cover,t_url,t_mode from t_article where t_id in (" + ids + ")";
            sql += " order by t_top desc,t_sort desc,t_scan desc,t_id desc limit 0,10 ";
            result = this.jdbcTemplate.queryForList(sql);
        }
        return result;
    }

    @RequestMapping("/queryarticle/{id}")
    @ResponseBody
    public Map<String, Object> queryarticle(@PathVariable("id") int id) {
        //处理浏览量
        String sql = "update t_article set t_scan=t_scan+1,t_scan_origin=t_scan_origin+1 where t_id=?";
        this.jdbcTemplate.update(sql, new Object[]{id});
        sql = "select * from t_article where t_id=?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{id});
        return list.get(0);
    }

    //</editor-fold>

    //<editor-fold desc="问题">
    @RequestMapping("/queryallquestions/{id}")
    @ResponseBody
    public List<Map<String, Object>> queryallquestions(@PathVariable("id") int id) {
        String sql = "select * from t_question where t_user_id=?";
        sql += " order by t_time desc ";
        return this.jdbcTemplate.queryForList(sql, new Object[]{id});
    }

    @RequestMapping("/queryquestions/{pageIndex}/{pageSize}")
    @ResponseBody
    public List<Map<String, Object>> queryquestions(@PathVariable("pageIndex") int pageIndex,
                                                    @PathVariable("pageSize") int pageSize) {
        String sql = "select * from t_question ";
        sql += " order by t_top desc,t_sort desc,t_scan desc,t_id desc ";
        sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
        return this.jdbcTemplate.queryForList(sql);
    }

    @RequestMapping("/queryquestionsbytop")
    @ResponseBody
    public List<Map<String, Object>> queryquestionsbytop() {
        String sql = "select * from t_question where t_solved=1";
        sql += " order by t_top desc,t_sort desc,t_scan desc,t_id desc ";
        sql += " limit 0,5";
        return this.jdbcTemplate.queryForList(sql);
    }

    @RequestMapping("/queryquestionsbytag/{t_title}")
    @ResponseBody
    public List<Map<String, Object>> queryquestionsbytag(@PathVariable("t_title") String t_title) {
        String sql = "select * from t_tag";
        List<Map<String, Object>> tagList = this.jdbcTemplate.queryForList(sql);
        List<Integer> tagIds = new ArrayList<>();
        for (Map<String, Object> m : tagList) {
            if (t_title.contains(String.valueOf(m.get("t_tag_name")))) {
                tagIds.add((int) m.get("t_id"));
            }
        }
        List<Map<String, Object>> questionList = new ArrayList<>();
        if (tagIds.size() != 0) {
            StringBuilder tags = new StringBuilder();
            for (int i : tagIds) {
                tags.append(i).append(",");
            }
            tags = new StringBuilder(tags.substring(0, tags.length() - 1));
            sql = "select t_title from t_question_tag left join t_question on t_id=t_question_id where t_tag_id in (" + tags + ") and t_question.t_solved=1 group by t_id";
            sql += " order by t_top desc,t_sort desc,t_scan desc,t_id desc limit 0,10 ";
            questionList = this.jdbcTemplate.queryForList(sql);
        }
        return questionList;
    }

    @RequestMapping("/queryquestion/{id}")
    @ResponseBody
    public Map<String, Object> queryquestion(@PathVariable("id") int id) {
        String sql = "update t_question set t_scan=t_scan+1,t_scan_origin=t_scan_origin+1 where t_id=?";
        this.jdbcTemplate.update(sql, new Object[]{id});
        sql = "select * from t_question where t_id=?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{id});
        return list.get(0);
    }

    //</editor-fold>

    //<editor-fold desc="用户">
    @RequestMapping("/queryuser/{id}")
    @ResponseBody
    public Map<String, Object> queryuser(@PathVariable("id") int id) {
        String sql = "select * from t_user where t_id=?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{id});
        return list.get(0);
    }

    @RequestMapping("/sendcode/{phone}")
    @ResponseBody
    public boolean sendcode(@PathVariable("phone") String phone) {
        String sql = "select count(*) from t_code where t_phone=?";
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{phone});
        int code = Util.getRandomNum(1000, 9999);
        if (count != 0) {
            sql = "update t_code set t_code=?,t_time=? where t_phone=?";
            count = this.jdbcTemplate.update(sql, new Object[]{code, new Date().getTime(), phone});
        } else {
            sql = "insert into t_code(t_phone,t_code,t_time) values(?,?,?)";
            count = this.jdbcTemplate.update(sql, new Object[]{phone, code, new Date().getTime()});
        }
        return count == 1;
//        if (count == 1) {
//            return SmsHandler.sendSms(phone, String.valueOf(code));
//        }
//        return false;
    }

    @RequestMapping("/register/{id}")
    @ResponseBody
    public Map<String, Object> register(@RequestBody Code code, @PathVariable("id") int id) {
        Map<String, Object> map = new HashMap<>();
        String sql = "select * from t_code where t_phone=?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{code.t_phone});
        if (list.size() == 0) {
            map.put("result", "手机号码不存在");
            return map;
        }
        if ((new Date().getTime() - (long) list.get(0).get("t_time")) > 10 * 60 * 1000) {//验证码十分钟有效
            map.put("result", "验证码过期");
            return map;
        }
        if (!code.t_code.equals(list.get(0).get("t_code"))) {
            map.put("result", "验证码错误");
            return map;
        }
        sql = "select count(*) from t_user where t_phone=?";
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{code.t_phone});
        if (count != 0) {
            map.put("result", "该手机号码已注册");
            return map;
        }
        sql = "update t_user set t_phone=? where t_id=?";
        count = this.jdbcTemplate.update(sql, new Object[]{code.t_phone, id});
        map.put("result", count == 1);
        return map;
    }
    //</editor-fold>


}
