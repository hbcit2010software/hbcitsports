/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.25 : Database - smms
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`smms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `smms`;

/*Table structure for table `t_department` */

DROP TABLE IF EXISTS `t_department`;

CREATE TABLE `t_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departname` varchar(50) NOT NULL COMMENT '部门名称',
  `departshortname` varchar(10) DEFAULT NULL COMMENT '系部名称缩写',
  `departtype` tinyint(1) NOT NULL COMMENT '部门类型：true表示有学生，false表示无学生',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='参赛部门表';

/*Data for the table `t_department` */

insert  into `t_department`(`id`,`departname`,`departshortname`,`departtype`) values (1,'思想政治与公共体育教学部','政体部',0),(2,'计算机技术系','计算机系',1),(3,'材料工程系','材料系',1),(4,'机电工程系','机电系',1);

/*Table structure for table `t_fieldjudge` */

DROP TABLE IF EXISTS `t_fieldjudge`;

CREATE TABLE `t_fieldjudge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gp2itid` int(11) NOT NULL COMMENT 'group2item表的id',
  `judge_1` varchar(20) DEFAULT NULL COMMENT '裁判长',
  `judge_2` varchar(20) DEFAULT NULL COMMENT '裁判长助理',
  `judge_3` text COMMENT '裁判员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='田赛裁判表';

/*Data for the table `t_fieldjudge` */

/*Table structure for table `t_finalitem` */

DROP TABLE IF EXISTS `t_finalitem`;

CREATE TABLE `t_finalitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gp2itid` int(11) NOT NULL COMMENT '组别与项目关系id group2item',
  `finalitemname` varchar(50) NOT NULL COMMENT '拆分后的项目名称',
  `finalitemtype` varchar(2) NOT NULL COMMENT '拆分后的项目类型：1预赛；2决赛；3预决赛',
  `date` varchar(10) DEFAULT NULL COMMENT '项目比赛日期',
  `time` varchar(5) DEFAULT NULL COMMENT '项目比赛时间',
  `groupnum` int(11) DEFAULT NULL COMMENT '分组数量',
  `promotionnum` int(11) DEFAULT NULL COMMENT '晋级数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分项竞赛项目表（预赛与决赛分离后的）';

/*Data for the table `t_finalitem` */

/*Table structure for table `t_group` */

DROP TABLE IF EXISTS `t_group`;

CREATE TABLE `t_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(50) NOT NULL COMMENT '组别名称：如学生男、教工老年男',
  `grouptype` tinyint(1) NOT NULL COMMENT '组别类型：true学生，false教工',
  `groupsex` tinyint(3) DEFAULT NULL COMMENT '组别的性别类型true男；false女',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组别表';

/*Data for the table `t_group` */

/*Table structure for table `t_group2item` */

DROP TABLE IF EXISTS `t_group2item`;

CREATE TABLE `t_group2item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gp2spid` int(11) NOT NULL COMMENT '组别与运动会关系group2sports id',
  `itemid` int(11) NOT NULL COMMENT '项目id',
  `matchtype` varchar(1) NOT NULL COMMENT '比赛类型：1预决赛；2预赛+决赛；3之后留作扩展',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分组与项目对应关系表';

/*Data for the table `t_group2item` */

/*Table structure for table `t_group2sports` */

DROP TABLE IF EXISTS `t_group2sports`;

