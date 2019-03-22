import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import serialize_test.Main01Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialzeTest {
	private static Logger logger = LoggerFactory.getLogger(SerialzeTest.class);
	private static final String CHARSET_UTF8 = "UTF-8";
	private static final String ISO_8859_1 = "GBK";//"ISO-8859-1";//;

	 public static void main(String[] arg){
		 Main01Test main01Test = new Main01Test();
		 String xx01 = serialize(main01Test);
		 Object yy01 = deserialization(xx01);
		 Main01Test main01Test1 = (Main01Test)yy01;
		 System.out.println("main01Test1:"+main01Test1.toString());

		QueueTest queueTest  = new QueueTest();
		queueTest.setName("hello 张三");
		String xx = serialize(queueTest);
		Object yy = deserialization(xx);

		QueueTest test = (QueueTest)yy;
		System.out.println("test:"+test.getName());
	 }

	public static String serialize(Object obj) {
		String serStr = null;
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			 ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
			objectOutputStream.writeObject(obj);
			serStr = byteArrayOutputStream.toString(ISO_8859_1);
			serStr = java.net.URLEncoder.encode(serStr, CHARSET_UTF8);
		} catch (Exception e) {
			logger.error("将Object序列化时出现异常：", e);
		}
		return serStr;
	}

	public static Object deserialization(String str) {
		Object newObj = null;
		try (
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
						java.net.URLDecoder.decode(str, CHARSET_UTF8).getBytes(ISO_8859_1));
				ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
			newObj = objectInputStream.readObject();
		} catch (Exception e) {
			logger.error("将Object反序列化时出现异常：", e);
		}
		return newObj;
	}

	private SerialzeTest() {

	}
}
