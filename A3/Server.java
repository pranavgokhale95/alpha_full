import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
public class Server { 
	   public static Scanner s = new Scanner(System.in); 
	   
	  
	   public int BoothsMultiplier(int n1, int n2) 
	    { 
	        int[] m = binary(n1); 
	        int[] m1 = binary(-n1); 
	        int[] r = binary(n2);        
	        int[] A = new int[9]; 
	        int[] S = new int[9]; 
	        int[] P = new int[9];        
	        for (int i = 0; i < 4; i++) 
	        { 
	            A[i] = m[i]; 
	            S[i] = m1[i]; 
	            P[i + 4] = r[i]; 
	        } 
	        display(A, 'A'); 
	        display(S, 'S'); 
	        display(P, 'P');        
	        System.out.println(); 
	 
	        for (int i = 0; i < 4; i++) 
	        { 
	            if (P[7] == 0 && P[8] == 0);          
	            else if (P[7] == 1 && P[8] == 0) 
	                add(P, S);                            
	            else if (P[7] == 0 && P[8] == 1) 
	                add(P, A);            
	            else if (P[7] == 1 && P[8] == 1); 
	 
	            rightShift(P); 
	            display(P, 'P'); 
	        } 
	        return getDecimal(P); 
	    } 
	   
	   public int getDecimal(int[] B) 
	    { 
	        int p = 0; 
	        int t = 1; 
	        for (int i = 7; i >= 0; i--, t *= 2) 
	            p += (B[i] * t); 
	        if (p > 64) 
	            p = -(256 - p); 
	        return p;        
	    } 
	   
	   public void rightShift(int[] A) 
	    {        
	        for (int i = 8; i >= 1; i--) 
	            A[i] = A[i - 1];        
	    } 
	   
	   public void add(int[] A, int[] B) 
	    { 
	        int carry = 0; 
	        for (int i = 8; i >= 0; i--) 
	        { 
	            int temp = A[i] + B[i] + carry; 
	            A[i] = temp % 2; 
	            carry = temp / 2; 
	        }        
	    } 
	   
	   public int[] binary(int n) 
	    { 
	        int[] bin = new int[4]; 
	        int ctr = 3; 
	        int num = n; 
	        if (n < 0) 
	            num = 16 + n; 
	        while (num != 0) 
	        { 
	            bin[ctr--] = num % 2; 
	            num /= 2; 
	        } 
	        return bin; 
	    } 
	   
	   public void display(int[] P, char ch) 
	    { 
	        System.out.print("\n"+ ch +" : "); 
	        for (int i = 0; i < P.length; i++) 
	        { 
	            if (i == 4) 
	                System.out.print(" "); 
	            if (i == 8) 
	                System.out.print(" "); 
	            System.out.print(P[i]); 
	        } 
	    } 

	      
	   public static void main(String argv[]) throws Exception 
	      { 
		 
		   Server boothserver =new Server(); 
		   	   
	   		 System.out.println(" Server is Running  " ); 
	         ServerSocket mysocket = new ServerSocket(5567); 
	 
	         while(true) 
	         { 
	            Socket connectionSocket = mysocket.accept(); 
	 
	            BufferedReader reader = 
	            		new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
	            BufferedWriter writer= 
	            		new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream())); 
	 
	            //writer.write("Booth Algorithm Assignment\n");            
	      
	            writer.flush(); 
	            String data1 = reader.readLine().trim(); 
	 
	            
	            writer.flush(); 
	            String data2 = reader.readLine().trim(); 
	 
	            int somenum1=Integer.parseInt(data1); 
	            int somenum2=Integer.parseInt(data2); 
	 
	            int result=boothserver.BoothsMultiplier(somenum1 , somenum2);          
	            System.out.println("Booth is done Displaying Result Below." ); 
	 
	            writer.write("\r\n= Result is  : "+result); 
	            writer.flush(); 
	            connectionSocket.close(); 
	         } 
	      } 
	}

