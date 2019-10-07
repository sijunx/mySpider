

# reverseIndex
Document document = new Document("keyWordId", keyWordId).
                          append("urlId", urlId).
                          append("title", title).
                          append("summary", summary).
                          append("hots", hots).
                          append("pageSeq", pageSeq).
                          append("keyWords", keyWords);


db.getCollection('keyWords').drop();
db.getCollection('blackWords').drop();
db.getCollection('inputData').drop();
db.getCollection('reverseIndex').drop();
db.getCollection('topicUrl').drop();
db.getCollection('urlInfo').drop();
db.getCollection('wordsDic').drop();

db.getCollection("urlInfo").insert ({"urlId" : 'e3a9c8d479b044949f95b262ed1e6e02', "url" : "http://www.cnblogs.com/chaohu13/p/5337498.html/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://www.cnblogs.com/","deep" : 1});
db.getCollection("urlInfo").insert ({"urlId" : '4506d401f3cc452190521d6e0f3163d2', "url" : "http://blog.csdn.net/qy20115549/article/details/52648631/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://blog.csdn.net/", "deep" : 1});
db.getCollection("urlInfo").insert ({"urlId" : 'd162bab5e5f345a58b161dbc467bb1f4', "url" : "http://www.runoob.com/mongodb/mongodb-java.html/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://www.runoob.com/", "deep" : 1});
db.getCollection("urlInfo").insert ({"urlId" : '51ad36fba00146d7849b5df7e96bb0d8', "url" : "https://www.zhihu.com/question/23401553?sort=created", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "https://www.zhihu.com/", "deep" : 1});
db.getCollection("urlInfo").insert ({"urlId" : '90ad36fba00146d784uiodf7e96bb9ij', "url" : "http://finance.sina.com.cn/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://www.sina.com.cn/", "deep" : 1});


db.getCollection("urlInfo").insert ({"urlId" : 'io5636fba00146d784uiodf7e96bb9ij', "url" : "http://bigcat2013.iteye.com/blog/2109633", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://bigcat2013.iteye.com", "deep" : 1});
db.getCollection("urlInfo").insert ({"urlId" : '455636fba001789784uiodf7e96bb9ij', "url" : "http://blog.csdn.net/cxmscb/article/details/71023576", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://bigcat2013.iteye.com", "deep" : 1});
db.getCollection("urlInfo").insert ({"urlId" : '6r5636fba00146d78uytodf7e96bb9ij', "url" : "http://blog.csdn.net/cwcww1314/article/details/73413169", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://bigcat2013.iteye.com", "deep" : 1});
db.getCollection("urlInfo").insert ({"urlId" : '6g5636fba00853d784uiodf7e96bb9ij', "url" : "https://iamtrask.github.io/2015/07/12/basic-python-network/", "deleteFlag" : "0", "createTime" : "20170505120012", "rootUrl" : "http://bigcat2013.iteye.com", "deep" : 1});

db.getCollection("topicUrl").insert ({"urlId" : 'io5636fba00146d784uiodf7e96bb9ij', "topicCode":"IT"});

db.getCollection("blackWords").insert ({"wordId" : '89hjyt56a00146d784uiodf7e96bb9ij', "word":"关于"});
db.getCollection("blackWords").insert ({"wordId" : '90io8986a00146d784uiodf7e96bb9ij', "word":"广告"});
db.getCollection("blackWords").insert ({"wordId" : 'uisdfsf6a00146d784uiodf7e96bb9ij', "word":"联系"});
db.getCollection("blackWords").insert ({"wordId" : 'iokldfs6a00146d784uiodf7e96bb9ij', "word":"登录"});
db.getCollection("blackWords").insert ({"wordId" : '9089675656a00146d784uiodf7e969ij', "word":"博客"});
db.getCollection("blackWords").insert ({"wordId" : '8956kljyt56a00146d784uiodf7e96bb', "word":"首页"});

db.getCollection('inputData').ensureIndex({"urlId":1})
db.getCollection('urlflow').ensureIndex({"flowId":1})
db.getCollection('keyWords').ensureIndex({"urlId":1});
db.getCollection('reverseIndex').ensureIndex({"urlId":1});
db.getCollection('urlInfo').ensureIndex({"urlId":1});
db.getCollection('wordsDic').ensureIndex({"wordId":1});

















