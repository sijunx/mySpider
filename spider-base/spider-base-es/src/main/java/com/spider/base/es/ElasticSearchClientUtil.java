package com.spider.base.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.ArrayList;
import java.util.List;

public class ElasticSearchClientUtil {

    private ElasticSearchClientUtil() {
    }

    /**
     * ES客户端
     */
    private static volatile RestHighLevelClient restHighLevelClient = null;


    private static RestHighLevelClient initClient() {
        //集群名
        //String clusterName = EsApolloConfig.getPartnerElasticClusterName();
        String clusterName = "我的集群";
        //rest节点列表
//        String restNodes = EsApolloConfig.getPartnerElasticClusterNodesRest();
//        if (StringUtils.isBlank(clusterName) || StringUtils.isBlank(restNodes)) {
//            throw new IllegalArgumentException("连接elasticsearch失败，无法获取配置信息。");
//        }
        List<HttpHost> esNodeList = getServerUrls();
        return new RestHighLevelClient(RestClient.builder(esNodeList.toArray(new HttpHost[]{})));
    }

    private static List<HttpHost> getServerUrls() {
//        String[] split = restNodes.split(",");
        List<HttpHost> list = new ArrayList<>();
//        for (String s : split) {
//            list.add(HttpHost.create(s));
//        }
        HttpHost httpHost = new HttpHost("106.13.93.110", 9200);
        list.add(httpHost);
        return list;
    }


    public static RestHighLevelClient getRestClient() {
        if (restHighLevelClient == null) {
            synchronized (ElasticSearchClientUtil.class) {
                if (restHighLevelClient == null) {
                    restHighLevelClient = initClient();
                }
            }
        }
        return restHighLevelClient;
    }

}
