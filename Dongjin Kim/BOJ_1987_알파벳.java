import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int R, C, ans;

	static Character[][] map;
	
	static int[][] dir = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new Character[R][C];
		
		boolean[] visited = new boolean[26];
		
		for(int i = 0; i < R; i++) {
			String input = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		ans = Integer.MIN_VALUE;
		visited[map[0][0] - 'A'] = true;
		
		find(0, 0, visited);
		System.out.println(ans);
	}
	
	// arrayList를 인자로 전하기(static 아닌). 
	private static void find(int x, int y, boolean[] arr) {
		
		int cnt = 0;
		int num = 0;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			
			if(!inMap(nx, ny) || arr[ctoi(map[nx][ny])]) {
				cnt++;	// 갈 곳이 없을 것을 파악
				continue;
			}
			
			arr[ctoi(map[nx][ny])] = true;
			find(nx, ny, arr);
			arr[ctoi(map[nx][ny])] = false;
			
		}
		
		if(cnt == 4) {
			for(int i = 0; i < 26; i++) {
				if(arr[i]) num++; 
			}
			ans = Math.max(ans, num);
			return;
		}
	}
	
	private static int ctoi(char c) {
		
		return c - 'A';
	}
	
	private static boolean inMap(int x, int y) {
		return (0 <= x && x < R && 0 <= y && y < C);
	}

}