CREATE TABLE `t_group2sports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sportsid` int(11) NOT NULL COMMENT '运动会id',
  `groupid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组别与运动会关系表';

/*Data for the table `t_group2sports` */

/*Table structure for table `t_item` */

DROP TABLE IF EXISTS `t_item`;

CREATE TABLE `t_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemname` varchar(50) NOT NULL COMMENT '项目名称',
  `itemtype` varchar(1) NOT NULL COMMENT '项目类型：1径赛；2田赛；3接力',
  `scoreformatid` int(11) DEFAULT NULL COMMENT '成绩格式ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='项目表';

/*Data for the table `t_item` */

insert  into `t_item`(`id`,`itemname`,`itemtype`,`scoreformatid`) values (1,'100米','1',1),(2,'跳远','2',5),(3,'110米跨栏','1',1),(4,'200米','1',1),(5,'400米','1',2),(6,'800米','1',3),(7,'1500米','1',3),(8,'400米栏','1',2),(9,'跳高','2',4),(10,'三级跳远','2',4),(11,'铅球','2',4),(12,'铁饼','2',7),(13,'标枪','2',8),(14,'4*100米','3',2),(15,'4*400米','3',2);

/*Table structure for table `t_mark` */

DROP TABLE IF EXISTS `t_mark`;

CREATE TABLE `t_mark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sp2dpid` int(11) NOT NULL COMMENT '运动会与部门关系表id',
  `sum` int(11) DEFAULT NULL COMMENT '总分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分表';

/*Data for the table `t_mark` */

/*Table structure for table `t_match` */

DROP TABLE IF EXISTS `t_match`;

CREATE TABLE `t_match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `finalitemid` int(11) NOT NULL COMMENT '拆分后组别id',
  `teamnum` int(11) DEFAULT NULL COMMENT '第几小组',
  `runway` int(11) DEFAULT NULL COMMENT '跑道号',
  `playerid` int(11) DEFAULT NULL COMMENT '运动员id',
  `score` varchar(50) DEFAULT NULL,
  `foul` tinyint(1) DEFAULT NULL COMMENT '是否犯规违纪：true违纪；false正常状态',
  `recordlevel` int(11) NOT NULL DEFAULT '0' COMMENT '记录级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞赛表（赛道分组）';

/*Data for the table `t_match` */

/*Table structure for table `t_official` */

DROP TABLE IF EXISTS `t_official`;

CREATE TABLE `t_official` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sportsid` int(11) NOT NULL COMMENT '运动会id',
  `presidium` text COMMENT '大会主席团',
  `org_committee_1` varchar(10) DEFAULT NULL COMMENT '大会组委会-主任',
  `org_committee_2` varchar(20) DEFAULT NULL COMMENT '大会组委会-副主任',
  `org_committee_3` text COMMENT '大会组委会-委员',
  `secretariat_1` varchar(10) DEFAULT NULL COMMENT '大会秘书处-秘书长',
  `secretariat_2` varchar(10) DEFAULT NULL COMMENT '大会秘书处-副秘书长',
  `secretariat_3` varchar(20) DEFAULT NULL COMMENT '大会秘书处-会务组负责人',
  `secretariat_4` varchar(20) DEFAULT NULL COMMENT '大会秘书处-宣传组负责人',
  `secretariat_5` varchar(20) DEFAULT NULL COMMENT '大会秘书处-奖品组负责人',
  `secretariat_6` varchar(20) DEFAULT NULL COMMENT '大会秘书处-保卫组负责人',
  `secretariat_7` varchar(20) DEFAULT NULL COMMENT '大会秘书处-后勤保障组负责人',
  `arbitration` text COMMENT '仲裁委员会',
  `chiefjudge_1` varchar(10) DEFAULT NULL COMMENT '总裁判长',
  `chiefjudge_2` text COMMENT '副总裁判长',
  `trackjudge` varchar(10) DEFAULT NULL COMMENT '径赛裁判长',
  `trackjudge_rollcall_1` varchar(10) DEFAULT NULL COMMENT '检录裁判长',
  `trackjudge_rollcall_2` varchar(10) DEFAULT NULL COMMENT '检录裁判长助理',
  `trackjudge_rollcall_3` text COMMENT '检录员',
  `startingpoint_1` varchar(10) DEFAULT NULL COMMENT '起点裁判长',
  `startingpoint_2` varchar(10) DEFAULT NULL COMMENT '起点裁判长助理',
  `startingpoint_3` text COMMENT '发令员',
  `timejudge_1` varchar(10) DEFAULT NULL COMMENT '计时长',
  `timejudge_2` text COMMENT '计时员',
  `timejudge_3` varchar(20) DEFAULT NULL COMMENT '司线员',
  `endpoint_1` varchar(10) DEFAULT NULL COMMENT '终点裁判长',
  `endpoint_2` varchar(10) DEFAULT NULL COMMENT '终点裁判长助理',
  `endpoint_3` text COMMENT '终点裁判员',
  `endpoint_4` varchar(10) DEFAULT NULL COMMENT '终点记录长',
  `endpoint_5` varchar(20) DEFAULT NULL COMMENT '终点记录员',
  `fieldjudge` varchar(10) DEFAULT NULL COMMENT '田赛裁判长',
  `fieldjudge_1` varchar(10) DEFAULT NULL COMMENT '田赛总记录裁判长',
  `fieldjudge_2` text COMMENT '田赛记录员',
  `fieldjudge_3` varchar(10) DEFAULT NULL COMMENT '田赛检查长',
  `fieldjudge_4` text COMMENT '田赛检查员',
  `fieldjudge_5` text COMMENT '场地器材组长',
  `fieldjudge_6` text COMMENT '场地器材员',
  `remarks_1` text COMMENT '参加办法',
  `remarks_2` text COMMENT '竞赛说明',
  `remarks_3` text COMMENT '计分方法',
  `remarks_4` text COMMENT '其他',
  `remarks_5` text COMMENT '大会纪律',
  `openingceremony` text COMMENT '开幕式',
  `closingceremony` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作人员表';

/*Data for the table `t_official` */

/*Table structure for table `t_player` */

DROP TABLE IF EXISTS `t_player`;

CREATE TABLE `t_player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sp2dpid` int(11) NOT NULL COMMENT '运动会-部门联系表id',
  `playernum` varchar(10) DEFAULT NULL,
  `playername` varchar(10) DEFAULT NULL COMMENT '运动员姓名',
  `playersex` tinyint(1) DEFAULT NULL COMMENT '运动员性别：true男；false女',
  `groupid` int(11) DEFAULT NULL COMMENT '组别id',
  `registitem` varchar(255) DEFAULT NULL COMMENT '该人所报的项目：用;隔开的itemid字符串。比如1;2;3代表报了1、2、3这三个项目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运动员表';

/*Data for the table `t_player` */

/*Table structure for table `t_playernum` */

DROP TABLE IF EXISTS `t_playernum`;

CREATE TABLE `t_playernum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sp2dpid` int(11) NOT NULL COMMENT '运动会2部门表id sports2department',
  `beginnum` varchar(4) DEFAULT NULL COMMENT '起始号码',
  `endnum` varchar(4) DEFAULT NULL,
  `numtype` tinyint(1) DEFAULT NULL COMMENT '号码类型：true学生；false教工',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运动员号码分布表';

/*Data for the table `t_playernum` */

/*Table structure for table `t_position` */

DROP TABLE IF EXISTS `t_position`;

CREATE TABLE `t_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `finalitemid` int(11) DEFAULT NULL COMMENT '具体组别id',
  `playerid` int(11) DEFAULT NULL COMMENT '运动员id',
  `position` int(11) DEFAULT NULL COMMENT '名次',
  `score` varchar(50) DEFAULT NULL COMMENT '成绩：几分几秒或多少米',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='比赛名次表';

