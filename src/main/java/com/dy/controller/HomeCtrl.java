package com.dy.controller;

import com.dy.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/home")
public class HomeCtrl {
    @Autowired
    @Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/ls")
    @ResponseBody
    public List<Map<String, Object>> ls() {

        //List<Answer> list = new ArrayList<>();
        //list.add(new Answer(1, 1, true, 1, ""));
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList("select * from t_type");
        return list;
    }

    @RequestMapping(value = "/imageUpload")
    public @ResponseBody
    String imageUpload(HttpServletRequest request) throws IOException {
        String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
        String urll = request.getQueryString();
        boolean uploadFlag = false;
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
                .getServletContext());
        // 图片名称
        String fileName = null;
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            //    定义存库相对路径名称
            String relativeName = "";
            //    功能文件夹,注意：代码只能创建一级文件夹，需先在服务器创建文件夹目录
            //String tmpPath = AdminConstant.ADMIN_FILE_PATH;
            //    时间文件夹
            //String folderYMD = new DateTime().toString("yyyyMMdd") + "/";

            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    int i = 0;
//                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
//                    if (myFileName.trim() != "") {
//                        // 获得图片的原始名称
//                        String originalFilename = file.getOriginalFilename();
//                        // 获得图片后缀名称,如果后缀不为图片格式，则不上传
//                        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
//                        if (!fileTypes.contains(suffix)) {
//                            continue;
//                        }
//                        InputStream is = file.getInputStream();
//                        byte[] picData = IOUtils.toByteArray(is);
//                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(picData);
//
//                        if (picData != null && picData.length > 0) {
//                            try {
//                                //    真实文件名,DigestUtils.md5Hex方法可以根据文件byte[]生成唯一哈希!!!(节省服务器内存)
//                                String realName = DigestUtils.md5Hex(picData) + suffix;
//                                //    存库相对文件路径
//                                relativeName = tmpPath + folderYMD + realName;
//                                //    文件在图片服务器的路径前缀
//                                String directory = UPLOAD_PATH + tmpPath + folderYMD;
//                                String imageContextPath = FILE_PATH + UPLOAD_PATH + relativeName;
//                                //  ftp上传文件
//                                uploadFlag = ftpUtil.uploadInputStreamFile(byteArrayInputStream, realName, directory);
//                                if (uploadFlag) {
//                                    return "{\"uploaded\":1,\"fileName\":\""+relativeName+"\",\"url\":\"" + imageContextPath + "\"}";
//                                } else {
//                                    return "{\"uploaded\":0,\"error\":{\"message\":\"upload file is not success!\"}}";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
                }
            }
        }
        return "{\"uploaded\":1,\"fileName\":\"" + "qq.png" + "\",\"url\":\"" + "http://localhost:8080/static/img/qq.png" + "\"}";
        //return "{\"uploaded\":0,\"error\":{\"message\":\"upload file is not success!\"}}";

    }


    //        List<Type> list = this.jdbcTemplate.query(sql, new RowMapper<Type>() {
//            @Override
//            public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Type type = new Type(
//                        rs.getInt("t_id"),
//                        rs.getString("t_type_name")
//                );
//                return type;
//            }
//        });
}
