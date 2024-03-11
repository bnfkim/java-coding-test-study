import java.util.*;
import java.io.*;

/**
 * 19184kb
 * 196ms
 */
public class BOJ_2116_주사위쌓기 {

    static int N, MAX, arr[][];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < 6; i++) {
            simulate(i);
        }
        System.out.println(MAX);
    }

    private static void simulate(int stt) {
        int val = 0;
        for (int i = 0; i < N; i++) {
            int end = opposite(stt);
            val += getMaxValue(i, stt, end);
            stt = getNewIndex(i, end);
        }
        MAX = Math.max(MAX, val);
    }

    private static int getNewIndex(int idx, int end) {
        if (idx == N - 1)
            return 0;

        int stt = -1;
        for (int i = 0; i < 6; i++) {
            if (arr[idx][end] == arr[idx + 1][i]) {
                stt = i;
                break;
            }
        }

        return stt;
    }

    private static int getMaxValue(int idx, int stt, int end) {
        int max = 0;
        for (int i = 0; i < 6; i++) {
            if (i == stt || i == end)
                continue;
            max = Math.max(max, arr[idx][i]);
        }
        return max;
    }

    private static int opposite(int idx) {
        if (idx == 0 || idx == 5) {
            return idx == 0 ? 5 : 0;
        } else if (idx == 1 || idx == 3) {
            return idx == 1 ? 3 : 1;
        } else {
            return idx == 2 ? 4 : 2;
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        MAX = 0;
        arr = new int[N][6];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}