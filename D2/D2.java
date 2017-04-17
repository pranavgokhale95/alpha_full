import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class D2 {
	public static int getRandom() {
		long seed = System.currentTimeMillis()%Integer.MAX_VALUE;
		
		int slen = (""+seed).length();
		
		seed++;
		
		long sq = seed*seed;
		
		String sqStr = ""+sq;
		
		int sqlen = sqStr.length();
		
		int a = (sqlen/2) - (slen/2);
		
		StringBuffer stringBuffer = new  StringBuffer();
		
		for(int i=a;i<a+slen;i++){
			stringBuffer.append(sqStr.charAt(i));
		}
		
		return (int)Long.parseLong(stringBuffer.toString());
	}
	
	public static final String ALGO = "Blowfish";

	public static void main(String[] args) throws Exception {
		
		byte[] key = MessageDigest.getInstance("SHA-1").digest((""+getRandom()).getBytes());
		
		key = Arrays.copyOf(key, 16);
		
		SecretKeySpec secretKeySpec = new SecretKeySpec(key,ALGO);
		
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		
		byte[] encrypted = cipher.doFinal("Pranav".getBytes());
		
		System.out.println("Encrypted String: " + new String(Base64.getEncoder().encode(encrypted)));
		
		Cipher decrypter = Cipher.getInstance(ALGO);
		decrypter.init(Cipher.DECRYPT_MODE, secretKeySpec);
		
		System.out.println("Decrypted String: " + new String(decrypter.doFinal(encrypted)));
		
	}

}