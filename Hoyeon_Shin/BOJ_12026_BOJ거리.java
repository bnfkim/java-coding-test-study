import java.io.*;
import java.util.*;

/**
 * 
 * 14448KB	144ms 
 *
 */
public class Main {
	static int N;
	static String block;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		block = br.readLine();
		
		dp = new int[N];
		dp[0] = 0;
		
		for (int cur = 0; cur < N; cur++) {
			
			// 첫 시작이 아닌데 dp가 0인 곳은 갈 수 없는 곳이므로 건너뛴다.
			if (cur != 0 && dp[cur] == 0) continue;
			
			char target = nextBlock(block.charAt(cur));
			
			// 현재 갈 수 있는 모든 다음 블록에 대해 최소 에너지를 갱신한다.
			for (int next = cur + 1; next < N; ++next) {
				if (block.charAt(next) != target) continue;
				
				int minCandidate = dp[cur] + (next - cur) * (next - cur);
				if (dp[next] == 0 || dp[next] > minCandidate)
					dp[next] = minCandidate;
			}
		}
		
		if (N != 1 && dp[N - 1] == 0)
			System.out.println(-1);
		else
			System.out.println(dp[N - 1]);
	}
	
	public static char nextBlock(char curBlock) {
		switch(curBlock) {
		case 'B': return 'O';
		case 'O': return 'J';
		default: return 'B';
		}
	}
}
