import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.util.Base64;

public class D3Client {

	public static void print(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args) throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");

		keyPairGenerator.initialize(1024);

		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		DSAPublicKey dsaPublicKey = (DSAPublicKey) keyPair.getPublic();

		DSAParams dsaParams = dsaPublicKey.getParams();

		BigInteger p = dsaParams.getP();
		BigInteger q = dsaParams.getQ();
		BigInteger g = dsaParams.getG();
		BigInteger y = dsaPublicKey.getY();

		print("p: " + p);
		print("q: " + q);
		print("g: " + g);
		print("y: " + y);

		DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();

		BigInteger x = dsaPrivateKey.getX();

		print("x: " + x);
		
		Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
		signature.initSign(dsaPrivateKey);
		
		String text = "Hello";
		byte[] textBytes = text.getBytes();
		signature.update(textBytes,0,textBytes.length);
		
		byte[] sign = signature.sign();
	
		System.out.println("Client Generated Signature: "  + new String(Base64.getEncoder().encode(sign)));

		Socket socket = new Socket("localhost",2122);
		
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

		byte[] keyBytes = dsaPublicKey.getEncoded();
		
		System.out.println("Sending public key");
		dataOutputStream.writeInt(keyBytes.length);
		dataOutputStream.write(keyBytes);

		System.out.println("Sending text key");
		dataOutputStream.writeInt(textBytes.length);
		dataOutputStream.write(textBytes);
		
		System.out.println("Sending signature");
		dataOutputStream.writeInt(sign.length);
		dataOutputStream.write(sign);
		
		dataOutputStream.close();
		socket.close();

		
	}

}