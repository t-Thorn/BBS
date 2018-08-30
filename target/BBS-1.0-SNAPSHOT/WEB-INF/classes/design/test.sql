/*
Navicat MySQL Data Transfer

Source Server         : thorn
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-07-30 14:52:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `grade` int(11) DEFAULT '0',
  `posttime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `postnum` int(11) DEFAULT '0',
  `lastposttime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `views` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `poster` (`username`),
  CONSTRAINT `poster` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `postid` int(11) NOT NULL,
  `floor` int(11) NOT NULL DEFAULT '1',
  `floorex` int(11) NOT NULL DEFAULT '-1',
  `content` varchar(255) NOT NULL DEFAULT '',
  `replyer` varchar(255) NOT NULL,
  `replytime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `lastfloor` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`postid`,`floor`,`floorex`),
  KEY `replyertouser` (`replyer`),
  CONSTRAINT `replyertouser` FOREIGN KEY (`replyer`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `replytopost` FOREIGN KEY (`postid`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(18) NOT NULL,
  `password` varchar(18) NOT NULL,
  `name` varchar(18) DEFAULT '',
  `age` int(11) DEFAULT '0',
  `gender` varchar(255) NOT NULL DEFAULT '',
  `identity` varchar(18) NOT NULL,
  `level` int(1) DEFAULT '1',
  `photo` varchar(255) DEFAULT '',
  `regdate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `fans` int(11) DEFAULT '0',
  `attention` text,
  `history` text,
  `collections` text,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
