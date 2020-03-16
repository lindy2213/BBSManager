/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.22-log : Database - bbs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bbs` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bbs`;

/*Table structure for table `bbs_category` */

DROP TABLE IF EXISTS `bbs_category`;

CREATE TABLE `bbs_category` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(20) NOT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_category` */

/*Table structure for table `bbs_follow` */

DROP TABLE IF EXISTS `bbs_follow`;

CREATE TABLE `bbs_follow` (
  `userId` varchar(32) NOT NULL COMMENT '用户账户',
  `bUserId` varchar(32) NOT NULL COMMENT '被关注的用户账户',
  `followDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '关注的时间',
  PRIMARY KEY (`userId`,`bUserId`),
  KEY `bUserId` (`bUserId`),
  CONSTRAINT `bbs_follow_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `bbs_user` (`userId`),
  CONSTRAINT `bbs_follow_ibfk_2` FOREIGN KEY (`bUserId`) REFERENCES `bbs_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_follow` */

/*Table structure for table `bbs_invitation` */

DROP TABLE IF EXISTS `bbs_invitation`;

CREATE TABLE `bbs_invitation` (
  `invitationId` varchar(128) NOT NULL COMMENT '帖子的编号(账户名+系统时间)',
  `invitationMessage` text NOT NULL COMMENT '帖子信息',
  `userId` varchar(32) NOT NULL COMMENT '发帖人账户',
  `plateId` int(11) NOT NULL COMMENT '所属板块',
  `categoryId` int(11) NOT NULL COMMENT '所属类型',
  `isPass` int(11) NOT NULL DEFAULT '0' COMMENT '审核状态，默认为0',
  `isEnable` int(11) NOT NULL DEFAULT '0' COMMENT '是否屏蔽，默认为0',
  `isCream` int(11) NOT NULL COMMENT '是否是精华帖，默认为0',
  `invitationCreate` timestamp NULL DEFAULT NULL COMMENT '发帖时间',
  `invitationModify` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`invitationId`),
  KEY `userId` (`userId`),
  KEY `plateId` (`plateId`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `bbs_invitation_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `bbs_user` (`userId`),
  CONSTRAINT `bbs_invitation_ibfk_2` FOREIGN KEY (`plateId`) REFERENCES `bbs_plate` (`plateId`),
  CONSTRAINT `bbs_invitation_ibfk_3` FOREIGN KEY (`categoryId`) REFERENCES `bbs_category` (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_invitation` */

/*Table structure for table `bbs_invitation_ans` */

DROP TABLE IF EXISTS `bbs_invitation_ans`;

CREATE TABLE `bbs_invitation_ans` (
  `ansId` varchar(128) NOT NULL COMMENT '回复ID，回复账户_系统时间',
  `ansMessage` varchar(512) NOT NULL COMMENT '回复的内容',
  `invitationId` varchar(128) NOT NULL COMMENT '回复帖子ID',
  `userId` varchar(32) NOT NULL COMMENT '回帖人的账号',
  `ansDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '回帖时间',
  PRIMARY KEY (`ansId`),
  KEY `invitationId` (`invitationId`),
  KEY `userId` (`userId`),
  CONSTRAINT `bbs_invitation_ans_ibfk_1` FOREIGN KEY (`invitationId`) REFERENCES `bbs_invitation` (`invitationId`),
  CONSTRAINT `bbs_invitation_ans_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `bbs_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_invitation_ans` */

/*Table structure for table `bbs_level` */

DROP TABLE IF EXISTS `bbs_level`;

CREATE TABLE `bbs_level` (
  `levelId` int(11) NOT NULL AUTO_INCREMENT COMMENT '等级id',
  `levelMessage` varchar(8) NOT NULL COMMENT '等级信息',
  PRIMARY KEY (`levelId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `bbs_level` */

insert  into `bbs_level`(`levelId`,`levelMessage`) values (1,'初级会员'),(2,'中级会员'),(3,'高级会员');

/*Table structure for table `bbs_plate` */

DROP TABLE IF EXISTS `bbs_plate`;

CREATE TABLE `bbs_plate` (
  `plateId` int(11) NOT NULL AUTO_INCREMENT,
  `plateTitle` varchar(32) NOT NULL,
  `plateMessage` varchar(128) NOT NULL,
  `isEnable` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`plateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_plate` */

/*Table structure for table `bbs_store` */

DROP TABLE IF EXISTS `bbs_store`;

CREATE TABLE `bbs_store` (
  `userId` varchar(32) NOT NULL COMMENT '收藏的用户账户',
  `invitationId` varchar(128) NOT NULL COMMENT '收藏的帖子ID',
  `storeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏的时间',
  PRIMARY KEY (`userId`,`invitationId`),
  KEY `invitationId` (`invitationId`),
  CONSTRAINT `bbs_store_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `bbs_user` (`userId`),
  CONSTRAINT `bbs_store_ibfk_2` FOREIGN KEY (`invitationId`) REFERENCES `bbs_invitation` (`invitationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_store` */

/*Table structure for table `bbs_system_ans` */

DROP TABLE IF EXISTS `bbs_system_ans`;

CREATE TABLE `bbs_system_ans` (
  `systemId` varchar(128) NOT NULL COMMENT '使用Java的UUID来生成',
  `userId` varchar(32) NOT NULL COMMENT '回复的用户ID',
  `message` varchar(255) NOT NULL COMMENT '发送的消息内容',
  `isRead` int(11) NOT NULL DEFAULT '0' COMMENT '是否已读，默认为0',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`systemId`),
  KEY `userId` (`userId`),
  CONSTRAINT `bbs_system_ans_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `bbs_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_system_ans` */

/*Table structure for table `bbs_user` */

DROP TABLE IF EXISTS `bbs_user`;

CREATE TABLE `bbs_user` (
  `userId` varchar(32) NOT NULL COMMENT '用户账号',
  `userpsw` varchar(50) NOT NULL COMMENT '密码',
  `userEmail` varchar(64) NOT NULL COMMENT '邮箱',
  `userSex` varchar(1) DEFAULT '男' COMMENT '性别',
  `userPhoto` varchar(32) DEFAULT NULL COMMENT '头像',
  `userScore` double NOT NULL DEFAULT '0' COMMENT '积分',
  `userLevel` int(11) NOT NULL DEFAULT '1' COMMENT '用户等级',
  `levelDown` datetime DEFAULT NULL COMMENT '降级截至时间',
  `userLock` datetime DEFAULT NULL COMMENT '锁级截至时间',
  `userCreateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建用户的时间',
  PRIMARY KEY (`userId`),
  KEY `userLevel` (`userLevel`),
  CONSTRAINT `bbs_user_ibfk_1` FOREIGN KEY (`userLevel`) REFERENCES `bbs_level` (`levelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_user` */

insert  into `bbs_user`(`userId`,`userpsw`,`userEmail`,`userSex`,`userPhoto`,`userScore`,`userLevel`,`levelDown`,`userLock`,`userCreateDate`) values ('admin000','0192023a7bbd73250516f069df18b500','admin000@163.com','男','verycode.gif',0,1,NULL,NULL,'2020-03-16 11:35:00'),('admin001','e10adc3949ba59abbe56e057f20f883e','admin001@qq.com','男','verycode.gif',0,1,NULL,NULL,'2020-03-16 09:03:24'),('admin003','0192023a7bbd73250516f069df18b500','admin003@qq.com','男','button.gif',0,1,NULL,NULL,'2020-03-16 11:39:50'),('test','e10adc3949ba59abbe56e057f20f883e','test@qq.com','男','button.gif',0,1,NULL,NULL,'2020-03-16 13:50:11'),('test002','16d7a4fca7442dda3ad93c9a726597e4','test002@qq.com','男','button.gif',0,1,NULL,NULL,'2020-03-16 11:31:32'),('test003','e10adc3949ba59abbe56e057f20f883e','test003@qq.com','男','button.gif',0,1,NULL,NULL,'2020-03-16 08:53:37'),('test004','e10adc3949ba59abbe56e057f20f883e','test004@qq.com','女','button.gif',0,1,NULL,NULL,'2020-03-16 11:37:54'),('zhangsan','81dc9bdb52d04dc20036dbd8313ed055','zhangsan@163.com','男',NULL,0,1,NULL,NULL,'2020-03-14 22:59:28');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
