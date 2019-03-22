import org.springframework.core.annotation.Order;

import java.util.concurrent.Callable;

public class MyCallAble implements Callable {

    @Override
    public Integer call(){
        System.out.println("----执行MyCallAble--call()-------------------");
        return 1;
    }
}
