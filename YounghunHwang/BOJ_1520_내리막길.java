import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int M, N, H;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        // 조기종료 조건
        if (map[0][0] < N + M - 1) {
            System.out.println(0);
            return;
        }

        // DP 시작
        init();
        DP();

        // 정답 출력
        System.out.println(H);
    }

    private static void init() {
        dp = new int[M][N];
        dp[0][0] = 1;
        for (int i = 0; i < M - 1; i++) { // 세로 초기화
            if (map[i][0] > map[i + 1][0]) {
                dp[i + 1][0] = 1;
            } else {
                dp[i + 1][0] = 0;
            }
        }
        for (int i = 0; i < N - 1; i++) { // 가로 초기화
            if (map[0][i] > map[0][i + 1]) {
                dp[0][i + 1] = 1;
            } else {
                dp[0][i + 1] = 0;
            }
        }
    }

    private static void DP() {
        boolean updated = true;

        while (updated) {
            updated = false;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    int sum = 0;
                    for (int k = 0; k < 4; k++) { // 4방향 진입 체크
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
                            continue;
                        }
                        if (map[i][j] < map[nx][ny]) {
                            sum += dp[nx][ny];
                        }
                    }
                    if (dp[i][j] != sum) { // 이전 경우의 수와 다르면 업데이트
                        updated = true;
                        dp[i][j] = sum;
                    }
                }
            }
        }

        H = dp[M - 1][N - 1];
    }
}