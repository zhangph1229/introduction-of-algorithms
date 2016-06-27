package cn.edu.neu.zhangph.ch6;

public class PriorityQueue {
	public static void main(String[] args) {
		int[] A = {0,4,1,3,2,16,9,10,14,8,7};
		Heap.show(A);
		Heap.buildHeap(A);
		Heap.show(A);
		System.out.println(headMaximum(A));
		System.out.println(heapExtractMax(A));
		Heap.showHeap(A);
		heapIncreaseKey(A, 5, 20);
		Heap.showHeap(A);
		heapInsert(A, 16); 
		//因为之前删除了一个元素这里插入成功，如果直接对一个数组进行插入，
		//首先应该判断其数组大小，如果大小不满足条件应该扩展数组，然后在进行插入。
		Heap.showHeap(A);
	}
	/**
	 * 
	 * @param A
	 * @return 返回A中最大键字元素
	 */
	public static int headMaximum(int[] A){
		return A[1];
	}
	/**
	 * 
	 * @param A
	 * @return 返回最大值元素并删除
	 */
	public static int heapExtractMax(int[] A){
		if(A[0] < 1) {
			System.err.println("heap underflow");
			System.exit(0);
		}
		int max = A[1];
		A[1] = A[A[0]];
		A[0]--;
		Heap.maxHeapify(A, 1);
		return max;
	}
	public static void heapIncreaseKey(int[] A, int i, int key){
		if(key < A[i]){
			System.err.println("new key is smaller than current key");
			System.exit(0);
		}
		A[i] = key;
		while(i > 1 && A[Heap.parent(i)] < A[i]){
			int tmp = A[i];
			A[i] = A[Heap.parent(i)];
			A[Heap.parent(i)] = tmp;
			i = Heap.parent(i);
		}
	}
	public static void heapInsert(int[] A, int key){
		A[0]++;
		A[A[0]] = Integer.MIN_VALUE;
		heapIncreaseKey(A, A[0], key);
	}
}
