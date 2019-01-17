import com.spider.base.es.ElasticSearchClientUtil;
import org.elasticsearch.client.RestHighLevelClient;

public class esTest {

    public static void main(String[] arg){
        RestHighLevelClient restHighLevelClient = ElasticSearchClientUtil.getRestClient();
        System.out.println(restHighLevelClient);
        System.exit(0);
    }

}
