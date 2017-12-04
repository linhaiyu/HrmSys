/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : tiger

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2017-12-04 13:14:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dept_inf`
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept_inf
-- ----------------------------
INSERT INTO `dept_inf` VALUES ('1', '产品研发部', '产品研发技术部');
INSERT INTO `dept_inf` VALUES ('2', '运营部', '运营部');
INSERT INTO `dept_inf` VALUES ('3', '财务部', '财务部');
INSERT INTO `dept_inf` VALUES ('5', '人力资源部', '人力资源部');
INSERT INTO `dept_inf` VALUES ('7', '维护部', '产品维护部');
INSERT INTO `dept_inf` VALUES ('8', '部门A', '部门A');

-- ----------------------------
-- Table structure for `document_inf`
-- ----------------------------
DROP TABLE IF EXISTS `document_inf`;
CREATE TABLE `document_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `filename` varchar(300) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_document_user` (`userId`),
  CONSTRAINT `fk_document_user` FOREIGN KEY (`userId`) REFERENCES `user_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of document_inf
-- ----------------------------
INSERT INTO `document_inf` VALUES ('2', '人事管理系统', '人事管理系统.xmind', '人事管理系统设计文档', '2017-11-30 15:41:45', '2');
INSERT INTO `document_inf` VALUES ('3', '常用头像', '多多证件照2017-10-8.jpg', '常用头像2', '2017-11-30 15:51:59', '2');
INSERT INTO `document_inf` VALUES ('4', '昆工二期需求流程图', '昆工二期需求流程图.vsdx', '昆工二期需求流程图', '2017-11-30 15:52:58', '2');
INSERT INTO `document_inf` VALUES ('5', '鲁大师', '2017-12-1 鲁大师2.jpg', '鲁大师检测结果，三星8G内存X2', '2017-12-01 23:39:26', '2');
INSERT INTO `document_inf` VALUES ('6', 'Tianmao zip', 'Tianmao.rar', 'Tianmao zip', '2017-12-01 23:41:07', '2');

-- ----------------------------
-- Table structure for `employee_inf`
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `cardId` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `sex` int(11) NOT NULL DEFAULT '1',
  `birthday` datetime DEFAULT NULL,
  `education` varchar(20) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_emp_dept` (`dept_id`),
  KEY `fk_emp_job` (`job_id`),
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`dept_id`) REFERENCES `dept_inf` (`id`),
  CONSTRAINT `fk_emp_job` FOREIGN KEY (`job_id`) REFERENCES `job_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_inf
-- ----------------------------
INSERT INTO `employee_inf` VALUES ('1', '1', '6', 'Peter', '51340119881108001x', '大石西路23号', '13981819292', '654123', 'Peterluo@mx.com', '1', '2017-11-10 14:22:11', '本科', '好好学习，天天向上');
INSERT INTO `employee_inf` VALUES ('3', '1', '12', '多多', '51340119781108001X', '成都市蜀汉路49号', '13981819292', '51345689', 'duoduo@max.com', '1', '2012-11-22 00:00:00', '博士', '多多臭娃娃');
INSERT INTO `employee_inf` VALUES ('4', '2', '11', '沉沉小筠筠', '51340119881218002x', '成都市蜀汉路49号金域港湾', '13456898754', '51345689', 'cj@max.com', '2', '2017-11-08 00:00:00', 'doctor', '厉害无比');
INSERT INTO `employee_inf` VALUES ('5', '8', '22', '员工A', '51340119781108001X', 'abc', '13456898754', '51345689', 'master@max.com', '2', '2017-12-01 00:00:00', 'master', 'abc');

