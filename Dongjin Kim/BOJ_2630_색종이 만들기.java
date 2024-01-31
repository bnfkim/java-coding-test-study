import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static int b_cnt, w_cnt;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cut(0, 0, N);
		System.out.println(w_cnt);
		System.out.println(b_cnt);
		
	}
	
	private static void cut(int start_x, int start_y, int size) {
		
		int cnt = 0;
		
		if (size == 1) {
			if(map[start_x][start_y] == 1) {
				b_cnt++;
				return;
			}else {
				w_cnt++;
				return;
			}
		}
		
		for(int i = start_x; i < start_x + size; i++) {
			for(int j = start_y; j < start_y + size; j++) {
				cnt += map[i][j];
			}
		}
		
		if(cnt == 0) {
			w_cnt++;
			return ;
		}else if(cnt == size * size) {
			b_cnt++;
			return ;
		}
		
		cut(start_x, start_y, size / 2);
		cut(start_x, start_y + size / 2, size / 2);
		cut(start_x + size / 2, start_y, size / 2);
		cut(start_x + size / 2, start_y + size / 2, size / 2);
	}

}
