BOJ_21609_상어중학교package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @intuition 경우의 수 저장 배열 선언하고, 이동 가능하면 경우의 수 배열 갱신
 *
 * @algorithm DP
 * @time O(NM) 델타탐색 + 메모이제이션 -> 380ms
 * @memory O(NM) M by N 배열 사용 -> 24964 KB
 */

// DFS 시도 -> timeout
public class BOJ_1520_내리막길 {
    private static int[][] height; // 높이 저장
    private static int[][] dp; // 해당 위치로 이동할 수 있는 최대 경우의 수
    private static int M, N; // M : 세로 , N : 가로 (500이하 자연수)
    private static int H; // 정답 (이동 가능한 경로의 수 - 10억 이하의 음이 아닌 정수);
    private static int[] dy = {0, 1, 0, -1};
    private static int[] dx = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        // 높이배열 초기화
        height = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP 초기화
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][0] = 1;
        
        int ans = go(M - 1, N - 1, 0);
        System.out.println(ans);
    }

    private static int go(int y, int x, int nextHeight) {
        if (!inRange(y, x)) {
            return 0;
        }
        int curHeight = height[y][x];
        if (nextHeight >= curHeight) {
            return 0;
        }
        if (dp[y][x] != -1) {
            return dp[y][x];
        }
        return dp[y][x] = go(y - 1, x, curHeight) + go(y, x - 1, curHeight) + go(y + 1, x, curHeight) + go(y, x + 1, curHeight);
    }

    private static boolean inRange(int y, int x) {
        return y >= 0 && y < M && x >= 0 && x < N;
    }

}
