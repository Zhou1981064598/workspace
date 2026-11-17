/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : project01

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 02/08/2021 10:45:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attachment_info
-- ----------------------------
DROP TABLE IF EXISTS `attachment_info`;
CREATE TABLE `attachment_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_origin_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `create_person` int(10) UNSIGNED NOT NULL,
  `last_upgrade_time` datetime NOT NULL,
  `last_upgrade_person` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of attachment_info
-- ----------------------------
INSERT INTO `attachment_info` VALUES (2, 'f42c4dd8-02db-4f69-9713-fa92c4486811&《刺客信条：遗弃》作者：[英]奥利弗·波登 夏青 汤姗华译.txt', '《刺客信条：遗弃》作者：[英]奥利弗·波登 夏青 汤姗华译.txt', '2021-07-30 16:19:56', 3, '2021-07-30 16:19:56', 3);
INSERT INTO `attachment_info` VALUES (3, 'd452cf95-3ae6-4c61-87b1-b4da14248f2a&189000313叶伟松.png', '189000313叶伟松.png', '2021-07-30 16:19:56', 3, '2021-07-30 16:19:56', 3);

-- ----------------------------
-- Table structure for class_base_info
-- ----------------------------
DROP TABLE IF EXISTS `class_base_info`;
CREATE TABLE `class_base_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `class_base_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_upgrade_time` datetime NOT NULL,
  `last_upgrade_person` int(10) UNSIGNED NOT NULL,
  `create_time` datetime NOT NULL,
  `create_person` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of class_base_info
-- ----------------------------
INSERT INTO `class_base_info` VALUES (1, '一班', '2021-07-23 14:08:51', 1, '2021-07-23 14:08:54', 1);
INSERT INTO `class_base_info` VALUES (2, '二班', '2021-07-23 14:08:51', 1, '2021-07-23 14:08:54', 1);
INSERT INTO `class_base_info` VALUES (3, '三班', '2021-07-23 14:08:51', 1, '2021-07-23 14:08:54', 1);
INSERT INTO `class_base_info` VALUES (4, '四班', '2021-07-23 14:08:51', 1, '2021-07-23 14:08:54', 1);
INSERT INTO `class_base_info` VALUES (5, '五班', '2021-07-23 14:08:51', 1, '2021-07-23 14:08:54', 1);
INSERT INTO `class_base_info` VALUES (6, '六班', '2021-07-23 14:08:51', 1, '2021-07-23 14:08:54', 1);
INSERT INTO `class_base_info` VALUES (7, '七班', '2021-07-23 14:08:51', 1, '2021-07-23 14:08:54', 1);

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `class_name_id` int(10) UNSIGNED NOT NULL,
  `department_id` int(10) UNSIGNED NOT NULL,
  `major_id` int(10) UNSIGNED NOT NULL,
  `grade_id` int(10) UNSIGNED NOT NULL,
  `teacher_id` int(10) UNSIGNED NOT NULL,
  `class_graduate` tinyint(1) UNSIGNED NOT NULL,
  `last_upgrade_time` datetime NULL DEFAULT NULL,
  `last_upgrade_person` int(10) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `create_person` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_departent`(`department_id`) USING BTREE,
  INDEX `fk_grade`(`grade_id`) USING BTREE,
  INDEX `fk_major`(`major_id`) USING BTREE,
  INDEX `fk_teacher`(`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES (1, 1, 1, 1, 1, 3, 1, '2021-07-23 17:38:42', 1, '2021-07-23 14:15:29', 1);
INSERT INTO `class_info` VALUES (2, 2, 1, 1, 1, 2, 1, '2021-07-23 17:21:18', 1, '2021-07-23 14:46:06', 1);
INSERT INTO `class_info` VALUES (3, 3, 1, 1, 1, 2, 1, '2021-07-23 17:21:18', 1, '2021-07-23 14:51:05', 1);
INSERT INTO `class_info` VALUES (4, 4, 1, 1, 1, 2, 1, '2021-07-23 17:21:18', 1, '2021-07-23 14:53:10', 1);

-- ----------------------------
-- Table structure for course_info
-- ----------------------------
DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_date` date NOT NULL,
  `course_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_teacher_id` int(10) UNSIGNED NOT NULL,
  `course_sign_time` time NOT NULL,
  `course_out_time` time NOT NULL,
  `course_classroom` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_note` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `last_upgrade_time` datetime NOT NULL,
  `last_upgrade_person` int(10) UNSIGNED NOT NULL,
  `create_time` datetime NOT NULL,
  `create_person` int(10) UNSIGNED NOT NULL,
  `course_abandon` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course_info
