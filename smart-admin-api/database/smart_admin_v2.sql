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

 Date: 08/08/2023 14:55:04
*/ 
DROP DATABASE IF EXISTS `smart_admin_v2`;
CREATE DATABASE IF NOT EXISTS `smart_admin_v2`;
USE `smart_admin_v2`;

SET NAMES utf8mb4;

SET FOREIGN_KEY_CHECKS = 0;-- ----------------------------
-- Table structure for dataitem
-- ----------------------------
DROP TABLE
IF
	EXISTS `dataitem`;
CREATE TABLE `dataitem` (
	`_id` INT NOT NULL,
	`datestamp` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`timestamp` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`dataitem1` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`dataitem2` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`dataitem3` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	PRIMARY KEY ( `_id` ) USING BTREE 
) ENGINE = INNODB CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of dataitem
-- ----------------------------
-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE
IF
	EXISTS `event`;
CREATE TABLE `event` (
	`_id` INT NOT NULL,
	`eventdate` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`eventtime` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`eventdesc` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	PRIMARY KEY ( `_id` ) USING BTREE 
) ENGINE = INNODB CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of event
-- ----------------------------
-- ----------------------------
-- Table structure for parameter
-- ----------------------------
DROP TABLE
IF
	EXISTS `parameter`;
CREATE TABLE `parameter` (
	`_id` INT NOT NULL,
	`paramname` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`paramvalue` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	PRIMARY KEY ( `_id` ) USING BTREE 
) ENGINE = INNODB CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of parameter
-- ----------------------------
INSERT INTO `parameter`
VALUES
	( 1, 'MODE', 'DEMO' );
INSERT INTO `parameter`
VALUES
	( 2, 'INTERVAL', '19800000' );
INSERT INTO `parameter`
VALUES
	( 3, 'DISPLAY', '300000' );
INSERT INTO `parameter`
VALUES
	( 4, 'REPROMPT', '300000' );
INSERT INTO `parameter`
VALUES
	( 5, 'NUMBERQ', '3' );
INSERT INTO `parameter`
VALUES
	( 6, 'HOUR', '8' );
INSERT INTO `parameter`
VALUES
	( 7, 'MINUTE', '00' );
INSERT INTO `parameter`
VALUES
	( 8, 'USUAL_DRINK', 'UNSPECIFIED' );
INSERT INTO `parameter`
VALUES
	( 9, 'USUAL_SIZE', '' );
INSERT INTO `parameter`
VALUES
	( 10, 'USUAL_WHERE', '' );
INSERT INTO `parameter`
VALUES
	( 11, 'USUAL_WHO', '' );
INSERT INTO `parameter`
VALUES
	( 12, 'NUMBERQINSET', '14' );-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE
IF
	EXISTS `question`;
