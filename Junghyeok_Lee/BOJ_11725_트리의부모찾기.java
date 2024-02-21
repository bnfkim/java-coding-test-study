package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11725_트리의부모찾기 {
	
	static List<Integer>[] tree;
	static int[] checked;
	static Deque<Integer> dq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		tree = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		for(int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			tree[a].add(b);
			tree[b].add(a);
			
		}
		
		checked = new int[n+1];
		checked[1]=1;
		dq = new ArrayDeque<Integer>();
		dq.add(1);
		while(!dq.isEmpty()) {
			int x = dq.poll();
			
			for(int i=0;i<tree[x].size();i++) {
				int xx = tree[x].get(i);
				if(checked[xx]==0) {
					checked[xx]=x;
					dq.add(xx);
				}
			}
			
		}
		
		for(int i=2;i<n+1;i++) {
			System.out.println(checked[i]);
		}
		
		
		
	}

}
