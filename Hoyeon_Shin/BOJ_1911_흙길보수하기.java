import java.io.*;
import java.util.*;

/**
 * 19988KB	 596ms
 */
public class Main {
	static int[][] pools;
	static int N, L;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		pools = new int[N][2];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			pools[i][0] = Integer.parseInt(st.nextToken());
			pools[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 웅덩이를 시작점 기준으로 정렬
		Arrays.sort(pools, (p1, p2) -> {
			return p1[0] - p2[0];
		});
		
		int coveredEnd = -1;  // 널빤지로 덮은 마지막 위치
		int count = 0;
		for (int[] pool: pools) {
			
			// 앞에 설치한 널빤지로 현재 웅덩이가 덮어지는 경우 건너뛴다.
			if (coveredEnd > pool[1])
				continue;
			
			int start;
			
			// 웅덩이 시작점이 이전에 설치한 널빤지보다 뒤에 있는 경우
			if (coveredEnd < pool[0])
				start = pool[0];
			
			// 웅덩이에 이전에 설치한 널빤지가 걸쳐있는 경우
			else
				start = coveredEnd + 1;

			// 현재 웅덩이가 커버될 때까지 널빤지 설치
			while (start < pool[1]) {
				count++;
				coveredEnd = start + L - 1;
				start += L;
			}	
		}
    
		System.out.println(count);
	}
}
