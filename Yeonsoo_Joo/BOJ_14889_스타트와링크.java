package Yeonsoo_Joo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {

    /*
     * 수행 시간: 332 ms
     * 메모리:  18544 KB
     */

    static int N;
    static int[][] S;
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combi(0, 0);
        System.out.println(ans);
    }

    private static void combi(int cnt, int start) {
        if (cnt == N/2) {
            int aSum = 0; int bSum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    if (visited[i] && visited[j]) {
                        aSum += (S[i][j] + S[j][i]);
                    } else if (!visited[i] && !visited[j]) {
                        bSum += (S[i][j] + S[j][i]);
                    }
                }
            }
            ans = Math.min(Math.abs(aSum - bSum), ans);
            return;
        }
        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combi(cnt+1, i+1);
                visited[i] = false;
            }
        }
    }
}
