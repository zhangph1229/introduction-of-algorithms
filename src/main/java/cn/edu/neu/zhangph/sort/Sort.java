package cn.edu.neu.zhangph.sort;

import java.util.Arrays;

public class Sort {
	public static void main(String[] args) {
		int[] num = {0, 5,2,3,7,4,1,-1};
		print(num);
//		insertSort(num);
//		bubbleSort(num);
//		selectSort(num);
//		quickSort(num, 0, num.length - 1);
		mergeSort(num, 0, num.length - 1);
//		heapSort(num);
		print(num);
	}
	/**
	 * 堆排序
	 * 最大堆
	 */
	public static void heapSort(int[] num){
		if(num.length < 2) return;
		buildMaxHeap(num);
		for (int i = num.length - 1; i > 1 ; i--) {
			int tmp = num[1];
			num[1] = num[i];
			num[i] = tmp;
			num[0] -= 1;
			maxHeapify(num, 1);
		}
	}
	/**
	 * 最大堆的建立
	 */
	public static void buildMaxHeap(int[] num){
		num[0] = num.length - 1;
		for(int i = (num.length - 1) / 2; i > 0; i--){
			maxHeapify(num, i);
		}
	}
	/**
	 * 最大堆的维护
	 */
	public static void maxHeapify(int[] num, int i){
		int left = left(i);
		int right = right(i);
		int largest = i;
		if(left <= num[0] && num[left] > num[i]){
			largest = left;
		} 
		if(right <= num[0] && num[right] > num[largest]) largest = right;
		if(largest != i){
			int tmp = num[i];
			num[i] = num[largest];
			num[largest] = tmp;
			maxHeapify(num, largest);
		}
	}
	private static int left(int i){
		return 2*i;
	}
	private static int right(int i){
		return 2*i + 1;
	}
	
	
	/**
	 * 归并排序
	 * 分而治之的策略
	 */
	public static void mergeSort(int[] num, int low, int high){
		if(low < high){
			int mid = (low + high) / 2;
			mergeSort(num, low, mid);
			mergeSort(num, mid + 1, high);
			merge(num, low, mid, high);
		}
	}
	private static void merge(int[] num, int low, int mid, int high){
		int n1 = mid - low + 1;
		int n2 = high - mid;
		int[] left = new int[n1+1];
		int[] right = new int[n2+1];
		for (int i = 0; i < n1; i++) {
			left[i] = num[low + i];
		}
		for (int i = 0; i < n2; i++) {
			right[i] = num[mid + i + 1];
		}
		left[n1] = Integer.MAX_VALUE;
		right[n2] = Integer.MAX_VALUE;
		int i = 0, j = 0;
		for(int k = low ; k <= high; k++){
			num[k] = left[i] < right[j] ? left[i++] : right[j++];
 		}
	}
	/**
	 * 快速排序
	 * 找到一个枢轴变量，每次排序都将分成两个部分
	 */
	public static void quickSort(int[] num, int low, int high){
		if(low < high){
			int loc = qsort(num, low, high);
			quickSort(num, low, loc - 1);
			quickSort(num, loc + 1, high);
		}
	}
	private static int qsort(int[] num, int low, int high){
		int pivot = num[low];
		while(low < high){
			while(low < high && num[high] > pivot) high--;
			num[low] = num[high];
			while(low < high && num[low] < pivot) low++;
			num[high] = num[low];
		}
		num[low] = pivot;
		return low;
	}
	
	/**
	 * 选择排序
	 * i ~ [0, len - 1]
	 * 		j = selectMin(L, i)
	 * 		if(i != j) swap(i,j)
	 */
	public static void selectSort(int[] num){
		if(num.length < 2)return;
		for (int i = 0; i < num.length; i++) {
			int min = i;
			for (int j = i + 1; j < num.length; j++) {
				if(num[min] > num[j])min = j;
			}
			if(i != min){
				int tmp = num[i];
				num[i] = num[min];
				num[min] = tmp;
			}
		}
	}
	
	/**
	 * 冒泡排序
	 * 助记码：
	 * i ~ [0, len-1]
	 * 		j ~ [0, len - i - 1]
	 * 			swap(j, j+1)
	 */
	public static void bubbleSort(int[] num){
		if(num.length < 2)return;
		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length - i - 1; j++) {
				if(num[j] > num[j+1]){
					int tmp = num[j];
					num[j] = num[j+1];
					num[j+1] = tmp;
				}
			}
		}
	}
	/**
	 * 插入排序
	 * 对于每个元素插入有已经有序的数组中
	 * 助记码：
	 * i ~ [1, len - 1]
	 * 		x = num[i]
	 * 		j ~ [i-1, 0]
	 */
	public static void insertSort(int[] num){
		if(2 > num.length) return;
		for(int i = 1; i < num.length; i++){
			int tmp = num[i];
			int j = i - 1;
			for(; j >= 0 && num[j] > tmp; j--){
				num[j + 1] = num[j];
			}
			num[j + 1] = tmp;
		}
	}
	public static void print(int[] num){
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i] + " ");
		}
		System.out.println();
	}
}
