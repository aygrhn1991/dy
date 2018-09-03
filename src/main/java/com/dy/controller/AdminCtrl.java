package com.dy.controller;

import com.dy.model.*;
import com.dy.util.FileUtil;
import com.dy.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
        sql += " order by t_top desc,t_sort desc,t_scan desc ";
        sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        return list;
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

    //<editor-fold desc="上传文件处理">
    @RequestMapping("/articleUpload")
    @ResponseBody
    public Map<String, String> articleUpload(HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<>();
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            while (iterator.hasNext()) {
                MultipartFile multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if (multipartFile != null) {
                    String fileName = UUID.randomUUID() + FileUtil.getFileExtensionName(multipartFile.getOriginalFilename());
                    String savePath = this.global.articleUploadPath + fileName;
                    File localFile = new File(savePath);
                    multipartFile.transferTo(localFile);
                    result.put("path", fileName);
                }
            }
        }
        return result;
    }
    //</editor-fold>
}
