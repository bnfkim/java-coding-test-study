package bj;

import java.util.*;
import java.io.*;

public class bj_16234 {
	static int N, L, R, cnt;
	static boolean check;
	
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1}; 
	
	public static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static Queue<Node> q = new LinkedList<Node>();
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while(true) {
			check = false;
			visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						
						int people = 0;
						people = dfs(i, j);
						int size = q.size();
						while(!q.isEmpty()) {
							Node nd = q.poll();
							map[nd.x][nd.y] = people / size;
						}
						
					}
				}
			}
			// dfs가 돌았는지 확인하는 변수
			if(!check) {
				System.out.println(cnt);
				break;
			}
			
			cnt++;
			
		}
	}
	
	static int dfs(int x, int y) {
		
		visited[x][y] = true;
		int people = map[x][y];
		q.add(new Node(x, y));
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(inMap(nx, ny) && !visited[nx][ny] && inRange(Math.abs(map[x][y] - map[nx][ny]))) {
				people += dfs(nx, ny);
				check = true;
			}
		}
		return people;
	}
	
	static boolean inMap(int x, int y) {
		return (0 <= x && x < N && 0 <= y && y < N);
	}
	
	static boolean inRange(int x) {
		return (L <= x && x <= R);
	}


}

