import java.util.*;
import java.io.*;

public class bj_2606 {
	
	static int cmps, couple, from, to, cnt;		// 컴퓨터 수, 쌍의 수, from, to, 정답 
	static int[] input;							// 입력받은
	static boolean[] visited;
	static ArrayList<Integer>[] edge;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		cmps = Integer.parseInt(br.readLine());
		couple = Integer.parseInt(br.readLine());
		
		input = new int[cmps + 1];
		edge = new ArrayList[cmps + 1]; 
		visited = new boolean[cmps + 1];
		
		for(int i = 1; i <= cmps; i++) {
			edge[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < couple; i++) {
			st = new StringTokenizer(br.readLine()," ");
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			edge[from].add(to);
			edge[to].add(from);
			
		}
		dfs(1);
		
		for(int i = 1; i <= cmps; i++) {
			if(visited[i]) cnt++;
		}
		
		System.out.println(cnt-1);		// 1을 제외하고 출력
	}
	
	static void dfs(int cur) {
		visited[cur] = true;
		
		for(int next : edge[cur]) {
			if(visited[next]) continue;
			dfs(next);
		}
	}

}
