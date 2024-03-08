package BOJ;

/*
 * BOJ_1911_흙길 보수하기.java
 * [BOJ]1911/골드5/256ms/0.5h/김동진
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1911_흙길보수하기 {

	static int N, L, cnt;
	
	static class Woong implements Comparable<Woong>{
		int start, end;
		public Woong(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Woong o) {
			return this.start - o.start;
		}
		
		@Override
		public String toString() {
			return "woong [start=" + start + ", end=" + end + "]";
		}
		
	}
	
	static Woong[] woongs;
	
	static int n_start, n_end;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());		// 웅덩이 개수
		L = Integer.parseInt(st.nextToken());		// 널빤지 길이

		woongs = new Woong[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			woongs[i] = new Woong(start, end);
		}
		
		Arrays.sort(woongs);
		
		solve();
		System.out.println(cnt);
		
	}

	public static void solve() {
		
		for(Woong w : woongs) {
			
			int w_len = 0;
			int n_num = 0;
			if(n_end < w.start) {		// 깔린 널빤지 뒤에 웅덩이가 나옴
				w_len = w.end - w.start;
				n_num = w_len % L == 0 ? w_len/L : w_len/L + 1;
				
				n_end = w.start + n_num * L;
				cnt += n_num;
			}else if(n_end >= w.start && n_end <= w.end) {
				w_len = w.end - n_end; 
				n_num = w_len % L == 0 ? w_len/L : w_len/L + 1;
				
				n_end = n_end + n_num * L;
				cnt += n_num;
			}else if(n_end > w.end) continue;
		
			//System.out.printf("현재 웅덩이 정보 : [%d - %d], 현재 널빤지 마지막 index : %d \n", w.start, w.end, n_end);
		}
		
	}
}
