package cn.edu.neu.zhangph.ch4;
/*
 * 使用动态规划的方式,求最大字数组
 */
public class MaxSubarrayDP {
	public static void main(String[] args) {
		int[] num = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		System.out.println(maxSubarrayDp(num));
	}
	private static int maxSubarrayDp(int[] A){
		int sum, max_sum;
		sum =  0;
		max_sum = A[0];
		for(int i = 0; i < A.length; i++){
			if(sum > 0) {
				sum += A[i];
			}else{
				sum = A[i];
			}
			if(sum > max_sum ) max_sum = sum;
		}
		return max_sum;
	}
}
