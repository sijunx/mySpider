package com.spider.base.rsa;

import java.math.BigDecimal;
import java.util.Date;

public class TokenUtil {

    private TokenUtil(){}

    public static boolean checkToken(Integer value){
        try {
            BigDecimal long01 = new BigDecimal(value).subtract(new BigDecimal("456789"));
            BigDecimal big = long01.divide(new BigDecimal("12321.0"));
            Date date = new Date();
            BigDecimal bigDecimal = new BigDecimal(date.getMinutes());
            big = big.subtract(bigDecimal);
            if (big.compareTo(new BigDecimal(1)) <= 1) {
                return true;
            }
        }catch (Exception e){
        }
        return false;
    }
}
