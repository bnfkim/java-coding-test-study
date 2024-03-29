package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, k;
	static long board[];
	
	public static long find_ans(long length) {
		if(length == 0) return 0;
		int res = 0;
		for(int i = 0; i < n; i++) {
			res += board[i]/length;
		}
		
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		board = new long [n];
		long l = 0, r = 0, ans = 0;
		for(int i = 0; i < n; i++) {
			board[i] = Integer.parseInt(br.readLine());
			r = Math.max(r, board[i]);
		}
		
		
		while(l <= r) {
			long mid = (l+r)/2;
			long target = find_ans((mid == 0) ? 1 : mid); 
			if(target >= k) {
				ans = mid;
				l = mid+1;
			}
			else
				r = mid-1;
		}

		System.out.println(ans);
	}
}
