package 문제풀이;

import java.util.*;
import java.io.*;

public class 연구소 {

    static int WALL_SIZE = 3;
    static int R, C, MAX;
    static int[][] map;
    static ArrayList<int[]> virusList;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        virusList = new ArrayList<>();

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) virusList.add(new int[]{j, i});
            }
        }
        makeWall(0);
        System.out.println(MAX);
    }
    static void makeWall(int dep) {
        if(dep == WALL_SIZE) {
            bfs();
            return;
        }

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j] != 0) continue;

                map[i][j] = 3;
                makeWall(dep + 1);
                map[i][j] = 0; //다시 복구
            }
        }
    }
    static void bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        for(int[] virus : virusList) queue.offer(virus);
        int[][] copyMap = copy();

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int i=0; i<4; i++) {
                int nc = now[0] + dx[i];
                int nr = now[1] + dy[i];

                if(nc<0 || nr<0 || nc>=C || nr>=R) continue;
                if(copyMap[nr][nc] != 0) continue;

                copyMap[nr][nc] = 2; //바이러스 감염
                queue.add(new int[]{nc, nr});
            }
        }
        MAX = Math.max(getSafeZoneSize(copyMap), MAX);
    }

    static int[][] copy() {
        int[][] copyMap = new int[R][C];
        for(int i=0; i<R; i++) copyMap[i] = map[i].clone();
        return copyMap;
    }

    static int getSafeZoneSize(int[][] copyMap) {
        int cnt = 0;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(copyMap[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }
}