-- ----------------------------
INSERT INTO `course_info` VALUES (1, '2021-09-01', 'WWII 第二次世界大战', 3, '09:00:00', '12:00:00', '6310', NULL, '2021-07-26 16:03:31', 1, '2021-07-26 16:03:36', 1, 0);
INSERT INTO `course_info` VALUES (2, '2021-07-28', '论语', 2, '08:00:00', '14:00:00', '6110', NULL, '2021-07-27 18:21:43', 1, '2021-07-27 14:37:20', 1, 0);

-- ----------------------------
-- Table structure for department_info
-- ----------------------------
DROP TABLE IF EXISTS `department_info`;
CREATE TABLE `department_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `department_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_upgrade_time` datetime NOT NULL,
  `last_upgrade_person` int(10) UNSIGNED NOT NULL,
  `create_time` datetime NOT NULL,
  `create_person` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of department_info
-- ----------------------------
INSERT INTO `department_info` VALUES (1, '软件学院', '2021-07-23 14:10:51', 1, '2021-07-23 14:10:55', 1);
INSERT INTO `department_info` VALUES (2, '数字媒体艺术学院', '2021-07-23 14:11:20', 1, '2021-07-23 14:11:25', 1);
INSERT INTO `department_info` VALUES (3, '土木学院', '2021-07-23 14:11:48', 1, '2021-07-23 14:11:51', 1);

-- ----------------------------
-- Table structure for grade_info
-- ----------------------------
DROP TABLE IF EXISTS `grade_info`;
CREATE TABLE `grade_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_upgrade_time` datetime NOT NULL,
  `last_upgrade_person` int(10) UNSIGNED NOT NULL,
  `create_time` datetime NOT NULL,
  `create_person` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of grade_info
-- ----------------------------
INSERT INTO `grade_info` VALUES (1, '2018级', '2021-07-23 14:12:23', 1, '2021-07-23 14:12:26', 1);
INSERT INTO `grade_info` VALUES (2, '2019级', '2021-07-23 14:12:37', 1, '2021-07-23 14:12:33', 1);
INSERT INTO `grade_info` VALUES (3, '2020级', '2021-07-23 14:12:52', 1, '2021-07-23 14:12:56', 1);

-- ----------------------------
-- Table structure for group_info
-- ----------------------------
DROP TABLE IF EXISTS `group_info`;
CREATE TABLE `group_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `group_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `group_graduate` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `group_leader_id` int(10) NULL DEFAULT NULL,
  `last_upgrade_time` datetime NOT NULL,
  `last_upgrade_person` int(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_person` int(10) NOT NULL,
  `group_abandon` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of group_info
-- ----------------------------
INSERT INTO `group_info` VALUES (1, '2018级实训1组', 0, 5, '2021-07-28 11:42:07', 1, '2021-07-26 16:57:36', 1, 0);
INSERT INTO `group_info` VALUES (2, '2018级实训2组', 0, NULL, '2021-07-26 17:02:10', 1, '2021-07-26 17:02:10', 1, 0);
INSERT INTO `group_info` VALUES (3, '2018级实训3组', 0, 8, '2021-07-28 14:12:45', 1, '2021-07-28 14:12:49', 1, 0);