-- ----------------------------
-- Table structure for `job_inf`
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE `job_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_inf
-- ----------------------------
INSERT INTO `job_inf` VALUES ('1', 'Java初级开发工程师', 'Java开发工程师.初级');
INSERT INTO `job_inf` VALUES ('2', 'Java中级开发工程师', 'Java中级开发工程师');
INSERT INTO `job_inf` VALUES ('3', 'Java高级开发工程师', 'Java高级开发工程师');
INSERT INTO `job_inf` VALUES ('5', '系统架构师', '系统架构师');
INSERT INTO `job_inf` VALUES ('6', '数据库开发工程师', '数据库开发工程师');
INSERT INTO `job_inf` VALUES ('7', '前端初级开发工程师', '前端初级开发工程师');
INSERT INTO `job_inf` VALUES ('8', '前端中级开发工程师', '前端中级开发工程师');
INSERT INTO `job_inf` VALUES ('9', '前端高级开发工程师', '前端高级开发工程师');
INSERT INTO `job_inf` VALUES ('10', '项目经理', '项目经理');
INSERT INTO `job_inf` VALUES ('11', '产品经理', '产品经理');
INSERT INTO `job_inf` VALUES ('12', '研发部主管', '研发部主管');
INSERT INTO `job_inf` VALUES ('13', '测试工程师', '测试工程师');
INSERT INTO `job_inf` VALUES ('14', '测试高级工程师', '测试高级工程师');
INSERT INTO `job_inf` VALUES ('15', '质量管理员', '质量管理员');
INSERT INTO `job_inf` VALUES ('16', '配置管理员', '配置管理员');
INSERT INTO `job_inf` VALUES ('17', '行政秘书', '行政秘书');
INSERT INTO `job_inf` VALUES ('18', '客户经理', '客户经理');
INSERT INTO `job_inf` VALUES ('19', '技术支持工程师', '技术支持工程师');
INSERT INTO `job_inf` VALUES ('20', '解决方案工程师', '解决方案工程师');
INSERT INTO `job_inf` VALUES ('21', 'C#初级工程师', 'C#初级工程师');
INSERT INTO `job_inf` VALUES ('22', '职位A', '职位A');

-- ----------------------------
-- Table structure for `notice_inf`
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE `notice_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notice_user` (`userId`),
  CONSTRAINT `fk_notice_user` FOREIGN KEY (`userId`) REFERENCES `user_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice_inf
-- ----------------------------
INSERT INTO `notice_inf` VALUES ('1', '圣诞节放假通知', '圣诞节放假三天，特此通知！\r\n如需加班，请提前申请，谢谢！', '2017-11-30 11:19:56', '2');
INSERT INTO `notice_inf` VALUES ('4', '技术大比武通知', '研发部门将在2018年一季度开展技术大比武，欢迎技术部门的员工踊跃参加！具体细则稍后另行通知！', '2017-11-30 11:20:50', '2');
INSERT INTO `notice_inf` VALUES ('5', '年度体检', '2018年1月10日——2018年1月20日，我司将开展公司年度体检，体检单位为爱康国宾，具体体检安排将由各个部门发送通知。届时请员工根据部门安排，提前预约，按时完成体检。', '2017-11-30 11:26:15', '2');
INSERT INTO `notice_inf` VALUES ('6', '下午茶', '增加下午茶，天天吃开心。', '2017-11-30 15:37:34', '2');

-- ----------------------------
-- Table structure for `train_inf`
-- ----------------------------
DROP TABLE IF EXISTS `train_inf`;
CREATE TABLE `train_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `remark` varchar(300) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_train_emp` (`employee_id`),
  CONSTRAINT `fk_train_emp` FOREIGN KEY (`employee_id`) REFERENCES `employee_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of train_inf
-- ----------------------------
INSERT INTO `train_inf` VALUES ('1', 'Java Web开发从入门到精通', 'Java Web开发从入门到精通，Java Web开发从入门到精通，Java Web开发从入门到精通', '1', '2017-12-01 11:50:05');
INSERT INTO `train_inf` VALUES ('2', 'SpringMVC从入门到精通', '讲解SpringMVC从入门到精通', '3', '2017-12-01 17:23:16');
INSERT INTO `train_inf` VALUES ('4', 'C#开发从入门到精通', 'C#开发从入门到精通', '3', '2017-12-01 11:51:17');
INSERT INTO `train_inf` VALUES ('5', 'Node.JS开发从入门到精通', 'Node.JS开发从入门到精通；Node.JS开发从入门到精通', '3', '2017-12-01 17:23:12');
INSERT INTO `train_inf` VALUES ('6', '从零开始学炒股', '从零开始学炒股', '4', '2017-12-01 11:51:48');
INSERT INTO `train_inf` VALUES ('7', '英语情景口语100讲', '英语情景口语100讲', '3', '2017-12-01 11:52:04');
INSERT INTO `train_inf` VALUES ('8', '培训A', '培训A', '5', '2017-12-01 23:00:28');

-- ----------------------------
-- Table structure for `user_inf`
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(20) NOT NULL,
  `password` varchar(16) NOT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES ('2', 'abc', 'abc', '林多多', '2017-11-28 13:43:39', '1');
INSERT INTO `user_inf` VALUES ('3', 'cxj', 'cxj', '陈陈小筠筠', '2017-11-29 12:44:57', '2');
INSERT INTO `user_inf` VALUES ('8', 'bbb', 'bbb', 'bbb', '2017-11-29 12:45:51', '1');
INSERT INTO `user_inf` VALUES ('9', 'aaa', 'aaa', 'aaa', '2017-11-29 12:46:00', '1');
