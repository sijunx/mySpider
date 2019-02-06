package com.spider.base.spring.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.objenesis.strategy.StdInstantiatorStrategy;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class SpiderKryoRedisSerializer<T> implements RedisSerializer<T> {

    private Kryo kryo = new Kryo();

    static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    SpiderKryoRedisSerializer(){
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        ((Kryo.DefaultInstantiatorStrategy) kryo.getInstantiatorStrategy())
                .setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
    }
  
    @Override  
    public byte[] serialize(Object obj) throws SerializationException {  
        if (null == obj) {
			return EMPTY_BYTE_ARRAY;
		}
         return writeToByteArray(obj);
    }  
    
	static boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}

    @Override  
    public T deserialize(byte[] bytes) throws SerializationException {  
    	 if (isEmpty(bytes)) {
  			return null;
  		}
        return readFromByteArray(bytes);
    }

    public <T> byte[] writeToByteArray(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
        kryo.writeClassAndObject(output, obj);
        output.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public <T> T readFromByteArray(byte[] byteArray) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        Input input = new Input(byteArrayInputStream);
        return (T) kryo.readClassAndObject(input);
    }
}