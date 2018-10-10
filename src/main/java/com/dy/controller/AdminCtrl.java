package com.dy.controller;

import com.dy.model.*;
import com.dy.util.FileUtil;
import com.dy.util.Global;
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
@RequestMapping("/admin")
public class AdminCtrl {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("global")
    private Global global;

    //<editor-fold desc="科室">
    @RequestMapping("/querytypescount")
    @ResponseBody
    public int querytypescount() {
        String sql = "select count(*) from t_type";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @RequestMapping("/queryalltypes")
    @ResponseBody
    public List<Map<String, Object>> queryalltypes() {
        String sql = "select * from t_type";
        sql += " order by t_id desc ";
        return this.jdbcTemplate.queryForList(sql);
    }

    @RequestMapping("/querytypes/{pageIndex}/{pageSize}")
    @ResponseBody
    public List<Map<String, Object>> querytypes(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
        String sql = "select * from t_type";
        sql += " order by t_id desc ";
        sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
        return this.jdbcTemplate.queryForList(sql);
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

    //<editor-fold desc="标签">
    @RequestMapping("/querytagscount")
    @ResponseBody
    public int querytagscount() {
        String sql = "select count(*) from t_tag";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @RequestMapping("/queryalltags")
    @ResponseBody
    public List<Map<String, Object>> queryalltags() {
        String sql = "select * from t_tag";
        sql += " order by t_id desc ";
        return this.jdbcTemplate.queryForList(sql);
    }

    @RequestMapping("/querytags/{pageIndex}/{pageSize}")
    @ResponseBody
    public List<Map<String, Object>> querytags(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
        String sql = "select * from t_tag";
        sql += " order by t_id desc ";
        sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
        return this.jdbcTemplate.queryForList(sql);
    }

    @RequestMapping("/addtag")
    @ResponseBody
    public boolean addtag(@RequestBody Tag tag) {
        String sql = "insert into t_tag(t_tag_name) values(?)";
        int count = this.jdbcTemplate.update(sql, new Object[]{tag.t_tag_name});
        return count == 1;
    }

    @RequestMapping("/edittag")
    @ResponseBody
    public boolean edittag(@RequestBody Tag tag) {
        String sql = "update t_tag set t_tag_name=? where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{tag.t_tag_name, tag.t_id});
        return count == 1;
    }

    @RequestMapping("/deletetag/{id}")
    @ResponseBody
    public boolean deletetag(@PathVariable("id") int id) {
        String sql = "delete from t_tag where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{id});
        return count == 1;
    }

    //</editor-fold>

    //<editor-fold desc="图文">
    @RequestMapping(value = {"/queryarticlescount/{id}",
            "/queryarticlescount/{id}/{keyword}"})
    @ResponseBody
    public int queryarticlescount(@PathVariable(value = "id") int id,
                                  @PathVariable(value = "keyword", required = false) String keyword) {
        String sql = "select count(*) from t_article";
        sql += " where 1=1 ";
        if (keyword != null && !keyword.equals("")) {
            sql += " and t_title like '%" + keyword + "%'";
        }
        if (id != -1) {
            sql += " and t_type_id=" + id;
        }
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @RequestMapping(value = {"/queryarticles/{pageIndex}/{pageSize}/{id}",
            "/queryarticles/{pageIndex}/{pageSize}/{id}/{keyword}"})
    @ResponseBody
    public List<Map<String, Object>> queryarticles(@PathVariable("pageIndex") int pageIndex,
                                                   @PathVariable("pageSize") int pageSize,
                                                   @PathVariable(value = "id") int id,
                                                   @PathVariable(value = "keyword", required = false) String keyword) {
        String sql = "select t_article.t_id,t_type_id,t_type_name,t_title,t_author,t_time,t_cover,t_scan,t_sort,t_top,t_scan_origin,t_search from t_article left join t_type on t_type.t_id=t_article.t_type_id";
        sql += " where 1=1 ";
        if (keyword != null && !keyword.equals("")) {
            sql += " and t_title like '%" + keyword + "%'";
        }
        if (id != -1) {
            sql += " and t_type_id=" + id;
        }
        sql += " order by t_top desc,t_sort desc,t_scan desc,t_id desc ";
        sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
        return this.jdbcTemplate.queryForList(sql);
    }

    @RequestMapping("/queryarticle/{id}")
    @ResponseBody
    public Map<String, Object> queryarticle(@PathVariable("id") int id) {
        String sql = "select * from t_article where t_id=?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{id});
        return list.get(0);
    }

    @RequestMapping("/addarticle")
    @ResponseBody
    public boolean addarticle(@RequestBody Article article) {
        String sql = "insert into t_article(t_type_id, t_title, t_author, t_time, t_cover, t_content, t_url, t_mode, t_scan, t_sort, t_top, t_scan_origin, t_search) values (?,?,?,?,?,?,?,?,0,0,0,0,0)";
        int count = this.jdbcTemplate.update(sql, new Object[]{article.t_type_id, article.t_title, article.t_author, article.t_time, article.t_cover, article.t_content, article.t_url, (article.t_url == null || article.t_url.equals("")) ? 0 : 1});
        return count == 1;
    }

    @RequestMapping("/editarticle")
    @ResponseBody
    public boolean editarticle(@RequestBody Article article) {
        String sql = "update t_article set t_type_id=?,t_title=?,t_author=?,t_time=?,t_cover=?,t_content=?,t_url=?,t_mode=? where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{article.t_type_id, article.t_title, article.t_author, article.t_time, article.t_cover, article.t_content, article.t_url, (article.t_url == null || article.t_url.equals("")) ? 0 : 1, article.t_id});
        return count == 1;
    }

    @RequestMapping("/deletearticle/{id}")
    @ResponseBody
    public boolean deletearticle(@PathVariable("id") int id) {
        String sql = "delete from t_article where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{id});
        return count == 1;
    }

    @RequestMapping("/setarticlescan/{id}/{scan}")
    @ResponseBody
    public boolean setarticlescan(@PathVariable("id") int id, @PathVariable("scan") int scan) {
        String sql = "update t_article set t_scan=? where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{scan, id});
        return count == 1;
    }

    @RequestMapping("/setarticlesort/{id}/{sort}")
    @ResponseBody
    public boolean setarticlesort(@PathVariable("id") int id, @PathVariable("sort") int sort) {
        String sql = "update t_article set t_sort=? where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{sort, id});
        return count == 1;
    }

    @RequestMapping("/setarticletop/{id}/{top}")
    @ResponseBody
    public boolean setarticletop(@PathVariable("id") int id, @PathVariable("top") int top) {
        String sql = "update t_article set t_top=? where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{top, id});
        return count == 1;
    }

    //</editor-fold>

    //<editor-fold desc="问题">
    @RequestMapping(value = {"/queryquestionscount/{state}",
            "/queryquestionscount/{state}/{keyword}"})
    @ResponseBody
    public int queryquestionscount(@PathVariable(value = "state") int state,
                                   @PathVariable(value = "keyword", required = false) String keyword) {
        String sql = "select count(*) from t_question";
        sql += " where 1=1 ";
        if (keyword != null && !keyword.equals("")) {
            sql += " and t_title like '%" + keyword + "%'";
        }
        if (state != -1) {
            sql += " and t_solved=" + state;
        }
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @RequestMapping("/queryallquestions/{id}")
    @ResponseBody
    public List<Map<String, Object>> queryallquestions(@PathVariable("id") int id) {
        String sql = "select t_title,t_time from t_question where t_user_id=?";
        sql += " order by t_time desc ";
        return this.jdbcTemplate.queryForList(sql, new Object[]{id});
    }

    @RequestMapping(value = {"/queryquestions/{pageIndex}/{pageSize}/{state}",
            "/queryquestions/{pageIndex}/{pageSize}/{state}/{keyword}"})
    @ResponseBody
    public List<Map<String, Object>> queryquestions(@PathVariable("pageIndex") int pageIndex,
                                                    @PathVariable("pageSize") int pageSize,
                                                    @PathVariable(value = "state") int state,
                                                    @PathVariable(value = "keyword", required = false) String keyword) {
        String sql = "select t_question.*,t_user.w_nickname from t_question left join t_user on t_user.t_id=t_question.t_user_id";
        sql += " where 1=1 ";
        if (keyword != null && !keyword.equals("")) {
            sql += " and t_title like '%" + keyword + "%'";
        }
        if (state != -1) {
            sql += " and t_solved=" + state;
        }
        sql += " order by t_top desc,t_sort desc,t_scan desc,t_id desc ";
        sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        for (Map<String, Object> m : list) {
            sql = "select t_tag.* from t_question_tag left join t_tag on t_tag.t_id=t_tag_id where t_question_id=?";
            List<Map<String, Object>> l = this.jdbcTemplate.queryForList(sql, new Object[]{m.get("t_id")});
            m.put("tags", l);
        }
        return list;
    }

    @RequestMapping("/addquestion")
    @ResponseBody
    public boolean addquestion(@RequestBody final Question question) {
        final String sql = "insert into t_question( t_title, t_user_id, t_time, t_scan, t_sort, t_top, t_solved, t_scan_origin) values (?,0,?,0,0,0,0,0)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, question.t_title);
                ps.setLong(2, new Date().getTime());
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

    @RequestMapping("/editquestion")
    @ResponseBody
    public boolean editquestion(@RequestBody Question question) {
        String sql = "update t_question set t_title=? where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{question.t_title, question.t_id});
        sql = "delete from t_question_tag where t_question_id=?";
        this.jdbcTemplate.update(sql, new Object[]{question.t_id});
        if (question.tags.size() != 0) {
            List<Object[]> objs = new ArrayList<>();
            for (int i : question.tags) {
                objs.add(new Object[]{question.t_id, i});
            }
            sql = "insert into t_question_tag (t_question_id,t_tag_id) values(?,?)";
            int[] counts = this.jdbcTemplate.batchUpdate(sql, objs);
            return counts.length == question.tags.size();
        }
        return count == 1;
    }

    @RequestMapping("/deletequestion/{id}")
    @ResponseBody
    public boolean deletequestion(@PathVariable("id") int id) {
        String sql = "delete from t_question where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{id});
        return count == 1;
    }

    @RequestMapping("/setquestionscan/{id}/{scan}")
    @ResponseBody
    public boolean setquestionscan(@PathVariable("id") int id, @PathVariable("scan") int scan) {
        String sql = "update t_question set t_scan=? where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{scan, id});
        return count == 1;
    }

    @RequestMapping("/setquestionsort/{id}/{sort}")
    @ResponseBody
    public boolean setquestionsort(@PathVariable("id") int id, @PathVariable("sort") int sort) {
        String sql = "update t_question set t_sort=? where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{sort, id});
        return count == 1;
    }

    @RequestMapping("/setquestiontop/{id}/{top}")
    @ResponseBody
    public boolean setquestiontop(@PathVariable("id") int id, @PathVariable("top") int top) {
        String sql = "update t_question set t_top=? where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{top, id});
        return count == 1;
    }

    //</editor-fold>

    //<editor-fold desc="回答">
    @RequestMapping("/queryallanswers/{id}")
    @ResponseBody
    public List<Map<String, Object>> queryallanswers(@PathVariable("id") int id) {
        String sql = "select t_answer.* from t_answer where t_question_id=? order by t_time asc";
        return this.jdbcTemplate.queryForList(sql, new Object[]{id});
    }

    @RequestMapping("/addanswer")
    @ResponseBody
    public boolean addanswer(@RequestBody Answer answer) {
        String sql = "insert into t_answer(t_question_id, t_user_id, t_isimg, t_time, t_content) values (?,0,0,?,?)";
        int count = this.jdbcTemplate.update(sql, new Object[]{answer.t_question_id, new Date().getTime(), answer.t_content});
        if (count == 1) {
            sql = "update t_question set t_solved=1 where t_id=" + answer.t_question_id;
            count = this.jdbcTemplate.update(sql);
            return count == 1;
        }
        return false;
    }

    @RequestMapping("/deleteanswer/{id}")
    @ResponseBody
    public boolean deleteanswer(@PathVariable("id") int id) {
        String sql = "delete from t_answer where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{id});
        return count == 1;
    }

    //</editor-fold>

    //<editor-fold desc="用户">
    @RequestMapping("/queryuserscount")
    @ResponseBody
    public int queryuserscount() {
        String sql = "select count(*) from t_user order by t_id desc";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @RequestMapping("/queryuser/{id}")
    @ResponseBody
    public Map<String, Object> queryuser(@PathVariable("id") int id) {
        String sql = "select * from t_user where t_id=?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{id});
        return list.get(0);
    }

    @RequestMapping("/queryusers/{pageIndex}/{pageSize}")
    @ResponseBody
    public List<Map<String, Object>> queryusers(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
        String sql = "select * from t_user";
        sql += " order by t_id ";
        sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
        return this.jdbcTemplate.queryForList(sql);
    }

    @RequestMapping("/deleteuser/{id}")
    @ResponseBody
    public boolean deleteuser(@PathVariable("id") int id) {
        String sql = "delete from t_user where t_id=?";
        int count = this.jdbcTemplate.update(sql, new Object[]{id});
        return count == 1;
    }

    //</editor-fold>

    //<editor-fold desc="个人中心">
    @RequestMapping("/changepassword/{oldpassword}/{newpassword}")
    @ResponseBody
    public boolean changepassword(@PathVariable("oldpassword") String oldpassword, @PathVariable("newpassword") String newpassword) {
        String sql = "select * from t_setting where t_key='password'";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        if (list.get(0).get("t_value").equals(oldpassword)) {
            sql = "update t_setting set t_value=? where t_key='password'";
            int count = this.jdbcTemplate.update(sql, new Object[]{newpassword});
            return count == 1;
        }
        return false;
    }

    //</editor-fold>

    //<editor-fold desc="登陆">
    @RequestMapping("/login/{password}")
    @ResponseBody
    public boolean login(@PathVariable("password") String password) {
        String sql = "select * from t_setting where t_key='password'";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        return list.get(0).get("t_value").equals(password);
    }

    //</editor-fold>

    //<editor-fold desc="上传文件处理">
    @RequestMapping("/articleCoverUpload")
    @ResponseBody
    public Map<String, String> articleUpload(@RequestParam("img") MultipartFile file) throws IOException {
        Map<String, String> result = new HashMap<>();
        String fileName = UUID.randomUUID() + FileUtil.getFileExtensionName(file.getOriginalFilename());
        String savePath = this.global.articleUploadPath + fileName;
        File localFile = new File(savePath);
        file.transferTo(localFile);
        result.put("path", fileName);
        return result;
    }

    @RequestMapping("/articleContentUpload")
    @ResponseBody
    public Map<String, Object> articleContentUpload(@RequestParam("upload") MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<>();
        String fileName = UUID.randomUUID() + FileUtil.getFileExtensionName(file.getOriginalFilename());
        String savePath = this.global.articleUploadPath + fileName;
        File localFile = new File(savePath);
        file.transferTo(localFile);
        result.put("uploaded", 1);
        result.put("fileName", file.getOriginalFilename());
        result.put("url", this.global.fileServer + "article/" + fileName);
        return result;
    }

    //</editor-fold>

    //<editor-fold desc="统计">
    @RequestMapping("/question_count_by_day/{begin}/{end}")
    @ResponseBody
    public List<Map<String, Object>> question_count_by_day(@PathVariable("begin") long begin, @PathVariable("end") long end) {
        String sql = "select t_time,t_solved from t_question where t_time>=? and t_time<?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{begin, end});
        return list;
    }

    @RequestMapping("/question_count_by_tag/{begin}/{end}")
    @ResponseBody
    public List<Map<String, Object>> question_count_by_tag(@PathVariable("begin") long begin, @PathVariable("end") long end) {
        String sql = "select t_tag_name,count(*) count from t_question_tag left join t_tag on t_tag_id=t_tag.t_id left join t_question on t_question_id=t_question.t_id where t_time>? and t_time<? group by t_tag_id";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{begin, end});
        return list;
    }

    @RequestMapping("/article_question_sort")
    @ResponseBody
    public Map<String, Object> article_question_sort() {
        Map<String, Object> map = new HashMap<>();
        String sql = "select t_title,t_scan_origin from t_article order by t_scan_origin desc limit 0,10";
        List<Map<String, Object>> article_scan = this.jdbcTemplate.queryForList(sql);
        sql = "select t_title,t_search from t_article order by t_search desc limit 0,10";
        List<Map<String, Object>> article_search = this.jdbcTemplate.queryForList(sql);
        sql = "select t_title,t_scan_origin from t_question order by t_scan_origin desc limit 0,10";
        List<Map<String, Object>> question_scan = this.jdbcTemplate.queryForList(sql);
        map.put("article_scan", article_scan);
        map.put("article_search", article_search);
        map.put("question_scan", question_scan);
        return map;
    }

    @RequestMapping("/login_scan_by_day/{begin}/{end}")
    @ResponseBody
    public List<Map<String, Object>> login_scan_by_day(@PathVariable("begin") long begin, @PathVariable("end") long end) {
        String sql = "select t_time,t_scan from t_scan where t_time>=? and t_time<?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, new Object[]{begin, end});
        return list;
    }
    //</editor-fold>
}
