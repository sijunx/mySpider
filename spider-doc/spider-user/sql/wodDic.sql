DROP TABLE IF EXISTS `item_info`;

CREATE TABLE `item_info` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                           `item_code` varchar(100) not NULL default '' COMMENT '列代码',
                           `item_desc` varchar(200) not NULL default '' COMMENT '列描述',
                           `item_cname` varchar(200) not NULL default '' COMMENT '中文名字',
                           `item_ename` varchar(200) not NULL default '' COMMENT '英文名字',
                           `item_type` int(11) NOT NULL default 0 COMMENT '列类型',
                           `item_len` varchar(200) NOT NULL default '0' COMMENT '列长度',
                           `item_remark` varchar(200) not NULL default '' COMMENT '备注',
                           `create_time` datetime not NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
                           `create_user_id` bigint(20) not null default 0 COMMENT '创建人id',
                           `update_time` datetime not NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           `update_user_id` bigint(20) not null DEFAULT 0 COMMENT '更新人id',
                           `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记 0正常 1删除',
                           PRIMARY KEY (`id`),
                           KEY `item_code_key` (`item_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='列信息';







DROP TABLE IF EXISTS `item_reverse_index`;

CREATE TABLE `item_reverse_index` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `word_id` bigint(20) not null default 0 COMMENT '关键词id',
  `item_id` bigint(20) not null default 0 COMMENT '列id',
  `create_time` date not NULL  COMMENT '创建时间',
  `create_user_id` bigint(20) not null default 0 COMMENT '创建人id',
  `update_time` date not NULL  COMMENT '更新时间',
  `update_user_id` bigint(20) not null DEFAULT 0 COMMENT '更新人id',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  PRIMARY KEY (`id`),
  KEY `word_id_key` (`word_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='列倒序索引';

DROP TABLE IF EXISTS `word_info`;

CREATE TABLE `word_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `word_cn_name` varchar(200) not NULL default '' COMMENT '关键词中文',
  `word_en_name` varchar(200) not NULL default '' COMMENT '关键词英文',
  `create_time` date not NULL  COMMENT '创建时间',
  `create_user_id` bigint(20) not null default 0 COMMENT '创建人id',
  `update_time` date not NULL  COMMENT '更新时间',
  `update_user_id` bigint(20) not null DEFAULT 0 COMMENT '更新人id',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='关键词';


