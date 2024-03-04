package Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/*
시작 시간 : 24-02-25
종료 시간 : 24-02-25
실행 시간 : 164ms
메 모 리 : 12604KB
*/

public class BOJ_1182_부분수열의합_S2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, S, ans;
    static int[] arr;
    static boolean[] isSelected;

    private static void input() throws IOException{

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        isSelected = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void subset(int cnt){
        if(cnt == N){
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if(isSelected[i]) sum += arr[i];
            }

            if(sum == S) ans++;
            return;
        }

        isSelected[cnt] = true;
        subset(cnt+1);
        isSelected[cnt] = false;
        subset(cnt+1);
    }

    public static void main(String[] args) throws IOException {

        input();

        subset(0);

        if(S == 0) ans--;
        System.out.println(ans);
        bw.close();
        br.close();
    }
}
