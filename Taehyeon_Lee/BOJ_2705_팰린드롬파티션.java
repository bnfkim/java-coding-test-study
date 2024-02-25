package Baekjoon;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/*
시작 시간 : 24-02-17 12:30
종료 시간 : 24-02-17 01:05
실행시간 : 80ms
메 모 리 : 11740KB
*/

public class BOJ_2705_팰린드롬파티션_S1 {

    static int T, N;
    static int[] memo = new int[1001];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    private static void input() throws IOException {

        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            N = Integer.parseInt(br.readLine());
            sb.append(dp(N)).append("\n");
        }
    }

    private static int dp(int x){
        if(memo[x] == 0){
            int val = 0;
            for(int i = x; i >= 0; i-= 2){
                val += dp((x-i)/2);
            }
            memo[x] = val;
        }
        return memo[x];
    }
    public static void main(String[] args) throws IOException {
        memo[0] = 1;
        memo[1] = 1;
        memo[2] = 2;

        input();
        bw.write(sb.toString());
        bw.close();
    }
}
