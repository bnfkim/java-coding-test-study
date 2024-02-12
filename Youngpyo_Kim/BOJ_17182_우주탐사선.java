import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17182_우주탐사선 {
    static int[][] dist;
    static int n, k, ans;

    public static void perm(int cnt, int flag, int from, int local_ans) {
        if (cnt == n) {
            ans = Math.min(ans, local_ans);
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((flag & (1 << i)) != 0)
                continue;

            perm(cnt + 1, flag | (1 << i), i, local_ans + dist[from][i]);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        perm(1, (1 << k), k, 0);

        System.out.println(ans);
    }
}

