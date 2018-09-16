/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : dy

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 16/09/2018 21:42:51
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
  `t_isimg` tinyint(1) NULL DEFAULT NULL,
  `t_time` bigint(20) NULL DEFAULT NULL,
  `t_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_answer
-- ----------------------------
INSERT INTO `t_answer` VALUES (1, 1, 0, 0, 1535775869331, '百年大计，教育为本。教育大计，教师为本。”党的十八大以来，以习近平同志为核心的党中央高度重视教师队伍建设问题，在不同场合多次强调教师工作的重要意义。今天，新时代学习工作室带您回顾习近平总书记对于教师的期望和寄语');
INSERT INTO `t_answer` VALUES (2, 1, 10, 0, 1535775869332, '发展教育事业，广大教师责任重大、使命光荣。希望你们牢记使命、不忘初衷，扎根西部、服务学生，努力做教育改革的奋进者、教育扶贫的先行者、学生成长的引导者，为贫困地区教育事业发展、为祖国下一代健康成长继续作出自己的贡献。');
INSERT INTO `t_answer` VALUES (3, 1, 0, 0, 1535775869333, '我是提问者，嘿嘿');
INSERT INTO `t_answer` VALUES (4, 1, 10, 0, 1535775869334, '我是医生bbb我是医生bbb我是医生bbb我是医生bbb我是医生bbb');
INSERT INTO `t_answer` VALUES (6, 1, 0, 0, 1536058378220, 'hi');
INSERT INTO `t_answer` VALUES (9, 1, 10, 1, 1536059087650, '微信图片_20180106104338.png');
INSERT INTO `t_answer` VALUES (10, 1, 0, 0, 1536069557411, '2');
INSERT INTO `t_answer` VALUES (13, 1, 10, 1, 1536070152392, '微信图片_20180106104338.png');
INSERT INTO `t_answer` VALUES (14, 1, 0, 0, 1536070161576, 'ook');
INSERT INTO `t_answer` VALUES (15, 1, 0, 0, 1536070258378, 'ok');
INSERT INTO `t_answer` VALUES (16, 1, 0, 0, 1536070373315, 'hahah');
INSERT INTO `t_answer` VALUES (17, 1, 0, 0, 1536070378096, 'hahah3');
INSERT INTO `t_answer` VALUES (18, 1, 0, 0, 1536070386225, 'hahah3');
INSERT INTO `t_answer` VALUES (19, 1, 0, 0, 1536071509857, '接上');
INSERT INTO `t_answer` VALUES (20, 1, 0, 0, 1536487974778, '55');
INSERT INTO `t_answer` VALUES (21, 1, 0, 0, 1536836162109, '喝点酒');
INSERT INTO `t_answer` VALUES (22, 1, 0, 0, 1536839942772, 'l');
INSERT INTO `t_answer` VALUES (23, 1, 10, 0, 1537069294612, '3');
INSERT INTO `t_answer` VALUES (25, 1, 10, 0, 1537069414542, '333');
INSERT INTO `t_answer` VALUES (27, 1, 10, 0, 1537071242280, '123');
INSERT INTO `t_answer` VALUES (28, 1, 10, 0, 1537071245452, '1233');
INSERT INTO `t_answer` VALUES (29, 1, 10, 0, 1537071257037, '12333');
INSERT INTO `t_answer` VALUES (30, 1, 10, 0, 1537071259115, '12333');
INSERT INTO `t_answer` VALUES (31, 1, 10, 0, 1537071277587, '12333');
INSERT INTO `t_answer` VALUES (32, 1, 10, 0, 1537071321353, '12333');
INSERT INTO `t_answer` VALUES (33, 1, 10, 0, 1537071331036, '1233311');
INSERT INTO `t_answer` VALUES (34, 1, 10, 0, 1537071351054, '3213');
INSERT INTO `t_answer` VALUES (35, 1, 10, 0, 1537071353156, '3213123123');
INSERT INTO `t_answer` VALUES (36, 1, 10, 0, 1537071361010, '12');
INSERT INTO `t_answer` VALUES (37, 1, 10, 0, 1537071375644, '12');
INSERT INTO `t_answer` VALUES (38, 1, 10, 0, 1537072025229, '3123');
INSERT INTO `t_answer` VALUES (39, 1, 10, 0, 1537072279101, '03');
INSERT INTO `t_answer` VALUES (40, 1, 10, 0, 1537079247253, '我要传图了');
INSERT INTO `t_answer` VALUES (41, 1, 10, 0, 1537079375386, '我要传图了2');
INSERT INTO `t_answer` VALUES (42, 1, 10, 1, 1537079381612, '424326b8-b9e4-4288-a383-2f330970b6b6.jpg');
INSERT INTO `t_answer` VALUES (43, 1, 10, 1, 1537079388810, '06188665-9055-4159-a889-80aa3e1a570f.jpg');
INSERT INTO `t_answer` VALUES (44, 1, 10, 1, 1537079501083, 'e9ea25ce-746b-4bdf-9e50-dafd13c75425.jpg');
INSERT INTO `t_answer` VALUES (45, 1, 10, 0, 1537080218977, '5');
INSERT INTO `t_answer` VALUES (46, 1, 0, 0, 1537083409505, '啥都别吃');
INSERT INTO `t_answer` VALUES (49, 5, 10, 1, 1537095287908, 'ac3bfbae-fbb6-4081-bcd5-ddb9bf2d964a.jpg');
INSERT INTO `t_answer` VALUES (50, 312, 10, 1, 1537104748618, '454c61c5-66e5-42c2-80d1-ed13241b1497.jpg');

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
  `t_scan_origin` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES (1, 1, '流行感冒吃什么药', '医学图解', 1535883229563, 'af7385f4-d706-40a6-b10c-8603ce5aa4db.jpg', '<p>123</p>\n', 1, 0, 0, 0);
INSERT INTO `t_article` VALUES (2, 1, '腰间盘突出了', '33', 1536067552321, '31ecd0ec-34f6-4772-841d-e4c86c1dd4b9.jpg', '<p style=\"text-align:center\"><img alt=\"\" src=\"http://localhost:8000/article/65eee5d2-5d07-40eb-b5a6-0ada25badf8e.jpg\" style=\"height:146px; width:111px\" /></p>\n\n<p style=\"text-align:justify\">&nbsp; &nbsp; &nbsp; &ldquo;<u>加强交流，互鉴共进，齐心协力，做中俄友好事业接班人，让中俄世代友好的伟大事业薪火相传、生生不息。</u>&rdquo;9月12日，习近平主席同俄罗斯总统普京一起访问&ldquo;海洋&rdquo;全俄儿童中心，对两国青少年提出殷切期望，寓意深长。</p>\n\n<p style=\"text-align:justify\">&nbsp; &nbsp; &nbsp; &nbsp; 这是一段感动中俄两国人民的故事。<span style=\"color:#e74c3c\">2008</span>年中国四川汶川特大地震发生后，受俄罗斯政府邀请，来自中国灾区的900多名儿童先后来到&ldquo;海洋&rdquo;全俄儿童中心疗养，收获了终生难忘的&ldquo;<span style=\"background-color:#f1c40f\">海洋</span>&rdquo;记忆。如同它的名字一样，&ldquo;海洋&rdquo;全俄儿童中心把最深沉的爱带给了中国灾区儿童，也为中俄世代友好开辟了更广阔的空间。&ldquo;<strong>接待中国灾区儿童赴俄疗养，早已超越帮助孩子身心康复这一意义，更重要的是，在中国孩子们心中播下的中俄友谊种子，如今已经生根、发芽、开花、结果</strong>。&rdquo;曾任俄罗斯教育和科学部副部长的先秋林感叹。</p>\n', 22, 1, 1, 7);
INSERT INTO `t_article` VALUES (3, 2, '腰间盘突出感冒了', '测试狗', 1538317690774, 'c6d26e0d-422a-490f-9ed3-78e4e385895a.jpg', '<p>正确时返回的JSON数据包如下：</p>\n\n<pre>\n<code>{ &quot;access_token&quot;:&quot;ACCESS_TOKEN&quot;,\n&quot;expires_in&quot;:7200,\n&quot;refresh_token&quot;:&quot;REFRESH_TOKEN&quot;,\n&quot;openid&quot;:&quot;OPENID&quot;,\n&quot;scope&quot;:&quot;SCOPE&quot; }\n</code></pre>\n\n<table>\n	<thead>\n		<tr>\n			<th>参数</th>\n			<th>描述</th>\n		</tr>\n	</thead>\n	<tbody>\n		<tr>\n			<td>access_token</td>\n			<td>网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同</td>\n		</tr>\n		<tr>\n			<td>expires_in</td>\n			<td>access_token接口调用凭证超时时间，单位（秒）</td>\n		</tr>\n		<tr>\n			<td>refresh_token</td>\n			<td>用户刷新access_token</td>\n		</tr>\n		<tr>\n			<td>openid</td>\n			<td>用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID</td>\n		</tr>\n		<tr>\n			<td>scope</td>\n			<td>用户授权的作用域，使用逗号（,）分隔</td>\n		</tr>\n	</tbody>\n</table>\n\n<p>错误时微信会返回JSON数据包如下（示例为Code无效错误）:</p>\n\n<pre>\n<code>{&quot;errcode&quot;:40029,&quot;errmsg&quot;:&quot;invalid code&quot;}\n</code></pre>\n\n<p>&nbsp;</p>\n\n<p><strong>第三步：刷新access_token（如果需要）</strong></p>\n\n<p>由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。</p>\n\n<p><strong>请求方法</strong></p>\n\n<pre>\n<code>获取第二步的refresh_token后，请求以下链接获取access_token：\nhttps://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&amp;grant_type=refresh_token&amp;refresh_token=REFRESH_TOKEN\n</code></pre>\n\n<table>\n	<thead>\n		<tr>\n			<th>参数</th>\n			<th>是否必须</th>\n			<th>说明</th>\n		</tr>\n	</thead>\n	<tbody>\n		<tr>\n			<td>appid</td>\n			<td>是</td>\n			<td>公众号的唯一标识</td>\n		</tr>\n		<tr>\n			<td>grant_type</td>\n			<td>是</td>\n			<td>填写为refresh_token</td>\n		</tr>\n		<tr>\n			<td>refresh_token</td>\n			<td>是</td>\n			<td>填写通过access_token获取到的refresh_token参数</td>\n		</tr>\n	</tbody>\n</table>\n\n<p>返回说明</p>\n\n<p>正确时返回的JSON数据包如下：</p>\n\n<pre>\n<code>{ &quot;access_token&quot;:&quot;ACCESS_TOKEN&quot;,\n&quot;expires_in&quot;:7200,\n&quot;refresh_token&quot;:&quot;REFRESH_TOKEN&quot;,\n&quot;openid&quot;:&quot;OPENID&quot;,\n&quot;scope&quot;:&quot;SCOPE&quot; }\n</code></pre>\n\n<table>\n	<thead>\n		<tr>\n			<th>参数</th>\n			<th>描述</th>\n		</tr>\n	</thead>\n	<tbody>\n		<tr>\n			<td>access_token</td>\n			<td>网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同</td>\n		</tr>\n		<tr>\n			<td>expires_in</td>\n			<td>access_token接口调用凭证超时时间，单位（秒）</td>\n		</tr>\n		<tr>\n			<td>refresh_token</td>\n			<td>用户刷新access_token</td>\n		</tr>\n		<tr>\n			<td>openid</td>\n			<td>用户唯一标识</td>\n		</tr>\n		<tr>\n			<td>scope</td>\n			<td>用户授权的作用域，使用逗号（,）分隔</td>\n		</tr>\n	</tbody>\n</table>\n\n<p>错误时微信会返回JSON数据包如下（示例为code无效错误）:</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><img alt=\"\" src=\"http://localhost:8000/article/29abb70a-1a07-41e1-843b-01172696da72.jpg\" style=\"height:750px; width:530px\" /></p>\n', 2, 0, 0, 2);
INSERT INTO `t_article` VALUES (4, 2, '感冒流鼻涕了', '阿', 1536489800992, 'c2a2bdb1-5337-405b-a30b-f8367e8bd033.jpg', '<p>恶趣味</p>\n\n<p><img alt=\"\" src=\"http://localhost:8000/article/7ee1eac8-b0ba-4c12-8ca1-76d92fb3ba71.jpeg\" style=\"height:1280px; width:720px\" /></p>\n', 3, 0, 0, 0);
INSERT INTO `t_article` VALUES (6, 4, '这个没有关键字', '1', 1537079911848, '86aff319-aa20-4cbf-a046-389928454f3a.jpg', '<p>132全球</p>\n\n<p><img alt=\"\" src=\"http://localhost:8000/article/887fd401-a6c6-4146-8798-fe5e24accbb3.jpg\" style=\"height:1134px; width:720px\" /></p>\n', 0, 0, 0, 0);

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_user_id` int(11) NULL DEFAULT NULL,
  `t_time` bigint(20) NULL DEFAULT NULL,
  `t_scan` int(11) NULL DEFAULT NULL,
  `t_sort` int(11) NULL DEFAULT NULL,
  `t_top` int(11) NULL DEFAULT NULL,
  `t_solved` tinyint(1) NULL DEFAULT NULL,
  `t_scan_origin` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 324 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_question
