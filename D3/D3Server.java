import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class D3Server {

	public static void main(String[] args) throws Exception{
		ServerSocket serverSocket = new ServerSocket(2122);
	
		Socket socket = serverSocket.accept();
		
		int len;
		byte[] keyBytes = null;
		
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		
		len = dataInputStream.readInt();
		
		if(len>0){
			keyBytes=new byte[len];
			dataInputStream.readFully(keyBytes);
		}
		
		X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(keyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		PublicKey publicKey = keyFactory.generatePublic(encodedKeySpec);
		
		len = dataInputStream.readInt();
		byte[] textBytes=null;

		if(len>0){
			textBytes=new byte[len];
			dataInputStream.readFully(textBytes);
		}	
		
		len = dataInputStream.readInt();
		byte[] signBytes=null;

		if(len>0){
			signBytes=new byte[len];
			dataInputStream.readFully(signBytes);
		}
		
		Signature signature = Signature.getInstance("SHA1withDSA","SUN");
		signature.initVerify(publicKey);
		
		signature.update(textBytes,0,textBytes.length);
		
		System.out.println("Recieved Signature: "  + new String(Base64.getEncoder().encode(signBytes)));
		
		if(signature.verify(signBytes)){
			System.out.println("Text verified Successfully");
		}else{
			System.out.println("Verification failed");
		}
		
		serverSocket.close();

	
	}
}