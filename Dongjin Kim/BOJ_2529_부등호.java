package bj;

import java.io.*;
import java.util.*;

public class bj_2529 {

	static int K;
	static char[] marks;
	static int[] numbers;
	static boolean[] visited;
	
	static ArrayList<String> answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		marks = new char[K];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			marks[i] = st.nextToken().charAt(0);
		}
		
		numbers = new int[10];
		visited = new boolean[10];
		answer = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			visited = new boolean[10];
			visited[i] = true;
			dfs(i, 0, i+"");
			visited[i] = false;
		}
		
		System.out.println(answer.get(answer.size() - 1));
		System.out.println(answer.get(0));
	}
	
	static void dfs(int start, int cnt, String num) {
		if(cnt == K) {
			answer.add(num);
			return;
		}
		for(int i = 0; i < 10; i++) {
			
			if(!visited[i]) {
				char mark = marks[cnt];
				if(mark == '<') {
					if(i > start) {
						visited[i] = true;
						dfs(i, cnt+1, num + i);
						visited[i] = false;
					}
				}else {
					if(i < start) {
						visited[i] = true;
						dfs(i, cnt+1, num + i);
						visited[i] = false;
					}
				}
			}
		}
	}
}
