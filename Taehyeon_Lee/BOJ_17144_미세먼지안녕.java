package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
시작 시간 : 24-02-13 19:45
종료 시간 : 24-02-13 20:45
실행시간 : 300ms
메 모 리 : 38036KB

*/

public class BOJ_17144_미세먼지안녕_G4 {

    static int R, C, T, ans;
    static int[][] map;
    static int[][] temp;
    static int[][] delta = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    static int cleaner = -1;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1 && cleaner == -1){
                    cleaner = i;
                }
            }
        }
    }

    static void print(){
        System.out.println("===================");
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void solve(){
        while(T-- > 0){
            spread();
            rotate();
        }
    }

    private static void rotate() {

        // 위 사이클
        for(int r = cleaner-1; r >= 1; r--) map[r][0] = map[r-1][0];
        for(int c = 0; c <= C-2; c++) map[0][c] = map[0][c+1];
        for(int r = 0; r <= cleaner-1; r++) map[r][C-1] = map[r+1][C-1];
        for(int c = C-1; c >= 2; c--) map[cleaner][c] = map[cleaner][c-1];
        map[cleaner][1] = 0;

        // 아래 사이클
        for(int r = cleaner+2; r <= R-2; r++) map[r][0] = map[r+1][0];
        for(int c = 0; c <= C-2; c++) map[R-1][c] = map[R-1][c+1];
        for(int r = R-1; r >= cleaner+2; r--) map[r][C-1] = map[r-1][C-1];
        for(int c = C-1; c >= 2; c--) map[cleaner+1][c] = map[cleaner+1][c-1];
        map[cleaner+1][1] = 0;
    }

    private static void spread() {
        temp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                spreadCell(i, j);
            }
        }

        for (int i = 0; i < R; i++) {
            map[i] = Arrays.copyOf(temp[i], C);
        }
        map[cleaner][0] = -1;
        map[cleaner+1][0] = -1;
    }

    private static void spreadCell(int idxI, int idxJ) {
        if(map[idxI][idxJ] == -1) return;

        int piece = map[idxI][idxJ] / 5;
        int spreadCnt = 0;
        for (int i = 0; i < 4; i++) {
            int nextI = idxI + delta[i][0], nextJ = idxJ + delta[i][1];
            if(nextI < 0 || nextJ < 0 || nextI >= R || nextJ >= C) continue;
            if(map[nextI][nextJ] == -1) continue;

            spreadCnt++;
            temp[nextI][nextJ] += piece;
        }
        temp[idxI][idxJ] += map[idxI][idxJ] - (piece * spreadCnt);
    }

    private static void countDust() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans += map[i][j];
            }
        }
        ans += 2;
    }

    public static void main(String[] args) throws IOException {

        input();
        solve();
        countDust();
        System.out.println(ans);
    }
}