/*Data for the table `t_position` */

/*Table structure for table `t_record` */

DROP TABLE IF EXISTS `t_record`;

CREATE TABLE `t_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemid` int(11) NOT NULL COMMENT '项目id',
  `sex` tinyint(1) DEFAULT NULL COMMENT 'true男；false女',
  `score` varchar(50) DEFAULT NULL COMMENT '成绩',
  `playername` varchar(10) DEFAULT NULL COMMENT '运动员姓名',
  `departname` varchar(50) DEFAULT NULL COMMENT '系别',
  `sportsname` varchar(50) DEFAULT NULL COMMENT '运动会名称',
  `recordtime` varchar(10) DEFAULT NULL COMMENT '破记录时间',
  `recordlevel` varchar(2) DEFAULT NULL COMMENT '记录级别：0院级；1省级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞赛记录表';

/*Data for the table `t_record` */

/*Table structure for table `t_rule` */

DROP TABLE IF EXISTS `t_rule`;

CREATE TABLE `t_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sportsid` int(11) NOT NULL COMMENT '运动会id',
  `position` int(11) DEFAULT NULL COMMENT '名次：默认8',
  `mark` varchar(50) DEFAULT NULL COMMENT '积分：中间用,隔开，如：9,7,6,5,4,3,2,1',
  `recordmark_low` int(11) DEFAULT NULL COMMENT '破院记录的加分数，默认9',
  `recordmark_high` int(11) DEFAULT NULL COMMENT '破省记录的加分数',
  `perman` int(11) DEFAULT NULL COMMENT '每系每个运动员限报数量（接力除外）',
  `perdepartment` int(11) DEFAULT NULL COMMENT '每项每个系部单位限报数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大赛规则';

