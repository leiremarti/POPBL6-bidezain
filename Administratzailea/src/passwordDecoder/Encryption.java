package passwordDecoder;

import org.apache.commons.codec.binary.Base64; 
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encryption {
	public static String decryptText(String msg, PrivateKey key)
			throws InvalidKeyException, UnsupportedEncodingException,
			IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException {
			Cipher.getInstance("RSA").init(Cipher.DECRYPT_MODE, key);
			return new String(Cipher.getInstance("RSA").doFinal(Base64.decodeBase64(msg)), "UTF-8");
			}

			public static String encryptText(String msg, PublicKey key)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException {
			Cipher.getInstance("RSA").init(Cipher.ENCRYPT_MODE, key);
			return Base64.encodeBase64String(Cipher.getInstance("RSA").doFinal(msg.getBytes("UTF-8")));
			}
}