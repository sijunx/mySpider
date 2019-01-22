package com.spider.dist.service.impl;

import com.spider.base.rsa.RSACodeUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by SK_ZARD on 2018/10/14.
 */
public class MyTest001 implements Serializable {

    public static void main(String[] arg)throws Exception{
        String encodeResult = RSACodeUtil.encode("你好加密测试");
        System.out.println("encodeResult:"+encodeResult);

        String decodeResult = RSACodeUtil.decode(encodeResult);
        System.out.println("decodeResult:"+decodeResult);
    }

}
