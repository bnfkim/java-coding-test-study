package boj;

/*
 * BOJ_1647_도시 분할 계획.java
 * [BOJ]1647/골드4/2468ms/1h/김동진
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1647_도시분할계획 {

	static int N, M;
	static int[] parents;
	
	static class Bridge implements Comparable<Bridge>{
		int from, to, value;
		Bridge(int from, int to, int value){
			this.from = from;
			this.to = to;
			this.value = value;
		}
		@Override
		public int compareTo(Bridge o) {
			return this.value - o.value;
		}
	}
	
	static Bridge[] bridges;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		bridges = new Bridge[M];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			bridges[i] = new Bridge(f, t, v);
		}
		
		kruscal();
		
	}
	
	public static void kruscal() {
		Arrays.sort(bridges);
		make();
		
		
		int weight = 0;
		int cnt = 0;
		int max = 0;
		for(Bridge bridge : bridges) {
			int f = bridge.from;
			int t = bridge.to;
			int v = bridge.value;
			
			if(!union(f, t)) continue;
			max = Math.max(max, v);
			weight += bridge.value;
			if(++cnt == N - 1) break;
		}
		System.out.println(weight - max);
	}
	
	public static void make() {
		parents = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}

}
