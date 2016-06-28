package cn.edu.neu.zhangph.ch4;

public class SquareMM {
	public static void main(String[] args) {
		int[][] first = {{1,2,3},{2,3,4},{3,4,5}};
		int[][] second = {{1,4,7},{2,5,8},{3,6,9}};
		int[][] res = matrixMultiply(first, second);
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	private static int[][] matrixMultiply(int[][] first, int[][] second){
		int len = first.length;
		int[][] result = new int[len][len];
		for(int i = 0; i < len; i ++){
			for(int j = 0; j < len; j++){
				result[i][j] = 0;
				for(int k = 0; k < len; k++){
					result[i][j] += first[i][k] + second[k][j]; 
				}
			}
		}
		return result;
	}
}
