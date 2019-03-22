import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectTest {
    private String name = "张三";
    private Map map = new HashMap<>();
    {
        map.put("xu", "许");
    }
    private String getName(String myName){
        System.out.println("myName:"+myName);
        return myName;
    }
    public void printMap(){
        System.out.println("map:"+map);
    }
    public static void main(String[] arg) throws Exception{
        //  通过反射拿到对象的私有变量
        Class<?> claz = Class.forName("ReflectTest");
        ReflectTest myObject = (ReflectTest)claz.newInstance();
        Field nameField = claz.getDeclaredField("name");
        Object obj = nameField.get(myObject);
        System.out.println("str:"+obj);
        //  通过反射拿到私有方法
        Method method = claz.getDeclaredMethod("getName", String.class);
        method.invoke(myObject, "王五");
        //  通过反射拿到Map，修改内容
        Field mapField = claz.getDeclaredField("map");
        Object obj01 = mapField.get(myObject);
        Map map01 = (Map)obj01;
        map01.put("xu","jack");
        System.out.println("obj01:"+obj01);
        myObject.printMap();
    }
}
