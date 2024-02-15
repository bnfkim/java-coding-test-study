package boj;

/*
 * BOJ_2447_별찍기 - 10.java
 * [BOJ]2447/골드5/848ms/1h/김동진
 */


import java.util.Scanner;

public class BOJ_2447_별찍기 {

	static int N;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sb = new StringBuilder();
		
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				divandcon(i, j, N);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void divandcon(int i, int j, int size) {
		
		int next_size = size / 3;
		
		if(next_size > 1) {
			int n_i = i / next_size;
			int n_j = j / next_size;
			if(n_i  % next_size == 1 && n_j  % next_size == 1) {
				sb.append(" ");
				return;
			}
		}
		
		if((i / size) % 3 == 1 && (j/size) % 3 == 1) {
			sb.append(" ");
		}else {
			if(size == 1) {
				sb.append("*");
			}else {
				divandcon(i, j, size / 3);
			}
		}
		
		
	}
}
