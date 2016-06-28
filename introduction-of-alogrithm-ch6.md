---
title: 第六章 堆排序
tags: 堆排序,优先队列
grammar_cjkRuby: true
---


 **{张培鹤}(Crane)**
 
 ### 堆
 **堆结构上的基本操作**
 *MAX-HEAPIFY* - 维护最大堆，时间复杂度为`O(lgn)`。
 *BUILD-MAX-HEAP* - 从无序的输入数列中，创建最大堆，时间复杂度 为线性。
 *HEAPSORT* - 对一个数组进行原址排序，时间复杂度为`O(nlgn)`。
 **优先队列的操作**
 *MAX-HEAP-INSERT* - 插入一个新元素到堆中
 *HEAP-EXTRACT-MAX* - 去掉并返回最大关键字元素
 *HEAP-INCREASE-KEY* - 将元素增加到一个值
 *HEAP-MAXIMUM* - 最大关键字元素
 
### 堆排序
*MAX-HEAPIFY伪代码*
 ```
 MAX-HEAPIFY(A, i)
 1	l = LEFT(i)
 2	r = RIGHT(i)
 3	if l <= A.heap-size and A[l] > A[i]
 4		largest = l
 5	else largest = i
 6	if r <= A.heap-size and A[r] > A[large]
 7		largest = r
 8	if largest != i
 9		exchange A[i] with A[largest]
 10		MAX-HEAPIFY(A, largest)
 ```
 *MAX-HEAPIFY 的java实现*
 ```java
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
 ```
 
 *BUILD-MAX-HEAP伪代码*
 ```
 BUILD-MAX-HEAP(A)
 1	A.heap-size = A.length
 2	for i = A.length / 2 downto 1
 3		MAX-HEAPIFY(A, i)
 ```
 *HEAPSORT伪代码*
 ```
 HEAPSORT(A)
 1	BUILD-MAX-HEAP(A)
 2	for i = A.length downto 2
 3 		exchange A[1] with A[i]
 4		A.heap-size++
 5		MAX-HEAPIFY(A,1)
 ```
 