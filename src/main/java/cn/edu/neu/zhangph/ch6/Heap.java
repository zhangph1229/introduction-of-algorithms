package cn.edu.neu.zhangph.ch6;

/**
 * 2016-6-27
 * @author zhangph
 * @see [算法导论 第六章]
 * 	堆排序
 */
public class Heap {
	public static void main(String[] args) {
//		int[] A = {10,16,4,10,14,7,9,3,2,8,1};
		int[] A = {0,4,1,3,2,16,9,10,14,8,7};
		show(A);
//		maxHeapify(A, 5);
//		show(A);
//		buildHeap(A);
//		show(A);
		heapSort(A);
		show(A);
	}
	public static void maxHeapify(int[] A, int i){
		//使用A[0]来存放 heap-size
		int l = left(i);
		int r = right(i);
		int largest = i;
		if(l <= A[0] && A[l] > A[i]){
			largest = l;
		}
		if(r <= A[0] && A[r] > A[largest]){
			largest = r;
		}
		if(largest != i){
			int tmp = A[i];
			A[i] = A[largest];
			A[largest] = tmp;
			maxHeapify(A, largest);
		}
	}
	public static void buildHeap(int[] A){
		A[0] = A.length - 1;
		for(int i = (int) Math.ceil(A[0]/2); i > 0; i--){
			maxHeapify(A, i);
		}
	}
	public static void heapSort(int[] A){
		buildHeap(A);
		for(int i = A.length - 1; i > 1; i--){
			int tmp = A[i];
			A[i] = A[1];
			A[1] = tmp;
			A[0]--;
			maxHeapify(A, 1);
		}
	}
	
	public static void show(int[] A){
		for(int i = 1; i < A.length; i++){
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
	public static void showHeap(int[] A){
		for (int i = 1; i <= A[0]; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
	public static int parent(int i){
		return i/2;
	}
	public static int left(int i){
		return 2 * i;
	}
	public static int right(int i){
		return 2 * i + 1;
	}
}
