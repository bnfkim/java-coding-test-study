package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴 {
	static Deque<Integer>[] dq1 = new ArrayDeque[5];
	static Deque<Integer>[] dq2 = new ArrayDeque[5];
	static int k;
	
	static void rotate(int n, int d) {
		if(d==1) {
			dq2[n].addFirst(dq1[n].pollLast());
			dq1[n].addFirst(dq2[n].pollLast());
		}else {
			dq2[n].add(dq1[n].poll());
			dq1[n].add(dq2[n].poll());
		}
	}
	
	static void left(int n, int d) {
		if(n==0 || dq2[n].getFirst()==dq2[n+1].getLast()) {
			return;
		}
		
		left(n-1,-d);
		rotate(n,d);
	}
	
	static void right(int n, int d) {
		if(n==5 || dq2[n].getLast()==dq2[n-1].getFirst()) {
			return;
		}
		
		right(n+1,-d);
		rotate(n,d);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s;
		for(int i=1;i<5;i++) {
			s=br.readLine();
			dq1[i] = new ArrayDeque<>();
			dq2[i] = new ArrayDeque<>();
			
			dq1[i].add(s.charAt(0)-'0');
			dq1[i].add(s.charAt(1)-'0');
			dq2[i].add(s.charAt(2)-'0');
			dq2[i].add(s.charAt(3)-'0');
			dq2[i].add(s.charAt(4)-'0');
			dq2[i].add(s.charAt(5)-'0');
			dq2[i].add(s.charAt(6)-'0');
			dq1[i].addFirst(s.charAt(7)-'0');
		}
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			left(n-1,-d);
			right(n+1,-d);
			
			rotate(n,d);
 		}
		
		int res=0;
		dq1[1].poll();
		res+=dq1[1].poll()*1;
		dq1[2].poll();
		res+=dq1[2].poll()*2;
		dq1[3].poll();
		res+=dq1[3].poll()*4;
		dq1[4].poll();
		res+=dq1[4].poll()*8;
		
		System.out.println(res);
		
	}

}
