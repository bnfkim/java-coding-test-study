package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
시작 시간 : 24-02-24 01:40
종료 시간 : 24-02-24 02:00
실행 시간 : 140ms
메 모 리 : 16404KB
*/

public class BOJ_1976_여행가자_G4 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;


    static int N, M;
    static int[][] map;
    static int[] travel, parents;
    static final int INF = 100_000_000;
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        travel = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            travel[i] = Integer.parseInt(st.nextToken());
        }

        // make set
        parents = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }


    private static int find(int v){
        if(parents[v] == v) return v;

        return parents[v] = find(parents[v]);
    }

    private static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        if (aRoot > bRoot) {
            parents[bRoot] = aRoot;
        } else {
            parents[aRoot] = bRoot;
        }

        return true;

    }

    private static String solve(){

        // union find로 그래프 내의 점들을 서로소 집합으로 만들음
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if(i == j) continue;
                if (map[i][j] == 0) continue;

                union(i+1, j+1);
            }
        }

        int p = find(travel[0]);
        for (int i = 1; i < M; i++) {
            if(find(travel[i]) != p) return "NO";
        }
        return "YES";
    }

    public static void main(String[] args) throws IOException {

        input();
        bw.write(solve());

        bw.close();
        br.close();
    }
}
