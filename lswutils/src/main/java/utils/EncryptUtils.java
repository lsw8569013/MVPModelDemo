package utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * 加密算法集合
 * 
 * @author
 */
public class EncryptUtils {
	public static final String Key = "Y2Y0NTlkYzMtY2EzOC00OTQ2";

	/**
	 * 请求参数body 加密
	 * @param data
	 * @return
	 */
	public static String getBase64EncodeBody(String data) {

		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(data);
			jsonObject.put("body", getBase64Encode(jsonObject.optString("body")));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	/**
	 * 3DES加密后的的Base64加密的结果
	 *
	 * @param data
	 * @return
	 */
	public static String getBase64Encode(String data) {
		byte[] encode = null;
		String result = null;
		try {
			encode = des3EncodeECB(Key.getBytes("UTF-8"),
					data.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (encode != null) {
			result = new BASE64Encoder().encode(encode);
		}
		return result;
	}

	/**
	 * Base64解密后的3DES解密结果
	 * 
	 * @param data
	 * @return
	 */
	public static String getBase64Decode(String data) {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b;
		byte[] cs = null;
		try {
			b = decoder.decodeBuffer(data);
			cs = ees3DecodeECB(Key.getBytes("UTF-8"), b);
			return new String(cs, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * ECB加密
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            要加密的数据
	 * @return 密文
	 * @throws Exception
	 */
	private static byte[] des3EncodeECB(byte[] key, byte[] data)
			throws Exception {
		SecretKey deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * ECB解密
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            解密数据
	 * @return 明文
	 * @throws Exception
	 */
	private static byte[] ees3DecodeECB(byte[] key, byte[] data)
			throws Exception {
		try { // 生成密钥
			SecretKey deskey = new SecretKeySpec(key, "DESede"); // 解密
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(data);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;

	}

	/**
	 * MD5加密
	 * 
	 * @param info
	 * @return
	 */
	public static String getMD5(String info) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(info.getBytes("UTF-8"));
			byte[] encryption = md5.digest();

			StringBuffer strBuf = new StringBuffer();
			for (int i = 0; i < encryption.length; i++) {
				if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
					strBuf.append("0").append(
							Integer.toHexString(0xff & encryption[i]));
				} else {
					strBuf.append(Integer.toHexString(0xff & encryption[i]));
				}
			}
			return strBuf.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			return "";
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}


}
