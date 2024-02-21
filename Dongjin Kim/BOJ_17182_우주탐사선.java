package boj;

/*
 * BOJ_17182_우주탐사선.java
 * [BOJ]17182/골드3/388ms/0.5h/김동진
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17182_우주탐사선 {

	static int N, K, ans;
	
	static int[] input, nums;
	static boolean[] isSelected;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		input = new int[N];
		nums = new int[N];
		isSelected = new boolean[N];
		for(int i = 0; i < N; i++) {
			input[i] = i;
		}
		
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		update();
		ans = Integer.MAX_VALUE;
		permutation(0);
		System.out.println(ans);
		
	}
	
	public static void permutation(int cnt) {
		if(cnt == N && nums[0] == K) {
			int temp = 0;
			for(int j = 0; j < N - 1; j++) {
				temp += map[nums[j]][nums[j+1]];
			}
			
			ans = Math.min(ans, temp);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(isSelected[i]) continue;
			nums[cnt] = input[i];
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
	
	
	public static void update() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(map[i][j] > map[i][k] + map[k][j]) map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
	}
	
	
}
