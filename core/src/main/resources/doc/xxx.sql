/*
Navicat MySQL Data Transfer

Source Server         : answer-203
Source Server Version : 50731
Source Host           : 10.114.20.203:3306
Source Database       : answer

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-11-26 16:04:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answer_record
-- ----------------------------
DROP TABLE IF EXISTS `answer_record`;
CREATE TABLE `answer_record` (
  `record_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL COMMENT '所属单位',
  `answer_count` int(255) DEFAULT NULL COMMENT '答对题数',
  `status` int(1) DEFAULT NULL COMMENT '答题状态  0  false 未答题 1 已答题',
  `score` int(11) DEFAULT NULL COMMENT '分数',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `answer_date` datetime DEFAULT NULL COMMENT '答题时间',
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `idx_user_id` (`user_id`),
  KEY `idx_org_id` (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10009 DEFAULT CHARSET=utf8mb4 COMMENT='答题记录表';

-- ----------------------------
-- Records of answer_record
-- ----------------------------

-- ----------------------------
-- Table structure for answer_record_detail
-- ----------------------------
DROP TABLE IF EXISTS `answer_record_detail`;
CREATE TABLE `answer_record_detail` (
  `record_id` int(11) NOT NULL COMMENT '答题记录ID',
  `question_id` int(11) DEFAULT NULL COMMENT '题目ID',
  `answer_info` varchar(64) DEFAULT NULL COMMENT '作答选项',
  `score` int(11) DEFAULT NULL COMMENT '所得分数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of answer_record_detail
-- ----------------------------
INSERT INTO `answer_record_detail` VALUES ('1', '2', '1', '5');
INSERT INTO `answer_record_detail` VALUES ('1', '2', '2', '5');

-- ----------------------------
-- Table structure for org_info
-- ----------------------------
DROP TABLE IF EXISTS `org_info`;
CREATE TABLE `org_info` (
  `org_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组织ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '父ID',
  `org_name` varchar(255) DEFAULT NULL COMMENT '单位名称',
  `industry_code` varchar(255) DEFAULT NULL COMMENT '行业编码',
  `industry_name` varchar(255) DEFAULT NULL COMMENT '行业名称',
  `staff_number` int(11) DEFAULT NULL COMMENT '员工数量',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=303 DEFAULT CHARSET=utf8mb4 COMMENT='单位表';

-- ----------------------------
-- Records of org_info
-- ----------------------------
INSERT INTO `org_info` VALUES ('211', null, '州委统战部', '10001', null, '20');
INSERT INTO `org_info` VALUES ('212', null, '州委政研室', '10001', null, '17');
INSERT INTO `org_info` VALUES ('213', null, '州委外事办', '10001', null, '15');
INSERT INTO `org_info` VALUES ('214', null, '州委编办', '10001', null, '16');
INSERT INTO `org_info` VALUES ('215', null, '州委直属机关工委', '10001', null, '16');
INSERT INTO `org_info` VALUES ('216', null, '州督查考评办', '10001', null, '19');
INSERT INTO `org_info` VALUES ('217', null, '州工商联', '10001', null, '14');
INSERT INTO `org_info` VALUES ('218', null, '团州委', '10001', null, '15');
INSERT INTO `org_info` VALUES ('219', null, '州妇联', '10001', null, '17');
INSERT INTO `org_info` VALUES ('220', null, '州科协', '10001', null, '18');
INSERT INTO `org_info` VALUES ('221', null, '州红十字会', '10001', null, '11');
INSERT INTO `org_info` VALUES ('222', null, '州铁路办', '10001', null, '13');
INSERT INTO `org_info` VALUES ('223', null, '州国资委', '10001', null, '18');
INSERT INTO `org_info` VALUES ('224', null, '邮政恩施州分公司', '10001', null, '13');
INSERT INTO `org_info` VALUES ('225', null, '州无线电管理处', '10001', null, '8');
INSERT INTO `org_info` VALUES ('226', null, '州硒资源保护与开发中心', '10001', null, '16');
INSERT INTO `org_info` VALUES ('227', null, '州信访局', '10002', null, '28');
INSERT INTO `org_info` VALUES ('228', null, '州委机要和保密局', '10002', null, '23');
INSERT INTO `org_info` VALUES ('229', null, '州委老干部局', '10002', null, '28');
INSERT INTO `org_info` VALUES ('230', null, '州总工会', '10002', null, '29');
INSERT INTO `org_info` VALUES ('231', null, '州残联', '10002', null, '27');
INSERT INTO `org_info` VALUES ('232', null, '州档案馆', '10002', null, '25');
INSERT INTO `org_info` VALUES ('233', null, '州民宗委', '10002', null, '27');
INSERT INTO `org_info` VALUES ('234', null, '州民政局', '10002', null, '37');
INSERT INTO `org_info` VALUES ('235', null, '州审计局', '10002', null, '54');
INSERT INTO `org_info` VALUES ('236', null, '州统计局', '10002', null, '46');
INSERT INTO `org_info` VALUES ('237', null, '州乡村振兴局', '10002', null, '24');
INSERT INTO `org_info` VALUES ('238', null, '州政务服务和大数据管理局', '10002', null, '44');
INSERT INTO `org_info` VALUES ('239', null, '州公共资源交易中心', '10002', null, '26');
INSERT INTO `org_info` VALUES ('240', null, '州大数据中心', '10002', null, '24');
INSERT INTO `org_info` VALUES ('241', null, '国家统计局恩施州调查队', '10002', null, '26');
INSERT INTO `org_info` VALUES ('242', null, '州邮政管理局', '10002', null, '59');
INSERT INTO `org_info` VALUES ('243', null, '州政府驻汉办事处', '10002', null, '23');
INSERT INTO `org_info` VALUES ('244', null, '州经信局', '10002', null, '38');
INSERT INTO `org_info` VALUES ('245', null, '州商务（招商）局', '10002', null, '52');
INSERT INTO `org_info` VALUES ('246', null, '州供销社', '10002', null, '26');
INSERT INTO `org_info` VALUES ('247', null, '湖北烟草金叶复烤公司', '10002', null, '36');
INSERT INTO `org_info` VALUES ('248', null, '州畜牧兽医服务中心', '10002', null, '41');
INSERT INTO `org_info` VALUES ('249', null, '州科技局', '10002', null, '29');
INSERT INTO `org_info` VALUES ('250', null, '州医疗保障局', '10002', null, '34');
INSERT INTO `org_info` VALUES ('251', null, '省新华书店集团恩施州分公司', '10002', null, '54');
INSERT INTO `org_info` VALUES ('252', null, '湖北广电网络恩施分公司', '10002', null, '47');
INSERT INTO `org_info` VALUES ('253', null, '州委政法委', '10002', null, '29');
INSERT INTO `org_info` VALUES ('254', null, '州地方金融工作局', '10002', null, '21');
INSERT INTO `org_info` VALUES ('255', null, '州委办公室', '10003', null, '73');
INSERT INTO `org_info` VALUES ('256', null, '州人大办公室', '10003', null, '93');
INSERT INTO `org_info` VALUES ('257', null, '州政协办公室', '10003', null, '77');
INSERT INTO `org_info` VALUES ('258', null, '州委组织部', '10003', null, '66');
INSERT INTO `org_info` VALUES ('259', null, '州委党校', '10003', null, '69');
INSERT INTO `org_info` VALUES ('260', null, '州政府办公室', '10003', null, '142');
INSERT INTO `org_info` VALUES ('261', null, '州发改委', '10003', null, '104');
INSERT INTO `org_info` VALUES ('262', null, '州财政局', '10003', null, '117');
INSERT INTO `org_info` VALUES ('263', null, '州自然资源和规划局', '10003', null, '93');
INSERT INTO `org_info` VALUES ('264', null, '州生态环境局', '10003', null, '67');
INSERT INTO `org_info` VALUES ('265', null, '州应急管理局', '10003', null, '74');
INSERT INTO `org_info` VALUES ('266', null, '州住建局', '10003', null, '98');
INSERT INTO `org_info` VALUES ('267', null, '州住房公积金中心', '10003', null, '119');
INSERT INTO `org_info` VALUES ('268', null, '州公共检验检测中心', '10003', null, '82');
INSERT INTO `org_info` VALUES ('269', null, '中石化恩施分公司', '10003', null, '65');
INSERT INTO `org_info` VALUES ('270', null, '州公路局', '10003', null, '68');
INSERT INTO `org_info` VALUES ('271', null, '州农业农村局', '10003', null, '125');
INSERT INTO `org_info` VALUES ('272', null, '州水利和湖泊局（含所有二级单位）', '10003', null, '117');
INSERT INTO `org_info` VALUES ('273', null, '州农科院', '10003', null, '134');
INSERT INTO `org_info` VALUES ('274', null, '州气象局', '10003', null, '76');
INSERT INTO `org_info` VALUES ('275', null, '州水文局', '10003', null, '79');
INSERT INTO `org_info` VALUES ('276', null, '州委宣传部（含州委网信办、州委理论信息中心）', '10003', null, '64');
INSERT INTO `org_info` VALUES ('277', null, '州文化和旅游局（含州博物馆、州图书馆、州文化馆）', '10003', null, '142');
INSERT INTO `org_info` VALUES ('278', null, '州机关事务服务中心', '10003', null, '113');
INSERT INTO `org_info` VALUES ('279', null, '州电大', '10003', null, '65');
INSERT INTO `org_info` VALUES ('280', null, '省民族歌舞团', '10003', null, '117');
INSERT INTO `org_info` VALUES ('281', null, '州人民检察院', '10003', null, '92');
INSERT INTO `org_info` VALUES ('282', null, '州司法局（含州强制隔离戒毒所）', '10003', null, '143');
INSERT INTO `org_info` VALUES ('283', null, '州纪委监委机关', '10004', null, '265');
INSERT INTO `org_info` VALUES ('284', null, '州人社局（含州社保局、州公共就业和人才服务局）', '10004', null, '182');
INSERT INTO `org_info` VALUES ('285', null, '省地质局第二地质大队', '10004', null, '184');
INSERT INTO `org_info` VALUES ('286', null, '州税务局', '10004', null, '301');
INSERT INTO `org_info` VALUES ('287', null, '州烟草专卖局', '10004', null, '226');
INSERT INTO `org_info` VALUES ('288', null, '州交通运输局（含州港航管理局、州运管处、州农村公路管理局）', '10004', null, '178');
INSERT INTO `org_info` VALUES ('289', null, '州市场监督管理局（含州计量检定测试所）', '10004', null, '157');
INSERT INTO `org_info` VALUES ('290', null, '电信恩施分公司', '10004', null, '223');
INSERT INTO `org_info` VALUES ('291', null, '移动恩施分公司', '10004', null, '706');
INSERT INTO `org_info` VALUES ('292', null, '湖北中烟恩施卷烟厂', '10004', null, '728');
INSERT INTO `org_info` VALUES ('293', null, '州林业局（湖北星斗山自然保护区管理局、湖北七姊妹山自然保护区管理局、湖北巴东金丝猴自然保护区管理局、湖北木林子自然保护区管理局）', '10004', null, '207');
INSERT INTO `org_info` VALUES ('294', null, '州教育局（含州民族幼儿园、州教科院）', '10004', null, '193');
INSERT INTO `org_info` VALUES ('295', null, '州卫生健康委（含州卫生计生综合监督执法局、州妇幼保健计划生育服务中心、州疾控中心）', '10004', null, '213');
INSERT INTO `org_info` VALUES ('296', null, '州退役军人事务局（含州优抚医院）', '10004', null, '314');
INSERT INTO `org_info` VALUES ('297', null, '恩施日报社', '10004', null, '191');
INSERT INTO `org_info` VALUES ('298', null, '州广播电视台', '10004', null, '320');
INSERT INTO `org_info` VALUES ('299', null, '恩施高中', '10004', null, '451');
INSERT INTO `org_info` VALUES ('300', null, '州中心医院', '10004', null, '3129');
INSERT INTO `org_info` VALUES ('301', null, '州中级人民法院', '10004', null, '162');
INSERT INTO `org_info` VALUES ('302', null, '州公安局', '10004', null, '593');

-- ----------------------------
-- Table structure for promotion_list
-- ----------------------------
DROP TABLE IF EXISTS `promotion_list`;
CREATE TABLE `promotion_list` (
  `org_id` int(11) DEFAULT NULL COMMENT '单位ID',
  `org_name` varchar(255) DEFAULT NULL COMMENT '单位名称',
  `answer_rate` decimal(11,2) DEFAULT NULL COMMENT '参与率',
  `score` decimal(11,2) DEFAULT NULL COMMENT '得分'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='晋级名单';

-- ----------------------------
-- Records of promotion_list
-- ----------------------------
INSERT INTO `promotion_list` VALUES ('211', '州委统战部', '1.74', '52.74');
INSERT INTO `promotion_list` VALUES ('212', '州委政研室', '0.26', '6.26');

-- ----------------------------
-- Table structure for question_bank
-- ----------------------------
DROP TABLE IF EXISTS `question_bank`;
CREATE TABLE `question_bank` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '题干',
  `type` int(1) DEFAULT NULL COMMENT '1，单选 2 多选',
  `item` varchar(255) DEFAULT NULL COMMENT '选项',
  `blank_count` int(11) DEFAULT NULL COMMENT '填空数量',
  `answer` varchar(255) DEFAULT NULL COMMENT '答案',
  `points` int(11) DEFAULT NULL COMMENT '分值',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8mb4 COMMENT='题库';

-- ----------------------------
-- Records of question_bank
-- ----------------------------
INSERT INTO `question_bank` VALUES ('1', '单选题1', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('2', '多选题1', '2', '选项1|选项2|选项3|选项4', null, '1|2', '5');
INSERT INTO `question_bank` VALUES ('3', '填空题1', '3', null, '2', '淮海战役|彭德怀', '7');
INSERT INTO `question_bank` VALUES ('4', '单选题2', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('5', '单选题3', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('6', '单选题4', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('7', '单选题5', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('8', '单选题6', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('9', '单选题7', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('10', '单选题8', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('11', '单选题9', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('12', '单选题10', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('13', '单选题11', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('14', '单选题12', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('15', '单选题13', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('16', '单选题14', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('17', '单选题15', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('18', '单选题16', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('19', '单选题17', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('20', '单选题18', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('21', '单选题19', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('22', '单选题20', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('23', '单选题21', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('24', '多选题2', '2', '选项1|选项2|选项3|选项4', null, '1|2', '5');
INSERT INTO `question_bank` VALUES ('25', '多选题3', '2', '选项1|选项2|选项3|选项4', null, '1|2', '5');
INSERT INTO `question_bank` VALUES ('26', '多选题4', '2', '选项1|选项2|选项3|选项4', null, '1|2', '5');
INSERT INTO `question_bank` VALUES ('27', '多选题5', '2', '选项1|选项2|选项3|选项4', null, '1|2', '5');
INSERT INTO `question_bank` VALUES ('28', '多选题6', '2', '选项1|选项2|选项3|选项4', null, '1|2', '3');
INSERT INTO `question_bank` VALUES ('29', '多选题7', '2', '选项1|选项2|选项3|选项4', null, '1|2', '3');
INSERT INTO `question_bank` VALUES ('30', '多选题8', '2', '选项1|选项2|选项3|选项4', null, '1|2', '3');
INSERT INTO `question_bank` VALUES ('31', '多选题9', '2', '选项1|选项2|选项3|选项4', null, '1|2', '3');
INSERT INTO `question_bank` VALUES ('32', '多选题10', '2', '选项1|选项2|选项3|选项4', null, '1|2', '3');
INSERT INTO `question_bank` VALUES ('33', '多选题11', '2', '选项1|选项2|选项3|选项4', null, '1|2', '5');
INSERT INTO `question_bank` VALUES ('34', '多选题12', '2', '选项1|选项2|选项3|选项4', null, '1|2', '5');
INSERT INTO `question_bank` VALUES ('35', '多选题13', '2', '选项1|选项2|选项3|选项4', null, '1|2', '5');
INSERT INTO `question_bank` VALUES ('36', '多选题14', '2', '选项1|选项2|选项3|选项4', null, '1|2', '5');
INSERT INTO `question_bank` VALUES ('37', '多选题15', '2', '选项1|选项2|选项3|选项4', null, '1|2', '7');
INSERT INTO `question_bank` VALUES ('38', '多选题16', '2', '选项1|选项2|选项3|选项4', null, '1|2', '7');
INSERT INTO `question_bank` VALUES ('39', '多选题17', '2', '选项1|选项2|选项3|选项4', null, '1|2', '7');
INSERT INTO `question_bank` VALUES ('40', '多选题18', '2', '选项1|选项2|选项3|选项4', null, '1|2', '7');
INSERT INTO `question_bank` VALUES ('41', '填空题2', '3', null, '2', '淮海战役|彭德怀', '3');
INSERT INTO `question_bank` VALUES ('42', '填空题3', '3', null, '2', '淮海战役|彭德怀', '3');
INSERT INTO `question_bank` VALUES ('43', '填空题4', '3', null, '2', '淮海战役|彭德怀', '3');
INSERT INTO `question_bank` VALUES ('44', '填空题5', '3', null, '2', '淮海战役|彭德怀', '3');
INSERT INTO `question_bank` VALUES ('45', '填空题6', '3', null, '2', '淮海战役|彭德怀', '3');
INSERT INTO `question_bank` VALUES ('46', '填空题7', '3', null, '2', '淮海战役|彭德怀', '3');
INSERT INTO `question_bank` VALUES ('47', '填空题8', '3', null, '2', '淮海战役|彭德怀', '5');
INSERT INTO `question_bank` VALUES ('48', '填空题9', '3', null, '2', '淮海战役|彭德怀', '5');
INSERT INTO `question_bank` VALUES ('49', '填空题10', '3', null, '2', '淮海战役|彭德怀', '5');
INSERT INTO `question_bank` VALUES ('50', '填空题11', '3', null, '2', '淮海战役|彭德怀', '5');
INSERT INTO `question_bank` VALUES ('51', '填空题12', '3', null, '2', '淮海战役|彭德怀', '5');
INSERT INTO `question_bank` VALUES ('52', '填空题13', '3', null, '2', '淮海战役|彭德怀', '5');
INSERT INTO `question_bank` VALUES ('53', '填空题14', '3', null, '2', '淮海战役|彭德怀', '5');
INSERT INTO `question_bank` VALUES ('54', '填空题15', '3', null, '2', '淮海战役|彭德怀', '5');
INSERT INTO `question_bank` VALUES ('55', '填空题16', '3', null, '2', '淮海战役|彭德怀', '5');
INSERT INTO `question_bank` VALUES ('56', '填空题17', '3', null, '2', '淮海战役|彭德怀', '5');
INSERT INTO `question_bank` VALUES ('57', '填空题18', '3', null, '2', '淮海战役|彭德怀', '5');
INSERT INTO `question_bank` VALUES ('58', '填空题19', '3', null, '2', '淮海战役|彭德怀', '7');
INSERT INTO `question_bank` VALUES ('59', '填空题20', '3', null, '2', '淮海战役|彭德怀', '7');
INSERT INTO `question_bank` VALUES ('60', '填空题21', '3', null, '2', '淮海战役|彭德怀', '7');
INSERT INTO `question_bank` VALUES ('61', '填空题22', '3', null, '2', '淮海战役|彭德怀', '7');
INSERT INTO `question_bank` VALUES ('62', '填空题23', '3', null, '2', '淮海战役|彭德怀', '7');
INSERT INTO `question_bank` VALUES ('63', '单选题20', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('64', '单选题21', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('65', '单选题22', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('66', '单选题23', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('67', '单选题24', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('68', '单选题25', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('69', '单选题26', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('70', '单选题27', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('71', '单选题28', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('72', '单选题29', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('73', '单选题30', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('74', '单选题31', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('75', '单选题32', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('76', '单选题33', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('77', '单选题34', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('78', '单选题35', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('79', '单选题36', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('80', '单选题37', '1', '选项1|选项2|选项3|选项4', null, '1', '3');
INSERT INTO `question_bank` VALUES ('81', '单选题38', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('82', '单选题39', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('83', '单选题40', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('84', '单选题41', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('85', '单选题42', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('86', '单选题43', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('87', '单选题44', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('88', '单选题45', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('89', '单选题46', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('90', '单选题47', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('91', '单选题48', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('92', '单选题49', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('93', '单选题50', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('94', '单选题51', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('95', '单选题52', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('96', '单选题53', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('97', '单选题54', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('98', '单选题55', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('99', '单选题56', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('100', '单选题57', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('101', '单选题58', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('102', '单选题59', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('103', '单选题60', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('104', '单选题61', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('105', '单选题62', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('106', '单选题63', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('107', '单选题64', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('108', '单选题65', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('109', '单选题66', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('110', '单选题67', '1', '选项1|选项2|选项3|选项4', null, '1', '5');
INSERT INTO `question_bank` VALUES ('111', '单选题68', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('112', '单选题69', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('113', '单选题70', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('114', '单选题71', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('115', '单选题72', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('116', '单选题73', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('117', '单选题74', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('118', '单选题75', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('119', '单选题76', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('120', '单选题77', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('121', '单选题78', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('122', '单选题79', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('123', '单选题80', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('124', '单选题81', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('125', '单选题82', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('126', '单选题83', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('127', '单选题84', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('128', '单选题85', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('129', '单选题86', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('130', '单选题87', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('131', '单选题88', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('132', '单选题89', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('133', '单选题90', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('134', '单选题91', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('135', '单选题92', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('136', '单选题93', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
INSERT INTO `question_bank` VALUES ('137', '单选题94', '1', '选项1|选项2|选项3|选项4', null, '1', '7');
