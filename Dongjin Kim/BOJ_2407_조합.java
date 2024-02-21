package dj_ct;

/*
 * BOJ_2407_조합.java
 * [BOJ]2407/실버3/208ms/0.5h/김동진
 */

import java.math.BigInteger;
import java.util.Scanner;

public class bj_2407_조합 {

	static int n, m;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		BigInteger ans = fac(1, n).divide(fac(1, m).multiply(fac(1,n-m)));
		System.out.println(ans);
		
	}
	

	
	private static BigInteger fac(int start, int end) {
		
		BigInteger result = new BigInteger("1");
		
		for(int i = start; i <= end; i++) {
			result = result.multiply(new BigInteger(String.valueOf(i)));
		}
		
		return result;
		
	}

}
