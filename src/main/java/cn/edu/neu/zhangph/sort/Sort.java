package cn.edu.neu.zhangph.sort;

import java.util.*;

public class Sort {
	/**
	 * 1. 桶排序
	 */
	@SuppressWarnings("unchecked")
	public static void bucketSort(double[] num) {
		int n = num.length;
		List bucketList[] = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			int temp = (int) Math.floor(n * num[i]);
			if (null == bucketList[temp])
				bucketList[temp] = new ArrayList<Object>();
			bucketList[temp].add(num[i]);
		}
		for (int i = 0; i < bucketList.length; i++) {
			if (bucketList[i] != null)
				insert(bucketList[i]);
		}
		int index = 0;
		for (int i = 0; i < n; i++) {
			if (null != bucketList[i]) {
				Iterator<?> it = bucketList[i].iterator();
				while (it.hasNext()) {
					num[index++] = (Double) it.next();
				}
			}
		}
	}

	/**
	 * 用插入排序对每个桶进行排序 从小到大排序
	 */
	private static void insert(List list) {
		if (list.size() > 1) {
			for (int i = 1; i < list.size(); i++) {
				double temp = (Double) list.get(i);
				int j = i - 1;
				for (; j >= 0 && ((Double) list.get(j) > (Double) list.get(j + 1)); j--)
					list.set(j + 1, list.get(j)); // 后移
				list.set(j + 1, temp);
			}
		}
	}

	/**
	 * 2. 堆排序 最大堆
	 */
	public static void heapSort(int[] num) {
		if(num == null || num.length < 2)return;
		buildMaxHeap(num);
		for (int i = num.length - 1; i > 1; i--) {
			int tmp = num[1];
			num[1] = num[i];
			num[i] = tmp;
			num[0]--;
			maxHeapify(num, 1);
		}
	}

	/**
	 * 最大堆的建立
	 */
	public static void buildMaxHeap(int[] num) {
		num[0] = num.length - 1;  //使用第一个元素记录堆的大小
		for (int i = (num.length - 1) >>> 1; i > 0; i--) {
			maxHeapify(num, i);
		}
	}
	/**
	 * 最大堆的维护
	 * 这是堆排序最关键的一个步骤
	 */
	public static void maxHeapify(int[] num, int i) {
		int left = left(i);
		int right = right(i);
		int largest = i;
		if (left <= num[0] && num[left] > num[i]) largest = left;
		if (right <= num[0] && num[right] > num[largest])largest = right;
		if (largest != i) {
			int tmp = num[i];
			num[i] = num[largest];
			num[largest] = tmp;
			maxHeapify(num, largest);
		}
	}

	private static int left(int i) {//左孩子
		return 2 * i;
	}

	private static int right(int i) {//右孩子
		return 2 * i + 1;
	}

	/**
	 * 3. 归并排序 
	 * 分而治之的策略 主程序递归，调用merge函数 merge函数把需要合并的两个部分存储到两个数组，
	 * 然后比较两个数组的大小，放进原数组
	 */
	public static void mergeSort(int[] num){
		if(num == null || num.length < 2) return;
		mergeSort(num, 0, num.length - 1);
	}
	
	private static void mergeSort(int[] num, int low, int high) {
		if (low < high) {
			int mid = (low + high) >>> 1;
			mergeSort(num, low, mid);
			mergeSort(num, mid + 1, high);
			merge(num, low, mid, high);
		}
	}

	private static void merge(int[] num, int low, int mid, int high) {
		int n1 = mid - low + 1;
		int n2 = high - mid;
		int[] left = new int[n1 + 1];
		int[] right = new int[n2 + 1];
		for (int i = 0; i < n1; i++) {
			left[i] = num[low + i];
		}
		for (int i = 0; i < n2; i++) {
			right[i] = num[mid + i + 1];
		}
		left[n1] = Integer.MAX_VALUE;
		right[n2] = Integer.MAX_VALUE;
		int i = 0, j = 0;
		for (int k = low; k <= high; k++) {
			num[k] = left[i] < right[j] ? left[i++] : right[j++];
		}
	}
	/**
	 * 4. 快速排序
	 *  找到一个枢轴变量，每次排序都将分成两个部分
	 */
	public static void qucikSort(int[] num){
		if(num == null || num.length < 2) return;
		quickSort(num, 0, num.length - 1);
	}
	private static void quickSort(int[] num, int low, int high) {
		if (low < high) {
			int loc = qsort(num, low, high);
			quickSort(num, low, loc - 1);
			quickSort(num, loc + 1, high);
		}
	}
	private static int qsort(int[] num, int low, int high) {
		int pivot = num[low];
		while (low < high) {
			while (low < high && num[high] > pivot)
				high--;
			num[low] = num[high];
			while (low < high && num[low] < pivot)
				low++;
			num[high] = num[low];
		}
		num[low] = pivot;
		return low;
	}

	/**
	 * 5. 选择排序 
	 * 从头到尾，每次选择一个最小的数放在前面----该排序方法不稳定
	 * 助记符：
	 * i ~ [0, len - 1] 
	 * 		j = selectMin(L, i) 
	 * 		if(i != j) 
	 * 			swap(i,j)
	 */
	public static void selectSort(int[] num) {
		if(num == null || num.length < 2) return;
		for (int i = 0; i < num.length; i++) {
			int min = i;
			for (int j = i + 1; j < num.length; j++) {
				if (num[min] > num[j])
					min = j;
			}
			if (i != min) {
				int tmp = num[i];
				num[i] = num[min];
				num[min] = tmp;
			}
		}
	}
	/**
	 * 6. 冒泡排序 
	 * 每次把大数沉底，小数往上冒
	 * 助记码： 
	 * i ~ [0, len-1] 
	 * 		j ~ [0, len - i - 1] 
	 * 			swap(j, j+1)
	 */
	public static void bubbleSort(int[] num) {
		if (null != null || num.length < 2)	return;
		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length - i - 1; j++) {
				if (num[j] > num[j + 1]) {
					int tmp = num[j];
					num[j] = num[j + 1];
					num[j + 1] = tmp;
				}
			}
		}
	}

	/**
	 * 7. 插入排序
	 *  对于每个元素插入有已经有序的数组中 
	 *  助记码： 
	 *  i ~ [1, len - 1] 
	 *  	x = num[i] 
	 *  	j ~ [i-1, 0]
	 */
	public static void insertSort(int[] num) {
		if (null != num || 2 > num.length) return;
		for (int i = 1; i < num.length; i++) {
			int tmp = num[i];
			int j = i - 1;
			for (; j >= 0 && num[j] > tmp; j--) {//调整数组位置，为tmp挑出空位
				num[j + 1] = num[j];
			}
			num[j + 1] = tmp; //将tmp插入到合适的位置
		}
	}
	
	//打印
	private static void print(int[] num) {
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i] + " ");
		}
		System.out.println();
	}
	private static void print(double[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] num = { 0, 5, 2, 3, 7, 4, 1, -1 };
		double[] arr = new double[] { 0.9, 0.7, 0.1, 0.2, 0.3, 0.8 };
		print(arr);
		// insertSort(num);
		// bubbleSort(num);
		// selectSort(num);
		// quickSort(num);
		// mergeSort(num);
		// heapSort(num);
		bucketSort(arr);
		print(arr);
	}
}
