package Baekjoon;

import java.io.*;
import java.util.*;

/*
시작 시간 : 24-02-18 17:55
종료 시간 : 24-02-18 18:35
실행 시간 : 88ms
메 모 리 : 12376KB
*/

public class BOJ_2583_영역구하기_S1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int H, W, K;
    static int[][] rects;
    static boolean[][] map;
    static boolean[][] isVisited;
    static ArrayList<Integer> result;

    static int[] di = {1, -1, 0, 0};
    static int[] dj = {0, 0, 1, -1};

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[H][W];
        isVisited = new boolean[H][W];
        rects = new int[K][4];
        result = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                rects[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solve(){

        setRect();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if(isVisited[i][j]) continue;
                if(map[i][j]) continue;

                result.add(bfs(i, j));
            }
        }

        Collections.sort(result);

        sb.append(result.size()).append("\n");
        for (int r : result) sb.append(r).append(" ");
    }

    private static int bfs(int ci, int cj){
        int cnt = 0;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{ci, cj});
        isVisited[ci][cj] = true;
        cnt++;

        int[] cur;
        while (!q.isEmpty()){
            cur = q.poll();

            int ni, nj;
            for (int i = 0; i < 4; i++) {
                ni = cur[0] + di[i];
                nj = cur[1] + dj[i];

                if (ni < 0 || nj < 0 || ni >= H || nj >= W) continue;
                if (isVisited[ni][nj]) continue;
                if (map[ni][nj]) continue;

                cnt++;
                isVisited[ni][nj] = true;
                q.offer(new int[]{ni, nj});
            }
        }
        return cnt;
    }

    private static void setRect() {
        for (int iter = 0; iter < K; iter++) {
            for (int h = H - rects[iter][3]; h < H - rects[iter][1]; h++) {
                for (int w = rects[iter][0]; w < rects[iter][2]; w++) {
                    map[h][w] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        input();
        solve();

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
