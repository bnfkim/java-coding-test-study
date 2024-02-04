package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 세 가지 풀이로 접근했습니다.
 *
 * 1. DFS
 * - 판단 근거 : 그냥 행렬 보자마자 무지성으로 떠오른 풀이입니다.
 * - 시간복잡도 : O(NM); 인접행렬로 전체탐색
 * - 실행시간 : 344 ms
 *
 * 2. 중복조합
 * - 판단 근거 : 격자상 이동 방식에 따라 visited를 검사할 이유가 없으니 이동 방향들의 조합으로 풀 수 있을 것으로 봤습니다.
 * - 시간복잡도 : O(NM); 중복조합 nHr == (n + r - 1) C (r) 이므로 (n + r - 1)! / (n - 1)! * (r)! 입니다.
 * - 실행 시간 : 160 ms
 *
 * 3. DP
 * - 판단 근거 : 점화식이 간단할 것 같았고, 경로가 매 회마다 누적되는 느낌이니 DP로 가능할 것으로 봤습니다.
 * - 시간복잡도 : O(NM); 2차원 loop 돌면서 dp matrix tabulation
 * - 실행 시간 : 128 ms
 */
public class BOJ_10164_격자상의경로 {
    private static int n, m, k, ky, kx;
    private static int resK, resNM;
    private static int[] dx = {1, 0};
    private static int[] dy = {0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        k = (k == 0) ? n*m : k;
        
        ky = (k - 1) / m;
        kx = (k - 1) % m;

//        solveByDP(); // DP 이용한 풀이
//        solveByDfs(); // DFS 이용한 풀이
//        solveByComb(); // 중복조합 이용한 풀이
    }

    private static void solveByDP() {
        int[][] dp = new int[n][m];

        // init
        for (int i = 1; i <= ky; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= kx; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= ky; i++) {
            for (int j = 1; j <= kx; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        //init
        for (int i = ky; i < n; i++) {
            dp[i][kx] = dp[ky][kx];
        }
        for (int i = kx; i < m; i++) {
            dp[ky][i] = dp[ky][kx];
        }

        for (int i = ky+1; i < n; i++) {
            for (int j = kx+1; j < m; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        System.out.println(dp[n-1][m-1]);
    }


    private static void solveByComb() {
        // 조건 활용하기 위해 값 조정
        n--;
        m--;

        combToK(0, 0);
        combToNM(ky, kx);

        System.out.println(resK * resNM);
    }

    private static void combToK(int depth, int start) {
        if (depth == ky) {
            resK++;
            return;
        }
        for (int i = start; i <= kx; i++) {
            combToK(depth + 1, i); // 중복조합이므로 start = i;
        }
    }

    private static void combToNM(int depth, int start) {
        if (depth == n) {
            resNM++;
            return;
        }
        for (int i = start; i <= m; i++) {
            combToNM(depth + 1, i); // 중복조합이므로 start = i;
        }
    }

    private static void solveByDfs() {
        // 조건 활용하기 위해 값 조정
        n--;
        m--;

        goK(0, 0);
        goNM(ky, kx);

        System.out.println(resK * resNM);
    }

    private static void goK(int y, int x) {
        if (y == ky && x == kx) {
            resK++;
            return;
        }
        for (int i = 0; i < 2; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (inRange(ny, nx, ky, kx)) {
                goK(ny,nx);
            }
        }
    }

    private static void goNM(int y, int x) {
        if (y == n && x == m) {
            resNM++;
            return;
        }
        for (int i = 0; i < 2; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (inRange(ny, nx, n, m)) {
                goNM(ny,nx);
            }
        }
    }

    private static boolean inRange(int y, int x, int my, int mx) {
        return y >= 0 && y <= my && x >= 0 && x <= mx;
    }
}
