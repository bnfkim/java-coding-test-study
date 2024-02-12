import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, ans;
	static int[] arr;
	static int[] team1;
	static int[] team2;
	static boolean[] isSelected;
  	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		map = new int[N][N];
		team1 = new int[N/2];
		team2 = new int[N/2];
		isSelected = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 조합으로 팀을 뽑기
		for(int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		ans = Integer.MAX_VALUE;
		combi(0, 0);
		System.out.println(ans);
	}
	
	// N/2만큼 뽑는 조합의 경우의 수
	static void combi(int cnt, int start) {
		if(cnt == N/2) {
			int score1 = 0, score2 = 0;
			score1 = score(team1);
			int idx = 0;
			for(int i = 0; i< N; i++) {
				if(!isSelected[i]) {
					team2[idx++] = i + 1;
				}
			}
			score2 = score(team2);
			
			ans = Math.min(ans, Math.abs(score1 - score2));
			return;
		}
		
		for(int i = start; i < N ; i++) {
			isSelected[i] = true;
			team1[cnt] = arr[i];
			combi(cnt + 1, i+1);
			isSelected[i] = false;
		}
	}
	
	// 점수를 계산하는 메서드
	static int score(int arr[]) {
		int sum = 0;
		
		for(int i = 0; i < N/2; i++) {
			for(int j = 0; j < N/2; j++) {
				sum += map[arr[i] - 1][arr[j] - 1];
			}
		}
		
		return sum;
	}


	
}
