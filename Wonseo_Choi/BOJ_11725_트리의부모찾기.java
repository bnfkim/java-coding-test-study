import java.io.*;
import java.util.*;

public class BOJ_11725_트리의부모찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int[] ans = new int[n];
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n+1);
		for (int i = 0; i <= n; i++ ) {
			graph.add(new ArrayList<>());
		}
		
		int a=0, b=0;
		for (int i = 0; i < n-1; i++ ) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		Queue<Integer> q2 = new LinkedList<Integer>();
		
		for (int i : graph.get(1)) {
			q.offer(i);
			q2.offer(1);
		}
		
		int temp=0, temp2=0;
		while (!q.isEmpty()) {
			temp = q.poll();
			temp2 = q2.poll();
			if (ans[temp-1] == 0) {
				ans[temp-1] = temp2;
				for (int i : graph.get(temp)) {
					q.offer(i);
					q2.offer(temp);
				}
			}
		}
		
		for(int i = 1; i < n; i++ ) {
			System.out.println(ans[i]);
		}
	}
}
