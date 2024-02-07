package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
    /*
    * 실행시간 : 424 ms
    * */
    private static int N;
    private static int ans = (int) 1e9;
    private static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0, 0);

        System.out.println(ans);

    }

    private static void comb(int cnt, int start, int visited) {
        if (cnt == N / 2) {
            int A = 0;
            int B = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    // A score ++
                    if ((visited & 1 << i) != 0 && (visited & 1 << j) != 0) {
                        A += (arr[i][j] + arr[j][i]);
                    }
                    // B score ++
                    if ((visited & 1 << i) == 0 && (visited & 1 << j) == 0) {
                        B += (arr[i][j] + arr[j][i]);
                    }
                }
            }
            int res = Math.abs(A - B);
            ans = Math.min(ans, res);
            return;
        }

        for (int i = start; i < N; i++) {
            comb(cnt + 1, i + 1, visited | 1 << i);
        }
    }
}
