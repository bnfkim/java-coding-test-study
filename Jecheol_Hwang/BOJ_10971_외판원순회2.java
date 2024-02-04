package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971_외판원순회2 {
    static int n;
    static int ans = (int) 1e9;
    static int visited;
    static int[][] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        adj = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            visited |= 1 << i;
            go(i, i, 1, 0); // 출발 노드 순회
            visited &= ~(1 << i);
        }

        System.out.println(ans);
    }

    private static void go(int start, int now, int cnt, int res) {
        if (cnt == n) {
            if (adj[now][start] > 0) {
                res += adj[now][start];
                ans = Math.min(ans, res);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((visited & 1 << i) != 0 || adj[now][i] == 0) {
                continue;
            }
            res += adj[now][i];
            visited |= 1 << i;
            go(start, i, cnt + 1, res);
            res -= adj[now][i];
            visited &= ~(1 << i);
        }
    }
}
