import static org.junit.Assert.*;

import org.junit.Test;

public class TestQuickSort {
	
	@Test
	public void test() throws InterruptedException{
		int[] expectedArray  = new int[]{13,14,22,38,45,48,58,69,72,79,81,93};
		int[] quick_sort_input = new int[]{38,81,22,48,13,69,93,14,45,58,79,72}; 
		QuickSort.quickSort(quick_sort_input, 0, 11);
		int[] actual_output = QuickSort.sortedArray(quick_sort_input);
		
		assertArrayEquals(expectedArray, actual_output);
	
	}
	
	@Test
	public void testNegative() throws InterruptedException{
		int[] expectedArray  = new int[]{};
		int[] quick_sort_input = new int[]{}; 
		QuickSort.quickSort(quick_sort_input, 0, 0);
		int[] actual_output = QuickSort.sortedArray(quick_sort_input);
		for(int i:actual_output)
			System.out.print(i + " ");
		assertArrayEquals(expectedArray, actual_output);
	
	}
	
}
