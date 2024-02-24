package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
시작 시간 : 24-02-24
종료 시간 : 24-02-24
실행 시간 : 272ms / 실패
메 모 리 : 28388KB
*/

public class BOJ_2110_공유기설치_G4 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, C, ans = 0;
    static int[] home;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        home = new int[N];

        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

    }

    public static void main(String[] args) throws IOException {

        input();

        Arrays.sort(home);

        int lo = 1;
        int hi = home[N-1] - home[0] + 1;

        while (lo < hi) {
            int mid = (hi + lo) / 2;

            if (canInstall(mid) < C) {
                hi = mid;
            }else {
                lo = mid+1;
            }
        }


        bw.write((lo-1) +"");
        bw.close();
        br.close();
    }

    private static int canInstall(int dist) {
        int cnt = 1;
        int lastLocation = home[0];

        for (int i = 1; i < home.length; i++) {
            if (home[i] - lastLocation >= dist) {
                cnt++;
                lastLocation = home[i];
            }
        }
        return cnt;
    }

}
