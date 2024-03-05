package 알고리즘연습.boj;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 폴리오미노 : 크기가 1x1인 정사각형을 여러 개 이어서 붙인 도형
 * 1. 정사각형은 서로 겹치면 안된다.
 * 2. 도형은 모두 연결되어 있어야 한다.
 * 3. 정사각형의 변끼리 연결되어 있어야 한다.
 *
 * 정사각형 4개를 이어 붙인 폴리오미노를 테트로미노라고 함
 * 테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하라.
 *
 * 종이의 세로 N (500 이하) , 가로 M (500 이하)
 *
 * @intuition 전체 탐색해도 문제 없겠다.
 * @algorithm 백트래킹 완탐
 * @time O(NM) : 시작점 선정하고 재귀 탐색 + 볼록블록 별도 탐색 -> 784 ms
 * @memory O(NM) -> 43908 kB
 */
public class BOJ_14500_테트로미노 {
    private static boolean[][] visited;
    private static int N, M;
    private static int[][] map;
    private static int[] dy = {1, -1, 0, 0};
    private static int[] dx = {0, 0, 1, -1};
    private static int ans;
    private static Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                go(i, j, map[i][j], 1);
                visited[i][j] = false;
                fuBlock(i, j);
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    private static void go(int y, int x, int res, int depth) {
        if (depth == 4) {
            if (ans < res) {
                ans = res;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (canGo(ny, nx)) {
                visited[ny][nx] = true;
                go(ny, nx, res + map[ny][nx], depth+1);
                visited[ny][nx] = false;
            }
        }
    }

    private static boolean inRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    private static boolean canGo(int y, int x) {
        return inRange(y, x) && !visited[y][x];
    }

    private static void fuBlock(int y, int x) {
        int up = 0, down = 0, left = 0, right = 0, center = 0;
        center = map[y][x];
        if (inRange(y - 1, x)) {
            up = map[y-1][x];
        }
        if (inRange(y, x - 1)) {
            left = map[y][x-1];
        }
        if (inRange(y, x + 1)) {
            right = map[y][x+1];
        }
        if (inRange(y + 1, x)) {
            down = map[y + 1][x];
        }
        int[] arr = {up, down, left, right};
        comb(0, 0, center, arr);
    }

    private static void comb(int cnt, int start, int res, int[] arr) {
        if (cnt == 3) {
            if (ans < res) {
                ans = res;
            }
            return;
        }
        for (int i = start; i < 4; i++) {
            if (arr[i] != 0) {
                comb(cnt + 1, i + 1, res + arr[i], arr);
            }
        }
    }
}
