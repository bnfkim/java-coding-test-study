import java.util.*;
import java.io.*;

/**
 * 14176kb
 * 456ms
 */
public class BOJ_24954_물약구매 {

    static int N, MIN, price[], sale[][];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int[] sales = new int[N];
        perm(0, 0, 0, sales);
        System.out.println(MIN);
    }

    private static void perm(int depth, int coins, int visited, int[] sales) {
        if (coins >= MIN) {
            return;
        }

        if (depth == N) {
            MIN = Math.min(MIN, coins);
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((visited & 1 << i) != 0)
                continue;

            for (int idx = 0; idx < N; idx++) {
                sales[idx] += sale[i][idx];
            }

            perm(depth + 1, coins + Math.max(price[i] - sales[i], 1), visited | 1 << i, sales);

            for (int idx = 0; idx < N; idx++) {
                sales[idx] -= sale[i][idx];
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        price = new int[N];
        sale = new int[N][N];
        MIN = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int T = Integer.parseInt(br.readLine());
            for (int j = 0; j < T; j++) {
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());
                sale[i][idx - 1] = val;
            }
        }
    }
}