/*Data for the table `t_rule` */

/*Table structure for table `t_scoreformat` */

DROP TABLE IF EXISTS `t_scoreformat`;

CREATE TABLE `t_scoreformat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `format` varchar(20) NOT NULL DEFAULT '' COMMENT '成绩格式',
  `reg` varchar(10) NOT NULL DEFAULT '' COMMENT '成绩正则（JavaScript）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='成绩格式表';

/*Data for the table `t_scoreformat` */

insert  into `t_scoreformat`(`id`,`format`,`reg`) values (1,'##.##','/^[1-5]?\\d'),(2,'#.##.##','/^[1-9]?\\.'),(3,'##.##.##','/^[1-9]?\\d'),(4,'#.##','/^[1-2]\\.\\'),(5,'#.##','/^[4-9]\\.\\'),(6,'##.##','/^[1-2]?\\d'),(7,'##.##','/^[1-7]\\d\\'),(9,'##.##','/^\\d\\d\\.\\d');

/*Table structure for table `t_sports` */

DROP TABLE IF EXISTS `t_sports`;

CREATE TABLE `t_sports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sportsname` varchar(50) NOT NULL COMMENT '运动会名称',
  `sportsbegin` varchar(10) NOT NULL COMMENT '起始日期',
  `sportsend` varchar(10) NOT NULL COMMENT '结束日期',
  `registend` varchar(10) NOT NULL COMMENT '报名截止日期',
  `address` varchar(50) NOT NULL COMMENT '大会地点',
  `current` tinyint(1) NOT NULL COMMENT '是否当前运动会',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='运动会表';

/*Data for the table `t_sports` */

insert  into `t_sports`(`id`,`sportsname`,`sportsbegin`,`sportsend`,`registend`,`address`,`current`) values (1,'河北工院第12届运动会','2012-02-01','2012-02-02','2012-01-30','南校区操场',0),(2,'河北工院第13届运动会','2012-09-01','2012-09-02','2012-08-18','南校区风雨操场',1),(3,'河北工院第11届运动会','2010-01-10','2010-01-11','2010-01-01','学院田径场（南校区）',0);

/*Table structure for table `t_sports2department` */

DROP TABLE IF EXISTS `t_sports2department`;

CREATE TABLE `t_sports2department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sportsid` int(11) NOT NULL COMMENT '运动会id',
  `departid` int(11) NOT NULL COMMENT '部门id',
  `teamleader` varchar(50) DEFAULT NULL COMMENT '领队',
  `coach` varchar(50) DEFAULT NULL COMMENT '教练',
  `doctor` varchar(50) DEFAULT NULL COMMENT '队医',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运动会与部门对应表';

/*Data for the table `t_sports2department` */

/*Table structure for table `t_stujudge` */

DROP TABLE IF EXISTS `t_stujudge`;

CREATE TABLE `t_stujudge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sp2dpid` int(11) NOT NULL COMMENT '运动会与部门关系id sports2department',
  `contact` varchar(20) DEFAULT NULL COMMENT '联系人姓名',
  `tel` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `member` text COMMENT '裁判成员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生裁判表';

/*Data for the table `t_stujudge` */

/*Table structure for table `t_sysadmin` */

DROP TABLE IF EXISTS `t_sysadmin`;

CREATE TABLE `t_sysadmin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `userright` int(11) NOT NULL COMMENT '用户权限',
  `realname` varchar(50) NOT NULL COMMENT '用户真实姓名',
  `departid` int(11) NOT NULL COMMENT '单位id。各部门报名账号的departid应对应t_department表的id，管理员和裁判员不对应。',
  PRIMARY KEY (`id`),
  KEY `FK_department2sysadmin` (`departid`),
  CONSTRAINT `FK_department2sysadmin` FOREIGN KEY (`departid`) REFERENCES `t_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='账号表';

/*Data for the table `t_sysadmin` */

insert  into `t_sysadmin`(`id`,`username`,`password`,`userright`,`realname`,`departid`) values (2,'admin','96e79218965eb72c92a549dd5a330112',31,'系统管理员',1),(5,'sohu','96e79218965eb72c92a549dd5a330112',22,'搜狐人',4),(6,'sina','96e79218965eb72c92a549dd5a330112',12,'阿卡',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
