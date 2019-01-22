



import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class RSACodeUtil {

    public static final String KEY_ALGORTHM = "RSA";

    private static final String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2R1XOJeXrCYAsKOHA7ELiIVzrfrFI7eD9LexmFe0Gs2i47zhoicTUP2MKvU5/s14Ze0fLeDe8hG8To+hL7oviTH4MF8SpY+lojj7vUMBzeu3v5Q1Px2VW1eNJ0aE7Bs1V7+LMYl/tjWIQbaFHxEXfguzb9X0o/SANSwS5cUyVzQIDAQAB";
    private static final String privateKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALZHVc4l5esJgCwo4cDsQuIhXOt+sUjt4P0t7GYV7QazaLjvOGiJxNQ/Ywq9Tn+zXhl7R8t4N7yEbxOj6Evui+JMfgwXxKlj6WiOPu9QwHN67e/lDU/HZVbV40nRoTsGzVXv4sxiX+2NYhBtoUfERd+C7Nv1fSj9IA1LBLlxTJXNAgMBAAECgYAFm2woS0InWMN4mElZhesIyb3yAJOzip3BLAh5m3MPIbW2+qThkltbrBd/3RLtGrdqUUCEIc6VHf3MvN4Id+4VmriQdTFi9SEr7GxVGJbHAstQrKfglyFPDhYTIFikDvxl4D98cEXy74Epr5wwgnQbQFi8+Z+Wp+vt5UW/MPSkgQJBAN9wT17D2rEW+VC8xJnJ8tAEfZDDUDvqfL8BQ0NXQri7HGYvsFMSax5rek5cjJVPangKUImOY3+5M/xf3geSbYMCQQDQ134F+a1c9t9KrqwfGx/1yoeqkXWDHJsunMrR38KC1q/OGR4fQOlcKDaILFXEucvXla2pUkqhpRgjg8hGs15vAkEAqj75ytvyMsKtfm4GYqN0Jjl1ryqSZMS6/hIpPRMs3HJ9JgMqF3HOOqRr0W9FErMrDYHWcakTeQsVaDNnil9wnwJAZ7Y8bXc6suoepZXtAF2WF5gGm5w1AXGZVyKiTmuSyysWj4FFxjuUKCIIQsPRrCqgomVAos+tJG06eZieQw4cnQJATuVeuGSu8zy8cm2r93KZ+mlqSu7ijwY2BjRI/1rqWF4dhScfZ+aLw72O7I8yPbeKEOWRZuHfzLEajOA3b4/wCg==";

    private static String encrypt(String content, PrivateKey privateKey) throws Exception{
        String encodeContent = null;
        Cipher cipher = Cipher.getInstance( "rsa" );
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        Base64 base64 = new Base64();
        byte [] cipherData = cipher.doFinal(content.getBytes());
        encodeContent = base64.encodeToString(cipherData);
        return encodeContent;
    }

    private static String decrypt(String content, PublicKey publicKey) throws Exception{
        Base64 base64 = new Base64();
        byte[] byteArray = base64.decodeBase64(content);
        String decodeContent = null;
        Cipher cipher = Cipher.getInstance( "rsa" );
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte [] cipherData = cipher.doFinal(byteArray);
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

    private static PublicKey getPublicKey(String publicKey) throws Exception{
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

    public static String encode(String content) throws Exception{
        return encrypt(content, getPrivateKey(privateKeyStr));
    }

    public static String decode(String content) throws Exception{
        return decrypt(content, getPublicKey(publicKeyStr));
    }

    public static void main(String[] args) {
        try {
            String content = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
//			String content = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
            String code = encode(content);
            System.out.println("code:"+code);
            String content2 = decode(code);
            System.out.println("content:"+content2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 