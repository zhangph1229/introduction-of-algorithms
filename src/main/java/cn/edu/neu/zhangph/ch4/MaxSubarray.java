package cn.edu.neu.zhangph.ch4;
/**
 * 使用分治法可以分成三种情况:
 * 
 * left,cross,right 
 *
 */
public class MaxSubarray {
	public static void main(String[] args) {
		int[] num = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		Result res = maxSubarray(num, 0, num.length - 1);
		System.out.println(res.getLow() + "--" + res.getHigh() + " = " + res.getSum());
	}
	private static Result maxSubarray(int[] A, int low, int high){
		if(low == high) return new Result(low, high, A[low]);
		else{
			int mid = (low + high)/2;
			Result left_res = maxSubarray(A, low, mid);
			Result right_res = maxSubarray(A, mid + 1, high);
			Result cross_res = maxCrossingSubarray(A, low, mid, high);
			if(left_res.getSum() >= right_res.getSum() && left_res.getSum() >= cross_res.getSum()){
				return left_res;
			}else if(right_res.getSum() >= left_res.getSum() && right_res.getSum() >= cross_res.getSum()){
				return right_res;
			}else return cross_res;
		}
	}
	private static Result maxCrossingSubarray(int[] A, int low, int mid, int high){
		int left, left_sum , max_left, right , right_sum, max_right;
		left_sum = right_sum = 0;
		left = right = mid;
		max_left = max_right = Integer.MIN_VALUE;
		for(int i = mid; i >= low; i--){
			left_sum += A[i];
			if(left_sum > max_left){
				max_left = left_sum;
				left = i;
			}
		}
		for(int i = mid + 1; i <= high; i++){
			right_sum += A[i];
			if(right_sum > max_right){
				max_right = right_sum;
				right = i;
			}
		}
		return new Result(left, right, max_right + max_left);
	}
}

class Result{
	private int low;
	private int high;
	private int sum;
	
	public Result(int low, int high, int sum){
		this.low = low;
		this.high = high;
		this.sum = sum;
	}
	public int getLow() {
		return low;
	}
	public int getHigh() {
		return high;
	}
	public int getSum() {
		return sum;
	}
}
