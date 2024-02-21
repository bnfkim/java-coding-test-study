package dj_ct;

/*
 * 	2024-02-02(금)
 *  BOJ_1522_문자열 교환.java
 *  [BOJ]1522/실버1/132ms/0.5h/김동진
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1522_문자열교환 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split("");
		int[] arr2 = new int[arr.length];
		
		
		int cnt_a = 0, ans = Integer.MAX_VALUE;
		
		// a와 b로 이루어진 배열을 0과 1로 교환
		for(int i = 0, size = arr.length; i < size; i++) {
			if(arr[i].equals("a")) {
				cnt_a++;
				arr2[i] = 1;
			}else {
				arr2[i] = 0;
			}
		}
		
		
		// a 의 전체 개수를 구하고, 선택되지 않은 조합에 포함된 a 의 개수를 return
		int limit = arr.length - cnt_a;
		for(int start = 0; start < arr2.length; start++) {
			int k = 0;
			int sum = 0;
			while(k < limit) {
				if(start + k >= arr.length) {
					sum += arr2[start + k - arr.length];
				}else {
					sum += arr2[start + k];
				}
				k++;
			}
			ans = Math.min(ans, sum);
		}
		
		System.out.println(ans);
		
	}

}
