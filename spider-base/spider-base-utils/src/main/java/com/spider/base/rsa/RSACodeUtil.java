package com.spider.base.rsa;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSACodeUtil {

    public static final String KEY_ALGORTHM = "RSA";

    private static final String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2pZPO8h6jKXlj6mOZHtGUaEln/8XnyCyAhwrzbOD4fz+Sa2r9uxQACZSxzLXOFdpJ4v5xkEMp3zRQBC6ZTXJr0fWknVB6A8icM8q5V9r439jVMAeClD6ejBVCFZDb7XjqxmPzwGNtSPEI0FdukacPpay8nJ6z8ZOowENMtFaODQIDAQAB";
    private static final String privateKeyStr = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALalk87yHqMpeWPqY5ke0ZRoSWf/xefILICHCvNs4Ph/P5Jrav27FAAJlLHMtc4V2kni/nGQQynfNFAELplNcmvR9aSdUHoDyJwzyrlX2vjf2NUwB4KUPp6MFUIVkNvteOrGY/PAY21I8QjQV26Rpw+lrLycnrPxk6jAQ0y0Vo4NAgMBAAECgYAkcdOVhqBwZDUxp7Jp+klNeKW1F8dQoZOnrcqUmhQl4ZY4zDBUIsEx5LeD5tocHiYpeh7VYDjWoRAaK0t1EYwT+soZd2rMWok0yoYrcqU7k93hrRgXsyjF8gb1onymF2Di7ULvxRfi9wejOODcA+7bcJQLDKAdN4fPCx/Cf9NOAQJBAPEQaDGgtmqy88/6AgfxNywzxaKov77ZSCSLi1nleW8WSrH3Lw087jRCOJjnu1etKh7Gs1/OHCCAsKuw9vWVPIECQQDB9phhWFctdmDN8II0gzEW2UejkwOvY43UOkyU0w4AaW6RezcQdUA+CTFEGcMRW5NHNQ21G7rsjw1me6I88ruNAkEAqyxDUDoWTd2UUvHrz0fxLWn1xHKe9R3EpG6SHNtdRwxFLemfd370hhcfqTOjS7aiWgguBrGMN2gqC0A1nFzEAQJADzEOPEmcAl6MNHUK7av800pPVxtzpXAJsr6iMuHY7eVUGKbq6CoWzwUPS8MsyRZAEcqw+Sdj25VUulsimN12HQJBAOntb2XRxa9ZqLjZcZspQHYcX1PCjQ8kJ9Kgj8bPXlVbYmPKWjSov/kDiiJ7L95TeNTSSNDihpicBw8k9jR1QHQ=";

    private static String encrypt(String content, PrivateKey privateKey) throws Exception {
        String encodeContent = null;
        Cipher cipher = Cipher.getInstance("rsa");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        Base64 base64 = new Base64();
        byte[] cipherData = cipher.doFinal(content.getBytes());
        encodeContent = base64.encodeToString(cipherData);
        return encodeContent;
    }

    private static String decrypt(String content, PublicKey publicKey) throws Exception {
        Base64 base64 = new Base64();
        byte[] byteArray = base64.decodeBase64(content);
        String decodeContent = null;
        Cipher cipher = Cipher.getInstance("rsa");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] cipherData = cipher.doFinal(byteArray);
        return new String(cipherData);
    }

    private static PrivateKey getPrivateKey(String privateKeyStr2) throws Exception {

        byte[] keyBytes = decryptBASE64(privateKeyStr2);

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory;
        keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        return privateKey;
    }

    private static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = decryptBASE64(publicKey);
        //构造X509EncodedKeySpec对象
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        //指定加密算法
        KeyFactory keyFactory;
        keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
        return publicKey2;
    }

    private static byte[] decryptBASE64(String keys) {
        Base64 base64 = new Base64();
        return base64.decode(keys);
    }

    public static String encode(String content) throws Exception {
        return encrypt(content, getPrivateKey(privateKeyStr));
    }

    public static String decode(String content) throws Exception {
        return decrypt(content, getPublicKey(publicKeyStr));
    }

    public static void main(String[] args) {
        try {
            String content = "whoAmI";
//			String content = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
            String code = encode(content);
            System.out.println("code:" + code);
            String content2 = decode(code);
            System.out.println("content:" + content2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
