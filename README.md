# spider-dist
分布式爬虫

CREATE TABLE `sm_fund_url_similar` (
  `id` varchar(50) NOT NULL,
  `url_id_seed` varchar(50) NOT NULL COMMENT '种子url',
  `url_id_other` varchar(50) NOT NULL COMMENT '其他url',
  `similar` decimal(16,4) DEFAULT NULL COMMENT '相似度',
  `delete_flag` varchar(10) DEFAULT NULL COMMENT '删除标记0:未删除 1:已删除',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文本相似度表';
# sm_fund_input_data
Document document = new Document("url", url_link).
                                        append("txt", txt).
                                        append("summary", summary02).
                                        append("title", title02).
                                        append("url_id", urlId).
                                        append("hots", hots);
# sm_fund_url_similar
 Document document = new Document("url_id_seed", urlIdSeed).
                          append("url_id_other", urlIdOther).
                          append("similar", similar).
                          append("delete_flag", deleteFlag).
                          append("create_time", createTime);

INSERT INTO `sm_fund_time_plan` (`time_plan_id`, `name`, `rule`, `interface_name`, `isenable`, `time_plan_type`, `status`, `create_time`, `modified_time`)
 VALUES ('741f803206da0oijb0cce28954538831', '爬虫抓取数据', '0 * 10 * * ?', 'SpiderCalScheduleService', 'E', 'HBXJ', '0', '2017-06-10 14:25:47', '2017-06-10 14:37:29');

delete from sm_fund_url;

INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.dahansoft.com/', '0', '2017-05-05 12:00:12','http://www.dahansoft.com/',1000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.163.com/', '0', '2017-05-05 12:00:12','http://www.163.com/',1000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.qq.com/', '0', '2017-05-05 12:00:12','http://www.qq.com/',1000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.sohu.com/', '0', '2017-05-05 12:00:12','http://www.sohu.com/',1000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.sina.com.cn/', '0', '2017-05-05 12:00:12','http://www.sina.com.cn/',1000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.youku.com/', '0', '2017-05-05 12:00:12','http://www.youku.com/',1000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.sogou.com/', '0', '2017-05-05 12:00:12','http://www.sogou.com/',1000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.iqiyi.com/', '0', '2017-05-05 12:00:12','http://www.iqiyi.com/',1000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.4399.com/', '0', '2017-05-05 12:00:12','http://www.4399.com/',1000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.tieba.baidu.com/', '0', '2017-05-05 12:00:12','http://www.tieba.baidu.com/',1000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.56.com/', '0', '2017-05-05 12:00:12','http://www.56.com/',2000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.2345.com/', '0', '2017-05-05 12:00:12','http://www.2345.com/',2000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.hao123.com/', '0', '2017-05-05 12:00:12','http://www.hao123.com/',2000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.weibo.com/', '0', '2017-05-05 12:00:12','http://www.weibos.com/',2000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.tianya.cn/', '0', '2017-05-05 12:00:12','http://www.tianya.cn/',2000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.baike.com/', '0', '2017-05-05 12:00:12','http://www.baike.com/',2000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.58.com/', '0', '2017-05-05 12:00:12','http://www.58.com/',2000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.tv.sohu.com/', '0', '2017-05-05 12:00:12','http://www.tv.sohu.com/',2000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.people.com.cn/', '0', '2017-05-05 12:00:12','http://www.people.com.cn/',2000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.v.qq.com/', '0', '2017-05-05 12:00:12','http://www.v.qq.com/',4000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.ifeng.com/', '0', '2017-05-05 12:00:12','http://www.ifeng.com/',4000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.jingyan.baidu.com/', '0', '2017-05-05 12:00:12','http://www.jingyan.baidu.com/',4000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.1688.com/', '0', '2017-05-05 12:00:12','http://www.1688.com/',4000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.ganji.com/', '0', '2017-05-05 12:00:12','http://www.ganji.com/',4000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.eastmoney.com/', '0', '2017-05-05 12:00:12','http://www.eastmoney.com/',4000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.china.com/', '0', '2017-05-05 12:00:12','http://www.china.com/',5000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.mt.sohu.com/', '0', '2017-05-05 12:00:12','http://www.mt.sohu.com/',5000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.pptv.com/', '0', '2017-05-05 12:00:12','http://www.pptv.com/',5000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.mtime.com/', '0', '2017-05-05 12:00:12','http://www.mtime.com/',5000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.duowan.com/', '0', '2017-05-05 12:00:12','http://www.duowan.com/',5000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.jd.com/', '0', '2017-05-05 12:00:12','http://www.jd.com/',5000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.tudou.com/', '0', '2017-05-05 12:00:12','http://www.tudou.com/',5000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.tvmao.com/', '0', '2017-05-05 12:00:12','http://www.tvmao.com/',6000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.360doc.com/', '0', '2017-05-05 12:00:12','http://www.360doc.com/',6000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.le.com/', '0', '2017-05-05 12:00:12','http://www.le.com/',6000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.v.baidu.com/', '0', '2017-05-05 12:00:12','http://www.v.baidu.com/',6000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.chinaz.com/', '0', '2017-05-05 12:00:12','http://www.chinaz.com/',6000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.cntv.cn/', '0', '2017-05-05 12:00:12','http://www.cntv.cn/',6000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.xcar.com.cn/', '0', '2017-05-05 12:00:12','http://www.xcar.com.cn/',6000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.yxdown.com/', '0', '2017-05-05 12:00:12','http://www.yxdown.com/',7000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.ku6.com/', '0', '2017-05-05 12:00:12','http://www.ku6.com/',7000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.v.ifeng.com/', '0', '2017-05-05 12:00:12','http://www.v.ifeng.com/',7000,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.pconline.com.cn/', '0', '2017-05-05 12:00:12','http://www.pconline.com.cn/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.music.baidu.com/', '0', '2017-05-05 12:00:12','http://www.music.baidu.com/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.pc6.com/', '0', '2017-05-05 12:00:12','http://www.pc6.com/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.tianqi.com/', '0', '2017-05-05 12:00:12','http://www.tianqi.com/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.xuexila.com/', '0', '2017-05-05 12:00:12','http://www.xuexila.com/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.1905.com/', '0', '2017-05-05 12:00:12','http://www.1905.com/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.taobao.com/', '0', '2017-05-05 12:00:12','http://www.taobao.com/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.blog.sina.com.cn/', '0', '2017-05-05 12:00:12','http://www.blog.sina.com.cn/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.bbs.tianya.cn/', '0', '2017-05-05 12:00:12','http://www.bbs.tianya.cn/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.bilibili.com/', '0', '2017-05-05 12:00:12','http://www.bilibili.com/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.cncn.com/', '0', '2017-05-05 12:00:12','http://www.cncn.com/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.baike.baidu.com/', '0', '2017-05-05 12:00:12','http://www.baike.baidu.com/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.jjwxc.net', '0', '2017-05-05 12:00:12','http://jjwxc.net',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.120ask.com/', '0', '2017-05-05 12:00:12','http://www.120ask.com/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.wenku.baidu.com/', '0', '2017-05-05 12:00:12','http://www.wenku.baidu.com/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'http://www.gamersky.com/', '0', '2017-05-05 12:00:12','http://www.gamersky.com/',66,0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'https://hao.360.cn/', '0', '2017-05-05 12:00:12','https://hao.360.cn/',66, 0);
INSERT INTO `fund`.`sm_fund_url` (`id`, `url`, `delete_flag`, `create_time`, `root_url`,`hots`,`deep`) VALUES (REPLACE(UUID(), '-', ''), 'https://www.2345.com/', '0', '2017-05-05 12:00:12','https://www.2345.com/',66, 0);

update sm_fund_url set deep = 0;


ALTER TABLE sm_fund_url ADD COLUMN sort_code varchar(200) NULL DEFAULT NULL COMMENT '分类代码';
ALTER TABLE sm_fund_url ADD COLUMN hots   int(11) DEFAULT NULL   COMMENT '热度';
ALTER TABLE sm_fund_url ADD COLUMN deep   int(11) DEFAULT NULL   COMMENT '层深度';


# sm_fund_reverse_index
Document document = new Document("keyWordId", keyWordId).
                          append("urlId", urlId).
                          append("title", title).
                          append("summary", summary).
                          append("hots", hots).
                          append("pageSeq", pageSeq).
                          append("keyWords", keyWords);


db.getCollection('sm_fund_keywords').drop();
db.getCollection('sm_fund_black_words').drop();
db.getCollection('sm_fund_input_data').drop();
db.getCollection('sm_fund_reverse_index').drop();
db.getCollection('sm_fund_topic').drop();
db.getCollection('sm_fund_url').drop();
db.getCollection('sm_fund_words_disc').drop();

db.getCollection("sm_fund_url").insert ({"urlId" : 'e3a9c8d479b044949f95b262ed1e6e02', "url" : "http://www.cnblogs.com/chaohu13/p/5337498.html/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://www.cnblogs.com/","deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : '4506d401f3cc452190521d6e0f3163d2', "url" : "http://blog.csdn.net/qy20115549/article/details/52648631/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://blog.csdn.net/", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : 'd162bab5e5f345a58b161dbc467bb1f4', "url" : "http://www.runoob.com/mongodb/mongodb-java.html/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://www.runoob.com/", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : '51ad36fba00146d7849b5df7e96bb0d8', "url" : "https://www.zhihu.com/question/23401553?sort=created", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "https://www.zhihu.com/", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : '90ad36fba00146d784uiodf7e96bb9ij', "url" : "http://finance.sina.com.cn/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://www.sina.com.cn/", "deep" : 1});

db.getCollection("sm_fund_url").insert ({"urlId" : 'uio936fba00146d784uiodf7e96bb9ij', "url" : "http://www.cntv.cn/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://www.cntv.cn/", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : 'shd1236fba00146d784uiodf7e96bb9ij', "url" : "http://www.hao123.com//", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://www.hao123.com/", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : '985f36fba00146d784uiodf7e96bb9ij', "url" : "http://www.weibo.com/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://www.weibo.com/", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : 'kl8936fba00146d784uiodf7e96bb9ij', "url" : "http://www.cncn.com/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://www.cncn.com/", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : '45rt36fba00146d784uiodf7e96bb9ij', "url" : "https://www.2345.com/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "https://www.2345.com/", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : 'nm8736fba00146d784uiodf7e96bb9ij', "url" : "https://hao.360.cn/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "https://hao.360.cn/", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : '56fg36fba00146d784uiodf7e96bb9ij', "url" : "http://www.blog.sina.com.cn//", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://www.blog.sina.com.cn/", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : '12kl36fba00146d784uiodf7e96bb9ij', "url" : "http://www.chinaz.com/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://www.chinaz.com/", "deep" : 1});


db.getCollection('sm_fund_black_words').drop();
db.getCollection('sm_fund_flow').drop();
db.getCollection('sm_fund_image').drop();
db.getCollection('sm_fund_input_data').drop();
db.getCollection('sm_fund_keywords').drop();
db.getCollection('sm_fund_reverse_index').drop();
db.getCollection('sm_fund_topic').drop();
db.getCollection('sm_fund_url').drop();
db.getCollection('sm_fund_words_disc').drop();

db.getCollection("sm_fund_url").insert ({"urlId" : 'io5636fba00146d784uiodf7e96bb9ij', "url" : "http://bigcat2013.iteye.com/blog/2109633", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://bigcat2013.iteye.com", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : '455636fba001789784uiodf7e96bb9ij', "url" : "http://blog.csdn.net/cxmscb/article/details/71023576", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://bigcat2013.iteye.com", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : '6r5636fba00146d78uytodf7e96bb9ij', "url" : "http://blog.csdn.net/cwcww1314/article/details/73413169", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://bigcat2013.iteye.com", "deep" : 1});
db.getCollection("sm_fund_url").insert ({"urlId" : '6g5636fba00853d784uiodf7e96bb9ij', "url" : "https://iamtrask.github.io/2015/07/12/basic-python-network/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://bigcat2013.iteye.com", "deep" : 1});

db.getCollection("sm_fund_topic").insert ({"urlId" : 'io5636fba00146d784uiodf7e96bb9ij', "topicCode":"IT"});

db.getCollection("sm_fund_black_words").insert ({"wordId" : '89hjyt56a00146d784uiodf7e96bb9ij', "word":"关于"});
db.getCollection("sm_fund_black_words").insert ({"wordId" : '90io8986a00146d784uiodf7e96bb9ij', "word":"广告"});
db.getCollection("sm_fund_black_words").insert ({"wordId" : 'uisdfsf6a00146d784uiodf7e96bb9ij', "word":"联系"});
db.getCollection("sm_fund_black_words").insert ({"wordId" : 'iokldfs6a00146d784uiodf7e96bb9ij', "word":"登录"});
db.getCollection("sm_fund_black_words").insert ({"wordId" : '9089675656a00146d784uiodf7e969ij', "word":"博客"});
db.getCollection("sm_fund_black_words").insert ({"wordId" : '8956kljyt56a00146d784uiodf7e96bb', "word":"首页"});

db.getCollection('sm_fund_input_data').ensureIndex({"urlId":1})
db.getCollection('sm_fund_flow').ensureIndex({"flowId":1})
db.getCollection('sm_fund_keywords').ensureIndex({"urlId":1});
db.getCollection('sm_fund_reverse_index').ensureIndex({"urlId":1});
db.getCollection('sm_fund_url').ensureIndex({"urlId":1});
db.getCollection('sm_fund_words_dic').ensureIndex({"wordId":1});


http://121.40.187.38:8091/serverapi/homePageController?method=smjr.fund.spiderCalStart&version=1.0
http://121.40.187.38:8091/serverapi/homePageController?method=smjr.fund.keyExtractCalStart&version=1.0
http://121.40.187.38:8091/serverapi/homePageController?method=smjr.fund.summaryExtractCalStart&version=1.0
http://121.40.187.38:8091/serverapi/homePageController?method=smjr.fund.reverseIndexCalStart&version=1.0


http://127.0.0.1:8068/homePageController?method=smjr.fund.spiderCalStart&version=1.0
http://127.0.0.1:8068/homePageController?method=smjr.fund.keyExtractCalStart&version=1.0
http://127.0.0.1:8068/homePageController?method=smjr.fund.summaryExtractCalStart&version=1.0
http://127.0.0.1:8068/homePageController?method=smjr.fund.reverseIndexCalStart&version=1.0














