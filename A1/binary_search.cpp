#include <iostream>
#include <fstream>
#include <cstdlib>
#include <ctime>
#include <stdio.h>

using namespace std;

class BinarySearch{
private:
	int arr[1001];
	int n;

public:
	BinarySearch(){
		ifstream ifstrm("data.txt");
		string s;

		if(!ifstrm.is_open())
			return;
		int i=0;

		while(!ifstrm.eof()){
			getline(ifstrm,s); 
			arr[i] = atoi(s.c_str());
			i++;
		}
		n=i;

		clock_t begin = clock();

		quick_sort(arr,0,n-1);

		clock_t end = clock();

		double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;

		printf("Elapsed for sorting: %f seconds\n",time_spent);

	}


	int partition(int arr[],int low,int high){
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

		    arr[low]=arr[j];
		    arr[j]=pivot;
		    return j;

	}


	void quick_sort(int arr[],int low,int high){
	    if(low>=high)
		return;

	    int p =partition(arr,low,high);

	    quick_sort(arr,low,p);

	    quick_sort(arr,p+1,high);

	}

	void print(){
		for(int i=0;i<n;i++){
			cout<<arr[i]<<" ";
		}
		cout<<endl;
	}

	int binarySearch(int val){
		int mid;

		int low=0;
		int high=n-1;

		while(low<=high){
			mid =(low+high)/2;
			
			if(arr[mid]==val)
				return mid;

			if(arr[mid]<val){
				low=mid+1;			
			}else{
				high=mid-1;			
			}

		}		

		return -1;
	}

};

int main(){
	BinarySearch b;
	b.print();

	int find;
    cout<<"Enter element: ";
	cin>>find;

	clock_t begin = clock();

	find = b.binarySearch(find);

	clock_t end = clock();

	double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
	cout<<"Found at " << find<<endl;
	printf("Elapsed for searching: %f seconds\n",time_spent);

	return 0;
}
