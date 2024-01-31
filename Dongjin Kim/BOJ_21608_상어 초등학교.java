import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int N, num;
	static int[] students;
	static int[][] map;
	
	static int[][] dir = {
			{-1, 0},		// 북
			{0, 1},			// 동 
			{0, -1},		// 서
			{1, 0}			// 남
	};
	
	static Map<Integer, Set<Integer>> likes;
	
	
	// 답변 참고 코드
	static class Seat implements Comparable<Seat>{
		int x;
		int y;
		int cdt1;		// 조건 1 결과값 
		int cdt2;		// 조건 2 결과값
		
		public Seat(int x, int y, int cdt1, int cdt2){
			this.x = x;
			this.y = y;
			this.cdt1 = cdt1;
			this.cdt2 = cdt2;
		}

		@Override
		public int compareTo(Seat o) {

			// 	조건 1로 비교 
			if(cdt1 != o.cdt1) return o.cdt1 - cdt1;
			
			// 조건 2로 비
			if(cdt2 != o.cdt2) return o.cdt2 - cdt2;
			
			// 행으로 비교
			if(x != o.x) return x - o.x;
			
			// 열로 비교
			return y - o.y;
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		num = N * N;
		map = new int[N][N];
		students = new int[num];
		likes = new HashMap<>();
		
		for(int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			int stu_id = Integer.parseInt(st.nextToken());
			students[i] = stu_id;
			likes.put(stu_id, new HashSet<>());
			while(st.hasMoreTokens()) {
				likes.get(stu_id).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i = 0; i < num; i++) {
			Seat seat = solution(students[i]);
			map[seat.x][seat.y] = students[i];
		}
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(condition1(i, j, map[i][j]) + " ");
//			}
//			System.out.println();
//		}
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				ans += getPoints(condition1(i, j, map[i][j]));
			}
		}
		System.out.println(ans);

	}
	
	private static Seat solution(int idx) {
		
		Seat seat = null;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] > 0) continue;
				
				// 현재 자리 
				Seat cur_seat = new Seat(i, j, condition1(i, j, idx), condition2(i, j));
				

				//System.out.printf("[%d %d] -> 좋 칸 : %d 빈 칸 : %d \n", i, j, condition1(i, j, idx), condition2(i, j));
				
				if(seat == null) {
					seat = cur_seat;
					continue;
				}
				
				// 우선순위가 높다면 현재 자리로 교
				if(seat.compareTo(cur_seat) > 0) seat = cur_seat;
				
			}
		}
		return seat;
	}
	
	private static int condition1(int x, int y, int idx) {
		int cnt = 0;
		for(int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			
			if(!inMap(nx, ny)) continue;
			
			if(likes.get(idx).contains(map[nx][ny])) cnt++;
		}
		return cnt;
	}
	
	private static int condition2(int x, int y) {
		int cnt = 0;
		for(int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			
			if(!inMap(nx, ny)) continue;
			
			if(map[nx][ny] == 0) cnt++;
		}
		return cnt;
	}
	
	private static int getPoints(int k) {

		switch(k) {
		case 4:
			return 1000;
		case 3:
			return 100;
		case 2:
			return 10;
		case 1:
			return 1;
		}
		return 0;
	}
	
	private static boolean inMap(int x, int y) {
		return (0 <= x && x < N && 0 <= y && y < N);
	}

}
