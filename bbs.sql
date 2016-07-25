-- phpMyAdmin SQL Dump
-- version 2.10.2
-- http://www.phpmyadmin.net
-- 
-- 主机: localhost
-- 生成日期: 2015 年 04 月 08 日 01:18
-- 服务器版本: 5.0.45
-- PHP 版本: 5.2.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- 数据库: `bbs`
-- 

-- --------------------------------------------------------

-- 
-- 表的结构 `attention`
-- 

CREATE TABLE `attention` (
  `id` int(11) NOT NULL auto_increment,
  `uid` int(11) NOT NULL,
  `uuid` int(11) NOT NULL,
  `atime` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `uid` (`uid`),
  KEY `uuid` (`uuid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=26 ;

-- 
-- 导出表中的数据 `attention`
-- 

INSERT INTO `attention` VALUES (3, 6, 2, '2014-06-21');
INSERT INTO `attention` VALUES (4, 1, 6, '2014-06-21');
INSERT INTO `attention` VALUES (5, 11, 10, '2014-06-21');
INSERT INTO `attention` VALUES (6, 6, 1, '2014-06-22');
INSERT INTO `attention` VALUES (7, 1, 11, '2014-06-22');
INSERT INTO `attention` VALUES (9, 2, 1, '2014-06-22');
INSERT INTO `attention` VALUES (11, 6, 11, '2014-06-22');
INSERT INTO `attention` VALUES (12, 2, 11, '2014-06-23');
INSERT INTO `attention` VALUES (13, 13, 11, '2014-06-23');
INSERT INTO `attention` VALUES (14, 13, 10, '2014-06-23');
INSERT INTO `attention` VALUES (15, 13, 6, '2014-06-23');
INSERT INTO `attention` VALUES (16, 13, 4, '2014-06-23');
INSERT INTO `attention` VALUES (17, 13, 14, '2014-06-23');
INSERT INTO `attention` VALUES (18, 2, 14, '2014-06-23');
INSERT INTO `attention` VALUES (20, 16, 14, '2014-06-23');
INSERT INTO `attention` VALUES (21, 16, 13, '2014-06-23');
INSERT INTO `attention` VALUES (22, 16, 11, '2014-06-23');
INSERT INTO `attention` VALUES (23, 16, 10, '2014-06-23');
INSERT INTO `attention` VALUES (24, 16, 1, '2014-06-23');
INSERT INTO `attention` VALUES (25, 1, 10, '2014-06-23');

-- --------------------------------------------------------

-- 
-- 表的结构 `card`
-- 

CREATE TABLE `card` (
  `id` int(11) NOT NULL auto_increment,
  `uid` int(11) NOT NULL,
  `title` varchar(100) default NULL,
  `content` varchar(1000) default NULL,
  `ctime` datetime default NULL,
  `section` varchar(10) default NULL,
  `style` int(11) default '0',
  PRIMARY KEY  (`id`),
  KEY `uid` (`uid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=34 ;

-- 
-- 导出表中的数据 `card`
-- 

INSERT INTO `card` VALUES (1, 1, '测试', '测试的内容', '2014-06-03 00:00:00', '技术', 0);
INSERT INTO `card` VALUES (3, 1, 'come on', 'testcontent', '2014-06-16 02:42:22', '闲聊', 0);
INSERT INTO `card` VALUES (4, 1, '来吧', 'testcontent', '2014-06-16 02:44:32', '闲聊', 0);
INSERT INTO `card` VALUES (5, 1, 'test2', 'testcontent', '2014-06-16 02:44:53', '闲聊', 0);
INSERT INTO `card` VALUES (6, 1, 'test1', 'testcontent', '2014-06-16 02:46:24', '闲聊', 0);
INSERT INTO `card` VALUES (7, 1, 'test3', 'testcontent', '2014-06-16 02:47:47', '闲聊', 0);
INSERT INTO `card` VALUES (8, 1, '呵呵', '<img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/0.gif" border="0" alt="" /><img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/6.gif" border="0" alt="" />谢谢', '2014-06-16 02:54:53', '文学', 0);
INSERT INTO `card` VALUES (9, 2, '数据库', '<img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/31.gif" border="0" alt="" /><img src="http://192.168.3.76:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/13.gif" border="0" alt="" /><img src="http://192.168.3.76:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/10.gif" border="0" alt="" /><img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/42.gif" border="0" alt="" />', '2014-06-16 10:36:54', '生活', 0);
INSERT INTO `card` VALUES (10, 2, '我也来一个', '哇咔咔<img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/28.gif" border="0" alt="" />', '2014-06-16 10:38:39', '文学', 0);
INSERT INTO `card` VALUES (11, 2, '好厉害喔', '<p style="text-align:center;">\n	<span style="color:#4C33E5;font-size:32px;line-height:1.5;"><strong><u>12354667</u></strong></span>\n</p>\n<p style="text-align:center;">\n	<span style="color:#4C33E5;font-size:32px;line-height:1.5;"><img src="http://192.168.3.76:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/42.gif" border="0" alt="" /><strong></strong><br />\n</span>\n</p>', '2014-06-16 10:45:32', '技术', 0);
INSERT INTO `card` VALUES (12, 9, '发一次贴', '第一次发帖，谢谢大家', '2014-06-17 04:05:37', '文学', 0);
INSERT INTO `card` VALUES (13, 9, '再一次发帖', '再发一次贴', '2014-06-17 04:06:20', '文学', 0);
INSERT INTO `card` VALUES (14, 4, '撒地方', '撒地方撒旦飞洒地方撒地方撒旦撒地方', '2014-06-17 09:38:14', '文学', 0);
INSERT INTO `card` VALUES (15, 1, '测试', '测试', '2014-06-21 02:37:42', '闲聊', 0);
INSERT INTO `card` VALUES (16, 6, 'Hello，大家好', '<img src="http://192.168.180.165:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/21.gif" alt="" border="0" /><img src="http://192.168.180.165:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/0.gif" alt="" border="0" /><img src="http://192.168.180.165:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/13.gif" alt="" border="0" /><img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/28.gif" alt="" border="0" />好开心啊', '2014-06-21 04:35:14', '闲聊', 0);
INSERT INTO `card` VALUES (17, 10, '我也来试试', '123456', '2014-06-21 05:32:16', '闲聊', 0);
INSERT INTO `card` VALUES (22, 1, 'Hello，大家好', '<img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/21.gif" alt="" border="0" /><img src="http://192.168.180.165:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/0.gif" alt="" border="0" /><img src="http://192.168.180.165:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/13.gif" alt="" border="0" /><img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/28.gif" alt="" border="0" />好开心啊', '2014-06-21 04:35:14', '闲聊', 0);
INSERT INTO `card` VALUES (23, 1, '好厉害喔', '<p style="text-align:center;">\n	<span style="color:#4C33E5;font-size:32px;line-height:1.5;"><strong><u>12354667</u></strong></span>\n</p>\n<p style="text-align:center;">\n	<span style="color:#4C33E5;font-size:32px;line-height:1.5;"><img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/42.gif" border="0" alt="" /><strong></strong><br />\n</span>\n</p>', '2014-06-16 10:45:32', '技术', 0);
INSERT INTO `card` VALUES (24, 6, '[转]黄俊明', '<img border="0" alt="" src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/49.gif" />', '2014-06-21 11:18:50', '闲聊', 0);
INSERT INTO `card` VALUES (25, 13, '你干嘛做的这么好看', 'RT，做这么好看干嘛??太棒了= =&nbsp;<img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/43.gif" border="0" alt="" />', '2014-06-23 12:08:45', '闲聊', 0);
INSERT INTO `card` VALUES (26, 13, '在水一贴', '。。。', '2014-06-23 12:30:46', '闲聊', 0);
INSERT INTO `card` VALUES (27, 14, '第一次发帖', '发帖。。。。。。。。。发帖。。。。。。。。。发帖。。。。。。。。。发帖。。。。。。。。。', '2014-06-23 12:31:47', '文学', 0);
INSERT INTO `card` VALUES (28, 13, '赞。。', '。。', '2014-06-23 12:32:29', '技术', 0);
INSERT INTO `card` VALUES (29, 16, '我来测试发帖是否成功', '测试一下内容是否能发得出去。', '2014-06-23 11:42:19', '技术', 0);
INSERT INTO `card` VALUES (30, 16, '[转]我来测试发帖是否成功', '测试一下内容是否能发得出去。', '2014-06-23 11:42:19', '技术', 0);
INSERT INTO `card` VALUES (31, 16, '[转]come on', 'testcontent', '2014-06-16 02:42:22', '闲聊', 0);
INSERT INTO `card` VALUES (32, 16, '[转][转]come on', 'testcontent', '2014-06-16 02:42:22', '闲聊', 0);
INSERT INTO `card` VALUES (33, 6, '[转]Hello，大家好', '<img src="http://192.168.180.165:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/21.gif" alt="" border="0" /><img src="http://192.168.180.165:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/0.gif" alt="" border="0" /><img src="http://192.168.180.165:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/13.gif" alt="" border="0" /><img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/28.gif" alt="" border="0" />好开心啊', '2014-06-21 04:35:14', '闲聊', 0);

-- --------------------------------------------------------

-- 
-- 表的结构 `collect`
-- 

CREATE TABLE `collect` (
  `id` int(11) NOT NULL auto_increment,
  `uid` int(11) NOT NULL,
  `tid` int(11) default NULL,
  `ctime` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `uid` (`uid`),
  KEY `tid` (`tid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

-- 
-- 导出表中的数据 `collect`
-- 

INSERT INTO `collect` VALUES (1, 1, 1, '2014-06-11 17:52:54');
INSERT INTO `collect` VALUES (2, 6, 9, '2014-06-21 17:52:54');
INSERT INTO `collect` VALUES (3, 1, 3, '2014-06-21 00:00:00');
INSERT INTO `collect` VALUES (7, 16, 29, '2014-06-23 00:00:00');
INSERT INTO `collect` VALUES (8, 6, 16, '2014-06-23 00:00:00');

-- --------------------------------------------------------

-- 
-- 表的结构 `fans`
-- 

CREATE TABLE `fans` (
  `id` int(11) NOT NULL auto_increment,
  `uid` int(11) NOT NULL,
  `uuid` int(11) NOT NULL,
  `ftime` varchar(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `uid` (`uid`),
  KEY `uuid` (`uuid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=25 ;

-- 
-- 导出表中的数据 `fans`
-- 

INSERT INTO `fans` VALUES (1, 3, 1, '2014-06-21');
INSERT INTO `fans` VALUES (3, 6, 1, '2014-06-21');
INSERT INTO `fans` VALUES (4, 10, 11, '2014-06-21');
INSERT INTO `fans` VALUES (7, 2, 1, '2014-06-22');
INSERT INTO `fans` VALUES (8, 1, 2, '2014-06-22');
INSERT INTO `fans` VALUES (9, 6, 2, '2014-06-22');
INSERT INTO `fans` VALUES (11, 11, 2, '2014-06-23');
INSERT INTO `fans` VALUES (12, 11, 13, '2014-06-23');
INSERT INTO `fans` VALUES (13, 10, 13, '2014-06-23');
INSERT INTO `fans` VALUES (14, 6, 13, '2014-06-23');
INSERT INTO `fans` VALUES (15, 4, 13, '2014-06-23');
INSERT INTO `fans` VALUES (16, 14, 13, '2014-06-23');
INSERT INTO `fans` VALUES (17, 14, 2, '2014-06-23');
INSERT INTO `fans` VALUES (18, 1, 11, '2014-06-23');
INSERT INTO `fans` VALUES (19, 14, 16, '2014-06-23');
INSERT INTO `fans` VALUES (20, 13, 16, '2014-06-23');
INSERT INTO `fans` VALUES (21, 11, 16, '2014-06-23');
INSERT INTO `fans` VALUES (22, 10, 16, '2014-06-23');
INSERT INTO `fans` VALUES (24, 10, 1, '2014-06-23');

-- --------------------------------------------------------

-- 
-- 表的结构 `notify`
-- 

CREATE TABLE `notify` (
  `id` int(11) NOT NULL auto_increment,
  `uid` int(11) NOT NULL,
  `tid` int(11) default NULL,
  `stime` varchar(20) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `uid` (`uid`),
  KEY `tid` (`tid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

-- 
-- 导出表中的数据 `notify`
-- 

INSERT INTO `notify` VALUES (12, 1, 11, '2014-06-22');
INSERT INTO `notify` VALUES (14, 16, 29, '2014-06-23');
INSERT INTO `notify` VALUES (15, 16, 3, '2014-06-23');
INSERT INTO `notify` VALUES (16, 16, 31, '2014-06-23');
INSERT INTO `notify` VALUES (17, 6, 16, '2014-06-23');

-- --------------------------------------------------------

-- 
-- 表的结构 `reverts`
-- 

CREATE TABLE `reverts` (
  `id` int(11) NOT NULL auto_increment,
  `uid` int(11) NOT NULL,
  `tid` int(11) default NULL,
  `reply` varchar(300) default NULL,
  `rtime` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `uid` (`uid`),
  KEY `tid` (`tid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=37 ;

-- 
-- 导出表中的数据 `reverts`
-- 

INSERT INTO `reverts` VALUES (1, 2, 3, '1', '2014-06-03 00:00:00');
INSERT INTO `reverts` VALUES (2, 2, 1, 'sdfsa', '2014-06-03 00:00:01');
INSERT INTO `reverts` VALUES (3, 2, 4, '12', '2014-06-17 20:14:14');
INSERT INTO `reverts` VALUES (4, 2, 1, '12342', '2014-06-03 00:00:06');
INSERT INTO `reverts` VALUES (7, 1, 3, '3333', '2014-06-16 21:26:17');
INSERT INTO `reverts` VALUES (8, 1, 4, '测试恢复', '2014-06-16 21:31:37');
INSERT INTO `reverts` VALUES (9, 2, 1, '<img src="http://192.168.3.76:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/12.gif" border="0" alt="" />', '2014-06-16 22:35:57');
INSERT INTO `reverts` VALUES (10, 2, 11, '<img src="http://192.168.3.76:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/79.gif" border="0" alt="" /><img src="http://192.168.3.76:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/63.gif" border="0" alt="" />', '2014-06-16 22:47:47');
INSERT INTO `reverts` VALUES (11, 3, 9, 'sdaf sd', '2014-06-17 15:40:10');
INSERT INTO `reverts` VALUES (12, 3, 4, 'asdf sadf', '2014-06-17 15:43:22');
INSERT INTO `reverts` VALUES (13, 3, 9, 'asdfasd', '2014-06-17 15:44:14');
INSERT INTO `reverts` VALUES (14, 3, 11, '果然厉害', '2014-06-17 15:46:20');
INSERT INTO `reverts` VALUES (15, 3, 3, '我是来测试的', '2014-06-17 15:50:04');
INSERT INTO `reverts` VALUES (16, 3, 11, '<p>\n	我来测试\n</p>\n<p>\n	<br />\n</p>', '2014-06-17 15:58:14');
INSERT INTO `reverts` VALUES (17, 8, 11, '我还是来测试的', '2014-06-17 16:00:41');
INSERT INTO `reverts` VALUES (18, 9, 11, '快点测试', '2014-06-17 16:03:58');
INSERT INTO `reverts` VALUES (19, 9, 12, '第一次回帖', '2014-06-17 16:06:01');
INSERT INTO `reverts` VALUES (20, 9, 13, '<img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/18.gif" border="0" alt="" />huifu', '2014-06-17 16:07:27');
INSERT INTO `reverts` VALUES (21, 1, 11, '测试了窝，紧张么', '2014-06-21 14:37:11');
INSERT INTO `reverts` VALUES (22, 1, 15, '<p>\n	测试啦\n</p>\n<p>\n	<br />\n</p>', '2014-06-21 14:38:03');
INSERT INTO `reverts` VALUES (23, 1, 15, '测试', '2014-06-21 14:38:12');
INSERT INTO `reverts` VALUES (24, 1, 15, '测试十几条', '2014-06-21 14:38:21');
INSERT INTO `reverts` VALUES (25, 6, 16, '<img src="http://192.168.180.165:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/63.gif" alt="" border="0" />', '2014-06-21 16:47:05');
INSERT INTO `reverts` VALUES (26, 6, 16, '冒泡一下', '2014-06-21 16:51:37');
INSERT INTO `reverts` VALUES (32, 14, 27, '<p>\n	第一次回帖！！！\n</p>', '2014-06-23 00:32:20');
INSERT INTO `reverts` VALUES (33, 14, 25, '就是啊', '2014-06-23 00:33:14');
INSERT INTO `reverts` VALUES (34, 2, 27, '<img src="http://127.0.0.1:8080/bbs/public/js/kindeditor-4.1.10/plugins/emoticons/images/21.gif" border="0" alt="" />', '2014-06-23 00:51:25');
INSERT INTO `reverts` VALUES (35, 16, 29, '我来回复一下内容是否能回复成功', '2014-06-23 11:42:49');
INSERT INTO `reverts` VALUES (36, 16, 30, '我又测试一下转帖是否能评论', '2014-06-23 11:43:37');

-- --------------------------------------------------------

-- 
-- 表的结构 `score`
-- 

CREATE TABLE `score` (
  `id` int(11) NOT NULL auto_increment,
  `uid` int(11) NOT NULL,
  `intergral` int(11) default '0',
  PRIMARY KEY  (`id`),
  KEY `uid` (`uid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

-- 
-- 导出表中的数据 `score`
-- 

INSERT INTO `score` VALUES (9, 1, 30);
INSERT INTO `score` VALUES (10, 4, 10);
INSERT INTO `score` VALUES (11, 12, 10);
INSERT INTO `score` VALUES (12, 6, 10);
INSERT INTO `score` VALUES (13, 2, 10);
INSERT INTO `score` VALUES (14, 16, 10);

-- --------------------------------------------------------

-- 
-- 表的结构 `sign`
-- 

CREATE TABLE `sign` (
  `id` int(11) NOT NULL auto_increment,
  `uid` int(11) NOT NULL,
  `stime` date default NULL,
  PRIMARY KEY  (`id`),
  KEY `uid` (`uid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=24 ;

-- 
-- 导出表中的数据 `sign`
-- 

INSERT INTO `sign` VALUES (15, 1, '2014-06-21');
INSERT INTO `sign` VALUES (18, 1, '2014-06-22');
INSERT INTO `sign` VALUES (19, 4, '2014-06-22');
INSERT INTO `sign` VALUES (20, 12, '2014-06-23');
INSERT INTO `sign` VALUES (21, 6, '2014-06-23');
INSERT INTO `sign` VALUES (22, 2, '2014-06-23');
INSERT INTO `sign` VALUES (23, 16, '2014-06-23');

-- --------------------------------------------------------

-- 
-- 表的结构 `user`
-- 

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(20) NOT NULL,
  `password` varchar(16) NOT NULL,
  `email` varchar(25) default NULL,
  `phone` varchar(11) default NULL,
  `head` varchar(255) default 'public/attachment/header/default.jpg',
  `nickname` varchar(20) default NULL,
  `gender` int(1) default NULL,
  `birthday` varchar(30) default NULL,
  `description` varchar(1000) default NULL,
  `role` int(1) default '0',
  `regtime` datetime default NULL,
  `stars` varchar(20) default NULL,
  `favorite` varchar(255) default NULL,
  `address` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

-- 
-- 导出表中的数据 `user`
-- 

INSERT INTO `user` VALUES (1, 'admin', 'szu201', '123456@123.com', '13800138000', 'public/attachment/20140623014144.gif', '管理员', 1, '2014-05-015', '三好学生', 1, '2014-05-18 21:00:00', '天秤座', '打篮球', '深圳');
INSERT INTO `user` VALUES (2, 'member', '123456', NULL, NULL, 'public/attachment/header/default.jpg', '小强', 1, NULL, NULL, 0, '2014-05-18 21:00:01', '狮子座', NULL, '信宜');
INSERT INTO `user` VALUES (3, 'denggzh', 'szu201', 'aaa1138@sina.cn', NULL, 'public/attachment/header/default.jpg', '邓高柱', NULL, NULL, NULL, 0, '2014-06-18 21:00:07', NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, 'dengdeng', 'szu201', 'a@1113.cn', NULL, 'public/attachment/header/default.jpg', '邓邓', NULL, NULL, NULL, 0, '2014-06-18 21:00:06', NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, 'ling', 'szu201', 'a@112.cn', NULL, 'public/attachment/header/default.jpg', 'ling', NULL, NULL, NULL, 0, '2014-06-18 21:00:05', NULL, NULL, NULL);
INSERT INTO `user` VALUES (6, 'xiaocai', '111111', '1263149820@qq.com', '13800138000', 'public/images/head_images/woman.png', '小菜', 0, '2014-05-16', '好孩子', 0, '2014-06-18 21:00:00', '天秤座', '打球', '深圳');
INSERT INTO `user` VALUES (7, 'xiaoming', 'szu201', 'a@sina.cn', NULL, 'public/attachment/header/default.jpg', '小明', NULL, NULL, NULL, 0, '2014-06-18 21:00:01', NULL, NULL, NULL);
INSERT INTO `user` VALUES (8, 'dengden', 'szu201', 'a@sina.cn', NULL, 'public/attachment/header/default.jpg', 'dengden', NULL, NULL, NULL, 0, '2014-06-18 21:00:02', NULL, NULL, NULL);
INSERT INTO `user` VALUES (9, 'testtest', 'szu201', 'a@sina.cn', NULL, 'public/attachment/header/default.jpg', 'testtest', NULL, NULL, NULL, 0, '2014-06-18 21:00:03', NULL, NULL, NULL);
INSERT INTO `user` VALUES (10, 'xpecial', '123456', 'abc@hotmai.com', NULL, 'public/attachment/header/default.jpg', 'xpecial', NULL, NULL, NULL, 0, '2014-06-21 05:31:37', NULL, NULL, NULL);
INSERT INTO `user` VALUES (11, 'huangjunm', 'jiayou', '', '', 'public/attachment/header/default.jpg', 'huangjunm', 1, '22ss', '', 0, '2014-06-21 11:09:30', '', '', '');
INSERT INTO `user` VALUES (12, 'jeffxxxxx', '123123123', '123123@163.com', '11111111111', 'public/images/head_images/man.png', 'jeffxxxxx', 1, '1990-1-1', '213213123', 0, '2014-06-23 12:07:07', '123123', '213123123', '1231231231');
INSERT INTO `user` VALUES (13, '123456', '123...', '781490346@qq.com', '', 'public/attachment/header/default.jpg', '123456', 1, '', '', 0, '2014-06-23 12:07:49', '', '', '');
INSERT INTO `user` VALUES (14, 'tneey', 'a123123', 'qo1u2vot@126.com', NULL, 'public/images/head_images/barehead.png', 'tneey', NULL, NULL, NULL, 0, '2014-06-23 12:30:22', NULL, NULL, NULL);
INSERT INTO `user` VALUES (15, '12345', '123456', '764367454@qq.com', NULL, 'public/attachment/header/default.jpg', '12345', NULL, NULL, NULL, 0, '2014-06-23 12:32:10', NULL, NULL, NULL);
INSERT INTO `user` VALUES (16, 'goodboy', 'goodboy', 'aaa1138@sina.cn', '13760274260', 'public/attachment/header/default.jpg', '好男儿', 1, '', '男，帅', 0, '2014-06-23 11:37:53', '火星座', '女', '深圳大学');
