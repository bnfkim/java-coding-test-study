package chapter09;

import java.io.*;
import java.util.*;

public class BOJ_1002_터렛 {
	static int[] arr;
	static int dist;
	static int sub, sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			arr = new int[6];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 6; i++ ) {
				arr[i] = Integer.parseInt(st.nextToken());	
			}
			
			dist = (arr[3]- arr[0])*(arr[3]- arr[0]) + (arr[4]- arr[1])*(arr[4]- arr[1]);
			sub = (arr[2]-arr[5])*(arr[2]-arr[5]);
			sum = (arr[2]+arr[5])*(arr[2]+arr[5]);
			
			if (dist == 0 && sub == 0) {
				System.out.println(-1);
			} else if (dist == sum || dist == sub) {
				System.out.println(1);
			} else if (dist > sum || dist < sub) {
				System.out.println(0);
			} else {
				System.out.println(2);
			}
		}
	}
}
