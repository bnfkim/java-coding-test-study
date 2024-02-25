package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/*
시작 시간 : 24-02-16 11:00
종료 시간 : 24-02-16 12:00
실행시간 : 120ms
메 모 리 : 11912KB
*/

public class BOJ_17182_우주탐사선_G3 {

    static int N, K, ans = Integer.MAX_VALUE;
    static int[][] dist;
    static boolean[] isVisited;

    private static void input() throws IOException {
        System.setIn(Files.newInputStream(Paths.get("input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[N][N];
        isVisited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solve(){
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        isVisited[K] = true;
        dfs(1, K, 0);
    }

    private static void dfs(int cnt, int cur, int sum) {
        if(cnt == N){
            ans = Math.min(ans, sum);
            return;
        }

        for(int i = 0; i < N; i++){
            if(isVisited[i]) continue;

            isVisited[i] = true;
            dfs(cnt+1, i, sum+dist[cur][i]);
            isVisited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(ans);
    }
}