-- ----------------------------
INSERT INTO `t_question` VALUES (1, '大姨夫来了吃什么？', 10, 1535883229563, 218, 10, 1, 0, 190);
INSERT INTO `t_question` VALUES (2, '小姨夫来了肿么办？', 10, 1535989001222, 5, 0, 1, 1, 2);
INSERT INTO `t_question` VALUES (3, '老姨夫来了肿么办？', 10, 1535989001223, 3, 1, 0, 1, 0);
INSERT INTO `t_question` VALUES (4, '老姨夫不来肿么办？', 10, 1535989001224, 0, 0, 1, 1, 0);
INSERT INTO `t_question` VALUES (5, '骨折了！！！', 10, 1536071454242, 2, 3, 1, 0, 2);
INSERT INTO `t_question` VALUES (6, '感冒发烧流鼻涕怎么办', 0, 1536836138967, 0, 0, 1, 1, 0);
INSERT INTO `t_question` VALUES (7, '腰间盘突出怎么办1', 0, 1536841234551, 0, 0, 0, 1, 0);
INSERT INTO `t_question` VALUES (8, '腰间盘突出怎么办2', 0, 1536841501555, 0, 0, 0, 1, 0);
INSERT INTO `t_question` VALUES (9, '腰间盘突出怎么办3', 0, 1536841639847, 0, 0, 0, 1, 0);
INSERT INTO `t_question` VALUES (10, '腰间盘突出怎么办4', 0, 1536841709382, 0, 0, 0, 1, 0);
INSERT INTO `t_question` VALUES (11, '感冒了哟', 0, 1536843402889, 0, 0, 0, 1, 0);
INSERT INTO `t_question` VALUES (12, '腰间盘突出发烧了1', 0, 1536844531246, 0, 0, 0, 1, 0);
INSERT INTO `t_question` VALUES (13, '腰间盘突出发烧了2', 0, 1536844787538, 3, 0, 1, 1, 3);
INSERT INTO `t_question` VALUES (14, '这句没有关键字', 0, 1536844859367, 0, 0, 0, 1, 0);
INSERT INTO `t_question` VALUES (312, '感冒腰间盘突出000', 10, 1537083964461, 1, 0, 0, 0, 1);
INSERT INTO `t_question` VALUES (313, '腰间盘突出', 10, 1537084431624, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (314, '123123', 10, 1537094549056, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (315, '腰间盘突出，提问完毕', 10, 1537102951915, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (316, '123腰间盘突出，咋办，在线等康师傅解答', 10, 1537103978104, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (317, '哈哈哈', 10, 1537104037245, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (318, '哈哈哈', 10, 1537104076035, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (319, '啊实打实', 10, 1537104168330, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (320, '腰间盘突出，感冒还发烧', 10, 1537104433162, 1, 0, 0, 0, 1);
INSERT INTO `t_question` VALUES (321, '感冒了', 10, 1537104618400, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (322, '老姨夫不来', 10, 1537105080866, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (323, '感冒了', 10, 1537105097165, 0, 0, 0, 0, 0);

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
-- Records of t_question_tag
-- ----------------------------
INSERT INTO `t_question_tag` VALUES (6, 1);
INSERT INTO `t_question_tag` VALUES (6, 2);
INSERT INTO `t_question_tag` VALUES (10, 6);
INSERT INTO `t_question_tag` VALUES (11, 1);
INSERT INTO `t_question_tag` VALUES (12, 2);
INSERT INTO `t_question_tag` VALUES (12, 6);
INSERT INTO `t_question_tag` VALUES (13, 2);
INSERT INTO `t_question_tag` VALUES (13, 6);
INSERT INTO `t_question_tag` VALUES (312, 1);
INSERT INTO `t_question_tag` VALUES (312, 6);
INSERT INTO `t_question_tag` VALUES (313, 6);
INSERT INTO `t_question_tag` VALUES (315, 6);
INSERT INTO `t_question_tag` VALUES (316, 6);
INSERT INTO `t_question_tag` VALUES (320, 1);
INSERT INTO `t_question_tag` VALUES (320, 2);
INSERT INTO `t_question_tag` VALUES (320, 6);
INSERT INTO `t_question_tag` VALUES (321, 1);
INSERT INTO `t_question_tag` VALUES (323, 1);

-- ----------------------------
-- Table structure for t_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_setting`;
CREATE TABLE `t_setting`  (
  `t_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `t_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`t_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_setting
-- ----------------------------
INSERT INTO `t_setting` VALUES ('password', '1');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (1, '感冒');
INSERT INTO `t_tag` VALUES (2, '发烧');
INSERT INTO `t_tag` VALUES (3, '腹泻');
INSERT INTO `t_tag` VALUES (4, '便秘');
INSERT INTO `t_tag` VALUES (5, '痛风');
INSERT INTO `t_tag` VALUES (6, '腰间盘突出');
INSERT INTO `t_tag` VALUES (7, '颈椎病');

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
INSERT INTO `t_type` VALUES (1, '妇产科');
INSERT INTO `t_type` VALUES (2, '儿科');
INSERT INTO `t_type` VALUES (3, '骨外科');
INSERT INTO `t_type` VALUES (4, '消化科');

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
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (5, '333', 'aaa', '0', '黑龙江', '哈尔滨', '中国', 'https://os.alipayobjects.com/rmsportal/UXamdIxYSkXfoVo.jpg', '张三', '12312312312', '1@1.com', 1535883229563);
INSERT INTO `t_user` VALUES (6, '222', '啊啊啊', '1', '黑龙江', '哈尔滨', '中国', 'https://os.alipayobjects.com/rmsportal/UXamdIxYSkXfoVo.jpg', '李四', '12312312312', '2@2.com', 1535883223333);
INSERT INTO `t_user` VALUES (10, 'ocEW80XQ0GdgjCmMOFuAsBrxuHcE', '陈玉锋', '1', '黑龙江', '哈尔滨', '中国', 'http://thirdwx.qlogo.cn/mmopen/Qx3BiargQCpSKRibEfXcrEp4qBd6jfOkt8Ys5lDXfuQiczy3XAvMiaQlUzwSmFoBsQBmpW06Rp595ibsNHciaYliaF8tOmmmlI0057ic/132', NULL, NULL, NULL, 1536484134632);

SET FOREIGN_KEY_CHECKS = 1;
