import java.io.*;
import java.util.*;

class Seat implements Comparable<Seat> {
	int r;
	int c;
	int space;  // 근처 빈 자리 수
	int fav;  // 근처 좋아하는 학생 수
	
	Seat() {
		this.space = -1;
		this.fav = -1;
	}
	
	Seat(int r, int c, int space, int fav) {
		this.r = r;
		this.c = c;
		this.space = space;
		this.fav = fav;
	}
	
	@Override
	public int compareTo(Seat s) {
		if (this.fav == s.fav) {
			if (this.space == s.space) {
				if (this.r == s.r) {
					return this.c - s.c;
				}
				return this.r - s.r;
			}
			return this.space - s.space;
		}
		return this.fav - s.fav;
	}
}


class Main {
	static int N;
	static int[][] classRoom;
	static boolean[][] favorite;  // favorite[N][M] == true : N번 학생이 M번 학생을 선호한다.
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	// n번 학생을 규칙에 따라 교실에 앉히는 메서드
	static void sitStudent(int n) {
		Seat bestSeat = new Seat();
		
		for (int x = 0; x < N; ++x) {
			for (int y = 0; y < N; ++y) {
				
				// 빈 좌석이 아니라면 건너뛴다.
				if (classRoom[x][y] != 0) continue;
				
				int curSpace = 0;
				int curFav = 0;
				
				// 인접한 좌석에 대해 좋아하는 학생 수, 빈 좌석 수를 조사
				for (int i = 0; i < 4; ++i) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					// 범위를 벗어나면 건너뛴다.
					if (nx < 0 || N <= nx || ny < 0 || N <= ny) continue;
					
					int neighbor = classRoom[nx][ny];
					if (neighbor == 0) {
						curSpace++;
					} else if (favorite[n][neighbor]) {
						curFav++;
					}
				}
				
				// 현재 좌석과 현재까지 최선이었던 좌석을 비교하여 갱신
				Seat curSeat = new Seat(x, y, curSpace, curFav);
				bestSeat = bestSeat.compareTo(curSeat) > 0 ? bestSeat : curSeat;
			}
		}
		
		// 계산한 최선의 좌석에 학생을 앉힌다.
		classRoom[bestSeat.r][bestSeat.c] = n; 
	}
	
	public static void main(String args[]) throws Exception	{

		// 학생 수 입력 및 자료구조 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		classRoom = new int[N][N];
		favorite = new boolean[N * N + 1][N * N + 1];
		
		for (int i = 0; i < N * N; ++i) {
			
			// 배치할 학생 입력받기
			String[] line = br.readLine().split(" ");
			
			int curStudent = Integer.parseInt(line[0]);
			for (int j = 1; j < 5; ++j) {
				favorite[curStudent][Integer.parseInt(line[j])] = true;
			}
			
			// 학생 앉히기
			sitStudent(curStudent);
		}
		
		// 만족도 조사하기
		int satis = 0;
		for (int x = 0; x < N; ++x) {
			for (int y = 0; y < N; ++y) {
				int fav = 0;
				
				for (int i = 0; i < 4; ++i) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if (nx < 0 || N <= nx || ny < 0 || N <= ny) continue;
					
					// 현재 학생 인접 봐석에 좋아하는 학생이 있다면 fav 증가
					if (favorite[classRoom[x][y]][classRoom[nx][ny]]) fav++;
				}
				
				if (fav == 1) 		satis += 1;
				else if (fav == 2) 	satis += 10;
				else if (fav == 3) 	satis += 100;
				else if (fav == 4) 	satis += 1000;
			}
		}
		
		System.out.print(satis);
	}
}
