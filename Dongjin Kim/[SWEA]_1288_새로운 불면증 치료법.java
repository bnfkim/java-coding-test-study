package dj_ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea_1288 {
	
	static int T;
	static boolean flag;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		
		for(int tc = 1; tc <= T; tc++) {
			
			visited = new boolean[10];
			int n = Integer.parseInt(br.readLine());

			int cnt = 1;
			flag = true;
			while(flag) {
				int test = n*cnt;
				
				String temp = test + "";
				char[] arr = temp.toCharArray();
				for(int i = 0, size = arr.length; i < size; i++) {
					int c = arr[i] - '0';
					visited[c] = true;
				}
				
				for(int i = 0; i < 10; i++) {
					if(!visited[i]) {
						flag = true;
						break;
					}
					flag = false;
				}
				cnt++;
			}
			
			System.out.printf("#%d %d%n", tc, n *(cnt - 1));
		}
	}
}
