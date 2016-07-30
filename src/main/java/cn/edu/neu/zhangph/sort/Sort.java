package cn.edu.neu.zhangph.sort;

import java.util.*;

public class Sort {
	/**
	 * 1. Ͱ����
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
	 * �ò��������ÿ��Ͱ�������� ��С��������
	 */
	private static void insert(List list) {
		if (list.size() > 1) {
			for (int i = 1; i < list.size(); i++) {
				double temp = (Double) list.get(i);
				int j = i - 1;
				for (; j >= 0 && ((Double) list.get(j) > (Double) list.get(j + 1)); j--)
					list.set(j + 1, list.get(j)); // ����
				list.set(j + 1, temp);
			}
		}
	}

	/**
	 * 2. ������ ����
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
	 * ���ѵĽ���
	 */
	public static void buildMaxHeap(int[] num) {
		num[0] = num.length - 1;  //ʹ�õ�һ��Ԫ�ؼ�¼�ѵĴ�С
		for (int i = (num.length - 1) >>> 1; i > 0; i--) {
			maxHeapify(num, i);
		}
	}
	/**
	 * ���ѵ�ά��
	 * ���Ƕ�������ؼ���һ������
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

	private static int left(int i) {//����
		return 2 * i;
	}

	private static int right(int i) {//�Һ���
		return 2 * i + 1;
	}

	/**
	 * 3. �鲢���� 
	 * �ֶ���֮�Ĳ��� ������ݹ飬����merge���� merge��������Ҫ�ϲ����������ִ洢���������飬
	 * Ȼ��Ƚ���������Ĵ�С���Ž�ԭ����
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
	 * 4. ��������
	 *  �ҵ�һ�����������ÿ�����򶼽��ֳ���������
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
	 * 5. ѡ������ 
	 * ��ͷ��β��ÿ��ѡ��һ����С��������ǰ��----�����򷽷����ȶ�
	 * ���Ƿ���
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
	 * 6. ð������ 
	 * ÿ�ΰѴ������ף�С������ð
	 * �����룺 
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
	 * 7. ��������
	 *  ����ÿ��Ԫ�ز������Ѿ������������ 
	 *  �����룺 
	 *  i ~ [1, len - 1] 
	 *  	x = num[i] 
	 *  	j ~ [i-1, 0]
	 */
	public static void insertSort(int[] num) {
		if (null != num || 2 > num.length) return;
		for (int i = 1; i < num.length; i++) {
			int tmp = num[i];
			int j = i - 1;
			for (; j >= 0 && num[j] > tmp; j--) {//��������λ�ã�Ϊtmp������λ
				num[j + 1] = num[j];
			}
			num[j + 1] = tmp; //��tmp���뵽���ʵ�λ��
		}
	}
	
	//��ӡ
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
