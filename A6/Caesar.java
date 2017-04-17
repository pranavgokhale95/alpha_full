
import java.util.Scanner;

public class Caesar {

	public static char p[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z' };
	public static char ch[] = { 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O',
			'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C',
			'V', 'B', 'N', 'M' };

	public static String doEncryption(String s) {

		char c[] = new char[(s.length())];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < 26; j++) {
				if (p[j] == s.charAt(i)) {
					c[i] = ch[j];
					break;
				}
			}
			// System.out.println(s.charAt(i) + " " + c[i]);
		}
		return (new String(c));
	}

	public static String doEncryption(String text, int s) {
		if(s<0){
			return "Invalid Input";
		}
		
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < text.length(); i++) {
			if (Character.isUpperCase(text.charAt(i))) {
				char ch = (char) (((int) text.charAt(i) + s - 65) % 26 + 65);
				result.append(ch);
			} else {
				char ch = (char) (((int) text.charAt(i) + s - 97) % 26 + 97);
				result.append(ch);
			}
		}
		return result.toString();
	}

	// Driver code
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Password: ");
		String text = scanner.next();


		System.out.println("Enter choice\n0. Caesar Cipher\n1. Monoalphabetic Cipher\n");
		int choice=scanner.nextInt();
		
		switch (choice) {
		case 0:
			System.out.print("Enter Shift: ");
			int s = scanner.nextInt();
			System.out.println("Caesar Cipher: " + doEncryption(text, s));
			break;

		case 1:
			System.out.println("Monoalphabetic Cipher: " + doEncryption(text));
			break;
		}
		

		scanner.close();
	}

}
