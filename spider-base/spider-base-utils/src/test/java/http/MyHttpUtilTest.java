package http;

import com.spider.base.http.DingDingUtil;
import com.spider.base.utils.MyHttpUtil;
import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class MyHttpUtilTest {

    public static void main(String[] arg){
        String monitorCountStr = "19";
        Integer monitorCount = 0;
        if(StringUtils.isNotBlank(monitorCountStr) && NumberUtils.isNumber(monitorCountStr)){
            monitorCount = new Integer(monitorCountStr);
        }


        String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        String frontStr = dateStr.substring(0, 10);
        String backStr = dateStr.substring(11, 19);

        String dateStr02 = new StringBuilder().append(frontStr).append("T").append(backStr).append("Z").toString();

//        String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");

        String url = "https://oapi.dingtalk.com/robot/send?access_token=11681ef2bf9db061c0b3d582c2a1d4e4f7c2f2b51a13ebc2a8153074b39430ac";
        //MyHttpUtil.send(url, "测试");
        DingDingUtil.sendDingMessage(url, "测试xx");
    }
}
