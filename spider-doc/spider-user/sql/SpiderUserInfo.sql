DROP TABLE IF EXISTS `user`;

CREATE TABLE `spider_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `idCard` varchar(64) DEFAULT NULL COMMENT '身份证',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `gender` tinyint(4) DEFAULT '0' COMMENT '性别 1:男 2:女',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39426 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

INSERT INTO `spider_user_info` VALUES (11, '李斯', '3107221990282736', '13999999999', 1);