-- ----------------------------
-- Table structure for homework_attachment
-- ----------------------------
DROP TABLE IF EXISTS `homework_attachment`;
CREATE TABLE `homework_attachment`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `homework_id` int(10) NOT NULL,
  `attachment_id` int(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of homework_attachment
-- ----------------------------
INSERT INTO `homework_attachment` VALUES (2, 9, 2);
INSERT INTO `homework_attachment` VALUES (3, 9, 3);

-- ----------------------------
-- Table structure for homework_info
-- ----------------------------
DROP TABLE IF EXISTS `homework_info`;
CREATE TABLE `homework_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_id` int(10) UNSIGNED NOT NULL,
  `homework_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `homework_expiry` datetime NOT NULL,
  `homework_type` int(1) UNSIGNED NOT NULL,
  `homework_main` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_upgrade_time` datetime NOT NULL,
  `last_upgrade_person` int(10) UNSIGNED NOT NULL,
  `create_time` datetime NOT NULL,
  `create_person` int(10) UNSIGNED NOT NULL,
  `homework_abandon` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of homework_info
-- ----------------------------
INSERT INTO `homework_info` VALUES (2, 1, '第一次作业', '2021-09-01 12:00:00', 1, '简单描述二战的几个转折点', '2021-07-28 17:12:50', 2, '2021-07-28 17:12:50', 2, 0);
INSERT INTO `homework_info` VALUES (3, 1, '第二次作业', '2021-09-01 12:00:00', 1, '简单描述珊瑚岛海战的战斗过程', '2021-07-29 09:04:55', 2, '2021-07-29 09:04:55', 2, 1);
INSERT INTO `homework_info` VALUES (4, 1, '第三次作业', '2021-09-09 12:00:00', 1, '简单描述中途岛海战的战斗过程', '2021-07-29 09:53:48', 3, '2021-07-29 09:53:48', 3, 1);
INSERT INTO `homework_info` VALUES (5, 2, '第一次作业', '2021-09-09 12:00:00', 1, '默写论语十则', '2021-07-29 10:18:34', 2, '2021-07-29 10:18:34', 2, 0);
INSERT INTO `homework_info` VALUES (6, 2, '第二次作业', '2021-09-10 12:00:00', 1, '论语是谁写的', '2021-07-29 10:45:22', 2, '2021-07-29 10:45:22', 2, 1);
INSERT INTO `homework_info` VALUES (7, 1, '第四次作业', '2021-09-10 12:00:00', 1, '描述中国战场在二战中的作用', '2021-07-30 09:29:22', 2, '2021-07-29 10:58:52', 2, 1);
INSERT INTO `homework_info` VALUES (8, 1, '第五次作业', '2021-12-10 12:00:00', 3, '总结二战于历史长河中的影响', '2021-07-29 13:58:15', 3, '2021-07-29 13:58:15', 3, 1);
INSERT INTO `homework_info` VALUES (9, 1, '最终考核', '2021-12-10 12:00:00', 3, '总结本学期的收获', '2021-07-29 14:02:50', 3, '2021-07-29 14:02:50', 3, 0);

-- ----------------------------
-- Table structure for major_info
-- ----------------------------
DROP TABLE IF EXISTS `major_info`;
CREATE TABLE `major_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `major_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `last_upgrade_time` datetime NOT NULL,
  `last_upgrade_person` int(10) UNSIGNED NOT NULL,
  `create_time` datetime NOT NULL,
  `create_person` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of major_info
-- ----------------------------
INSERT INTO `major_info` VALUES (1, '软件工程', 1, '2021-07-23 14:07:55', 1, '2021-07-23 14:08:12', 1);
INSERT INTO `major_info` VALUES (2, '数字媒体艺术', 2, '2021-07-23 14:13:58', 1, '2021-07-23 14:14:00', 1);
INSERT INTO `major_info` VALUES (3, '土木工程', 3, '2021-07-23 14:13:19', 1, '2021-07-23 14:13:22', 1);

-- ----------------------------
-- Table structure for student_attachment
-- ----------------------------
DROP TABLE IF EXISTS `student_attachment`;
CREATE TABLE `student_attachment`  (
  `id` int(10) UNSIGNED NOT NULL,
  `student_homework_id` int(10) UNSIGNED NOT NULL,
  `attachment_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for student_class
-- ----------------------------
DROP TABLE IF EXISTS `student_class`;
CREATE TABLE `student_class`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `class_id` int(10) UNSIGNED NOT NULL,
  `student_id` int(10) UNSIGNED NOT NULL,
  `student_role` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_class
-- ----------------------------
INSERT INTO `student_class` VALUES (1, 1, 5, 2);
INSERT INTO `student_class` VALUES (4, 1, 6, 1);
INSERT INTO `student_class` VALUES (5, 1, 7, 2);
INSERT INTO `student_class` VALUES (6, 2, 8, 2);

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` int(10) UNSIGNED NOT NULL,
  `course_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES (1, 6, 1);
INSERT INTO `student_course` VALUES (2, 5, 1);
INSERT INTO `student_course` VALUES (3, 7, 1);
INSERT INTO `student_course` VALUES (4, 8, 1);
INSERT INTO `student_course` VALUES (5, 7, 2);
INSERT INTO `student_course` VALUES (6, 8, 2);

-- ----------------------------
-- Table structure for student_group
-- ----------------------------
DROP TABLE IF EXISTS `student_group`;
CREATE TABLE `student_group`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` int(10) UNSIGNED NOT NULL,
  `group_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_group
-- ----------------------------
INSERT INTO `student_group` VALUES (1, 5, 1);
INSERT INTO `student_group` VALUES (2, 6, 2);
INSERT INTO `student_group` VALUES (3, 8, 3);
INSERT INTO `student_group` VALUES (4, 7, 3);

-- ----------------------------
-- Table structure for student_homework
-- ----------------------------
DROP TABLE IF EXISTS `student_homework`;
CREATE TABLE `student_homework`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` int(10) UNSIGNED NOT NULL,
  `homework_id` int(10) UNSIGNED NOT NULL,
  `homework_answer` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `homework_score` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `homework_teacher_note` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `homework_status` int(1) UNSIGNED NOT NULL DEFAULT 1,
  `student_group_id` int(10) UNSIGNED NOT NULL,
  `last_upgrade_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_homework
-- ----------------------------
INSERT INTO `student_homework` VALUES (1, 5, 2, NULL, NULL, NULL, 1, 1, NULL);
INSERT INTO `student_homework` VALUES (2, 7, 3, NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `student_homework` VALUES (3, 8, 3, NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `student_homework` VALUES (4, 7, 4, NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `student_homework` VALUES (5, 8, 4, NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `student_homework` VALUES (6, 7, 5, NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `student_homework` VALUES (7, 8, 5, NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `student_homework` VALUES (8, 7, 6, NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `student_homework` VALUES (9, 8, 6, NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `student_homework` VALUES (10, 5, 7, NULL, NULL, NULL, 1, 1, NULL);
INSERT INTO `student_homework` VALUES (11, 7, 7, NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `student_homework` VALUES (12, 8, 7, NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `student_homework` VALUES (13, 8, 8, NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `student_homework` VALUES (14, 8, 9, '越是写作业就越是发现写作业是有极限的，我不写作业了JOJO', '100.0', '', 3, 3, '2021-07-30 10:19:08');

-- ----------------------------
-- Table structure for teacher_course
-- ----------------------------
DROP TABLE IF EXISTS `teacher_course`;
CREATE TABLE `teacher_course`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_id` int(10) UNSIGNED NOT NULL,
  `teacher_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher_course
-- ----------------------------
INSERT INTO `teacher_course` VALUES (1, 1, 3);
INSERT INTO `teacher_course` VALUES (2, 1, 2);
INSERT INTO `teacher_course` VALUES (6, 2, 2);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '用户工号',
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `user_sex` tinyint(1) UNSIGNED NOT NULL COMMENT '0为男，1为女',
  `user_identity` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户身份证号码',
  `user_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户手机号码',
  `user_role` tinyint(1) UNSIGNED NOT NULL COMMENT '0学生，1教师，2管理员',
  `user_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `user_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登录令牌',
  `teacher_role` int(4) UNSIGNED NULL DEFAULT NULL COMMENT '教师职称',
  `user_abandon` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否停用,0否，1是',
  `student_graduate` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '学生是否毕业，0否，1是',
  `student_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_person` int(10) UNSIGNED NOT NULL,
  `last_upgrade_time` datetime NOT NULL,
  `last_upgrade_person` int(10) UNSIGNED NOT NULL,
  `has_login` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uk_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 100000001, 'Freza_ben', 1, '50000000000000', '1000000000', 3, '351f996da2e21c7f58bf3c12439a5b9b', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjI3Njk4MjQ3LCJ1c2VySWQiOjEwMDAwMDAwMX0.Ib4ShpL2iD0G6SHLVO1bdW0gkJi5rq-QnVlMsFEtP-M', NULL, 0, NULL, NULL, '2021-07-21 13:58:42', 1, '2021-07-21 13:58:58', 1, 1);
INSERT INTO `user_info` VALUES (2, 100000002, '俾斯麦', 1, '500000000000000000', '18010000000', 2, '351f996da2e21c7f58bf3c12439a5b9b', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwiZXhwIjoxNjI3NzIxODg2LCJ1c2VySWQiOjEwMDAwMDAwMn0.06OK2n9TPfSThAM7BoAIe7bZjjuT9KMf-nPYHeUuyMU', 2, 0, NULL, NULL, '2021-07-22 15:25:07', 1, '2021-07-22 15:25:07', 1, 1);
INSERT INTO `user_info` VALUES (3, 100000003, '王德德', 1, '510000000000000000', '18100000000', 2, '351f996da2e21c7f58bf3c12439a5b9b', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MywiZXhwIjoxNjI3NzgyNjIzLCJ1c2VySWQiOjEwMDAwMDAwM30.hgtYT9TbFCc-RY44jSpGCTsDXK1n0UYJqg1KyTwB0i0', 1, 0, NULL, NULL, '2021-07-22 15:27:18', 1, '2021-07-22 15:27:18', 1, 1);
INSERT INTO `user_info` VALUES (4, 100000004, '提尔比茨', 1, '680000000000000', '18910000000', 2, '351f996da2e21c7f58bf3c12439a5b9b', NULL, 3, 0, NULL, NULL, '2021-07-22 16:16:24', 1, '2021-07-22 16:16:24', 1, 0);
INSERT INTO `user_info` VALUES (5, 189000301, '小王', 2, '589210000000000', '18900000000', 1, 'f752a64b4ce8e40da5e15f930a5dd750', NULL, NULL, 0, 0, '12345678@qq.com', '2021-07-23 18:56:33', 1, '2021-07-23 18:56:33', 1, 0);
INSERT INTO `user_info` VALUES (6, 189000302, '小林', 2, '589210000000001', '18900000019', 1, 'b9a1002d6344be976d0ba9754ba96dd2', NULL, NULL, 0, 0, '22222222@qq.com', '2021-07-23 19:03:11', 1, '2021-07-23 19:03:11', 1, 0);
INSERT INTO `user_info` VALUES (7, 189000303, '萨拉托加', 2, '589210002200001', '18900001019', 1, '3217b28486054d44ab1204a8dbe1010e', NULL, NULL, 0, 0, '33222222@qq.com', '2021-07-23 19:35:05', 1, '2021-07-26 13:13:36', 1, 0);
INSERT INTO `user_info` VALUES (8, 189000304, '小长春', 2, '589210202200001', '18910001019', 1, '351f996da2e21c7f58bf3c12439a5b9b', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6OCwiZXhwIjoxNjI3ODAzMTY0LCJ1c2VySWQiOjE4OTAwMDMwNH0.ZxvMWmPVqHPanbk-rZOzEjm0h9xwspUx4uom1qJMxIA', NULL, 0, 0, '444422@qq.com', '2021-07-23 19:37:12', 1, '2021-07-23 19:37:12', 1, 1);

-- ----------------------------
-- View structure for class_teacher_view
-- ----------------------------
DROP VIEW IF EXISTS `class_teacher_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `class_teacher_view` AS SELECT
	class_info.id, 
	department_info.department_name, 
	grade_info.grade_name, 
	major_info.major_name, 
	user_info.user_name, 
	user_info.id as teacher_id,
	(SELECT COUNT(*) FROM student_class WHERE student_class.class_id = class_info.id) as total_student
FROM
	class_info
	INNER JOIN
	department_info
	ON 
		class_info.department_id = department_info.id
	INNER JOIN
	grade_info
	ON 
		class_info.grade_id = grade_info.id
	INNER JOIN
	major_info
	ON 
		class_info.major_id = major_info.id
	INNER JOIN
	user_info
	ON 
		class_info.teacher_id = user_info.id ;

-- ----------------------------
-- View structure for course_view
-- ----------------------------
DROP VIEW IF EXISTS `course_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `course_view` AS SELECT
	course_info.id,
	course_info.course_date,
	course_info.course_name,
	course_info.course_sign_time,
	course_info.course_out_time,
	course_info.course_classroom,
	course_info.course_note,
	course_info.course_teacher_id AS leader_teacher_id,
	user_info.user_name AS leader_teacher,
	GROUP_CONCAT( teacher_course.teacher_id ) AS teacher_id,
	GROUP_CONCAT( user_name ) AS teacher 
FROM
	course_info
	LEFT JOIN teacher_course ON teacher_course.course_id = course_info.id
	LEFT JOIN user_info ON teacher_course.teacher_id = user_info.id 
WHERE
	course_info.course_abandon = 0 
GROUP BY
	course_info.id ;

-- ----------------------------
-- View structure for homework_view
-- ----------------------------
DROP VIEW IF EXISTS `homework_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `homework_view` AS SELECT
	homework_info.id,
	homework_info.create_time,
	homework_info.course_id ,
	homework_info.create_person,
	homework_info.homework_abandon,
	homework_info.homework_main,	
	student_homework.homework_teacher_note,
	student_homework.homework_score,
	( SELECT course_name FROM course_info WHERE course_info.id = homework_info.course_id ) AS course_name,
	homework_info.homework_title,
	homework_info.homework_type,
	GROUP_CONCAT(DISTINCT(SELECT id FROM user_info WHERE teacher_course.teacher_id = user_info.id )) as teacher_id ,
	( SELECT user_name FROM user_info WHERE user_info.id = homework_info.create_person ) AS teacher,
	homework_info.homework_expiry,
	GROUP_CONCAT(DISTINCT(SELECT id FROM group_info WHERE group_info.id = student_homework.student_group_id) )as group_id,
	GROUP_CONCAT(DISTINCT(SELECT group_name FROM group_info WHERE group_info.id = student_homework.student_group_id) )as group_name,
	( SELECT COUNT(*) FROM student_homework WHERE homework_id = homework_info.id ) AS total_student,
	( SELECT COUNT(*) FROM student_homework WHERE homework_id = homework_info.id AND student_homework.homework_status = 1 ) AS un_commit_student,
	( SELECT COUNT(*) FROM student_homework WHERE homework_id = homework_info.id AND student_homework.homework_status = 2 ) AS un_correct_student,
	( SELECT COUNT(*) FROM student_homework WHERE homework_id = homework_info.id AND student_homework.homework_status = 3 ) AS correct_student 
FROM
	homework_info LEFT JOIN 
	student_homework ON homework_info.id = student_homework.homework_id LEFT JOIN
	user_info ON user_info.id = homework_info.create_person LEFT JOIN
	group_info ON student_homework.student_group_id = group_info.id LEFT JOIN
	course_info ON course_info.id = homework_info.course_id LEFT JOIN 
	teacher_course ON teacher_course.course_id = homework_info.course_id
WHERE
	homework_info.homework_abandon = 0
GROUP BY
	homework_info.id ;

-- ----------------------------
-- View structure for student_homework_view
-- ----------------------------
DROP VIEW IF EXISTS `student_homework_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `student_homework_view` AS SELECT
	student_homework.id,
	student_homework.student_id,
	(SELECT user_info.user_name FROM user_info WHERE student_homework.student_id = user_info.id) as student_name,
	homework_view.id AS homework_id,
	homework_view.create_time,
	homework_view.homework_main ,
	homework_view.course_id,
	homework_view.course_name,
	homework_view.homework_title,
	homework_view.homework_type,
	homework_view.teacher_id,
	homework_view.teacher,
	homework_view.homework_expiry,
	homework_view.homework_teacher_note,
	homework_view.create_person ,
	student_homework.homework_status,
	student_homework.last_upgrade_time,
	student_homework.homework_score ,
	student_homework.homework_answer
FROM
	student_homework
	LEFT JOIN homework_view ON student_homework.homework_id = homework_view.id 
	LEFT JOIN user_info ON user_info.id = student_homework.student_id
WHERE
	homework_view.homework_abandon = 0 ;

-- ----------------------------
-- View structure for student_view
-- ----------------------------
DROP VIEW IF EXISTS `student_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `student_view` AS SELECT
	user_info.id, 
	user_info.user_id, 
	user_info.user_name, 
	user_info.user_sex, 
	user_info.user_identity, 
	user_info.user_phone, 
	user_info.student_email,
	student_class.student_role,
	student_class.class_id,
	class_base_info.class_base_name,
	class_info.department_id ,
	department_info.department_name ,
	class_info.major_id ,
	major_info.major_name ,
	class_info.grade_id ,
	grade_info.grade_name,
	(SELECT user_info.user_name FROM user_info WHERE class_info.teacher_id = user_info.id) as teacher_name , 
	group_info.group_name ,
	student_group.group_id ,
	course_id
	

FROM
	user_info 
	LEFT JOIN student_class ON user_info.id = student_class.student_id
	LEFT JOIN class_info ON class_info.id = student_class.class_id
	LEFT JOIN class_base_info ON class_base_info.id = class_info.class_name_id
	LEFT JOIN department_info ON department_info.id = class_info.department_id
	LEFT JOIN major_info ON major_info.id = class_info.major_id
	LEFT JOIN grade_info ON grade_info.id = class_info.grade_id
	LEFT JOIN student_group ON student_group.student_id = user_info.id
	LEFT JOIN group_info ON group_info.id = student_group.group_id
	LEFT JOIN student_course ON student_course.student_id = user_info.id
	
WHERE
	user_info.user_role = 1
GROUP BY
	user_info.id ;

SET FOREIGN_KEY_CHECKS = 1;
