DROP TABLE IF EXISTS `item_info`;

CREATE TABLE `item_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `itemCode` varchar(100) not NULL default '' COMMENT '列代码',
  `itemDesc` varchar(200) not NULL default '' COMMENT '列描述',
  `itemType` int(11) NOT NULL default 0 COMMENT '列类型',
  `itemLen` int(11) NOT NULL default 0 COMMENT '列长度',
  `createTime` date not NULL COMMENT '创建时间',
  `createUserId` bigint(20) not null default 0 COMMENT '创建人id',
  `updateTime` date not NULL COMMENT '更新时间',
  `updateUserId` bigint(20) not null DEFAULT 0 COMMENT '更新人id',
  `deleteFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  PRIMARY KEY (`id`),
  KEY `itemCode` (`itemCode`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='列信息';

DROP TABLE IF EXISTS `item_reverse_index`;

CREATE TABLE `item_reverse_index` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `wordId` bigint(20) not null default 0 COMMENT '关键词id',
  `itemId` bigint(20) not null default 0 COMMENT '列id',
  `createTime` date not NULL  COMMENT '创建时间',
  `createUserId` bigint(20) not null default 0 COMMENT '创建人id',
  `updateTime` date not NULL  COMMENT '更新时间',
  `updateUserId` bigint(20) not null DEFAULT 0 COMMENT '更新人id',
  `deleteFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  PRIMARY KEY (`id`),
  KEY `wordIdKey` (`wordId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='列倒序索引';

DROP TABLE IF EXISTS `word_info`;

CREATE TABLE `word_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `wordCnName` varchar(200) not NULL default '' COMMENT '关键词中文',
  `wordEnName` varchar(200) not NULL default '' COMMENT '关键词英文',
  `createTime` date not NULL  COMMENT '创建时间',
  `createUserId` bigint(20) not null default 0 COMMENT '创建人id',
  `updateTime` date not NULL  COMMENT '更新时间',
  `updateUserId` bigint(20) not null DEFAULT 0 COMMENT '更新人id',
  `deleteFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='关键词';









CREATE TABLE `pre_monitor_message_recv` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`message_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息类型',
`message_type_desc` varchar(100) NOT NULL DEFAULT '' COMMENT '消息类型描述',
`recv_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '接收人ID',
`recv_user_name` varchar(100) NOT NULL DEFAULT '' COMMENT '接收人姓名',
`rule_config_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '预警规则ID',
`ding_webhook` varchar(100) NOT NULL DEFAULT '' COMMENT '钉钉webhook',
`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人ID',
`create_user_name` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
`create_user_type` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人用户类型',
`update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人ID',
`update_user_name` varchar(100) NOT NULL DEFAULT '' COMMENT '修改人',
`update_user_type` varchar(100) NOT NULL DEFAULT '' COMMENT '修改人用户类型',
`is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0-正常 1-删除',
`drc_check_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '仅供DRC数据校验使用',
`ezone_shard_info` bigint(20) DEFAULT NULL COMMENT 'shard info',
PRIMARY KEY (`id`),
KEY `ix_rule_config_id` (`rule_config_id`),
KEY `ix_created_at` (`created_at`),
KEY `ix_updated_at` (`updated_at`),
KEY `ix_drc_check_time` (`drc_check_time`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8 COMMENT='前哨预警消息接收人表'





INSERT INTO `spider_user_info` VALUES (11, '李斯', '3107221990282736', '13999999999', 1);


CREATE TABLE `eleme_order1` (  `id` bigint(20) NOT NULL AUTO_INCREMENT,  `restaurant_id` int(11) NOT NULL,  `restaurant_name` varchar(255) NOT NULL,  `rst_owner_id` int(11) NOT NULL DEFAULT '0',  `user_id` int(11) DEFAULT NULL,  `user_name` varchar(255) DEFAULT NULL,  `detail_json` text NOTNULL,  `total` decimal(10,2) NOT NULL DEFAULT '0.00',  `deliver_fee` decimal(10,2) NOT NULL DEFAULT '0.00',  `is_online_paid` tinyint(1) NOT NULL DEFAULT '0',   `settled_at` datetime DEFAULTNULL,  `address` varchar(255) NOT NULL,  `phone` varchar(255) NOT NULL,  `restaurant_number` int(11) NOT NULL,  `ip` varchar(255) DEFAULT NULL,  `description` varchar(255) DEFAULT NULL,  `unique_id`varchar(255) DEFAULT NULL,  `auto_memo` varchar(255) DEFAULT NULL,  `order_mode` tinyint(4) NOT NULL DEFAULT '0',  `status_code` int(11) NOT NULL DEFAULT '0',  `refund_status` int(11) NOT NULL DEFAULT '0',  `is_book` tinyint(4) NOT NULL DEFAULT '0',  `deliver_time` datetime DEFAULT NULL,  `category_id` int(11) NOT NULL DEFAULT '1',  `come_from` tinyint(4) DEFAULT NULL,  `entry_id` int(11) DEFAULT NULL,  `time_spent` tinyint(4) DEFAULT NULL,  `coupon_id` int(11) DEFAULT NULL,`created_at` datetime DEFAULT NULL,  `invoice` varchar(255) NOT NULL DEFAULT '',  `attribute_json` text NOT NULL,  `geohash` bigint(20) NOT NULL DEFAULT '0',  `source` varchar(50) NOT NULL DEFAULT '',  `phone_rating` tinyint(4) NOT NULL DEFAULT '0',  `delivery_status` tinyint(4) NOT NULL DEFAULT '0',  `active_at` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',  `updated_at1` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3), `updated_at2` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATECURRENT_TIMESTAMP(3) ,PRIMARY KEY (`id`),KEY `phone` (`phone`),  KEY `unique_id` (`unique_id`),  KEY `created_at` (`created_at`),  KEY `rst_owner_id` (`rst_owner_id`),  KEY `settled_at` (`settled_at`),  KEY `idx_online_paid` (`is_online_paid`,`refund_status`),  KEY    `idx_refund` (`refund_status`),  KEY `geohash` (`geohash`),  KEY `coupon_id` (`coupon_id`),  KEY    `phone_rating` (`phone_rating`),  KEY `restaurant_id` (`restaurant_id`,`status_code`,`category_id`),  KEY `status_code` (`status_code`),  KEY `delivery_status` (`delivery_status`),  KEY `restaurant_id_created_at` (`restaurant_id`,`created_at`),  KEY `user_id` (`user_id`,`created_at`),  KEY `ix_updated_at` (`updated_at1`) ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;CREATE TABLE `eleme_order2` (  `id` bigint(20) NOT NULL AUTO_INCREMENT,`count` int(11) NOT NULL,`restaurant_id` int(11) NOT NULL,  `restaurant_name` varchar(255) NOT NULL,  `rst_owner_id` int(11) NOT NULL DEFAULT '0',  `user_id` int(11) DEFAULT NULL,  `user_name` varchar(255) DEFAULT NULL,  `detail_json` text NOT NULL,  `total` decimal(10,2) NOT NULL DEFAULT '0.00',  `deliver_fee` decimal(10,2) NOT NULL DEFAULT '0.00',  `is_online_paid` tinyint(1) NOT NULL DEFAULT '0',  `settled_at` datetimeDEFAULT NULL,  `address` varchar(255) NOT NULL,  `phone` varchar(255) NOT NULL,  `restaurant_number` int(11) NOT NULL,  `ip` varchar(255) DEFAULT NULL,  `description` varchar(255)DEFAULT NULL,  `unique_id` varchar(255) DEFAULT NULL,  `auto_memo` varchar(255) DEFAULT NULL,  `order_mode` tinyint(4) NOT NULL DEFAULT '0',  `status_code` int(11) NOT NULL DEFAULT '0',  `refund_status` int(11) NOT NULL DEFAULT '0',  `is_book` tinyint(4) NOT NULL DEFAULT '0',  `deliver_time` datetime DEFAULT NULL,  `category_id` int(11) NOT NULL DEFAULT '1',  `come_from` tinyint(4) DEFAULT NULL,  `entry_id` int(11) DEFAULT NULL,  `time_spent` tinyint(4) DEFAULT NULL,`coupon_id` int(11) DEFAULT NULL,  `created_at` datetime DEFAULT NULL,  `invoice` varchar(255) NOT NULL DEFAULT '',  `attribute_json` text NOT NULL,  `geohash` bigint(20) NOT NULL DEFAULT '0',  `source` varchar(50) NOT NULL DEFAULT '',  `phone_rating` tinyint(4) NOT NULL DEFAULT '0',  `delivery_status` tinyint(4) NOT NULL DEFAULT '0',  `active_at` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',  `updated_at1` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3), `updated_at2` timestamp(3) NOT NULL DEFAULTCURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) ,PRIMARY KEY (`id`),KEY `phone` (`phone`),  KEY `unique_id` (`unique_id`),  KEY    `created_at` (`created_at`),  KEY `rst_owner_id` (`rst_owner_id`),  KEY `settled_at` (`settled_at`),  KEY `idx_online_paid` (`is_online_paid`,`refund_status`),  KEY
`idx_refund` (`refund_status`),  KEY `geohash` (`geohash`),  KEY `coupon_id` (`coupon_id`),  KEY `phone_rating`(`phone_rating`),  KEY `restaurant_id` (`restaurant_id`,`status_code`,`category_id`),  KEY `status_code` (`status_code`),  KEY `delivery_status` (`delivery_status`),  KEY `restaurant_id_created_at` (`restaurant_id`,`created_at`),  KEY    `user_id` (`user_id`,`created_at`),  KEY `ix_updated_at` (`updated_at1`) ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


