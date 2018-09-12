/*
 Navicat Premium Data Transfer

 Source Server         : local-mysql
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3306
 Source Schema         : dy

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 12/09/2018 12:34:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_answer`;
CREATE TABLE `t_answer`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_question_id` int(11) NULL DEFAULT NULL,
  `t_user_id` int(11) NULL DEFAULT NULL,
  `t_time` bigint(20) NULL DEFAULT NULL,
  `t_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_answer
-- ----------------------------
INSERT INTO `t_answer` VALUES (1, 1, 1, 1535775869331, '百年大计，教育为本。教育大计，教师为本。”党的十八大以来，以习近平同志为核心的党中央高度重视教师队伍建设问题，在不同场合多次强调教师工作的重要意义。今天，新时代学习工作室带您回顾习近平总书记对于教师的期望和寄语');
INSERT INTO `t_answer` VALUES (2, 1, NULL, 1535775869332, '发展教育事业，广大教师责任重大、使命光荣。希望你们牢记使命、不忘初衷，扎根西部、服务学生，努力做教育改革的奋进者、教育扶贫的先行者、学生成长的引导者，为贫困地区教育事业发展、为祖国下一代健康成长继续作出自己的贡献。');
INSERT INTO `t_answer` VALUES (3, 1, 1, 1535775869333, '我是提问者，嘿嘿');
INSERT INTO `t_answer` VALUES (4, 1, NULL, 1535775869334, '我是医生bbb我是医生bbb我是医生bbb我是医生bbb我是医生bbb');
INSERT INTO `t_answer` VALUES (6, 3, 1, 1536058378220, 'hi');
INSERT INTO `t_answer` VALUES (7, 3, NULL, 1536058390140, 'hi1');
INSERT INTO `t_answer` VALUES (9, 10, NULL, 1536059087650, 'ww\n');
INSERT INTO `t_answer` VALUES (10, 5, NULL, 1536069557411, '2');
INSERT INTO `t_answer` VALUES (13, 3, NULL, 1536070152392, 'ook');
INSERT INTO `t_answer` VALUES (14, 3, NULL, 1536070161576, 'ook');
INSERT INTO `t_answer` VALUES (15, 3, NULL, 1536070258378, 'ok');
INSERT INTO `t_answer` VALUES (16, 4, NULL, 1536070373315, 'hahah');
INSERT INTO `t_answer` VALUES (17, 4, NULL, 1536070378096, 'hahah3');
INSERT INTO `t_answer` VALUES (18, 7, NULL, 1536070386225, 'hahah3');
INSERT INTO `t_answer` VALUES (19, 14, NULL, 1536071509857, '接上');
INSERT INTO `t_answer` VALUES (20, 3, NULL, 1536487974778, '55');

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_type_id` int(11) NULL DEFAULT NULL,
  `t_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_time` bigint(20) NULL DEFAULT NULL,
  `t_cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `t_scan` int(11) NULL DEFAULT NULL,
  `t_sort` int(11) NULL DEFAULT NULL,
  `t_top` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES (1, 14, '流行感冒吃什么药', '医学图解', 1535883229563, 'af7385f4-d706-40a6-b10c-8603ce5aa4db.jpg', '<p>123</p>\n', 0, 0, 1);
INSERT INTO `t_article` VALUES (2, 7, '333', '33', 1536067552321, '31ecd0ec-34f6-4772-841d-e4c86c1dd4b9.jpg', '<p style=\"text-align:center\"><img alt=\"\" src=\"http://localhost:8000/article/65eee5d2-5d07-40eb-b5a6-0ada25badf8e.jpg\" style=\"height:146px; width:111px\" /></p>\n\n<p>123123123</p>\n', 0, 1, 0);
INSERT INTO `t_article` VALUES (4, 13, '测试', '测试狗', 1538317690774, 'c6d26e0d-422a-490f-9ed3-78e4e385895a.jpg', '<p>正确时返回的JSON数据包如下：</p>\n\n<pre>\n<code>{ &quot;access_token&quot;:&quot;ACCESS_TOKEN&quot;,\n&quot;expires_in&quot;:7200,\n&quot;refresh_token&quot;:&quot;REFRESH_TOKEN&quot;,\n&quot;openid&quot;:&quot;OPENID&quot;,\n&quot;scope&quot;:&quot;SCOPE&quot; }\n</code></pre>\n\n<table>\n	<thead>\n		<tr>\n			<th>参数</th>\n			<th>描述</th>\n		</tr>\n	</thead>\n	<tbody>\n		<tr>\n			<td>access_token</td>\n			<td>网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同</td>\n		</tr>\n		<tr>\n			<td>expires_in</td>\n			<td>access_token接口调用凭证超时时间，单位（秒）</td>\n		</tr>\n		<tr>\n			<td>refresh_token</td>\n			<td>用户刷新access_token</td>\n		</tr>\n		<tr>\n			<td>openid</td>\n			<td>用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID</td>\n		</tr>\n		<tr>\n			<td>scope</td>\n			<td>用户授权的作用域，使用逗号（,）分隔</td>\n		</tr>\n	</tbody>\n</table>\n\n<p>错误时微信会返回JSON数据包如下（示例为Code无效错误）:</p>\n\n<pre>\n<code>{&quot;errcode&quot;:40029,&quot;errmsg&quot;:&quot;invalid code&quot;}\n</code></pre>\n\n<p>&nbsp;</p>\n\n<p><strong>第三步：刷新access_token（如果需要）</strong></p>\n\n<p>由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。</p>\n\n<p><strong>请求方法</strong></p>\n\n<pre>\n<code>获取第二步的refresh_token后，请求以下链接获取access_token：\nhttps://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&amp;grant_type=refresh_token&amp;refresh_token=REFRESH_TOKEN\n</code></pre>\n\n<table>\n	<thead>\n		<tr>\n			<th>参数</th>\n			<th>是否必须</th>\n			<th>说明</th>\n		</tr>\n	</thead>\n	<tbody>\n		<tr>\n			<td>appid</td>\n			<td>是</td>\n			<td>公众号的唯一标识</td>\n		</tr>\n		<tr>\n			<td>grant_type</td>\n			<td>是</td>\n			<td>填写为refresh_token</td>\n		</tr>\n		<tr>\n			<td>refresh_token</td>\n			<td>是</td>\n			<td>填写通过access_token获取到的refresh_token参数</td>\n		</tr>\n	</tbody>\n</table>\n\n<p>返回说明</p>\n\n<p>正确时返回的JSON数据包如下：</p>\n\n<pre>\n<code>{ &quot;access_token&quot;:&quot;ACCESS_TOKEN&quot;,\n&quot;expires_in&quot;:7200,\n&quot;refresh_token&quot;:&quot;REFRESH_TOKEN&quot;,\n&quot;openid&quot;:&quot;OPENID&quot;,\n&quot;scope&quot;:&quot;SCOPE&quot; }\n</code></pre>\n\n<table>\n	<thead>\n		<tr>\n			<th>参数</th>\n			<th>描述</th>\n		</tr>\n	</thead>\n	<tbody>\n		<tr>\n			<td>access_token</td>\n			<td>网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同</td>\n		</tr>\n		<tr>\n			<td>expires_in</td>\n			<td>access_token接口调用凭证超时时间，单位（秒）</td>\n		</tr>\n		<tr>\n			<td>refresh_token</td>\n			<td>用户刷新access_token</td>\n		</tr>\n		<tr>\n			<td>openid</td>\n			<td>用户唯一标识</td>\n		</tr>\n		<tr>\n			<td>scope</td>\n			<td>用户授权的作用域，使用逗号（,）分隔</td>\n		</tr>\n	</tbody>\n</table>\n\n<p>错误时微信会返回JSON数据包如下（示例为code无效错误）:</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><img alt=\"\" src=\"http://localhost:8000/article/29abb70a-1a07-41e1-843b-01172696da72.jpg\" style=\"height:750px; width:530px\" /></p>\n', 0, 0, 0);
INSERT INTO `t_article` VALUES (5, 14, '阿', '阿', 1536489800992, 'c2a2bdb1-5337-405b-a30b-f8367e8bd033.jpg', '<p>恶趣味</p>\n\n<p><img alt=\"\" src=\"http://localhost:8000/article/7ee1eac8-b0ba-4c12-8ca1-76d92fb3ba71.jpeg\" style=\"height:1280px; width:720px\" /></p>\n', 0, 0, 0);

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_type_id` int(11) NULL DEFAULT NULL,
  `t_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_user_id` int(11) NULL DEFAULT NULL,
  `t_time` bigint(20) NULL DEFAULT NULL,
  `t_scan` int(11) NULL DEFAULT NULL,
  `t_sort` int(11) NULL DEFAULT NULL,
  `t_top` int(11) NULL DEFAULT NULL,
  `t_solved` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_question
-- ----------------------------
INSERT INTO `t_question` VALUES (1, 13, '大姨夫来了吃什么？', 5, 1535883229563, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (3, 13, '小姨夫来了肿么办？小姨夫来了肿么办？小姨夫来了肿么办？小姨夫来了肿么办？小姨夫来了肿么办？', 5, 0, 3, 0, 1, 1);
INSERT INTO `t_question` VALUES (4, 14, '发', 5, 0, 0, 0, 0, 1);
INSERT INTO `t_question` VALUES (6, 14, '发4', NULL, 0, 0, 0, 0, 1);
INSERT INTO `t_question` VALUES (7, 14, '发5', NULL, 0, 0, 0, 0, 1);
INSERT INTO `t_question` VALUES (8, 14, '发7', NULL, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (10, 13, '测试', NULL, 1535989001223, 3, 1, 0, 0);
INSERT INTO `t_question` VALUES (11, 13, '测userid', NULL, 1536058556415, 0, 0, 1, 0);
INSERT INTO `t_question` VALUES (12, 7, 'ee', NULL, 1536060709695, 0, 0, 1, 0);
INSERT INTO `t_question` VALUES (13, 7, 'eee1', NULL, 1536060714307, 0, 0, 1, 0);
INSERT INTO `t_question` VALUES (14, 14, '骨折了！！！', NULL, 1536071454242, 0, 3, 1, 1);

-- ----------------------------
-- Table structure for t_question_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_question_tag`;
CREATE TABLE `t_question_tag`  (
  `t_question_id` int(11) NOT NULL,
  `t_tag_id` int(11) NOT NULL,
  PRIMARY KEY (`t_question_id`, `t_tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (7, '妇科');
INSERT INTO `t_type` VALUES (13, '儿科');
INSERT INTO `t_type` VALUES (14, '骨科');
INSERT INTO `t_type` VALUES (15, '消化科');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `w_openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `w_nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `w_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `w_province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `w_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `w_country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `w_headimgurl` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_time` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (5, '333', 'aaa', '0', '黑龙江', '哈尔滨', '中国', 'https://os.alipayobjects.com/rmsportal/UXamdIxYSkXfoVo.jpg', '张三', '12312312312', '1@1.com', 1535883229563);
INSERT INTO `t_user` VALUES (6, '222', '啊啊啊', '1', '黑龙江', '哈尔滨', '中国', 'https://os.alipayobjects.com/rmsportal/UXamdIxYSkXfoVo.jpg', '李四', '12312312312', '2@2.com', 1535883223333);
INSERT INTO `t_user` VALUES (10, 'ocEW80XQ0GdgjCmMOFuAsBrxuHcE', '陈玉锋', '1', '黑龙江', '哈尔滨', '中国', 'http://thirdwx.qlogo.cn/mmopen/Qx3BiargQCpSKRibEfXcrEp4qBd6jfOkt8Ys5lDXfuQiczy3XAvMiaQlUzwSmFoBsQBmpW06Rp595ibsNHciaYliaF8tOmmmlI0057ic/132', NULL, NULL, NULL, 1536484134632);

SET FOREIGN_KEY_CHECKS = 1;
