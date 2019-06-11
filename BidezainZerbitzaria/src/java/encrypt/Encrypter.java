
package encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Pasahitzak enkriptatzeko erabiltzen diren funtzioak definitzen dira.
 * @author user
 */
public class Encrypter {
	
    SecretKeySpec secretKey;
    byte[] key;
 
    /**
     * Encrypter klase berri bat sortzeko metodoa, pasahitzari klabe sekretua zehaztuz
     *
     *@param value
     *     allowed object is
     *     {@link String }
     */
    public Encrypter(String secret) {
    	setKey(secret);
    }
    
    /**
     * Pasahitzari klabe sekretu bat ezartzen duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * pasahitza enkriptatzen duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @return
     *     possible object is
     *     {@link String }
     */
    public String encrypt(String strToEncrypt)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    /**
     * pasahitza desenkriptatzen duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    public String decrypt(String strToDecrypt)
    {
        try
        {
            System.out.println("--->"+strToDecrypt);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
