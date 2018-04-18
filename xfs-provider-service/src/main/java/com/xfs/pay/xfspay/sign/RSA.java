package com.xfs.pay.xfspay.sign;

import org.apache.log4j.Logger;
import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA{
	
	public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
	private static final Logger log = Logger.getLogger(RSA.class);
	
	/**
	* RSA签名
	* @param content 待签名数据
	* @param privateKey 商户私钥
	* @param input_charset 编码格式
	* @return 签名值
	*/
	public static String sign(String content, String privateKey, String input_charset)
	{
        try 
        {
        	PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) );
        	KeyFactory keyf 				= KeyFactory.getInstance("RSA");
        	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update( content.getBytes(input_charset) );
            byte[] signed = signature.sign();
            return Base64.encode(signed);
        }
        catch (Exception e) 
        {
			log.error("RSA.sign加密",e);
        }
        
        return null;
    }
	
	/**
	* RSA验签名检查
	* @param content 待签名数据
	* @param sign 签名值
	* @param ali_public_key 支付宝公钥
	* @param input_charset 编码格式
	* @return 布尔值
	*/
	public static boolean verify(String content, String sign, String ali_public_key, String input_charset)
	{
		try 
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        byte[] encodedKey = Base64.decode(ali_public_key);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		
			java.security.Signature signature = java.security.Signature
			.getInstance(SIGN_ALGORITHMS);
		
			signature.initVerify(pubKey);
			signature.update( content.getBytes(input_charset) );
		
			boolean bverify = signature.verify( Base64.decode(sign) );
			return bverify;
			
		} 
		catch (Exception e) 
		{
			log.error("SA验签名检查",e);
		}
		
		return false;
	}
	
	/**
	* 解密
	* @param content 密文
	* @param private_key 商户私钥
	* @param input_charset 编码格式
	* @return 解密后的字符串
	*/
	public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), input_charset);
    }

	
	/**
	* 得到私钥
	* @param key 密钥字符串（经过base64编码）
	* @throws Exception
	*/
	public static PrivateKey getPrivateKey(String key) throws Exception {

		byte[] keyBytes;
		
		keyBytes = Base64.decode(key);
		
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		
		return privateKey;
	}

	public static void main(String[] args) {
		String aa = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKgIaNtRCb+/SNY+yB16h/Yw9RbuHQSHiDwn43gwcEqUqnnWoGnA1lgN19HPt1xVxQLljKahBcVxSGk/6t0SCNXlZ9vN5G8tBkY9tuhN4Ek35COe62wJlWHKfoLOWKB+HcMHsWN2OnPu3ZUB2oB2Rua6Ad2mBf5VaztNlzvGGU8vAgMBAAECgYEAi+DUSR4g23pFC/SfacrCu/LtU/VMOqUIh+s8tKS+FZfEgkBksSRSSmLbslET9s1/f3XPrDaHM2GmQddvkDCfIPWq8Lgc+aXNyWLteSugOb5qMAE9qWjSJNfHzct3SDfqTZl8oX35bT/fhPm+vqh0t4EvQ5vX9B5IsPkMrSHwNmECQQDhHvKU7xYGpqVL9Ub4RRbmkTSNNVAWDEbxK8jXjErOMPTBBjLc1DMMMe0S/+j0NyUcM9HHr7cBC8aMxeggnvm1AkEAvxTVKYDC53XMrKbTnP/oilPvooyibLIz5b7jitk7IcBe6pfEmtmKZnfA90OWcSJbdngnCE7iMBsjSNWAjM7j0wJAJG6Lt4qRt/XUHlzNZXRY5mttY8TS9x55hKIfWLK9kGzfzzUfmhlFQZ36ze5YVt1txgik1HodVbAAK9wDWvQfWQJAQaL2VftU+A0O8X+c0SFWz61lc4C7inrsOM8JeniER2O9NvhLIKX5VJluP4GH+/TBVkCPp9rw93Va+XndXoDy4QJBAMnE0vutbDAxk5W5u8QTo3PSYsw8gRnawvMKibhCLu0lRN/17BSzDDs03Zg+CcEyBdxEuGpJDVirrHjqRrEAS58=";
		String sign = RSA.sign("amount=4736.65&appId=fd437b96311b4c0cbac63795c27208de&buyerId=1619&buyerName=1103test客户&buyerType=COPR&createBy=1173&discounted=3600.0&notifyUrl=&outerTradeNo=b20161103000001&payeeId=100&payeeName=北京庄智科技有限责任公司&payeeType=SERVICE&price=8336.65&productInfo=&productIntro=&returnUrl=&tradeType=PAY_FEE",aa,"UTF-8");
		System.out.println(RSA.sign("amount=4736.65&appId=fd437b96311b4c0cbac63795c27208de&buyerId=1619&buyerName=1103test客户&buyerType=COPR&createBy=1173&discounted=3600.0&notifyUrl=&outerTradeNo=b20161103000001&payeeId=100&payeeName=北京庄智科技有限责任公司&payeeType=SERVICE&price=8336.65&productInfo=&productIntro=&returnUrl=&tradeType=PAY_FEE",aa,"UTF-8"));
		String pubkey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoCGjbUQm/v0jWPsgdeof2MPUW7h0Eh4g8J+N4MHBKlKp51qBpwNZYDdfRz7dcVcUC5YymoQXFcUhpP+rdEgjV5WfbzeRvLQZGPbboTeBJN+QjnutsCZVhyn6Czligfh3DB7Fjdjpz7t2VAdqAdkbmugHdpgX+VWs7TZc7xhlPLwIDAQAB";
		System.out.println(RSA.verify("amount=4736.65&appId=fd437b96311b4c0cbac63795c27208de&buyerId=1619&buyerName=1103test客户&buyerType=COPR&createBy=1173&discounted=3600.0&notifyUrl=&outerTradeNo=b20161103000001&payeeId=100&payeeName=北京庄智科技有限责任公司&payeeType=SERVICE&price=8336.65&productInfo=&productIntro=&returnUrl=&tradeType=PAY_FEE",sign,pubkey,"UTF-8"));

//		try{
//			Map<String, Object> keys = com.xfs.pay.chanpay.sign.RSA.genKeyPair();
//			System.out.println(com.xfs.pay.chanpay.sign.RSA.getPrivateKey(keys));
//			System.out.println(com.xfs.pay.chanpay.sign.RSA.getPublicKey(keys));
//		}catch (Exception e){
//			e.printStackTrace();
//		}

	}
}
