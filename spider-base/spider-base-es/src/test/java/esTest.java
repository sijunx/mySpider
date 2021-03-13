import com.alibaba.fastjson.JSON;
import com.spider.base.es.ElasticSearchClientUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.action.search.SearchRequest;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.IOException;

public class esTest {

    public static void main(String[] arg){
        RestHighLevelClient restHighLevelClient = ElasticSearchClientUtil.getRestClient();
        System.out.println(restHighLevelClient);

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
//        queryBuilder.filter(QueryBuilders.termQuery("name", "tom"));

        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource().query(queryBuilder).version(true).from(0).size(10);
        String index = "logstash-2020.03.03";
        String type = "doc";
        SearchRequest request = Requests.searchRequest(index).types(type).source(sourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        SearchHits hits = response.getHits();
//        SearchHit[] hitsHits = hits.getHits();
        System.out.println("结果："+ JSON.toJSONString(response));

    }

}
