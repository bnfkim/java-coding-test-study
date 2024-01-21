import java.util.*;
import java.io.*;

public class bj_16235 {

	static int[] dx= {0,1,1,1,0,-1,-1,-1};
	static int[] dy= {-1,-1,0,1,1,1,0,-1};
	
	static int n, m, k;
	static int[][] map, energy;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		energy = new int[n+1][n+1];
		
		// 나이가 어린 나무부터 양분을 먹어야 하기에 우선순위 큐 사용 (age 기준)
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		// 죽은 나무와 살아있는 나무, 2가지 큐
		Queue<int[]> deadT = new LinkedList<int[]>();
		Queue<int[]> T = new LinkedList<int[]>();
		
		for(int i = 1; i <=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				energy[i][j] = 5;		// 초기값은 5로 세팅
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			int[] tree = {x, y, z};
			pq.add(tree);
		}
		
		for(int year = 1; year <= k; year++){
			// case1: 봄
			while(!pq.isEmpty()) {
				// poll vs remove
				int[] tmp = pq.remove();
				int t_x = tmp[0];
				int t_y = tmp[1];
				int t_z = tmp[2];
				if(energy[t_x][t_y] < t_z) {
					deadT.add(tmp);
				}else {
					energy[t_x][t_y] -= t_z;
					int[] tmp2 = {t_x, t_y, t_z+1};
					T.add(tmp2);
				}
			}
			// case2: 여름
			while(!deadT.isEmpty()) {
				int[] tmp = deadT.remove();
				int d_x = tmp[0];
				int d_y = tmp[1];
				int d_z = tmp[2] / 2;
				energy[d_x][d_y] += d_z;
 			}
			
			// case3: 가을
			while(!T.isEmpty()) {
				int[] tmp = T.remove();
				int f_x = tmp[0];
				int f_y = tmp[1];
				int f_z = tmp[2];
				if(f_z % 5 == 0) {
					for(int i = 0; i < 8; i++) {
						int nx = f_x + dx[i];
						int ny = f_y + dy[i];
						if(nx <= 0 || nx > n || ny <= 0 || ny > n) continue;
						int[] new_tree = {nx, ny, 1};
						pq.add(new_tree);
					}
				}
				pq.add(tmp);		// 여기 tmp 를 추가해주지 않아서 오류 발생
			}
			
			// case4: 겨울
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					energy[i][j] += map[i][j];
				}
			}
		}
		int cnt = 0;
		while(!pq.isEmpty()) {
			int[] count = pq.remove();
			cnt++;
		}
		System.out.println(cnt);
	}
}