CREATE TABLE `question` (
	`_id` INT NOT NULL,
	`questionID` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`question` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`answer1ID` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`answer1` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`answer2ID` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`answer2` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`answer3ID` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`answer3` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`answer4ID` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`answer4` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	PRIMARY KEY ( `_id` ) USING BTREE 
) ENGINE = INNODB CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question`
VALUES
	( 1, '01000', 'How are you feeling overall right now?', '01010', 'Bad', '01020', 'Good', '01030', 'Neutral', '01040', 'NULL' );
INSERT INTO `question`
VALUES
	( 2, '02000', 'How are you feeling now?', '02010', 'Press', '02020', 'To', '02030', 'Continue...', '02040', 'NULL' );
INSERT INTO `question`
VALUES
	( 3, '03000', 'Feeling nervous, anxious, or on edge', '03010', 'Not at all', '03020', 'A little', '03030', 'A lot', '03040', 'Extremely' );
INSERT INTO `question`
VALUES
	( 4, '04000', 'Feeling unable to stop or control worrying', '04010', 'Not at all', '04020', 'A little', '04030', 'A lot', '04040', 'Extremely' );
INSERT INTO `question`
VALUES
	( 5, '05000', 'Feeling down, depressed, or hopeless', '05010', 'Not at all', '05020', 'A little', '05030', 'A lot', '05040', 'Extremely' );
INSERT INTO `question`
VALUES
	( 6, '06000', 'Experiencing little interest or pleasure in doing things', '06010', 'Not at all', '06020', 'A little', '06030', 'A lot', '06040', 'Extremely' );
INSERT INTO `question`
VALUES
	( 7, '07000', 'Feeling cheerful, feeling in good spirits', '07010', 'Not at all', '07020', 'A little', '07030', 'A lot', '07040', 'Extremely' );
INSERT INTO `question`
VALUES
	( 8, '08000', 'Feeling calm and relaxed', '08010', 'Not at all', '08020', 'A little', '08030', 'A lot', '08040', 'Extremely' );
INSERT INTO `question`
VALUES
	( 9, '09000', 'Since we last asked you questions...', '09010', 'Press', '09020', 'To', '09030', 'Continue...', '09040', 'NULL' );
INSERT INTO `question`
VALUES
	( 10, '10000', 'How was your sleep since your last response?', '10010', 'Restless', '10020', 'Acceptable', '10030', 'Refreshing', '10040', 'Didn''t sleep' );
INSERT INTO `question`
VALUES
	( 11, '11000', 'Have you had a conversation with anyone?', '11010', 'Yes, in person', '11020', 'Yes, remotely', '11030', 'No', '11040', 'NULL' );
INSERT INTO `question`
VALUES
	( 12, '12000', 'Was there a significant event since your last response?', '12010', 'Positive event', '12020', 'Negative event', '12030', 'No event', '12040', 'NULL' );
INSERT INTO `question`
VALUES
	( 13, '13000', 'Have you exercised, and if so who with?', '13010', 'With others', '13020', 'Alone', '13030', 'Have not exercised', '13040', 'NULL' );
INSERT INTO `question`
VALUES
	( 14, '14000', 'If there is anything else you want to tell us about, please add a tag in the Oura app', '14010', 'Press to', '14020', 'finish', '14030', 'NULL', '14040', 'NULL' );
INSERT INTO `question`
VALUES
	( 15, '15000', 'Question Fifteen?', '15010', 'Ans 15.1', '15020', 'Ans 15.2', '15030', 'Ans 15.3', '15040', 'Ans 15.4' );
INSERT INTO `question`
VALUES
	( 16, '16000', 'Question Sixteen?', '16010', 'Ans 16.1', '16020', 'Ans 16.2', '16030', 'Ans 16.3', '16040', 'Ans 16.4' );
INSERT INTO `question`
VALUES
	( 17, '17000', 'Question Seventeen?', '17010', 'Ans 17.1', '17020', 'Ans 17.2', '17030', 'Ans 17.3', '17040', 'Ans 17.4' );
INSERT INTO `question`
VALUES
	( 18, '18000', 'Question Eighteen?', '18010', 'Ans 18.1', '18020', 'Ans 18.2', '18030', 'Ans 18.3', '18040', 'Ans 18.4' );
INSERT INTO `question`
VALUES
	( 19, '19000', 'Question Nineteen?', '19010', 'Ans 19.1', '19020', 'Ans 19.2', '19030', 'Ans 19.3', '19040', 'Ans 19.4' );
INSERT INTO `question`
VALUES
	( 20, '20000', 'Question Twenty?', '20010', 'Ans 20.1', '20020', 'Ans 20.2', '20030', 'Ans 20.3', '20040', 'Ans 20.4' );-- ----------------------------
-- Table structure for response
-- ----------------------------
DROP TABLE
IF
	EXISTS `response`;
CREATE TABLE `response` (
	`_id` INT NOT NULL AUTO_INCREMENT,
	`date` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`time` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`responseTime` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`questionID` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`answerID` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`question` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	`response` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	PRIMARY KEY ( `_id` ) USING BTREE 
) ENGINE = INNODB AUTO_INCREMENT = 13 CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of response
-- ----------------------------
INSERT INTO `response`
VALUES
	( 4, '12.5', 'a', '1', '1', '123', '123', 'asdads' );
INSERT INTO `response`
VALUES
	( 5, '12.5', 'b', '2', '2', '444', '123', 'asdads' );
INSERT INTO `response`
VALUES
	( 6, '12.5', 'c', '3', '3', '333', '123', 'asdads' );
INSERT INTO `response`
VALUES
	( 7, '12.5', 'd', '4', '34', '32', '123', 'asdads' );
INSERT INTO `response`
VALUES
	( 8, '12.5', 'e', 'a5', '1242', '234', '123', 'aa' );
INSERT INTO `response`
VALUES
	( 9, '12.5', 'f', '6', '122344', '3444', '123', 'asdads' );
INSERT INTO `response`
VALUES
	( 10, '12.5', 'g', '7', '12343434', '12333', '123', 'asdads' );
INSERT INTO `response`
VALUES
	( 11, '12.5', 'h', '8', '12343343333', '1233434', '123', 'asdads' );
INSERT INTO `response`
VALUES
	( 12, '12.5', 'i', '9', '22', '12334334', '123', 'asdads' );
INSERT INTO `response`
VALUES
	( 13, 'a', 'a', 'a', 'a', 'a', 'a', 'a' );-- ----------------------------
-- Table structure for t_data_tracer
-- ----------------------------
DROP TABLE
IF
	EXISTS `t_data_tracer`;
CREATE TABLE `t_data_tracer` (
	`data_tracer_id` BIGINT NOT NULL AUTO_INCREMENT,
	`data_id` BIGINT NOT NULL COMMENT '各种单据的id',
	`type` INT NOT NULL COMMENT '单据类型',
	`content` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '操作内容',
	`diff_old` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '差异：旧的数据',
	`diff_new` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '差异：新的数据',
	`extra_data` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '额外信息',
	`user_id` BIGINT NOT NULL COMMENT '用户id',
	`user_type` INT NOT NULL COMMENT '用户类型：1 后管用户 ',
	`user_name` VARCHAR ( 50 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名称',
	`ip` VARCHAR ( 50 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
	`user_agent` VARCHAR ( 2000 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY ( `data_tracer_id` ) USING BTREE,
	INDEX `order_id_order_type` ( `data_id`, `type` ) USING BTREE 
) ENGINE = INNODB AUTO_INCREMENT = 19 CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '各种单据操作记录' ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of t_data_tracer
-- ----------------------------
INSERT INTO `t_data_tracer`
VALUES
	(
		1,
		49,
		2,
		'新增',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 14:27:33',
	'2022-10-22 14:27:33' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		2,
		50,
		2,
		'新增',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 14:29:56',
	'2022-10-22 14:29:56' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		3,
		51,
		2,
		'新增',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 14:30:46',
	'2022-10-22 14:30:46' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		4,
		52,
		2,
		'新增',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 14:33:03',
	'2022-10-22 14:33:03' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		5,
		53,
		2,
		'新增',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 14:34:56',
	'2022-10-22 14:34:56' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		6,
		54,
		2,
		'新增',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 14:36:10',
	'2022-10-22 14:36:10' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		7,
		55,
		2,
		'新增',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 14:37:57',
	'2022-10-22 14:37:57' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		8,
		56,
		2,
		'新增',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 14:40:45',
	'2022-10-22 14:40:45' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		9,
		57,
		2,
		'新增',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 14:46:00',
	'2022-10-22 14:46:00' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		10,
		58,
		2,
		'新增',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 14:47:12',
	'2022-10-22 14:47:12' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		11,
		58,
		2,
		'',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 14:47:26',
	'2022-10-22 14:47:26' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		12,
		59,
		2,
		'新增',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 14:50:12',
	'2022-10-22 14:50:12' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		13,
		17,
		3,
		'新增',
		NULL,
		NULL,
		NULL,
		44,
		1,
		'卓大',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.47',
	'2022-10-22 14:57:36',
	'2022-10-22 14:57:36' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		14,
		18,
		3,
		'新增',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 17:03:35',
	'2022-10-22 17:03:35' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		15,
		2,
		3,
		'新增银行:<br/>银行信息ID:26<br/>账户名称:\"1024创新实验室\"<br/>禁用状态:false<br/>开户银行:\"工商银行\"<br/>备注:\"基本户\"<br/>账号:\"1024\"<br/>是否对公:true',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 17:58:43',
	'2022-10-22 17:58:43' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		16,
		2,
		3,
		'新增银行:<br/>银行信息ID:27<br/>账户名称:\"1024创新实验室\"<br/>禁用状态:false<br/>开户银行:\"建设银行\"<br/>备注:\"其他户\"<br/>账号:\"10241\"<br/>是否对公:false',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 17:59:19',
	'2022-10-22 17:59:19' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		17,
		2,
		3,
		'新增发票：<br/>禁用状态:false<br/>开户行:\"中国银行\"<br/>备注:\"\"<br/>银行账户:\"1024lab\"<br/>开票抬头:\"1024创新实验室\"<br/>纳税人识别号:\"1024lab\"',
		NULL,
		NULL,
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 17:59:35',
	'2022-10-22 17:59:35' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		18,
		2,
		3,
		'修改企业信息',
		'统一社会信用代码:\"1024lab\"<br/>详细地址:\"1024大楼\"<br/>区县名称:\"洛龙区\"<br/>禁用状态:false<br/>类型:有限企业<br/>城市名称:\"洛阳市\"<br/>删除状态:false<br/>联系人:\"卓大\"<br/>省份名称:\"河南省\"<br/>企业logo:\"public/common/fb827d63dda74a60ab8b4f70cc7c7d0a_20221022145641_jpg\"<br/>联系人电话:\"18637925892\"<br/>企业名称:\"1024创新实验室\"<br/>邮箱:\"lab1024@163.com\"',
		'统一社会信用代码:\"1024lab\"<br/>详细地址:\"1024大楼\"<br/>区县名称:\"洛龙区\"<br/>禁用状态:false<br/>类型:有限企业<br/>城市名称:\"洛阳市\"<br/>删除状态:false<br/>联系人:\"卓大\"<br/>省份名称:\"河南省\"<br/>企业logo:\"public/common/fb827d63dda74a60ab8b4f70cc7c7d0a_20221022145641_jpg\"<br/>联系人电话:\"18637925892\"<br/>企业名称:\"1024创新实验室1\"<br/>邮箱:\"lab1024@163.com\"',
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 17:59:49',
	'2022-10-22 17:59:49' 
);
INSERT INTO `t_data_tracer`
VALUES
	(
		19,
		2,
		3,
		'修改企业信息',
		'统一社会信用代码:\"1024lab\"<br/>详细地址:\"1024大楼\"<br/>区县名称:\"洛龙区\"<br/>禁用状态:false<br/>类型:有限企业<br/>城市名称:\"洛阳市\"<br/>删除状态:false<br/>联系人:\"卓大\"<br/>省份名称:\"河南省\"<br/>企业logo:\"public/common/fb827d63dda74a60ab8b4f70cc7c7d0a_20221022145641_jpg\"<br/>联系人电话:\"18637925892\"<br/>企业名称:\"1024创新实验室1\"<br/>邮箱:\"lab1024@163.com\"',
		'统一社会信用代码:\"1024lab\"<br/>详细地址:\"1024大楼\"<br/>区县名称:\"洛龙区\"<br/>禁用状态:false<br/>类型:有限企业<br/>城市名称:\"洛阳市\"<br/>删除状态:false<br/>联系人:\"卓大\"<br/>省份名称:\"河南省\"<br/>企业logo:\"public/common/fb827d63dda74a60ab8b4f70cc7c7d0a_20221022145641_jpg\"<br/>联系人电话:\"18637925892\"<br/>企业名称:\"1024创新实验室\"<br/>邮箱:\"lab1024@163.com\"',
		NULL,
		1,
		1,
		'管理员',
		'127.0.0.1',
		'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36',
	'2022-10-22 17:59:52',
	'2022-10-22 17:59:52' 
);-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE
IF
	EXISTS `t_menu`;
CREATE TABLE `t_menu` (
	`menu_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
	`menu_name` VARCHAR ( 200 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
	`menu_type` INT NOT NULL COMMENT '类型',
	`parent_id` BIGINT NOT NULL COMMENT '父菜单ID',
	`sort` INT NULL DEFAULT NULL COMMENT '显示顺序',
	`path` VARCHAR ( 255 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
	`component` VARCHAR ( 255 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
	`perms_type` INT NULL DEFAULT NULL COMMENT '权限类型',
	`api_perms` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '后端权限字符串',
	`web_perms` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '前端权限字符串',
	`icon` VARCHAR ( 100 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
	`context_menu_id` BIGINT NULL DEFAULT NULL COMMENT '功能点关联菜单ID',
	`frame_flag` TINYINT ( 1 ) NOT NULL DEFAULT 0 COMMENT '是否为外链',
	`frame_url` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '外链地址',
	`cache_flag` TINYINT ( 1 ) NOT NULL DEFAULT 0 COMMENT '是否缓存',
	`visible_flag` TINYINT ( 1 ) NOT NULL DEFAULT 1 COMMENT '显示状态',
	`disabled_flag` TINYINT ( 1 ) NOT NULL DEFAULT 0 COMMENT '禁用状态',
	`deleted_flag` TINYINT ( 1 ) NOT NULL DEFAULT 0 COMMENT '删除状态',
	`create_user_id` BIGINT NOT NULL COMMENT '创建人',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_user_id` BIGINT NULL DEFAULT NULL COMMENT '更新人',
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY ( `menu_id` ) USING BTREE 
) ENGINE = INNODB AUTO_INCREMENT = 212 CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu`
VALUES
	(
		26,
		'菜单管理',
		2,
		50,
		0,
		'/menu/list',
		'/system/menu/menu-list.vue',
		NULL,
		NULL,
		NULL,
		'CopyOutlined',
		NULL,
		0,
		NULL,
		1,
		0,
		0,
		0,
		2,
		'2021-08-09 15:04:35',
		1,
		'2023-08-07 16:32:51' 
	);
INSERT INTO `t_menu`
VALUES
	(
		40,
		'批量删除',
		3,
		26,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:menu:batch:delete',
		NULL,
		26,
		0,
		NULL,
		0,
		0,
		0,
		0,
		1,
		'2021-08-12 09:45:56',
		1,
		'2023-08-07 16:32:53' 
	);
INSERT INTO `t_menu`
VALUES
	(
		45,
		'User management',
		1,
		0,
		100,
		'/organization',
		NULL,
		NULL,
		NULL,
		NULL,
		'UserSwitchOutlined',
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2021-08-12 16:13:27',
		44,
		'2023-08-08 14:52:09' 
	);
INSERT INTO `t_menu`
VALUES
	(
		46,
		'User',
		2,
		45,
		1,
		'/user/department',
		'/system/user/department/index.vue',
		NULL,
		NULL,
		NULL,
		'AuditOutlined',
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2021-08-12 16:21:50',
		1,
		'2023-08-08 14:52:04' 
	);
INSERT INTO `t_menu`
VALUES
	(
		76,
		'Role',
		2,
		45,
		2,
		'/user/role',
		'/system/user/role/index.vue',
		NULL,
		NULL,
		NULL,
		'SlidersOutlined',
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2021-08-26 10:31:00',
		1,
		'2023-08-08 12:36:05' 
	);
INSERT INTO `t_menu`
VALUES
	(
		91,
		'Add user',
		3,
		46,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:user:add',
		NULL,
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2022-05-27 00:11:38',
		NULL,
		'2023-08-08 14:52:32' 
	);
INSERT INTO `t_menu`
VALUES
	(
		92,
		'Edit user',
		3,
		46,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:user:update',
		NULL,
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2022-05-27 00:12:10',
		NULL,
		'2023-08-08 14:52:37' 
	);
INSERT INTO `t_menu`
VALUES
	(
		93,
		'Disable/Enable user',
		3,
		46,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:user:disabled',
		NULL,
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2022-05-27 00:12:37',
		NULL,
		'2023-08-08 14:52:43' 
	);
INSERT INTO `t_menu`
VALUES
	(
		95,
		'Reset password',
		3,
		46,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:user:password:reset',
		NULL,
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2022-05-27 00:13:30',
		1,
		'2023-08-08 14:52:47' 
	);
INSERT INTO `t_menu`
VALUES
	(
		96,
		'Delete user',
		3,
		46,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:user:delete',
		NULL,
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2022-05-27 00:14:08',
		NULL,
		'2023-08-08 14:52:52' 
	);
INSERT INTO `t_menu`
VALUES
	(
		97,
		'Add role',
		3,
		76,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:role:add',
		NULL,
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2022-05-27 00:34:00',
		1,
		'2023-08-08 14:52:56' 
	);
INSERT INTO `t_menu`
VALUES
	(
		98,
		'Delete role',
		3,
		76,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:role:delete',
		NULL,
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2022-05-27 00:34:19',
		1,
		'2023-08-08 14:53:02' 
	);
INSERT INTO `t_menu`
VALUES
	(
		99,
		'Edit role',
		3,
		76,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:role:update',
		NULL,
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2022-05-27 00:34:55',
		NULL,
		'2023-08-08 14:53:06' 
	);
INSERT INTO `t_menu`
VALUES
	(
		101,
		'Batch delete role',
		3,
		76,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:role:user:batch:delete',
		NULL,
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2022-05-27 00:39:05',
		NULL,
		'2023-08-08 14:53:19' 
	);
INSERT INTO `t_menu`
VALUES
	(
		102,
		'Delete user',
		3,
		76,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:role:user:delete',
		NULL,
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2022-05-27 00:39:21',
		NULL,
		'2023-08-08 14:53:52' 
	);
INSERT INTO `t_menu`
VALUES
	(
		103,
		'Add user',
		3,
		76,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:role:user:add',
		NULL,
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2022-05-27 00:39:38',
		NULL,
		'2023-08-08 14:53:48' 
	);
INSERT INTO `t_menu`
VALUES
	(
		104,
		'Change authority',
		3,
		76,
		NULL,
		NULL,
		NULL,
		1,
		NULL,
		'system:role:menu:update',
		NULL,
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2022-05-27 00:41:55',
		NULL,
		'2023-08-08 14:53:57' 
	);
INSERT INTO `t_menu`
VALUES
	(
		208,
		'Response',
		2,
		0,
		1,
		'/response',
		'smartWatch/response/response-list.vue',
		1,
		NULL,
		NULL,
		'AlignCenterOutlined',
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2023-07-01 13:08:07',
		NULL,
		'2023-07-01 13:08:07' 
	);
INSERT INTO `t_menu`
VALUES
	(
		209,
		'Question',
		2,
		0,
		1,
		'/question',
		'smartWatch/question/question-list.vue',
		1,
		NULL,
		NULL,
		'AlignCenterOutlined',
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2023-08-07 20:11:06',
		NULL,
		'2023-08-07 20:11:06' 
	);
INSERT INTO `t_menu`
VALUES
	(
		210,
		'DataItem',
		2,
		0,
		1,
		'/dataItem',
		'smartWatch/dataItem/dataItem-list.vue',
		1,
		NULL,
		NULL,
		'AlignCenterOutlined',
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2023-08-07 20:11:43',
		NULL,
		'2023-08-07 20:11:43' 
	);
INSERT INTO `t_menu`
VALUES
	(
		211,
		'Parameter',
		2,
		0,
		1,
		'/parameter',
		'smartWatch/parameter/parameter-list.vue',
		1,
		NULL,
		NULL,
		'AlignCenterOutlined',
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2023-08-07 20:12:25',
		NULL,
		'2023-08-07 20:12:25' 
	);
INSERT INTO `t_menu`
VALUES
	(
		212,
		'Event',
		2,
		0,
		1,
		'/event',
		'smartWatch/event/event-list.vue',
		1,
		NULL,
		NULL,
		'AlignCenterOutlined',
		NULL,
		0,
		NULL,
		0,
		1,
		0,
		0,
		1,
		'2023-08-07 20:16:41',
		NULL,
		'2023-08-07 20:16:41' 
	);-- ----------------------------
-- Table structure for t_reload_result
-- ----------------------------
DROP TABLE
IF
	EXISTS `t_reload_result`;
CREATE TABLE `t_reload_result` (
	`tag` VARCHAR ( 255 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
	`identification` VARCHAR ( 255 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '运行标识',
	`args` VARCHAR ( 255 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
	`result` TINYINT UNSIGNED NOT NULL COMMENT '是否成功 ',
	`exception` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP 
) ENGINE = INNODB CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'reload结果' ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of t_reload_result
-- ----------------------------
-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE
IF
	EXISTS `t_role`;
CREATE TABLE `t_role` (
	`role_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
	`role_name` VARCHAR ( 20 ) CHARACTER 
	SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
	`remark` VARCHAR ( 255 ) CHARACTER 
	SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '角色描述',
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY ( `role_id` ) USING BTREE 
) ENGINE = INNODB AUTO_INCREMENT = 37 CHARACTER 
SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role`
VALUES
	( 1, '技术总监', '', '2022-10-19 20:24:09', '2019-06-21 12:09:34' );
INSERT INTO `t_role`
VALUES
	( 34, '销售总监', '', '2022-10-19 20:24:28', '2019-08-30 09:30:50' );
INSERT INTO `t_role`
VALUES
	( 35, '总经理', '', '2019-08-30 09:31:05', '2019-08-30 09:31:05' );
INSERT INTO `t_role`
VALUES
	( 36, '董事长', '', '2019-08-30 09:31:11', '2019-08-30 09:31:11' );
INSERT INTO `t_role`
VALUES
	( 37, '财务', '', '2019-08-30 09:31:16', '2019-08-30 09:31:16' );-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE
IF
	EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
	`role_menu_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`role_id` BIGINT NOT NULL COMMENT '角色id',
	`menu_id` BIGINT NOT NULL COMMENT '菜单id',
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY ( `role_menu_id` ) USING BTREE,
	INDEX `idx_role_id` ( `role_id` ) USING BTREE,
	INDEX `idx_menu_id` ( `menu_id` ) USING BTREE 
) ENGINE = INNODB AUTO_INCREMENT = 34 CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色-菜单\n' ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu`
VALUES
	( 1, 1, 45, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 2, 1, 46, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 3, 1, 86, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 4, 1, 87, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 5, 1, 88, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 6, 1, 91, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 7, 1, 92, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 8, 1, 93, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 9, 1, 94, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 10, 1, 95, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 11, 1, 96, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 12, 1, 76, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 13, 1, 97, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 14, 1, 98, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 15, 1, 99, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 16, 1, 100, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 17, 1, 101, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 18, 1, 102, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 19, 1, 103, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 20, 1, 104, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 21, 1, 26, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 22, 1, 40, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 23, 1, 105, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 24, 1, 106, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 25, 1, 108, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 26, 1, 50, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 27, 1, 130, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 28, 1, 157, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 29, 1, 158, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 30, 1, 193, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 31, 1, 200, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 32, 1, 122, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 33, 1, 111, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );
INSERT INTO `t_role_menu`
VALUES
	( 34, 1, 143, '2022-10-22 10:54:27', '2022-10-22 10:54:27' );-- ----------------------------
-- Table structure for t_role_user
-- ----------------------------
DROP TABLE
IF
	EXISTS `t_role_user`;
CREATE TABLE `t_role_user` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`role_id` BIGINT NOT NULL COMMENT '角色id',
	`user_id` BIGINT NOT NULL COMMENT '员工id',
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY ( `id` ) USING BTREE,
	UNIQUE INDEX `uk_role_employee` ( `role_id`, `user_id` ) USING BTREE 
) ENGINE = INNODB AUTO_INCREMENT = 326 CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色员工功能表' ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of t_role_user
-- ----------------------------
INSERT INTO `t_role_user`
VALUES
	( 325, 36, 63, '2022-10-19 20:25:26', '2022-10-19 20:25:26' );
INSERT INTO `t_role_user`
VALUES
	( 326, 1, 44, '2022-10-22 10:54:35', '2022-10-22 10:54:35' );-- ----------------------------
-- Table structure for t_table_column
-- ----------------------------
DROP TABLE
IF
	EXISTS `t_table_column`;
CREATE TABLE `t_table_column` (
	`table_column_id` BIGINT NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT NOT NULL COMMENT '用户id',
	`user_type` INT NOT NULL COMMENT '用户类型',
	`table_id` INT NOT NULL COMMENT '表格id',
	`columns` TEXT CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '具体的表格列，存入的json',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY ( `table_column_id` ) USING BTREE,
	UNIQUE INDEX `uni_employee_table` ( `user_id`, `table_id` ) USING BTREE 
) ENGINE = INNODB CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '表格的自定义列存储' ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of t_table_column
-- ----------------------------
-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE
IF
	EXISTS `t_user`;
CREATE TABLE `t_user` (
	`user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
	`login_name` VARCHAR ( 30 ) CHARACTER 
	SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '登录帐号',
	`login_pwd` VARCHAR ( 50 ) CHARACTER 
	SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '登录密码',
	`actual_name` VARCHAR ( 30 ) CHARACTER 
	SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '员工名称',
	`gender` TINYINT ( 1 ) NOT NULL DEFAULT 0 COMMENT '性别',
	`phone` VARCHAR ( 15 ) CHARACTER 
	SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号码',
	`department_id` INT NOT NULL COMMENT '部门id',
	`disabled_flag` TINYINT UNSIGNED NOT NULL COMMENT '是否被禁用 0否1是',
	`deleted_flag` TINYINT UNSIGNED NOT NULL COMMENT '是否删除0否 1是',
	`administrator_flag` TINYINT NOT NULL DEFAULT 0 COMMENT '是否为超级管理员: 0 不是，1是',
	`remark` VARCHAR ( 200 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY ( `user_id` ) USING BTREE 
) ENGINE = INNODB AUTO_INCREMENT = 69 CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '员工表' ROW_FORMAT = DYNAMIC;-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user`
VALUES
	( 1, 'admin', '40cc20b8891cd3fd1f008ea7f4ac17c3', '管理员', 0, '13500000000', 1, 0, 0, 1, NULL, '2022-10-22 19:33:02', '2018-05-11 09:38:54' );
INSERT INTO `t_user`
VALUES
	( 2, 'huke', '40cc20b8891cd3fd1f008ea7f4ac17c3', '胡克', 0, '13123123121', 1, 0, 0, 0, NULL, '2022-10-19 20:17:30', '2021-07-29 11:24:55' );
INSERT INTO `t_user`
VALUES
	( 44, 'zhuoda', '40cc20b8891cd3fd1f008ea7f4ac17c3', '卓大', 1, '18637925892', 1, 0, 0, 1, NULL, '2022-10-22 14:29:04', '2021-08-11 10:04:53' );
INSERT INTO `t_user`
VALUES
	( 47, 'shanyi', '40cc20b8891cd3fd1f008ea7f4ac17c3', '善逸', 1, '13123111123', 1, 0, 0, 0, NULL, '2022-10-19 20:19:50', '2021-08-16 17:14:55' );
INSERT INTO `t_user`
VALUES
	( 48, 'qinjiu', '40cc20b8891cd3fd1f008ea7f4ac17c3', '琴酒', 2, '14112343212', 2, 0, 0, 0, NULL, '2022-10-19 20:23:40', '2021-08-17 10:29:41' );
INSERT INTO `t_user`
VALUES
	( 63, 'kaiyun', '40cc20b8891cd3fd1f008ea7f4ac17c3', '开云', 0, '13112312346', 2, 0, 0, 0, NULL, '2022-10-19 20:23:40', '2022-06-03 22:41:55' );
INSERT INTO `t_user`
VALUES
	( 64, 'qingye', '40cc20b8891cd3fd1f008ea7f4ac17c3', '清野', 1, '13123123111', 2, 0, 0, 0, NULL, '2022-10-19 20:23:40', '2022-06-16 17:19:08' );
INSERT INTO `t_user`
VALUES
	( 65, 'feiye', '40cc20b8891cd3fd1f008ea7f4ac17c3', '飞叶', 1, '13123123112', 1, 0, 0, 0, NULL, '2022-09-15 16:51:09', '2022-06-16 17:24:18' );
INSERT INTO `t_user`
VALUES
	( 66, 'luoyi', '40cc20b8891cd3fd1f008ea7f4ac17c3', '罗伊', 1, '13123123142', 1, 1, 0, 0, NULL, '2022-09-15 16:51:19', '2022-06-16 17:24:56' );
INSERT INTO `t_user`
VALUES
	( 67, 'chuxiao', '7287168489ed5598741362cbec2b0741', '初晓', 1, '13123123123', 1, 0, 0, 0, NULL, '2022-09-17 15:42:42', '2022-06-16 17:28:32' );
INSERT INTO `t_user`
VALUES
	( 68, 'xuanpeng', '40cc20b8891cd3fd1f008ea7f4ac17c3', '玄朋', 1, '13123123124', 1, 0, 0, 0, NULL, '2022-09-15 16:51:43', '2022-06-16 17:30:17' );
INSERT INTO `t_user`
VALUES
	( 69, 'peixian', '40cc20b8891cd3fd1f008ea7f4ac17c3', '佩弦', 1, '18377482773', 1, 0, 0, 0, NULL, '2022-10-19 20:17:35', '2022-06-25 16:42:52' );

SET FOREIGN_KEY_CHECKS = 1;-- ----------------------------
-- Table structure for t_user_db
-- ----------------------------
DROP TABLE
IF
	EXISTS `t_user_db`;
CREATE TABLE `t_user_db` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT NOT NULL COMMENT '用户id',
	`file` VARCHAR(255) NOT NULL COMMENT '文件名字',
	`file_data` LONGBLOB NOT NULL COMMENT '文件数据',
	PRIMARY KEY ( `id` ) USING BTREE,
	UNIQUE INDEX `uni_user_table` ( `user_id`, `file` ) USING BTREE 
) ENGINE = INNODB CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户数据库储存' ROW_FORMAT = DYNAMIC;