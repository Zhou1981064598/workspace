/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80023
Source Host           : localhost:3306
Source Database       : students_db

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2022-05-27 14:26:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_building
-- ----------------------------
DROP TABLE IF EXISTS `t_building`;
CREATE TABLE `t_building` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `building_num` varchar(32) NOT NULL COMMENT '宿舍楼号',
  `floor` varchar(10) NOT NULL COMMENT '宿舍楼层',
  `room_num` varchar(10) NOT NULL COMMENT '宿舍房间号',
  `room_type` int NOT NULL COMMENT '宿舍类型：1单人间，2双人间，4四人间，6六人间',
  `is_bathroom` int DEFAULT NULL COMMENT '是否独立卫浴：0是；1否',
  `is_air` int DEFAULT NULL COMMENT '是否有空调：0是；1否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_building
-- ----------------------------
INSERT INTO `t_building` VALUES ('1', 'H5', '4', '403', '6', '0', '0');
INSERT INTO `t_building` VALUES ('2', 'H5', '4', '427', '6', '0', '0');

-- ----------------------------
-- Table structure for t_score
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `score` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '分数',
  `subject` int unsigned NOT NULL COMMENT '科目',
  `sno` varchar(32) NOT NULL COMMENT '学号',
  `syear` varchar(50) NOT NULL COMMENT '学年学期',
  PRIMARY KEY (`id`),
  KEY `sno` (`sno`),
  KEY `subject` (`subject`),
  CONSTRAINT `t_score_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `t_student` (`stu_no`),
  CONSTRAINT `t_score_ibfk_2` FOREIGN KEY (`subject`) REFERENCES `t_subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_score
-- ----------------------------
INSERT INTO `t_score` VALUES ('1', '66.00', '3', '202103410226', '2021-2022学年第2学期');
INSERT INTO `t_score` VALUES ('2', '80.50', '4', '202103410226', '2021-2022学年第2学期');
INSERT INTO `t_score` VALUES ('3', '78.00', '2', '202103410226', '2021-2022学年第2学期');
INSERT INTO `t_score` VALUES ('4', '90.00', '1', '202103410226', '2021-2022学年第2学期');
INSERT INTO `t_score` VALUES ('5', '90.00', '1', '202103410203', '2021-2022学年第2学期');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(50) NOT NULL COMMENT '学生姓名',
  `stu_no` varchar(32) NOT NULL COMMENT '学号',
  `id_card` varchar(32) NOT NULL COMMENT '身份证号',
  `sex` int NOT NULL COMMENT '性别：0男，1女',
  `prov` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `stu_tel` varchar(20) DEFAULT NULL COMMENT '手机号',
  `stu_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `stu_people` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名族：Han (汉族)、 Zhuang (壮族)、 Manchu (满族)、 Hui (回族)、 Miao (苗族) 、(Hmong) Uighur (维吾尔族)、 Yi (彝族)、 Tujia (土家族) 、Mongol (蒙古族) 、Tibetan (藏族) 、Buyi (布依族) Dong (侗族)、 Yao (瑶族) 、Korean (朝鲜族)、 Bai (白族) 、Hani (哈尼族) 、Li (黎族) 、Kazakh (哈萨克族)、Dai (傣族, also called Dai Lue, one of the Thai ethnic groups)、 She (畲族) 、Lisu (僳僳族) Gelao (仡佬族)、Lahu (拉祜族) 、Dongxiang (东乡族) 、Wa (佤族) 、(Va) Shui (水族) 、Naxi (纳西族) (includes the Mosuo (摩梭))、 Qiang (羌族) 、Du (土族)、Xibe (锡伯族) 、Mulam (仫佬族)、 Kirghiz (柯尔克孜族)、 Daur (达斡尔族) 、Jingpo (景颇族) 、Salar (撒拉族) Blang (布朗族 Bulang)、 Maonan (毛南族) 、Tajik (塔吉克族)、 Pumi (普米族) 、Achang (阿昌族)、Nu (怒族) 、Evenki (鄂温克族)、 Gin (京族 Jing1) 、Jino (基诺族) 、De''ang (德昂族)、Uzbek (乌孜别克族)、 Russian (俄罗斯族)、 Yugur (裕固族) 、Bonan (保安族)、 Menba (门巴族) 、Oroqin (鄂伦春族) 、Drung (独龙族) 、Tatar (塔塔尔族) 、Hezhen (赫哲族) 、Lhoba (珞巴族) 、Gaoshan (高山族)',
  PRIMARY KEY (`id`),
  KEY `stu_no` (`stu_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('1', '高天', '202103410203', '130223200010101213', '0', '河北省', '唐山市', '丰南区XXX小区', '13080882303', '123456@qq.com', 'Han');
INSERT INTO `t_student` VALUES ('2', '吴海波', '202103410230', '130223200102031234', '0', '河北省', '唐山市', '丰润区XXX小区', '19931399942', '1234567890@qq.com', 'Han');
INSERT INTO `t_student` VALUES ('3', '崔海涛', '202103410226', '130223200101311234', '0', '河北省', '秦皇岛市', '海港区', '19931399942', '243569@qq.com', 'Han');

-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '科目名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_subject
-- ----------------------------
INSERT INTO `t_subject` VALUES ('1', 'Java面向对象程序设计');
INSERT INTO `t_subject` VALUES ('2', 'JavaScript页面特效设计');
INSERT INTO `t_subject` VALUES ('3', 'Java高级特性');
INSERT INTO `t_subject` VALUES ('4', '项目实训（二）');
INSERT INTO `t_subject` VALUES ('5', 'HTML');
INSERT INTO `t_subject` VALUES ('6', '计算机基础');
INSERT INTO `t_subject` VALUES ('7', 'Java基础');
INSERT INTO `t_subject` VALUES ('8', 'Photoshop');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `user_pwd` varchar(50) NOT NULL COMMENT '密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '小胖子');
INSERT INTO `t_user` VALUES ('2', 'zhangsan', '202cb962ac59075b964b07152d234b70', '三三');
INSERT INTO `t_user` VALUES ('3', 'lisi', '202cb962ac59075b964b07152d234b70', '思思');
INSERT INTO `t_user` VALUES ('4', 'wangwu', '202cb962ac59075b964b07152d234b70', '五五');
INSERT INTO `t_user` VALUES ('5', 'zhaoliu', '202cb962ac59075b964b07152d234b70', '六六');
INSERT INTO `t_user` VALUES ('6', 'ff', '123456', '峰峰');
INSERT INTO `t_user` VALUES ('7', 'dd', '123456', '试试');
INSERT INTO `t_user` VALUES ('8', 'aa', '123456', '啊啊');
INSERT INTO `t_user` VALUES ('15', '34', 'e807f1fcf82d132f9bb018ca6738a19f', '都是');
INSERT INTO `t_user` VALUES ('19', '45t', 'd8b1227c82bdfd76a8a782a1f4461001', '3245443425');
