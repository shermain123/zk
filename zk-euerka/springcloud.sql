/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - springcloud-01
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springcloud-01` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `springcloud-01`;

/*Table structure for table `cp_sys_exchange_date` */

DROP TABLE IF EXISTS `cp_sys_exchange_date`;

CREATE TABLE `cp_sys_exchange_date` (
  `MARKET_CODE` char(1) COLLATE utf8_bin DEFAULT NULL,
  `TRADEDATE` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `DATE_FLAG` char(1) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cp_sys_exchange_date` */

/*Table structure for table `cp_sys_resource` */

DROP TABLE IF EXISTS `cp_sys_resource`;

CREATE TABLE `cp_sys_resource` (
  `RES_ID` int(11) DEFAULT NULL,
  `RES_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `RES_TYPE` int(11) DEFAULT NULL COMMENT '类型 1 菜单 2按钮',
  `URL` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源路径',
  `RES_LOGO` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标识',
  `PARENT_ID` varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '父节点',
  `RES_ORDER` int(11) DEFAULT NULL COMMENT '排序',
  `RES_LEVEL` int(11) DEFAULT NULL COMMENT '级别  1.资源 2.top选项卡 3.每个选项卡的父选项 4每个选项卡下的页面',
  `UPDATE_ID` varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `UPDATE_TIME` date DEFAULT NULL COMMENT '更新时间',
  `FLAG` int(11) DEFAULT NULL COMMENT '标志1-正常 10-删除',
  `IS_SYS` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '系统资源，1是，0否'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cp_sys_resource` */

insert  into `cp_sys_resource`(`RES_ID`,`RES_NAME`,`RES_TYPE`,`URL`,`RES_LOGO`,`PARENT_ID`,`RES_ORDER`,`RES_LEVEL`,`UPDATE_ID`,`UPDATE_TIME`,`FLAG`,`IS_SYS`) values (1,'资源',1,NULL,'root','0',1,1,'1','2019-03-27',1,'1'),(2,'系统管理',1,NULL,'sys','1',9,2,'1','2016-12-19',1,'1'),(3,'用户管理',1,NULL,'user','2',1,3,'1','2016-12-20',1,'1'),(4,'用户列表',1,'user/userList','userlist','3',1,4,'1','2016-12-12',1,'1'),(5,'用户新增',2,NULL,'useradd','4',2,5,'1','2017-05-10',1,'1'),(6,'用户编辑',2,NULL,'useredit','4',3,5,'1','2017-05-10',1,'1'),(7,'用户删除',2,NULL,'userdel','4',4,5,'1','2017-05-10',1,'1'),(8,'密码修改',1,'user/toResetPsd','psdupd','3',2,4,'1','2016-12-20',1,'1'),(13,'权限管理',1,NULL,'perm','2',2,3,'1','2016-12-19',1,'1'),(14,'角色列表',1,'role/roleList','role','13',1,4,'1','2016-12-19',1,'1'),(15,'资源管理',1,'res/resAllList','res','13',2,4,'1','2016-12-19',1,'1'),(21,'角色新增',2,NULL,'roleadd','14',2,5,'1','2017-05-10',1,'1'),(22,'角色编辑',2,NULL,'roleedit','14',3,5,'1','2017-05-10',1,'1'),(23,'角色删除',2,NULL,'roledel','14',4,5,'1','2017-05-10',1,'1'),(24,'权限设置',2,NULL,'roleset','14',5,5,'1','2017-06-27',1,'1'),(25,'资源新增',2,NULL,'resadd','15',2,5,'1','2017-05-10',1,'1'),(26,'资源编辑',2,NULL,'resedit','15',3,5,'1','2017-05-10',1,'1'),(27,'资源删除',2,NULL,'resdel','15',4,5,'1','2017-05-10',1,'1'),(54,'用户查询',2,NULL,'userquery','4',1,5,'1','2017-05-10',1,'1'),(55,'角色查询',2,NULL,'rolequery','14',1,5,'1','2017-05-10',1,'1'),(56,'资源查询',2,NULL,'resquery','15',1,5,'1','2017-05-10',1,'1'),(79,'密码重置',2,NULL,'userpsdupd','4',5,5,'1','2018-10-10',1,'0'),(85,'数据字典',1,'dic/dicMain','dic','13',4,4,'1','2018-11-27',1,'0'),(88,'路径管理',1,'res/resList','res','13',5,4,'1','2019-04-02',1,'0'),(89,'导航菜单',1,NULL,'caidan1','1',1,2,'1','2019-05-10',1,'0'),(90,'Echar',1,NULL,'echar','89',1,3,'1','2019-05-10',1,'0'),(91,'分片区销售',1,'sale/saleInfo','saleInfo','90',1,4,'1','2019-05-10',1,'0'),(92,'项目',1,NULL,'project','89',2,3,'1','2019-05-13',1,'0'),(93,'项目立项',1,'pro/approvalList','approvalList','92',1,4,'1','2019-05-13',1,'0'),(94,'客户资料库',1,'custData/custDataList','custDataList','92',2,4,'1','2019-05-30',1,'0');

/*Table structure for table `cp_sys_role` */

DROP TABLE IF EXISTS `cp_sys_role`;

CREATE TABLE `cp_sys_role` (
  `ID` varchar(18) COLLATE utf8_bin NOT NULL,
  `ROLE_NAME` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `UPDATE_ID` varchar(18) COLLATE utf8_bin DEFAULT NULL,
  `UPDATE_TIME` date DEFAULT NULL,
  `ROLE_LOGO` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `FLAG` int(2) DEFAULT NULL,
  `IS_SYS` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '系统用户；1是 0否',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cp_sys_role` */

insert  into `cp_sys_role`(`ID`,`ROLE_NAME`,`DESCRIPTION`,`UPDATE_ID`,`UPDATE_TIME`,`ROLE_LOGO`,`FLAG`,`IS_SYS`) values ('1','管理员','系统管理员','1','2019-04-11','admin',1,'1'),('VORsn7xdK1xyY68mJW','用户权限','一般用户权限和反洗钱','1','2019-04-11','user1',1,'0'),('VUTOAalbbWfeUfO3BH','操作员','ddd','1','2019-04-11','admin1',1,'0'),('lPVfLsaTtM0grVDqzC','一般用户','浏览系统数据','W4K2dTE7sDq3CQnOl0','2019-04-17','user',1,'0');

/*Table structure for table `cp_sys_roleres` */

DROP TABLE IF EXISTS `cp_sys_roleres`;

CREATE TABLE `cp_sys_roleres` (
  `ID` varchar(18) COLLATE utf8_bin NOT NULL,
  `RES_ID` varchar(18) COLLATE utf8_bin DEFAULT NULL,
  `ROLE_ID` varchar(18) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cp_sys_roleres` */

insert  into `cp_sys_roleres`(`ID`,`RES_ID`,`ROLE_ID`) values ('6jFxBtwXgpdEXqiDXt','1','1'),('8RUMFRBCdcih8KBapX','2','1'),('BTYhxYHsc6ZAHFg9E3','13','1'),('RHyWwP9K9uZNBuQIFB','14','1'),('fJtuSjQU6wCefX89IR','21','1'),('PCJpW1QxYsymhXofgC','22','1'),('BGKt2fQmHfTKSkLLKg','23','1'),('D6OVQGIqQyALeL7jsc','24','1'),('ZSLbA6y6kHPho73iY6','55','1'),('RzuB9PqyC9iYIMLBpK','15','1'),('10JSfRaaxyOKc88KOe','25','1'),('PhrIg42SzCfAihLmC9','26','1'),('6FzmZTqii6W387Mcl5','27','1'),('wQtf3P9EvNokcB8Yey','56','1'),('uOL553vANZhpH8cMsd','85','1'),('aUdwNVBUkt4qO75APm','88','1'),('ttzjjORZ1PjhDmZFxp','3','1'),('yBObTV9czhvHcb07M2','4','1'),('WP5pcq4oBjuSqOMbgu','5','1'),('ie12YZWtpff1VS6q8X','54','1'),('b6ArGzAz5gYa2ONHmO','6','1'),('Ez5pQjBpj5JJM8uJ1b','7','1'),('WWALRrwYPQE7GSZuYT','79','1'),('O318ty9lk5Gh1yQmVn','8','1'),('Ubh214WPsYtvdeipFy','89','1'),('7ixluODm8EaCCWMyx5','90','1'),('S6M2QdxyITdIuNlG0a','91','1'),('7hGn33TIssMwse7Py5','92','1'),('JVP27jeI5qp2uPnI92','93','1'),('P3PemlaSPDIt5TREvL','94','1'),('tqrkRYiwn6GbFFE0rw','2','1'),('QXtg3Xoo5wwnr88Zsq','13','1'),('a1Sepr6Ggt9hi6kohB','14','1'),('zRuP4ha5owKXuQy245','21','1'),('x8hW5nyVfukJ3THO2C','22','1'),('cNJRTfRFBDxx7hUKuL','23','1'),('nxhpeH1DGWq0fTBZRO','24','1'),('bsy0KKe0FeaIH3GiKg','55','1'),('nkp4YCfhNF92ZnCjpa','15','1'),('brCubeRbB70rUhDoEt','25','1'),('RPDQaKMtbg9sFXPkj3','26','1'),('TO1CuN5F5I0aFZeluX','27','1'),('jjHeXkxCuQIDM9EDYk','56','1'),('Cz3LLTEl3TBpFEI2nI','85','1'),('lOSdYE4eAlHM0gcOPu','88','1'),('BRska45fVOCcdQOZN5','3','1'),('SJVC6OwfOYQQZzGBMC','4','1'),('5nErI959yYT7sq9lXH','5','1'),('zihfDBW7m5BGjAckId','54','1'),('geilFSpmdZ1vscEdkx','6','1'),('RCpNyM9vcy8jLdPcQo','7','1'),('2jUjpWYqIqRt1KEvoV','79','1'),('9mjY4kEq51bJSIxJ4l','8','1'),('89wSlZMnIt9vKuVNqL','13','1'),('85SmLpPO2zk3onJM0W','14','1'),('zQTeBZVLmirb1kksnC','21','1'),('dhWGyTMluB8yLrd2fb','22','1'),('adoyNtMTaUVMXpSuLu','23','1'),('vto05mnUcMhenyACiS','24','1'),('op27tGn3JCLVfdQfHj','55','1'),('cMkeOjX0lN5SmOe9iK','15','1'),('pp4AU3m0oVpvMnfX4C','25','1'),('CoxnD5lFoJcT3E8FY4','26','1'),('Doh00GQ4il8nyqOyV0','27','1'),('pwdodiyuK0ymoB76w6','56','1'),('U2iwn5Ga6bpv5N6BCE','85','1'),('JbWTYpyTt8IP3EFApq','88','1'),('Sz7o3JqrWywJO7cXm1','14','1'),('eYXPXpaKGpt3EqF0re','21','1'),('V9a8eshEaGnl19mHxV','22','1'),('8TddMbR3e5hxfbMY6i','23','1'),('t3TxYuqpj6X6cgXIp1','24','1'),('JzVFviNdetaIaHP1qp','55','1'),('d3NsecMR0r6FbgN285','21','1'),('wrTeAcaBTGmxGyGHXM','22','1'),('51P14xFov02Gml0KPe','23','1'),('C82WSz7itNj4ZBJd5M','24','1'),('C2LYSBtDxN0Z3caLFt','55','1'),('RgNgAauECt4sJDdaqT','15','1'),('fj0n6sh1zh4ALvlmM1','25','1'),('Vb9yNYOyWDoq7dHZex','26','1'),('E190hewTMz0tzKLt9W','27','1'),('dCKEPB463F7wt1a1W6','56','1'),('tyc3yXdnfNiPlMjaLe','25','1'),('NsiMb8iuRh8IZcF2Pd','26','1'),('mhxA1ZExtaIqw5ivHU','27','1'),('VJleQLk1FUhByUcz7l','56','1'),('8Up7mwSxzRg69Qtkua','85','1'),('bAJsBJeAfg8WBT5oUq','88','1'),('3Om8NnlYDkCWLjT3i0','3','1'),('alWGM6ikQ2EpkoJG1A','4','1'),('hKJTRKOKkBiire6eD2','5','1'),('3F5GamgJEYwbSE8t97','54','1'),('UUJH1LLKcJ9BbucvC3','6','1'),('RtRBUoyLcSP3d0D7ve','7','1'),('6LfG3yRG7zoPam0Rl7','79','1'),('gquGtbcCdxm29Fa13o','8','1'),('EwAUuGV5Zcv5lros18','4','1'),('jhfz8UYWOed5qmwT9Y','5','1'),('FuMGPlBqpdPTXJLfJb','54','1'),('lGBAJGeIeX1pAztLOw','6','1'),('Mb3TGUxIL9kNMgropG','7','1'),('YHjVpSbW6IHa7KuIqF','79','1'),('ZX3IwW3hQV7DhNyGHf','5','1'),('BMjkmRmiSqGodHAriE','54','1'),('0fNS5kiIIFX2MqPmo7','6','1'),('83nrxEAnzJOdQ13QdC','7','1'),('NLpthcPF7ibRt2Mg3y','79','1'),('gCxhlMDxEcfMFdtxXx','8','1'),('xYY1YHd4YvnJDTOoLT','89','1'),('zuliTUURV0CLHcYnHk','90','1'),('OSYQ4mPqPk3KMKyXmi','91','1'),('nhgBvgnIA4QAIdgnat','92','1'),('4frpwh2oUVDOpcMFKC','93','1'),('xniM3cKeEBdp2gP8Sl','94','1'),('cTIGJeLS3qrxqaK0vb','90','1'),('kyOkfBTp3Vuol3IRB5','91','1'),('Y59o2eSHokdpVFDeAh','91','1'),('T8asGJoDJZbPOHErkl','92','1'),('a04AsXWU7PqfkmPBeS','93','1'),('ppPVHz9947gUqdIZYh','94','1'),('fpeUkjdjCMJX6p4tA0','93','1');

/*Table structure for table `cp_sys_temlst` */

DROP TABLE IF EXISTS `cp_sys_temlst`;

CREATE TABLE `cp_sys_temlst` (
  `id` int(11) DEFAULT NULL,
  `nLevel` int(11) DEFAULT NULL,
  `sCort` varchar(8000) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cp_sys_temlst` */

insert  into `cp_sys_temlst`(`id`,`nLevel`,`sCort`) values (1,0,'1'),(2,1,'12'),(89,1,'189'),(3,2,'123'),(13,2,'1213'),(90,2,'18990'),(92,2,'18992'),(4,3,'1234'),(8,3,'1238'),(14,3,'121314'),(15,3,'121315'),(85,3,'121385'),(88,3,'121388'),(91,3,'1899091'),(93,3,'1899293'),(94,3,'1899294'),(5,4,'12345'),(6,4,'12346'),(7,4,'12347'),(54,4,'123454'),(79,4,'123479'),(21,4,'12131421'),(22,4,'12131422'),(23,4,'12131423'),(24,4,'12131424'),(55,4,'12131455'),(25,4,'12131525'),(26,4,'12131526'),(27,4,'12131527'),(56,4,'12131556');

/*Table structure for table `cp_sys_user` */

DROP TABLE IF EXISTS `cp_sys_user`;

CREATE TABLE `cp_sys_user` (
  `ID` varchar(18) COLLATE utf8_bin NOT NULL,
  `USER_CODE` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `USER_NAME` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `PASS_WORD` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `IS_SYS` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '系统用户,1是 0否',
  `UPDATE_TIME` date DEFAULT NULL,
  `DEPT_ID` varchar(18) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cp_sys_user` */

insert  into `cp_sys_user`(`ID`,`USER_CODE`,`USER_NAME`,`PASS_WORD`,`IS_SYS`,`UPDATE_TIME`,`DEPT_ID`) values ('1','admin','超级管理员','admin','1','2019-04-17',NULL),('W4K2dTE7sDq3CQnOl0','aaa','qq','123','0','2019-04-17','');

/*Table structure for table `cp_sys_userrole` */

DROP TABLE IF EXISTS `cp_sys_userrole`;

CREATE TABLE `cp_sys_userrole` (
  `ID` varchar(18) COLLATE utf8_bin NOT NULL,
  `USER_ID` varchar(18) COLLATE utf8_bin DEFAULT NULL,
  `ROLE_ID` varchar(18) COLLATE utf8_bin DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cp_sys_userrole` */

insert  into `cp_sys_userrole`(`ID`,`USER_ID`,`ROLE_ID`) values ('w6s0zgRh5EWUx2yMtL','W4K2dTE7sDq3CQnOl0','lPVfLsaTtM0grVDqzC'),('DufyPknmzmlHcLHuoy','1','1');

/*Table structure for table `sp_customer` */

DROP TABLE IF EXISTS `sp_customer`;

CREATE TABLE `sp_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cusid` varchar(50) COLLATE utf8_bin NOT NULL,
  `cusname` varchar(50) COLLATE utf8_bin NOT NULL,
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `brithday` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `destric` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `updatetime` date DEFAULT NULL,
  `lasttime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sp_customer` */

insert  into `sp_customer`(`id`,`cusid`,`cusname`,`phone`,`address`,`brithday`,`email`,`destric`,`updatetime`,`lasttime`) values (1,'A1001','Shermain','13152468462','南昌','1989-04-21','4379654@qq.com','aaaaaaaa','2018-10-29','2018-10-29'),(2,'A1002','Item Product','14587652249','杭州','1999-05-14','7821656@163.com','bbbbbbbb','2018-11-08','2018-11-08'),(3,'A1003','Work Reng','17854624566','川南','1991-05-16','98568268@163.com','acdsdf','2018-11-08','2018-11-08'),(4,'A1004','Ling Da','15746248405','大理','1994-10-12','95423651@163.com','ijdkxua','2018-11-08','2018-11-08'),(5,'A1005','WenChuan','13654268460','辽宁','1989-12-22','631566412@163.com','liao ning','2018-11-08','2018-11-08'),(6,'A1006','wang wu','19256085420','四川','1993-11-08','68454967@163.com','shi chuan','2018-11-08','2018-11-08'),(7,'A1007','Li Shi','18364235879','唐炒板粒','1992-3-05','djch234@163.xom','tangchaobanli','2018-11-08','2018-11-08'),(8,'A1008','zhuang san','1384384438','大唐经理','1996-3-24','426512345@qq.com','datangjingli','2018-11-08','2018-11-08'),(9,'A1009','fei ji ','16288788268','飞机','1997-4-30','459655966@qq.com','ddfewsdsf','2018-11-08','2018-11-08'),(10,'A1010','liang cheng','15656456654','凉城','1998-5-23','6554785456@qq.com','liang cheng','2018-11-08','2018-11-08'),(11,'A1011','Yi Ge Ren','11111111111','一个人','1993-2-22','123997939@qq.com','yi ge ren ','2018-11-08','2018-11-08');

/*Table structure for table `tb_cust_data` */

DROP TABLE IF EXISTS `tb_cust_data`;

CREATE TABLE `tb_cust_data` (
  `id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_must` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `tb_cust_data` */

insert  into `tb_cust_data`(`id`,`parent_id`,`name`,`is_must`,`order_num`) values (1,0,'资料库','1',1),(2,1,'客户资料','1',2),(3,1,'推荐方资料','1',3),(4,1,'尽调资料','1',4),(5,1,'审批资料','1',5),(6,1,'交易资料','1',6),(7,1,'贷后管理报告及相关资料','1',7),(100013,2,'合伙协议或企业组织文件、验资报告','1',1),(100014,2,'执行事务合伙人证明书及身份证明文件复印件','1',2);

/* Function  structure for function  `getChild` */

/*!50003 DROP FUNCTION IF EXISTS `getChild` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `getChild`(rootId INT) RETURNS varchar(1000) CHARSET utf8 COLLATE utf8_bin
BEGIN
        DECLARE ptemp VARCHAR(1000);
        DECLARE ctemp VARCHAR(1000);
               SET ptemp = '#';
               SET ctemp =CAST(rootId AS CHAR);
               WHILE ctemp IS NOT NULL DO
                 SET ptemp = CONCAT(ptemp,',',ctemp);
                SELECT GROUP_CONCAT(res_id) INTO ctemp FROM cp_sys_resource   
                WHERE FIND_IN_SET(parent_id,ctemp)>0; 
               END WHILE;  
               RETURN ptemp;  
    END */$$
DELIMITER ;

/* Function  structure for function  `getChildList` */

/*!50003 DROP FUNCTION IF EXISTS `getChildList` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `getChildList`(rootId INT) RETURNS varchar(1000) CHARSET utf8 COLLATE utf8_bin
BEGIN
    DECLARE sTemp VARCHAR(1000);
    DECLARE sTempChd VARCHAR(1000);

    SET sTemp = '$';
    SET sTempChd =cast(rootId as CHAR);

    WHILE sTempChd is not null DO
        SET sTemp = concat(sTemp,',',sTempChd);
        SELECT group_concat(res_id) INTO sTempChd FROM  cp_sys_resource where parent_id in (sTempChd)>0  ;
    END WHILE;
    RETURN sTemp; 
END */$$
DELIMITER ;

/* Function  structure for function  `getParList` */

/*!50003 DROP FUNCTION IF EXISTS `getParList` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `getParList`(rootId INT) RETURNS varchar(1000) CHARSET utf8 COLLATE utf8_bin
BEGIN
    DECLARE sTemp VARCHAR(1000);
    DECLARE sTempPar VARCHAR(1000); 
    SET sTemp = ''; 
    SET sTempPar =rootId; 
 
    #循环递归
    WHILE sTempPar is not null DO 
        #判断是否是第一个，不加的话第一个会为空
        IF sTemp != '' THEN
            SET sTemp = concat(sTemp,',',sTempPar);
        ELSE
            SET sTemp = sTempPar;
        END IF;
        SET sTemp = concat(sTemp,',',sTempPar); 
        SELECT group_concat(res_id) INTO sTempPar FROM cp_sys_resource where parent_id<>res_id and FIND_IN_SET(parent_id,sTempPar)>0; 
    END WHILE; 
 
RETURN sTemp; 
END */$$
DELIMITER ;

/* Procedure structure for procedure `showTreeNodes_yongyupost2000` */

/*!50003 DROP PROCEDURE IF EXISTS  `showTreeNodes_yongyupost2000` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `showTreeNodes_yongyupost2000`(IN rootid INT)
BEGIN
 DECLARE Level int ;

 Set Level=0 ;
	delete from cp_sys_temlst ;
 INSERT into cp_sys_temlst SELECT RES_ID,Level,RES_ID FROM cp_sys_resource WHERE PARENT_ID=rootid order by RES_LEVEL,parent_id,res_order,RES_ID;
 WHILE ROW_COUNT()>0 DO
  SET Level=Level+1 ;
  INSERT into cp_sys_temlst 
   SELECT A.RES_ID,Level,concat(B.sCort,A.RES_ID) FROM cp_sys_resource A,cp_sys_temlst B 
    WHERE  A.PARENT_ID=B.ID AND B.nLevel=Level-1  ;
 END WHILE;

END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
