import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {

    static int N, visited;
    static int[][] S;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        combination(0, 0);
        System.out.println(min);
    }

    private static void combination(int depth, int start) {
        if (min == 0) {
            return;
        }

        if (depth == N / 2) {
            updateStatDiff();
            return;
        }

        for (int i = start; i < N; i++) {
            visited |= (1 << i);
            combination(depth + 1, i + 1);
            visited &= ~(1 << i);
        }
    }

    private static void updateStatDiff() {
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                if ((visited & (1 << i)) != 0 && (visited & (1 << j)) != 0) {
                    sum1 += S[i][j];
                }
                if ((visited & (1 << i)) == 0 && (visited & (1 << j)) == 0) {
                    sum2 += S[i][j];
                }
            }
        }

        min = Math.min(min, Math.abs(sum1 - sum2));
    }
}
