package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
시작 시간 : 24-02-17 18:00
종료 시간 : 24-02-17 18:20
실행시간 : 300ms
메 모 리 : 68948KB
*/

public class BOJ_2447_별찍기10_G5 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static char[][] map;
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i],'*');
        }
    }
    private static void dfs(int n, int si, int sj) {
        if(n == 3){
            map[si+1][sj+1] = ' ';
            return;
        }

        // 중심부 빈칸 설정
        for (int i = si+n/3; i < si+2*n/3; i++) {
            for (int j = sj+n/3; j < sj+2*n/3; j++) {
                map[i][j] = ' ';
            }
        }

        // 나머지 8칸 재귀
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                if(i == 1 && j == 1) continue;
                dfs(n/3, si+n/3*i, sj+n/3*j);
            }
        }

    }
    public static void main(String[] args) throws IOException {

        input();
        dfs(N, 0, 0);
        for (char[] row : map){
            for(char c : row)
                sb.append(c);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
    }
}
