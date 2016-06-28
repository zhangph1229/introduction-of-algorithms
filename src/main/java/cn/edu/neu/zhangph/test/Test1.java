package cn.edu.neu.zhangph.test;

/**
 * 
 * @author zhangph
 *
 *
 *         腾讯面试题： 给你10分钟时间，根据上排给出十个数，在其下排填出对应的十个数 要求下排每个数都是先前上排那十个数在下排出现的次数。
 *         上排的十个数如下： 【0，1，2，3，4，5，6，7，8，9】
 * 
 *         初看此题，貌似很难，10分钟过去了，可能有的人，题目都还没看懂。
 * 
 *         举一个例子， 数值: 0,1,2,3,4,5,6,7,8,9 分配: 6,2,1,0,0,0,1,0,0,0
 *         0在下排出现了6次，1在下排出现了2次， 2在下排出现了1次，3在下排出现了0次.... 以此类推..
 */
public class Test1 {
	public static int[] judge(int[] up){
		int[] down = new int[up.length];
		for (int i = 0; i < down.length; i++) {
			down[i] = 0;
		}
		
		
		
		return down;
	}
}
