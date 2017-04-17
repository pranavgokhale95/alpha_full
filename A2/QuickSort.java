import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class QuickSort {
	
	public int add(int a,int b){
		return a+b;
	}
	
	public static void quickSort(final int arr[],final int low,final int high) throws InterruptedException{
		if(low>=high)
			return;
		
		final int p = partition(arr,low,high);
		
		Thread t1 = (new Thread(){
			@Override
			public void run() {
				try {
					System.out.println("Created thread with ID: " + this.getId());
					quickSort(arr, low, p-1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				super.run();
			}
		});

		t1.start();

		Thread t2 = new Thread(){
			public void run() {
				try {
					System.out.println("Created thread with ID: " + this.getId());
					quickSort(arr, p+1, high);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			};
		};
		
		t2.start();
		
		t1.join();
		t2.join();
		
	}
	
	private static int partition(int[] arr, int low, int high) {
		int i=low+1;
		int j=high;
		
		int pivot = arr[low];
		
		while(i<=j){
			
			while(i<=high && arr[i]<pivot)
				i++;
			
			while(j>=low && arr[j]>pivot)
				j--;
			
			if(i>j)
				break;
			
			int temp = arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
			
			i++;
			j--;
		}
		
		int temp = arr[j];
		arr[j]=pivot;
		arr[low]=temp;
		
		return j;
	}
	public static int[] sortedArray(int arr[])
	{
		
		return arr;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		
		File f = new File("/home/pranav/workspace/QuickSort/src/input.xml");
		
		FileReader fileReader = new FileReader(f);
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String s;
		
		ArrayList<Integer> alist = new ArrayList<>();
		
		while((s=bufferedReader.readLine())!=null){
			if(s.contains("items")){
				continue;
			}else if (!s.contains("item")){
				continue;
			}
			alist.add(Integer.parseInt(s.split(">")[1].split("<")[0]));
		}
		
		int arr[] = new int[alist.size()];
		
		for(int i=0;i<alist.size();i++){
			arr[i]=alist.get(i);
		}
		
		quickSort(arr, 0, alist.size()-1);
		arr=sortedArray(arr);

		System.out.println("Sorted List:");

		for(int i:arr)
			System.out.print(i + " ");
		
		System.out.println();
		bufferedReader.close();
		
	}

}
