import java.util.*;
import java.io.*;

/**
 * BOJ_1182_부분수열의합
 */
public class BOJ_1182_부분수열의합 {

    static int N, S, arr[];
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();
    }

    public static void solve() {
        int cnt = 0;

        for (int i = 1; i < (1 << N); i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                if ((i & 1 << j) != 0) {
                    sum += arr[j];
                }
            }
            if (sum == S) cnt++;
        }

        System.out.println(cnt);
    }
}