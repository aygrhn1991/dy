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

 Date: 03/09/2018 23:43:49
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
  `t_by_user` tinyint(1) NULL DEFAULT NULL,
  `t_time` bigint(20) NULL DEFAULT NULL,
  `t_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_answer
-- ----------------------------
INSERT INTO `t_answer` VALUES (1, 1, 1, 1535775869338, '2323');
INSERT INTO `t_answer` VALUES (2, 2, 0, 1535775869338, '事撕碎');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES (1, 6, '流行感冒吃什么药', '医学图解', 1535883229563, 'af7385f4-d706-40a6-b10c-8603ce5aa4db.jpg', '<p>123</p>\n', 0, 0, 0);
INSERT INTO `t_article` VALUES (2, 7, '33', '33', 1536067552321, '31ecd0ec-34f6-4772-841d-e4c86c1dd4b9.jpg', '<p style=\"text-align:center\"><img alt=\"\" src=\"http://localhost:8000/article/65eee5d2-5d07-40eb-b5a6-0ada25badf8e.jpg\" style=\"height:146px; width:111px\" /></p>\n\n<p>123123123</p>\n', 0, 0, 0);

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_question
-- ----------------------------
INSERT INTO `t_question` VALUES (1, 13, '大姨夫来了吃什么？', 5, 1535883229563, 0, 0, 1, 0);
INSERT INTO `t_question` VALUES (3, 13, '给', NULL, 0, 3, 0, 1, 0);
INSERT INTO `t_question` VALUES (4, 14, '发', NULL, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (5, 14, '发3', NULL, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (6, 14, '发4', NULL, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (7, 14, '发5', NULL, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (8, 14, '发7', NULL, 0, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (9, 6, '3', NULL, 1535988659315, 0, 0, 0, 0);
INSERT INTO `t_question` VALUES (10, 13, '测试', NULL, 1535989001223, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (6, '多大213');
INSERT INTO `t_type` VALUES (7, '321');
INSERT INTO `t_type` VALUES (13, '趣味请问');
INSERT INTO `t_type` VALUES (14, '趣味请问123123');
INSERT INTO `t_type` VALUES (15, '123');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (5, 'aaa', '15645100192');
INSERT INTO `t_user` VALUES (6, '啊啊啊', 'ccc');

SET FOREIGN_KEY_CHECKS = 1;
