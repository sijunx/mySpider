//import com.spider.base.dbcp.SpiderDynamicDataSource;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class SpiderDataSourceTest {
//
//    private static final Pattern PATTERN = Pattern
//            .compile("((\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\:\\d{1,5})");
//
//    public static void main(String[] arg){
//        SpiderDynamicDataSource spiderDataSource = new SpiderDynamicDataSource();
//        System.out.println("test");
//
//        String ipPortStr = getIpPort("220.220.220.220:9090/test@root");
//
//        System.out.println(ipPortStr);
//    }
//
//    private static String getIpPort(String dataSourceStr) {
//        Matcher m = PATTERN.matcher(dataSourceStr);
//        if (m.find()) {
//            return m.group(1);
//        }
//        return "";
//    }
//
//}
