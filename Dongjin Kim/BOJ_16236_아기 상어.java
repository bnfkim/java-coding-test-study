package bj;

import java.util.*;
import java.io.*;

public class bj_16236 {

	static int N;
	static int shark_size = 2;		// shark 의 크기
	static boolean check;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	
	
	public static class fish implements Comparable<fish>{
		int x;
		int y;
		int size;
		int dist;
		
		fish(int x, int y, int size, int dist){
			this.x = x;
			this.y = y;
			this.size = size;
			this.dist = dist;
		}

		@Override
		public int compareTo(fish o) {
			
			// 1순위 거리
			if(this.dist < o.dist) return -1;
			if(this.dist == o.dist) {
				// x 가 작을 수록(위)
				if(this.x < o.x) return -1;
				if(this.x == o.x) {
					// y 가 작을수록(왼쪽)
					if(this.y < o.y) return -1;
				}
			}
			
			return 1;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		fish shark = null;
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new fish(i, j, 2, 0);
				}
			}
		}	
		System.out.println(bfs(shark));	
	}	
	static int bfs(fish f) {
		
		int time = 0;
		int cnt = 0;
		
		// dist에 대한 처리가 필요. 너무 커질까봐. 
		while(true) {
			
			Queue<fish> q = new LinkedList<>();
			List<fish> fishes = new ArrayList<>();
			q.add(f);
			
			//System.out.printf("현재 상어 위치 : [%d, %d] 상어 크기 : %d \n", f.x, f.y, f.size);
			
			visited = new boolean[N][N];
			visited[q.peek().x][q.peek().y] = true;
			
			int limit = 401;		//dist 제한하기 위한 limit
			
			while(!q.isEmpty()) {
				
				fish now = q.poll();
//				System.out.printf("출력 : [%d, %d] -> limit : %d, dist : %d\n", now.x, now.y, limit, now.dist);
//				System.out.printf("fish의 크기 : %d \n", fishes.size());
//				System.out.println();
				
				for(int i = 0; i < 4; i++) {
					
					if(limit < now.dist) {
						break;
					}
					
					int nx = now.x + dx[i];
					int ny = now.y + dy[i];
					int n_dist = now.dist + 1;
					if(inMap(nx, ny) && !visited[nx][ny] && (map[nx][ny] <= now.size)) {
						if(map[nx][ny] != 0 && map[nx][ny] < now.size) {
							//System.out.printf("먹이 발견 : [%d, %d] -> %d 먹이 크기 : %d \n", nx, ny, n_dist, map[nx][ny]);
							fishes.add(new fish(nx, ny, now.size, n_dist));
							visited[nx][ny] = true;
							limit = now.dist;
							break;				// 다음 fish 찾아서 넣으면 탈출
						}
						else {
							// 물고기는 아니지만 이동가능한 구역 (size 가 0임)
							q.add(new fish(nx, ny, now.size, n_dist));
							visited[nx][ny] = true;
						}
					}
				}
				
			}
			if(fishes.isEmpty()) {
				break;
			}
			
			if(fishes.size() > 1) {
				Collections.sort(fishes);
			}
			fish next_fish = fishes.get(0);
			//System.out.printf("다음 fish : [%d, %d], 여기까지 거리 : %d 총 거리 : %d \n", next_fish.x, next_fish.y, next_fish.dist, time);
			map[f.x][f.y] = 0;
			map[next_fish.x][next_fish.y] = 9;
			time += next_fish.dist;
			next_fish.dist = 0;
			f = next_fish;

			cnt++;
			//System.out.printf("먹은 물고기 수 : %d \n", cnt);
			if(f.size == cnt) {
				f.size++;
				cnt = 0;
				//System.out.println("상어 size up");
			}
		}
		return time;	
	}
	
	static boolean inMap(int x, int y) {
		return (0 <= x && x < N && 0 <= y && y < N);
	}
}
