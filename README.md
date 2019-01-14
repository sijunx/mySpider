# spider-dist
分布式爬虫

搜索引擎设计和实现（附git源码）

简要说明
爬取网站，采用流程节点，用来处理摘要计算、关键字计算、相似度计算、热度计算，利用线程池启动多线程扫表的方式。数据经过流程计算以后，落库，搜索查询采用倒排索引原理实现快速搜索。
git地址：https://github.com/sijunx/mySpider
demo网址：www.dahansoft.com

实现步骤
1.收集一些网址，作为爬/虫的入口。
种子url表结构：
{
“_id” : ObjectId(“5a9ec8965dc54c4352310b3c”),
“urlId” : “io5636fba00146d784uiodf7e96bb9ij”,
“url” : “http://bigcat2013.iteye.com/blog/2109633”,
“deleteFlag” : “1”,
“createTime” : “20170505120012”,
“rootUrl” : “http://bigcat2013.iteye.com”,
“deep” : 1.0
}
urlId为随机生成，url为种子url，deleteFlag:0表示未删除1为已删除 rootUrl:根网站地址 deep:访问深度，深度越深表示层级越低。

2.整理行业类别，并设置类别的关键字（为后续网页行业归类做相似度计算依据）。
行业类别表结构：
{
“_id” : ObjectId(“5a9ec8b05dc54c4352310b3d”),
“urlId” : “io5636fba00146d784uiodf7e96bb9ij”,
“topicCode” : “IT”
}
urlId为行业代表url topicCode：表示行业类别编码

3.爬取网页(jsoup工具)，分析、识别、审核网页的内容（例如：是否为广告、内容过短），若内容审核通过，则落库。流程的第一个节点完成。
数据设计：
流程表结构：
{
“_id” : ObjectId(“5aa00c229ccee50cd0c5e5df”),
“flowId” : “83150063a6034a128e8ae8c03b1b648c”,
“urlId” : “d162bab5e5f345a58b161dbc467bb1f4”,
“nodeCode” : “UrlDataExtract”,
“startFlag” : “1”,
“endFlag” : “1”,
“seqNo” : 1.0
}
flowId：流程ID urlId：网页ID nodeCode：节点编码 startFlag:流程开始标记 1：已开始 0：未开始 endFlag：结束标记 0：未结束 1：已结束 seqNo：流程节点序号（升序编号）

网页数据表结构：
{
“_id” : ObjectId(“5aa2adb491bf5255c5c6b736”),
“urlId” : “9bc15a1c0e504deb807808d4c1f03c27”,
“url” : “http://www.runoob.com/angularjs/angularjs-http.html”,
“txt” : “AngularJS Http | 菜鸟教程 菜鸟教程 – 学的不仅是技术，更是梦想！ 首页 HTML CSS JavaScript jQuery Bootstrap SQL MySQL PHP Python2 Python3 C C++ C# Java 更多……此处省略”,
“title” : “AngularJS Http | 菜鸟教程”,
“hots” : 30.0
}
urlId：网页ID url:网页地址 txt:网页摘要（限制500字以内） title:标题名称 hots:表示搜索热度

4.网页摘要内容生成，关键词提取（Ikanalyzer开源）。关键词存入关键词表。
关键词表结构：
{
“_id” : ObjectId(“5aa00c779ccee50cd0c5e88a”),
“urlId” : “d162bab5e5f345a58b161dbc467bb1f4”,
“keyWord” : “数据库”,
“counts” : 10.0
}
urlId：网页ID keyWords：关键词描述 counts：关键词出现次数

数据字典表：
{
“_id” : ObjectId(“5aa00ce99ccee50cd0c5ec78”),
“wordId” : “9e83bed9f070416eb29e1b6f15664bcb”,
“word” : “网络”
}
wordId:字ID word:字描述

5.网页行业归类，与行业的关键词做相似度计算(矩阵相乘算法实现）。倒排索引生成，将url对应的id存入倒序索引表。
相似度表：
{
“_id” : ObjectId(“5aa00ce99ccee50cd0c5ec89”),
“urlIdSeed” : “4506d401f3cc452190521d6e0f3163d2”,
“urlId”:werqd09if3cc458yuef21d6e0f39ijnh
“similar” : 30.0
}
urlIdSeed：种子urlId urlId:网页ID similar:相似度数值（100分为上限）

倒序索引表：
{
“_id” : ObjectId(“5aa00ce99ccee50cd0c5ec66”),
“urlId” : “e3a9c8d479b044949f95b262ed1e6e02”,
“url” : “http://www.cnblogs.com/chaohu13/p/5337498.html/”,
“wordId” : “fa368b5b5b69461b988142f8c8e2230c”,
“title” : “Java 网络爬虫获取网页源代码原理及实现 - 编程小队 - 博客园”,
“summary” : “过配置文件将该请求转换为网站主页地址index.php或index.jsp或者index.html等） HTTP请求 HTTP对应的文件 ttp://www.baidu.com http://www.baidu.com/index.php http://www.sina.”,
“hots” : 30.0
}
urlId：网页ID url：网址 wordId：关键词ID title：标题 summary：摘要 hots:热度

技术工具
springBoot、mongodb
业余时间自己做了部分实现，欢迎一起交流讨论。












