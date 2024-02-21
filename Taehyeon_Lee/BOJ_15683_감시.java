package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 감시 15683 G4
시작 시간 : 24-01-26 23:40
종료 시간 : 24-01-27 19:30
실행시간 : 248ms

고려사항
cctv의 위치를 list로 저장
DFS를 이용하여 각 cctv가 바라볼수 있는 각도별로 모든 경우의 수를 탐색
모든 cctv를 체크 할 때마다 남은 사각지대의 값을 현재 최소 사각지대의 수와 비교하여
최소 사각지대의 값을 출력
*/

public class Solution15683 {

    static class CCTV {
        int type;
        int i, j;

        public CCTV(int type, int i, int j) {
            this.type = type;
            this.i = i;
            this.j = j;
        }
    }
    static int[][] data;
    static StringTokenizer st;
    static List<CCTV> cctvs = new ArrayList<>();

    public static int[][][] mode = {
            {{0}}, // 사용 x
            {{0}, {1}, {2}, {3}}, // type 1 cctv 4가지 경우의 수
            {{2, 3}, {0, 1}}, // type 2 cctv  2가지 경우의 수
            {{0, 3}, {1, 3}, {1, 2}, {0, 2}}, // type 3 cctv 4가지 경우의 수
            {{0, 2, 3}, {0, 1, 3}, {1, 2, 3}, {0, 1, 2}}, // type 4 cctv 4가지 경우의 수
            {{0, 1, 2, 3}}}; // type 5 cctv 1가지 경우의 수

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int ans;
    static int N, M;
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        data = new int[N][M];

        int zeroCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info >= 1 && info <= 5) {
                    cctvs.add(new CCTV(info, i, j));
                } else zeroCnt++;

                data[i][j] = info;
            }
        }

        ans = zeroCnt;
        combination(0, cctvs.size(), data);
        System.out.println(ans);
    }

    private static void combination(int depth, int cctvNum, int[][] map) {

        // 마지막 cctv까지 체크한 경우
        if(depth == cctvNum){
            int cnt = check(map);
            ans = Math.min(ans, cnt);
            return;
        }

        int cctvType = cctvs.get(depth).type;
        int x = cctvs.get(depth).i;
        int y = cctvs.get(depth).j;

        for (int i = 0; i < mode[cctvType].length; i++) {

            // 현재 상태를 복사한 객체 사용
            int[][] mapCopy = new int[N][M];
            for(int k = 0; k < N; k++) {
                mapCopy[k] = map[k].clone();
            }

            for (int j = 0; j < mode[cctvType][i].length; j++) {
                int dir = mode[cctvType][i][j]; // mode에 따라 cctv가 바라보는 방향 설정
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // cctv가 볼 수 있는 방향 체크
                while (true) {
                    if(nx < 0 || nx >= N || ny < 0 || ny >= M) break;
                    if(map[nx][ny] == 6) break;

                    mapCopy[nx][ny] = -1; // 가시 구역 처리
                    nx += dx[dir];
                    ny += dy[dir];
                }
            }
            combination(depth+1, cctvNum, mapCopy);
        }
    }


    private static int check(int[][] map) {
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

}