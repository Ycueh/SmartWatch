/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : smart_admin_v2

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 21/07/2023 15:18:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dataitem
-- ----------------------------
DROP TABLE IF EXISTS `dataitem`;
CREATE TABLE `dataitem`  (
  `_id` int NOT NULL AUTO_INCREMENT,
  `datestamp` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `timestamp` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `dataitem1` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `dataitem2` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `dataitem3` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dataitem
-- ----------------------------

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event`  (
  `_id` int NOT NULL AUTO_INCREMENT,
  `eventdate` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `eventtime` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `eventdesc` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of event
-- ----------------------------

-- ----------------------------
-- Table structure for parameter
-- ----------------------------
DROP TABLE IF EXISTS `parameter`;
CREATE TABLE `parameter`  (
  `_id` int NOT NULL AUTO_INCREMENT,
  `paramname` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `paramvalue` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parameter
-- ----------------------------
INSERT INTO `parameter` VALUES (1, 'MODE', 'DEMO');
INSERT INTO `parameter` VALUES (2, 'INTERVAL', '19800000');
INSERT INTO `parameter` VALUES (3, 'DISPLAY', '300000');
INSERT INTO `parameter` VALUES (4, 'REPROMPT', '300000');
INSERT INTO `parameter` VALUES (5, 'NUMBERQ', '3');
INSERT INTO `parameter` VALUES (6, 'HOUR', '8');
INSERT INTO `parameter` VALUES (7, 'MINUTE', '00');
INSERT INTO `parameter` VALUES (8, 'USUAL_DRINK', 'UNSPECIFIED');
INSERT INTO `parameter` VALUES (9, 'USUAL_SIZE', '');
INSERT INTO `parameter` VALUES (10, 'USUAL_WHERE', '');
INSERT INTO `parameter` VALUES (11, 'USUAL_WHO', '');
INSERT INTO `parameter` VALUES (12, 'NUMBERQINSET', '14');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `_id` int NOT NULL AUTO_INCREMENT,
  `questionID` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `question` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `answer1ID` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `answer1` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `answer2ID` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `answer2` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `answer3ID` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `answer3` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `answer4ID` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `answer4` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, '01000', 'How are you feeling overall right now?', '01010', 'Bad', '01020', 'a', '01030', 'Neutral', '01040', 'NULL');
INSERT INTO `question` VALUES (2, '02000', 'Feeling nervous, anxious, or on edge', '02010', 'Not at all', '02020', 'A little', '02030', 'A lot', '02040', 'Extremely');
INSERT INTO `question` VALUES (3, '03000', 'Feeling unable to stop or control worrying', '03010', 'Not at all', '03020', 'A little', '03030', 'A lot', '03040', 'Extremely');
INSERT INTO `question` VALUES (4, '04000', 'Feeling down, depressed, or hopeless', '04010', 'Not at all', '04020', 'A little', '04030', 'A lot', '04040', 'Extremely');
INSERT INTO `question` VALUES (5, '05000', 'Experiencing little interest or pleasure in doing things', '05010', 'Not at all', '05020', 'A little', '05030', 'A lot', '05040', 'Extremely');
INSERT INTO `question` VALUES (6, '06000', 'Feeling cheerful, feeling in good spirits', '06010', 'Not at all', '06020', 'A little', '06030', 'A lot', '06040', 'Extremely');
INSERT INTO `question` VALUES (7, '07000', 'Feeling calm and relaxed', '07010', 'Not at all', '07020', 'A little', '07030', 'A lot', '07040', 'Extremely');
INSERT INTO `question` VALUES (8, '08000', 'Since we last asked you questions...', '08010', 'Press', '08020', 'To', '08030', 'Continue...', '08040', 'NULL');
INSERT INTO `question` VALUES (9, '09000', 'How was your sleep since your last response?', '09010', 'Restless', '09020', 'Acceptable', '09030', 'Refreshing', '09040', 'Didn\'t sleep');
INSERT INTO `question` VALUES (10, '10000', 'Have you had a conversation with anyone?', '10010', 'Yes, in person', '10020', 'Yes, remotely', '10030', 'No', '10040', 'NULL');
INSERT INTO `question` VALUES (11, '11000', 'Was there a significant event since your last response?', '11010', 'Positive event', '11020', 'Negative event', '11030', 'No event', '11040', 'NULL');
INSERT INTO `question` VALUES (12, '12000', 'Have you exercised, and if so who with?', '12010', 'With others', '12020', 'Alone', '12030', 'Have not exercised', '12040', 'NULL');
INSERT INTO `question` VALUES (13, '13000', 'If there is anything else you want to tell us about, please add a tag in the Oura app', '13010', 'Press to', '13020', 'finish', '13030', 'NULL', '13040', 'NULL');
INSERT INTO `question` VALUES (14, '14000', 'Question Fifteen?', '14010', 'Ans 15.1', '14020', 'Ans 15.2', '14030', 'Ans 15.3', '14040', 'Ans 15.4');
INSERT INTO `question` VALUES (15, '15000', 'Question Sixteen?', '15010', 'Ans 16.1', '15020', 'Ans 16.2', '15030', 'Ans 16.3', '15040', 'Ans 16.4');
INSERT INTO `question` VALUES (16, '16000', 'Question Seventeen?', '16010', 'Ans 17.1', '16020', 'Ans 17.2', '16030', 'Ans 17.3', '16040', 'Ans 17.4');
INSERT INTO `question` VALUES (17, '17000', 'Question Eighteen?', '17010', 'Ans 18.1', '17020', 'Ans 18.2', '17030', 'Ans 18.3', '17040', 'Ans 18.4');
INSERT INTO `question` VALUES (18, '18000', 'Question Nineteen?', '18010', 'Ans 19.1', '18020', 'Ans 19.2', '18030', 'Ans 19.3', '18040', 'Ans 19.4');
INSERT INTO `question` VALUES (19, '19000', 'Question Twenty?', '19010', 'Ans 20.1', '19020', 'Ans 20.2', '19030', 'Ans 20.3', '19040', 'Ans 20.4');

-- ----------------------------
-- Table structure for response
-- ----------------------------
DROP TABLE IF EXISTS `response`;
CREATE TABLE `response`  (
  `_id` int NOT NULL AUTO_INCREMENT,
  `date` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `responseTime` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `questionID` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `answerID` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `question` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `response` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of response
-- ----------------------------
INSERT INTO `response` VALUES (7, '12.5', 'd', '4', '34', '32', 'lalise', 'asdads');
INSERT INTO `response` VALUES (8, '12.5', 'e', 'a5', '1242', '234', '123', 'aa');
INSERT INTO `response` VALUES (10, '12.5', 'g', '7', '12343434', '12333', '123', 'asdads');
INSERT INTO `response` VALUES (11, '12.5', 'h', '8', '12343343333', '1233434', '4', '4');
INSERT INTO `response` VALUES (15, '1', '1', '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for t_data_tracer
-- ----------------------------
DROP TABLE IF EXISTS `t_data_tracer`;
CREATE TABLE `t_data_tracer`  (
  `data_tracer_id` bigint NOT NULL AUTO_INCREMENT,
  `data_id` bigint NOT NULL COMMENT '各种单据的id',
  `type` int NOT NULL COMMENT '单据类型',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '操作内容',
  `diff_old` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '差异：旧的数据',
  `diff_new` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '差异：新的数据',
  `extra_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '额外信息',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `user_type` int NOT NULL COMMENT '用户类型：1 后管用户 ',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名称',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `user_agent` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`data_tracer_id`) USING BTREE,
  INDEX `order_id_order_type`(`data_id`, `type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '各种单据操作记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_data_tracer
-- ----------------------------
INSERT INTO `t_data_tracer` VALUES (1, 49, 2, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 14:27:33', '2022-10-22 14:27:33');
INSERT INTO `t_data_tracer` VALUES (2, 50, 2, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 14:29:56', '2022-10-22 14:29:56');
INSERT INTO `t_data_tracer` VALUES (3, 51, 2, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 14:30:46', '2022-10-22 14:30:46');
INSERT INTO `t_data_tracer` VALUES (4, 52, 2, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 14:33:03', '2022-10-22 14:33:03');
INSERT INTO `t_data_tracer` VALUES (5, 53, 2, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 14:34:56', '2022-10-22 14:34:56');
INSERT INTO `t_data_tracer` VALUES (6, 54, 2, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 14:36:10', '2022-10-22 14:36:10');
INSERT INTO `t_data_tracer` VALUES (7, 55, 2, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 14:37:57', '2022-10-22 14:37:57');
INSERT INTO `t_data_tracer` VALUES (8, 56, 2, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 14:40:45', '2022-10-22 14:40:45');
INSERT INTO `t_data_tracer` VALUES (9, 57, 2, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 14:46:00', '2022-10-22 14:46:00');
INSERT INTO `t_data_tracer` VALUES (10, 58, 2, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 14:47:12', '2022-10-22 14:47:12');
INSERT INTO `t_data_tracer` VALUES (11, 58, 2, '', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 14:47:26', '2022-10-22 14:47:26');
INSERT INTO `t_data_tracer` VALUES (12, 59, 2, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 14:50:12', '2022-10-22 14:50:12');
INSERT INTO `t_data_tracer` VALUES (13, 17, 3, '新增', NULL, NULL, NULL, 44, 1, '卓大', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.47', '2022-10-22 14:57:36', '2022-10-22 14:57:36');
INSERT INTO `t_data_tracer` VALUES (14, 18, 3, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 17:03:35', '2022-10-22 17:03:35');
INSERT INTO `t_data_tracer` VALUES (15, 2, 3, '新增银行:<br/>银行信息ID:26<br/>账户名称:\"1024创新实验室\"<br/>禁用状态:false<br/>开户银行:\"工商银行\"<br/>备注:\"基本户\"<br/>账号:\"1024\"<br/>是否对公:true', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 17:58:43', '2022-10-22 17:58:43');
INSERT INTO `t_data_tracer` VALUES (16, 2, 3, '新增银行:<br/>银行信息ID:27<br/>账户名称:\"1024创新实验室\"<br/>禁用状态:false<br/>开户银行:\"建设银行\"<br/>备注:\"其他户\"<br/>账号:\"10241\"<br/>是否对公:false', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 17:59:19', '2022-10-22 17:59:19');
INSERT INTO `t_data_tracer` VALUES (17, 2, 3, '新增发票：<br/>禁用状态:false<br/>开户行:\"中国银行\"<br/>备注:\"\"<br/>银行账户:\"1024lab\"<br/>开票抬头:\"1024创新实验室\"<br/>纳税人识别号:\"1024lab\"', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 17:59:35', '2022-10-22 17:59:35');
INSERT INTO `t_data_tracer` VALUES (18, 2, 3, '修改企业信息', '统一社会信用代码:\"1024lab\"<br/>详细地址:\"1024大楼\"<br/>区县名称:\"洛龙区\"<br/>禁用状态:false<br/>类型:有限企业<br/>城市名称:\"洛阳市\"<br/>删除状态:false<br/>联系人:\"卓大\"<br/>省份名称:\"河南省\"<br/>企业logo:\"public/common/fb827d63dda74a60ab8b4f70cc7c7d0a_20221022145641_jpg\"<br/>联系人电话:\"18637925892\"<br/>企业名称:\"1024创新实验室\"<br/>邮箱:\"lab1024@163.com\"', '统一社会信用代码:\"1024lab\"<br/>详细地址:\"1024大楼\"<br/>区县名称:\"洛龙区\"<br/>禁用状态:false<br/>类型:有限企业<br/>城市名称:\"洛阳市\"<br/>删除状态:false<br/>联系人:\"卓大\"<br/>省份名称:\"河南省\"<br/>企业logo:\"public/common/fb827d63dda74a60ab8b4f70cc7c7d0a_20221022145641_jpg\"<br/>联系人电话:\"18637925892\"<br/>企业名称:\"1024创新实验室1\"<br/>邮箱:\"lab1024@163.com\"', NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 17:59:49', '2022-10-22 17:59:49');
INSERT INTO `t_data_tracer` VALUES (19, 2, 3, '修改企业信息', '统一社会信用代码:\"1024lab\"<br/>详细地址:\"1024大楼\"<br/>区县名称:\"洛龙区\"<br/>禁用状态:false<br/>类型:有限企业<br/>城市名称:\"洛阳市\"<br/>删除状态:false<br/>联系人:\"卓大\"<br/>省份名称:\"河南省\"<br/>企业logo:\"public/common/fb827d63dda74a60ab8b4f70cc7c7d0a_20221022145641_jpg\"<br/>联系人电话:\"18637925892\"<br/>企业名称:\"1024创新实验室1\"<br/>邮箱:\"lab1024@163.com\"', '统一社会信用代码:\"1024lab\"<br/>详细地址:\"1024大楼\"<br/>区县名称:\"洛龙区\"<br/>禁用状态:false<br/>类型:有限企业<br/>城市名称:\"洛阳市\"<br/>删除状态:false<br/>联系人:\"卓大\"<br/>省份名称:\"河南省\"<br/>企业logo:\"public/common/fb827d63dda74a60ab8b4f70cc7c7d0a_20221022145641_jpg\"<br/>联系人电话:\"18637925892\"<br/>企业名称:\"1024创新实验室\"<br/>邮箱:\"lab1024@163.com\"', NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36', '2022-10-22 17:59:52', '2022-10-22 17:59:52');
INSERT INTO `t_data_tracer` VALUES (20, 9, 1, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 16:39:00', '2023-06-25 16:39:00');
INSERT INTO `t_data_tracer` VALUES (21, 2, 4, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 16:42:41', '2023-06-25 16:42:41');
INSERT INTO `t_data_tracer` VALUES (22, 3, 4, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 16:44:06', '2023-06-25 16:44:06');
INSERT INTO `t_data_tracer` VALUES (23, 4, 4, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 16:55:52', '2023-06-25 16:55:52');
INSERT INTO `t_data_tracer` VALUES (24, 5, 4, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 16:57:15', '2023-06-25 16:57:15');
INSERT INTO `t_data_tracer` VALUES (25, 6, 4, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 16:58:34', '2023-06-25 16:58:34');
INSERT INTO `t_data_tracer` VALUES (26, 7, 4, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 17:03:00', '2023-06-25 17:03:00');
INSERT INTO `t_data_tracer` VALUES (27, 8, 4, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 18:18:44', '2023-06-25 18:18:44');
INSERT INTO `t_data_tracer` VALUES (28, 9, 4, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 18:19:44', '2023-06-25 18:19:44');
INSERT INTO `t_data_tracer` VALUES (29, 10, 4, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 18:22:38', '2023-06-25 18:22:38');
INSERT INTO `t_data_tracer` VALUES (30, 1, 4, '', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 19:12:54', '2023-06-25 19:12:54');
INSERT INTO `t_data_tracer` VALUES (31, 3, 4, '', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 19:13:10', '2023-06-25 19:13:10');
INSERT INTO `t_data_tracer` VALUES (32, 8, 4, '', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 19:13:35', '2023-06-25 19:13:35');
INSERT INTO `t_data_tracer` VALUES (33, 8, 4, '', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 19:15:09', '2023-06-25 19:15:09');
INSERT INTO `t_data_tracer` VALUES (34, 11, 4, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 19:16:49', '2023-06-25 19:16:49');
INSERT INTO `t_data_tracer` VALUES (35, 1, 1, '', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 19:23:10', '2023-06-25 19:23:10');
INSERT INTO `t_data_tracer` VALUES (36, 8, 4, '', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 19:30:57', '2023-06-25 19:30:57');
INSERT INTO `t_data_tracer` VALUES (37, 12, 4, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'PostmanRuntime/7.32.3', '2023-06-25 19:32:45', '2023-06-25 19:32:45');
INSERT INTO `t_data_tracer` VALUES (38, 13, 4, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Mobile Safari/537.36', '2023-07-01 13:45:41', '2023-07-01 13:45:41');
INSERT INTO `t_data_tracer` VALUES (39, 17, 1, '新增', NULL, NULL, NULL, 1, 1, '管理员', '127.0.0.1', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Mobile Safari/537.36', '2023-07-21 14:51:27', '2023-07-21 14:51:27');

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee`  (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `login_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `login_pwd` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `actual_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` int NOT NULL,
  `phone` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `department_id` int NOT NULL,
  `disabled_flag` int NOT NULL,
  `deleted_flag` int NOT NULL,
  `administrator_flag` int NOT NULL,
  `remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `update_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES (1, 'admin', '40cc20b8891cd3fd1f008ea7f4ac17c3', '管理员', 0, '13500000000', 1, 0, 0, 1, NULL, '2022-10-22 19:33:02', '2018-05-11 09:38:54');
INSERT INTO `t_employee` VALUES (2, 'huke', '40cc20b8891cd3fd1f008ea7f4ac17c3', '胡克', 0, '13123123121', 1, 0, 0, 0, NULL, '2022-10-19 20:17:30', '2021-07-29 11:24:55');
INSERT INTO `t_employee` VALUES (44, 'zhuoda', '40cc20b8891cd3fd1f008ea7f4ac17c3', '卓大', 1, '18637925892', 1, 0, 0, 1, NULL, '2022-10-22 14:29:04', '2021-08-11 10:04:53');
INSERT INTO `t_employee` VALUES (47, 'shanyi', '40cc20b8891cd3fd1f008ea7f4ac17c3', '善逸', 1, '13123111123', 1, 0, 0, 0, NULL, '2022-10-19 20:19:50', '2021-08-16 17:14:55');
INSERT INTO `t_employee` VALUES (48, 'qinjiu', '40cc20b8891cd3fd1f008ea7f4ac17c3', '琴酒', 2, '14112343212', 2, 0, 0, 0, NULL, '2022-10-19 20:23:40', '2021-08-17 10:29:41');
INSERT INTO `t_employee` VALUES (63, 'kaiyun', '40cc20b8891cd3fd1f008ea7f4ac17c3', '开云', 0, '13112312346', 2, 0, 0, 0, NULL, '2022-10-19 20:23:40', '2022-06-03 22:41:55');
INSERT INTO `t_employee` VALUES (64, 'qingye', '40cc20b8891cd3fd1f008ea7f4ac17c3', '清野', 1, '13123123111', 2, 0, 0, 0, NULL, '2022-10-19 20:23:40', '2022-06-16 17:19:08');
INSERT INTO `t_employee` VALUES (65, 'feiye', '40cc20b8891cd3fd1f008ea7f4ac17c3', '飞叶', 1, '13123123112', 1, 0, 0, 0, NULL, '2022-09-15 16:51:09', '2022-06-16 17:24:18');
INSERT INTO `t_employee` VALUES (66, 'luoyi', '40cc20b8891cd3fd1f008ea7f4ac17c3', '罗伊', 1, '13123123142', 1, 1, 0, 0, NULL, '2022-09-15 16:51:19', '2022-06-16 17:24:56');
INSERT INTO `t_employee` VALUES (67, 'chuxiao', '7287168489ed5598741362cbec2b0741', '初晓', 1, '13123123123', 1, 0, 0, 0, NULL, '2022-09-17 15:42:42', '2022-06-16 17:28:32');
INSERT INTO `t_employee` VALUES (68, 'xuanpeng', '40cc20b8891cd3fd1f008ea7f4ac17c3', '玄朋', 1, '13123123124', 1, 0, 0, 0, NULL, '2022-09-15 16:51:43', '2022-06-16 17:30:17');
INSERT INTO `t_employee` VALUES (69, 'peixian', '40cc20b8891cd3fd1f008ea7f4ac17c3', '佩弦', 1, '18377482773', 1, 0, 0, 0, NULL, '2022-10-19 20:17:35', '2022-06-25 16:42:52');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `menu_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `menu_type` int NOT NULL,
  `parent_id` int NOT NULL,
  `sort` int NULL DEFAULT NULL,
  `path` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `component` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `perms_type` int NULL DEFAULT NULL,
  `api_perms` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `web_perms` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `icon` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `context_menu_id` int NULL DEFAULT NULL,
  `frame_flag` int NOT NULL,
  `frame_url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `cache_flag` int NOT NULL,
  `visible_flag` int NOT NULL,
  `disabled_flag` int NOT NULL,
  `deleted_flag` int NOT NULL,
  `create_user_id` int NOT NULL,
  `create_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_user_id` int NULL DEFAULT NULL,
  `update_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 210 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (26, 'Menu', 2, 50, 0, '/menu/list', '/system/menu/menu-list.vue', NULL, NULL, NULL, 'CopyOutlined', NULL, 0, NULL, 1, 1, 0, 0, 2, '2021-08-09 15:04:35', 1, '2023-07-13 18:17:33');
INSERT INTO `t_menu` VALUES (40, '批量删除', 3, 26, NULL, NULL, NULL, 1, NULL, 'system:menu:batch:delete', NULL, 26, 0, NULL, 0, 1, 0, 0, 1, '2021-08-12 09:45:56', 1, '2022-10-22 10:39:01');
INSERT INTO `t_menu` VALUES (45, '部门员工', 1, 0, 100, '/organization', NULL, NULL, NULL, NULL, 'UserSwitchOutlined', NULL, 0, NULL, 0, 0, 0, 0, 1, '2021-08-12 16:13:27', 1, '2023-07-13 16:26:59');
INSERT INTO `t_menu` VALUES (46, '部门员工', 2, 45, 1, '/employee/department', '/system/employee/department/index.vue', NULL, NULL, NULL, 'AuditOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2021-08-12 16:21:50', 1, '2022-06-23 16:19:54');
INSERT INTO `t_menu` VALUES (47, '商品管理', 2, 48, 3, '/erp/goods/list', '/business/erp/goods/goods-list.vue', NULL, NULL, NULL, 'AliwangwangOutlined', NULL, 0, NULL, 0, 1, 0, 1, 1, '2021-08-12 17:58:39', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (48, '商品管理', 1, 137, 10, '/goods', NULL, NULL, NULL, NULL, 'BarcodeOutlined', NULL, 0, NULL, 0, 1, 0, 1, 1, '2021-08-12 18:02:59', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (50, 'System', 1, 0, 200, '/setting', NULL, NULL, NULL, NULL, 'SettingOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2021-08-13 16:41:33', 1, '2023-07-13 18:17:24');
INSERT INTO `t_menu` VALUES (76, '角色管理', 2, 45, 2, '/employee/role', '/system/employee/role/index.vue', NULL, NULL, NULL, 'SlidersOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2021-08-26 10:31:00', 1, '2022-06-23 16:21:06');
INSERT INTO `t_menu` VALUES (78, '商品分类', 2, 48, 1, '/erp/catalog/goods', '/business/erp/catalog/goods-catalog.vue', NULL, NULL, NULL, 'ApartmentOutlined', NULL, 0, NULL, 0, 1, 0, 1, 1, '2022-05-18 23:34:14', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (79, '自定义分组', 2, 48, 2, '/erp/catalog/custom', '/business/erp/catalog/custom-catalog.vue', NULL, NULL, NULL, 'AppstoreAddOutlined', NULL, 0, NULL, 0, 1, 0, 1, 1, '2022-05-18 23:37:53', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (81, '请求监控', 2, 111, 3, '/support/operate-log/operate-log-list', '/support/operate-log/operate-log-list.vue', NULL, NULL, NULL, 'VideoCameraOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-20 12:37:24', 1, '2022-10-22 18:33:10');
INSERT INTO `t_menu` VALUES (85, '组件演示', 2, 84, NULL, '/demonstration/index', '/support/demonstration/index.vue', NULL, NULL, NULL, 'ClearOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-20 23:16:46', NULL, '2022-05-20 23:16:46');
INSERT INTO `t_menu` VALUES (86, '添加部门', 3, 46, NULL, NULL, NULL, 1, NULL, 'system:department:add', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-26 23:33:37', NULL, '2022-10-22 10:39:01');
INSERT INTO `t_menu` VALUES (87, '修改部门', 3, 46, NULL, NULL, NULL, 1, NULL, 'system:department:update', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-26 23:34:11', NULL, '2022-10-22 10:39:02');
INSERT INTO `t_menu` VALUES (88, '删除部门', 3, 46, NULL, NULL, NULL, 1, NULL, 'system:department:delete', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-26 23:34:49', NULL, '2022-10-22 10:39:03');
INSERT INTO `t_menu` VALUES (91, '添加员工', 3, 46, NULL, NULL, NULL, 1, NULL, 'system:employee:add', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:11:38', NULL, '2022-10-22 10:39:03');
INSERT INTO `t_menu` VALUES (92, '编辑员工', 3, 46, NULL, NULL, NULL, 1, NULL, 'system:employee:update', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:12:10', NULL, '2022-10-22 10:39:04');
INSERT INTO `t_menu` VALUES (93, '禁用启用员工', 3, 46, NULL, NULL, NULL, 1, NULL, 'system:employee:disabled', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:12:37', NULL, '2022-10-22 10:39:05');
INSERT INTO `t_menu` VALUES (94, '调整部门', 3, 46, NULL, NULL, NULL, 1, NULL, 'system:employee:department:update', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:12:59', NULL, '2022-10-22 10:39:06');
INSERT INTO `t_menu` VALUES (95, '重置密码', 3, 46, NULL, NULL, NULL, 1, NULL, 'system:employee:password:reset', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:13:30', 1, '2022-10-20 13:39:13');
INSERT INTO `t_menu` VALUES (96, '删除员工', 3, 46, NULL, NULL, NULL, 1, NULL, 'system:employee:delete', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:14:08', NULL, '2022-10-22 10:39:07');
INSERT INTO `t_menu` VALUES (97, '添加角色', 3, 76, NULL, NULL, NULL, 1, NULL, 'system:role:add', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:34:00', 1, '2022-10-22 10:39:09');
INSERT INTO `t_menu` VALUES (98, '删除角色', 3, 76, NULL, NULL, NULL, 1, NULL, 'system:role:delete', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:34:19', 1, '2022-10-22 10:39:09');
INSERT INTO `t_menu` VALUES (99, '编辑角色', 3, 76, NULL, NULL, NULL, 1, NULL, 'system:role:update', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:34:55', NULL, '2022-10-22 10:39:10');
INSERT INTO `t_menu` VALUES (100, '更新数据范围', 3, 76, NULL, NULL, NULL, 1, NULL, 'system:role:dataScope:update', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:37:03', NULL, '2022-10-22 10:39:11');
INSERT INTO `t_menu` VALUES (101, '批量移除员工', 3, 76, NULL, NULL, NULL, 1, NULL, 'system:role:employee:batch:delete', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:39:05', NULL, '2022-10-22 10:39:38');
INSERT INTO `t_menu` VALUES (102, '移除员工', 3, 76, NULL, NULL, NULL, 1, NULL, 'system:role:employee:delete', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:39:21', NULL, '2022-10-22 10:39:39');
INSERT INTO `t_menu` VALUES (103, '添加员工', 3, 76, NULL, NULL, NULL, 1, NULL, 'system:role:employee:add', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:39:38', NULL, '2022-10-22 10:39:40');
INSERT INTO `t_menu` VALUES (104, '修改权限', 3, 76, NULL, NULL, NULL, 1, NULL, 'system:role:menu:update', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:41:55', NULL, '2022-10-22 10:39:41');
INSERT INTO `t_menu` VALUES (105, '添加', 3, 26, NULL, NULL, NULL, 1, NULL, 'system:menu:add', NULL, 26, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:44:37', 1, '2022-10-22 10:39:41');
INSERT INTO `t_menu` VALUES (106, '编辑', 3, 26, NULL, NULL, NULL, 1, NULL, 'system:menu:update', NULL, 26, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:44:59', 1, '2022-10-22 10:39:44');
INSERT INTO `t_menu` VALUES (108, '删除', 3, 26, NULL, NULL, NULL, 1, NULL, 'system:menu:delete', NULL, 26, 0, NULL, 0, 1, 0, 0, 1, '2022-05-27 00:45:55', 1, '2022-10-22 10:39:43');
INSERT INTO `t_menu` VALUES (109, '参数配置', 2, 50, 3, '/config/config-list', '/support/config/config-list.vue', NULL, NULL, NULL, 'AntDesignOutlined', NULL, 0, NULL, 0, 0, 0, 0, 1, '2022-05-27 13:34:41', 1, '2023-07-13 18:17:38');
INSERT INTO `t_menu` VALUES (110, '数据字典', 2, 50, 4, '/setting/dict', '/support/dict/index.vue', NULL, NULL, NULL, 'BarcodeOutlined', NULL, 0, NULL, 0, 0, 0, 0, 1, '2022-05-27 17:53:00', 1, '2023-07-13 18:17:42');
INSERT INTO `t_menu` VALUES (111, '监控服务', 1, 0, 4, '/monitor', NULL, NULL, NULL, NULL, 'BarChartOutlined', NULL, 0, NULL, 0, 0, 0, 0, 1, '2022-06-17 11:13:23', 1, '2023-07-13 16:26:47');
INSERT INTO `t_menu` VALUES (113, '查询', 3, 112, NULL, NULL, NULL, NULL, NULL, 'ad', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-06-17 11:31:36', NULL, '2022-06-17 11:31:36');
INSERT INTO `t_menu` VALUES (114, '运维工具', 1, 0, 5, NULL, NULL, NULL, NULL, NULL, 'NodeCollapseOutlined', NULL, 0, NULL, 0, 0, 0, 0, 1, '2022-06-20 10:09:16', 1, '2023-07-13 16:26:53');
INSERT INTO `t_menu` VALUES (117, 'Reload', 2, 114, NULL, '/hook', '/support/reload/reload-list.vue', NULL, NULL, NULL, 'ReloadOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-06-20 10:16:49', NULL, '2022-06-20 10:16:49');
INSERT INTO `t_menu` VALUES (122, '数据库监控', 2, 111, 4, '/support/druid/index', NULL, NULL, NULL, NULL, 'ConsoleSqlOutlined', NULL, 1, 'http://localhost:60001/druid/index.html', 0, 1, 0, 0, 1, '2022-06-20 14:49:33', 1, '2022-10-22 18:33:07');
INSERT INTO `t_menu` VALUES (130, '单号管理', 2, 50, 6, '/support/serial-number/serial-number-list', '/support/serial-number/serial-number-list.vue', NULL, NULL, NULL, 'NumberOutlined', NULL, 0, NULL, 0, 0, 0, 0, 1, '2022-06-24 14:45:22', 1, '2023-07-13 18:17:47');
INSERT INTO `t_menu` VALUES (132, '通知公告', 2, 138, NULL, '/oa/notice/notice-list', '/business/oa/notice/notice-list.vue', NULL, NULL, NULL, 'SoundOutlined', NULL, 0, NULL, 0, 1, 0, 1, 1, '2022-06-24 18:23:09', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (133, '缓存管理', 2, 114, NULL, '/support/cache/cache-list', '/support/cache/cache-list.vue', NULL, NULL, NULL, 'BorderInnerOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-06-24 18:52:25', NULL, '2022-06-24 18:52:25');
INSERT INTO `t_menu` VALUES (137, '进销存系统', 1, 0, 2, NULL, NULL, NULL, NULL, NULL, 'AccountBookOutlined', NULL, 0, NULL, 0, 0, 0, 1, 1, '2022-06-24 20:07:20', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (138, 'OA系统', 1, 0, 1, NULL, NULL, NULL, NULL, NULL, 'BankOutlined', NULL, 0, NULL, 0, 1, 0, 1, 1, '2022-06-24 20:09:18', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (142, '公告详情', 2, 132, NULL, '/oa/notice/notice-detail', '/business/oa/notice/notice-detail.vue', NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0, 0, 1, 1, '2022-06-25 16:38:47', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (143, '登录日志', 2, 111, 2, '/support/login-log/login-log-list', '/support/login-log/login-log-list.vue', NULL, NULL, NULL, 'LoginOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-06-28 15:01:38', 1, '2022-10-22 18:33:03');
INSERT INTO `t_menu` VALUES (144, '企业信息', 2, 138, 2, '/oa/enterprise/enterprise-list', '/business/oa/enterprise/enterprise-list.vue', NULL, NULL, NULL, 'ShopOutlined', NULL, 0, NULL, 0, 1, 0, 1, 1, '2022-09-14 17:00:07', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (145, '企业详情', 2, 144, NULL, '/oa/enterprise/enterprise-detail', '/business/oa/enterprise/enterprise-detail.vue', NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0, 0, 1, 1, '2022-09-14 18:52:52', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (146, '系统文档', 1, 0, 3, NULL, NULL, NULL, NULL, NULL, 'FileWordOutlined', NULL, 0, NULL, 0, 0, 0, 0, 1, '2022-09-14 19:56:53', 1, '2023-07-13 16:26:42');
INSERT INTO `t_menu` VALUES (147, '系统手册', 2, 146, NULL, '/help-doc/help-doc-manage-list', '/support/help-doc/management/help-doc-manage-list.vue', NULL, NULL, NULL, 'FolderViewOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-09-14 19:59:01', NULL, '2022-09-14 19:59:01');
INSERT INTO `t_menu` VALUES (148, '意见反馈', 2, 146, NULL, '/feedback/feedback-list', '/support/feedback/feedback-list.vue', NULL, NULL, NULL, 'CoffeeOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-09-14 19:59:52', 1, '2022-09-14 20:00:37');
INSERT INTO `t_menu` VALUES (149, '我的通知', 2, 132, NULL, '/oa/notice/notice-employee-list', '/business/oa/notice/notice-employee-list.vue', NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0, 0, 1, 1, '2022-09-14 20:29:41', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (150, '我的通知公告详情', 2, 132, NULL, '/oa/notice/notice-employee-detail', '/business/oa/notice/notice-employee-detail.vue', NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0, 0, 1, 1, '2022-09-14 20:30:25', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (151, '代码生成', 2, 0, 600, '/support/code-generator', '/support/code-generator/code-generator-list.vue', NULL, NULL, NULL, 'CoffeeOutlined', NULL, 0, NULL, 0, 0, 0, 0, 1, '2022-09-21 18:25:05', 1, '2023-07-13 16:27:23');
INSERT INTO `t_menu` VALUES (152, '更新日志', 2, 146, 3, '/support/change-log/change-log-list', '/support/change-log/change-log-list.vue', NULL, NULL, NULL, 'HeartOutlined', NULL, 0, NULL, 0, 1, 0, 0, 44, '2022-10-10 10:31:20', 44, '2022-10-10 10:31:45');
INSERT INTO `t_menu` VALUES (153, '清除缓存', 3, 133, NULL, NULL, NULL, 1, NULL, 'support:cache:delete', NULL, 133, 0, NULL, 0, 1, 1, 0, 1, '2022-10-15 22:45:13', 1, '2022-10-21 20:37:13');
INSERT INTO `t_menu` VALUES (154, '获取缓存key', 3, 133, NULL, NULL, NULL, NULL, NULL, 'support:cache:keys', NULL, 133, 0, NULL, 0, 1, 1, 0, 1, '2022-10-15 22:45:48', 1, '2022-10-15 23:40:09');
INSERT INTO `t_menu` VALUES (155, '执行', 3, 117, NULL, NULL, NULL, 1, NULL, 'support:reload:execute', NULL, 117, 0, NULL, 0, 1, 0, 0, 1, '2022-10-15 23:16:45', 1, '2022-10-22 10:37:33');
INSERT INTO `t_menu` VALUES (156, '查看结果', 3, 117, NULL, NULL, NULL, 1, NULL, 'support:reload:result', NULL, 117, 0, NULL, 0, 1, 0, 0, 1, '2022-10-15 23:17:23', 1, '2022-10-22 10:37:36');
INSERT INTO `t_menu` VALUES (157, '单号生成', 3, 130, NULL, NULL, NULL, 1, NULL, 'support:serial:number:generate', NULL, 130, 0, NULL, 0, 1, 0, 0, 1, '2022-10-15 23:21:06', 1, '2022-10-22 10:36:34');
INSERT INTO `t_menu` VALUES (158, '生成记录', 3, 130, NULL, NULL, NULL, 1, NULL, 'support:serial:number:record', NULL, 130, 0, NULL, 0, 1, 0, 0, 1, '2022-10-15 23:21:34', 1, '2022-10-22 10:36:42');
INSERT INTO `t_menu` VALUES (159, '新建', 3, 110, NULL, NULL, NULL, 1, NULL, 'support:dict:add', NULL, 110, 0, NULL, 0, 1, 0, 0, 1, '2022-10-15 23:23:51', NULL, '2022-10-22 10:41:07');
INSERT INTO `t_menu` VALUES (160, '编辑', 3, 110, NULL, NULL, NULL, 1, NULL, 'support:dict:update', NULL, 110, 0, NULL, 0, 1, 0, 0, 1, '2022-10-15 23:24:05', NULL, '2022-10-22 10:41:08');
INSERT INTO `t_menu` VALUES (161, '批量删除', 3, 110, NULL, NULL, NULL, 1, NULL, 'support:dict:batch:delete', NULL, 110, 0, NULL, 0, 1, 0, 0, 1, '2022-10-15 23:24:34', NULL, '2022-10-22 10:41:09');
INSERT INTO `t_menu` VALUES (162, '刷新缓存', 3, 110, NULL, NULL, NULL, 1, NULL, 'support:dict:refresh', NULL, 110, 0, NULL, 0, 1, 0, 0, 1, '2022-10-15 23:24:55', NULL, '2022-10-22 10:41:10');
INSERT INTO `t_menu` VALUES (163, '新建', 3, 109, NULL, NULL, NULL, 1, NULL, 'support:config:add', NULL, 109, 0, NULL, 0, 1, 0, 0, 1, '2022-10-15 23:26:56', NULL, '2022-10-22 10:41:10');
INSERT INTO `t_menu` VALUES (164, '编辑', 3, 109, NULL, NULL, NULL, 11, NULL, 'support:config:update', NULL, 109, 0, NULL, 0, 1, 0, 0, 1, '2022-10-15 23:27:07', NULL, '2022-10-22 10:41:12');
INSERT INTO `t_menu` VALUES (165, '查询', 3, 47, NULL, NULL, NULL, 1, NULL, 'goods:query', NULL, 47, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 19:55:39', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (166, '新建', 3, 47, NULL, NULL, NULL, 1, NULL, 'goods:add', NULL, 47, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 19:56:00', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (167, '批量删除', 3, 47, NULL, NULL, NULL, 1, NULL, 'goods:batchDelete', NULL, 47, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 19:56:15', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (168, '查询', 3, 147, NULL, NULL, NULL, 1, NULL, 'helpDoc:query', NULL, 147, 0, NULL, 0, 1, 0, 0, 1, '2022-10-16 20:12:13', NULL, '2022-10-22 10:41:17');
INSERT INTO `t_menu` VALUES (169, '新建', 3, 147, NULL, NULL, NULL, 1, NULL, 'helpDoc:add', NULL, 147, 0, NULL, 0, 1, 0, 0, 1, '2022-10-16 20:12:37', 1, '2022-10-21 20:49:24');
INSERT INTO `t_menu` VALUES (170, '新建目录', 3, 147, NULL, NULL, NULL, 1, NULL, 'helpDocCatalog:addCategory', NULL, 147, 0, NULL, 0, 1, 0, 0, 1, '2022-10-16 20:12:57', NULL, '2022-10-22 10:41:18');
INSERT INTO `t_menu` VALUES (171, '修改目录', 3, 147, NULL, NULL, NULL, 1, NULL, 'helpDocCatalog:edit', NULL, 147, 0, NULL, 0, 1, 0, 0, 1, '2022-10-16 20:13:46', 1, '2022-10-21 20:50:01');
INSERT INTO `t_menu` VALUES (173, '新建', 3, 78, NULL, NULL, NULL, 1, NULL, 'goodsCategory:add', NULL, 78, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:17:02', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (174, '添加子分类', 3, 78, NULL, NULL, NULL, 1, NULL, 'goodsCategory:addChild', NULL, 78, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:17:22', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (175, '编辑', 3, 78, NULL, NULL, NULL, 1, NULL, 'goodsCategory:edit', NULL, 78, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:17:38', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (176, '删除', 3, 78, NULL, NULL, NULL, 1, NULL, 'goodsCategory:delete', NULL, 78, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:17:50', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (177, '新建', 3, 79, NULL, NULL, NULL, 1, NULL, 'customCategory:add', NULL, 78, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:17:02', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (178, '添加子分类', 3, 79, NULL, NULL, NULL, 1, NULL, 'customCategory:addChild', NULL, 78, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:17:22', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (179, '编辑', 3, 79, NULL, NULL, NULL, 1, NULL, 'customCategory:edit', NULL, 78, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:17:38', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (180, '删除', 3, 79, NULL, NULL, NULL, 1, NULL, 'customCategory:delete', NULL, 78, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:17:50', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (181, '查询', 3, 144, NULL, NULL, NULL, 2, 'EnterpriseController.deleteEnterprise', 'enterprise:query', NULL, 144, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:25:14', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (182, '新建', 3, 144, NULL, NULL, NULL, 2, 'EnterpriseController.createEnterprise', 'enterprise:add', NULL, 144, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:25:25', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (183, '编辑', 3, 144, NULL, NULL, NULL, 2, 'EnterpriseController.updateEnterprise', 'enterprise:edit', NULL, 144, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:25:36', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (184, '删除', 3, 144, NULL, NULL, NULL, 2, 'EnterpriseController.deleteEnterprise', 'enterprise:delete', NULL, 144, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:25:53', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (185, '查询', 3, 132, NULL, NULL, NULL, 2, 'NoticeController.query', 'notice:query', NULL, 132, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:26:38', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (186, '新建', 3, 132, NULL, NULL, NULL, 2, 'NoticeController.add', 'notice:add', NULL, 132, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:27:04', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (187, '编辑', 3, 132, NULL, NULL, NULL, 2, 'NoticeController.update', 'notice:edit', NULL, 132, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:27:15', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (188, '删除', 3, 132, NULL, NULL, NULL, 2, 'NoticeController.delete', 'notice:delete', NULL, 132, 0, NULL, 0, 1, 0, 1, 1, '2022-10-16 20:27:23', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (189, '查询', 3, 148, NULL, NULL, NULL, 1, NULL, 'feedback:query', NULL, 148, 0, NULL, 0, 1, 0, 0, 1, '2022-10-16 20:28:04', 1, '2022-10-22 10:38:46');
INSERT INTO `t_menu` VALUES (190, '查询', 3, 152, NULL, NULL, NULL, 1, NULL, 'changeLog:query', NULL, 152, 0, NULL, 0, 1, 0, 0, 1, '2022-10-16 20:28:33', 1, '2022-10-21 20:42:00');
INSERT INTO `t_menu` VALUES (191, '新建', 3, 152, NULL, NULL, NULL, 1, NULL, 'changeLog:add', NULL, 152, 0, NULL, 0, 1, 0, 0, 1, '2022-10-16 20:28:46', 1, '2022-10-21 20:42:09');
INSERT INTO `t_menu` VALUES (192, '批量删除', 3, 152, NULL, NULL, NULL, 1, NULL, 'changeLog:batchDelete', NULL, 152, 0, NULL, 0, 1, 0, 0, 1, '2022-10-16 20:29:10', 1, '2022-10-21 20:42:16');
INSERT INTO `t_menu` VALUES (193, '文件管理', 2, 50, 20, '/support/file/file-list', '/support/file/file-list.vue', NULL, NULL, NULL, 'FolderOpenOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-10-21 11:26:11', 1, '2022-10-22 11:29:22');
INSERT INTO `t_menu` VALUES (194, '删除', 3, 47, NULL, NULL, NULL, 1, NULL, 'goods:delete', NULL, 47, 0, NULL, 0, 1, 0, 1, 1, '2022-10-21 20:00:12', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (195, '修改', 3, 47, NULL, NULL, NULL, 1, NULL, 'goods:update', NULL, NULL, 0, NULL, 0, 1, 0, 1, 1, '2022-10-21 20:05:23', 1, '2023-07-13 16:28:22');
INSERT INTO `t_menu` VALUES (196, '查看详情', 3, 145, NULL, NULL, NULL, 2, 'EnterpriseController.getDetail', 'enterprise:detail', NULL, NULL, 0, NULL, 0, 1, 0, 1, 1, '2022-10-21 20:16:47', 1, '2023-07-12 21:41:20');
INSERT INTO `t_menu` VALUES (198, '删除', 3, 152, NULL, NULL, NULL, 1, NULL, 'changeLog:delete', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-10-21 20:42:34', NULL, '2022-10-21 20:42:34');
INSERT INTO `t_menu` VALUES (199, '查询', 3, 109, NULL, NULL, NULL, 1, NULL, 'support:config:query', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-10-21 20:45:14', NULL, '2022-10-21 20:45:14');
INSERT INTO `t_menu` VALUES (200, '查询', 3, 193, NULL, NULL, NULL, 1, NULL, 'support:file:query', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-10-21 20:47:23', 1, '2022-10-22 11:21:10');
INSERT INTO `t_menu` VALUES (201, '删除', 3, 147, NULL, NULL, NULL, 1, NULL, 'helpDoc:delete', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-10-21 21:03:20', NULL, '2022-10-21 21:03:20');
INSERT INTO `t_menu` VALUES (202, '更新', 3, 147, NULL, NULL, NULL, 1, NULL, 'helpDoc:update', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-10-21 21:03:32', NULL, '2022-10-21 21:03:32');
INSERT INTO `t_menu` VALUES (203, '查询', 3, 143, NULL, NULL, NULL, 1, NULL, 'loginLog:query', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-10-21 21:05:11', NULL, '2022-10-21 21:05:11');
INSERT INTO `t_menu` VALUES (204, '查询', 3, 81, NULL, NULL, NULL, 1, NULL, 'operateLog:query', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-10-22 10:33:31', NULL, '2022-10-22 10:33:31');
INSERT INTO `t_menu` VALUES (205, '详情', 3, 81, NULL, NULL, NULL, 1, NULL, 'operateLog:detail', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-10-22 10:33:49', NULL, '2022-10-22 10:33:49');
INSERT INTO `t_menu` VALUES (206, '心跳监控', 2, 111, 1, '/support/heart-beat/heart-beat-list', '/support/heart-beat/heart-beat-list.vue', 1, NULL, NULL, 'FallOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-10-22 10:47:03', 1, '2022-10-22 18:32:52');
INSERT INTO `t_menu` VALUES (207, '更新', 3, 152, NULL, NULL, NULL, 1, NULL, 'changeLog:update', NULL, NULL, 0, NULL, 0, 1, 0, 0, 1, '2022-10-22 11:51:32', NULL, '2022-10-22 11:51:32');
INSERT INTO `t_menu` VALUES (208, 'Response', 2, 0, 1, '/response', 'smartWatch/response/response-list.vue', 1, NULL, NULL, 'AlignCenterOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2023-07-01 13:08:07', NULL, '2023-07-01 13:08:07');
INSERT INTO `t_menu` VALUES (209, 'Question', 2, 0, 1, '/question', 'smartWatch/question/question-list.vue', 1, NULL, NULL, 'AppstoreOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, '2023-07-12 21:38:08', 1, '2023-07-12 21:38:42');

-- ----------------------------
-- Table structure for t_reload_item
-- ----------------------------
DROP TABLE IF EXISTS `t_reload_item`;
CREATE TABLE `t_reload_item`  (
  `tag` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '项名称',
  `args` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '参数 可选',
  `identification` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '运行标识',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'reload项目' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_reload_item
-- ----------------------------
INSERT INTO `t_reload_item` VALUES ('system_config', 'e', 'e', '2022-06-20 14:43:14', '2019-04-18 11:48:27');
INSERT INTO `t_reload_item` VALUES ('test', 'd', 'd', '2022-06-20 14:40:33', '2022-06-20 14:05:05');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `update_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '技术总监', '', '2022-10-19 20:24:09', '2019-06-21 12:09:34');
INSERT INTO `t_role` VALUES (34, '销售总监', '', '2022-10-19 20:24:28', '2019-08-30 09:30:50');
INSERT INTO `t_role` VALUES (35, '总经理', '', '2019-08-30 09:31:05', '2019-08-30 09:31:05');
INSERT INTO `t_role` VALUES (36, '董事长', '', '2019-08-30 09:31:11', '2019-08-30 09:31:11');
INSERT INTO `t_role` VALUES (37, '财务', '', '2019-08-30 09:31:16', '2019-08-30 09:31:16');

-- ----------------------------
-- Table structure for t_role_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_role_employee`;
CREATE TABLE `t_role_employee`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `update_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_role_employee`(`role_id`, `employee_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 327 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_employee
-- ----------------------------
INSERT INTO `t_role_employee` VALUES (325, 36, 63, '2022-10-19 20:25:26', '2022-10-19 20:25:26');
INSERT INTO `t_role_employee` VALUES (326, 1, 44, '2022-10-22 10:54:35', '2022-10-22 10:54:35');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `role_menu_id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `menu_id` int NOT NULL,
  `update_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`role_menu_id`) USING BTREE,
  INDEX `idx_menu_id`(`menu_id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 1, 45, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (2, 1, 46, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (3, 1, 86, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (4, 1, 87, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (5, 1, 88, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (6, 1, 91, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (7, 1, 92, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (8, 1, 93, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (9, 1, 94, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (10, 1, 95, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (11, 1, 96, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (12, 1, 76, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (13, 1, 97, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (14, 1, 98, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (15, 1, 99, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (16, 1, 100, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (17, 1, 101, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (18, 1, 102, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (19, 1, 103, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (20, 1, 104, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (21, 1, 26, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (22, 1, 40, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (23, 1, 105, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (24, 1, 106, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (25, 1, 108, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (26, 1, 50, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (27, 1, 130, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (28, 1, 157, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (29, 1, 158, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (30, 1, 193, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (31, 1, 200, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (32, 1, 122, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (33, 1, 111, '2022-10-22 10:54:27', '2022-10-22 10:54:27');
INSERT INTO `t_role_menu` VALUES (34, 1, 143, '2022-10-22 10:54:27', '2022-10-22 10:54:27');

-- ----------------------------
-- Table structure for t_table_column
-- ----------------------------
DROP TABLE IF EXISTS `t_table_column`;
CREATE TABLE `t_table_column`  (
  `table_column_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `user_type` int NOT NULL,
  `table_id` int NOT NULL,
  `columns` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `create_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`table_column_id`) USING BTREE,
  UNIQUE INDEX `uni_employee_table`(`user_id`, `table_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_table_column
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
