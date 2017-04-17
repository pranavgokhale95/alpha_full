import static org.junit.Assert.*;

import org.junit.Test;


public class TestCaesar {

	@Test
	public void testCaesar() {
		String expectedOutput = "qfsgpsnbodf";
		String testCase = "performance";
		
		int charShift = 1;
		
		assertEquals(expectedOutput, Caesar.doEncryption(testCase, charShift));
	}
	
	@Test
	public void testMonoAlphabetic() {
		String expectedOutput = "LGYZVQKT";
		String testCase = "software";
		
		
		assertEquals(expectedOutput, Caesar.doEncryption(testCase));
	}
	
	@Test
	public void testCaesarNegative() {
		String expectedOutput = "Invalid Input";
		String testCase = "test";
		
		int charShift = -1;
		
		assertEquals(expectedOutput, Caesar.doEncryption(testCase, charShift));
	}
	
	@Test
	public void testMonoAlphabeticNegative() {
		String expectedOutput = "";
		String testCase = "";
		
		assertEquals(expectedOutput, Caesar.doEncryption(testCase));
	}

}
