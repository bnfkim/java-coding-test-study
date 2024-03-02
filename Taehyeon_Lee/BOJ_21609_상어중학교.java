package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
실행 시간 : 152ms
메 모 리 : 23452KB
*/

public class BOJ_21609_상어중학교_G2 {

    static class Cell implements Comparable<Cell>{

        int i, j, rainbow, size;
        // 본인 셀 포함
        List<int[]> group = new ArrayList<>();

        public Cell(int i, int j, int rainbow, int size, List<int[]> group) {
            this.i = i;
            this.j = j;
            this.rainbow = rainbow;
            this.size = size;
            this.group = group;
        }

        @Override
        public int compareTo(Cell o) {
            if(this.size != o.size) return o.size - this.size;
            if(this.rainbow != o.rainbow) return o.rainbow - this.rainbow;
            if(this.i != o.i) return o.i - this.i;
            return o.j - this.j;
        }

    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, score;
    static Integer[][] map;
    static boolean[][] grouped;
    static PriorityQueue<Cell> pq = new PriorityQueue<>();

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};

    private static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Integer[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void bfs(int idxI, int idxJ){
        int rainbowCnt = 0;
        int color = map[idxI][idxJ];
        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> group = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];

        visited[idxI][idxJ] = true;
        q.offer(new int[]{idxI, idxJ});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0], cj = cur[1];
            group.add(new int[]{ci, cj});

            if(map[ci][cj] == 0) rainbowCnt++;

            for (int i = 0; i < 4; i++) {
                int ni = ci + di[i];
                int nj = cj + dj[i];

                if(ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if(visited[ni][nj]) continue;
                if(map[ni][nj] == null) continue;

                // 색이 같거나 OR 무지개
                if(map[ni][nj] == color || map[ni][nj] == 0){
                    visited[ni][nj] = true;
                    q.offer(new int[]{ni, nj});
                }
            }
        }

        if(group.size() < 2) return;

        // 대표 셀 정하기
        group.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]) return o1[0] - o2[0];
                else return o1[1] - o2[1];
            }
        });

        int[] head = new int[]{-1, -1};
        boolean initHead = false;
        for (int[] cur : group) {
            grouped[cur[0]][cur[1]] = true;
            if (!initHead && map[cur[0]][cur[1]] != 0) {
                head = cur;
                initHead = true;
            }
        }

        Cell cell = new Cell(head[0], head[1], rainbowCnt, group.size(), group);

        pq.add(cell);
    }
    private static void search(){

        pq.clear();
        grouped = new boolean[N][N];

        // BFS 탐색하며 PQ에 담기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == null || map[i][j] == -1
                        || map[i][j] == 0 || grouped[i][j]) continue;

                bfs(i, j);
            }
        }
    }

    private static void destroy(List<int[]> near){

        // 블록 삭제
        for(int[] cur : near){
            map[cur[0]][cur[1]] = null;
        }

        // 점수 계산
        score += near.size() * near.size();
    }

    private static boolean fall(int ci, int cj){
        if(ci + 1 >= N || map[ci+1][cj] != null) return false;

        map[ci+1][cj] = map[ci][cj];
        map[ci][cj] = null;
        return true;
    }

    private static void gravity(){

        for(int w = 0; w < N; w++){
            for (int h = N-1; h >= 0; h--) {
                if(map[h][w] == null || map[h][w] == -1) continue;

                int curH = h;
                // 내려갈수 있다면 내려감
                while(fall(curH++, w));
            }
        }
    }

    private static void rotate(){
        // 회전
        Integer[][] temp = new Integer[N][N];
        for(int a = N-1, i = 0; a >= 0; a--, i++){
            for(int b = 0, j = 0; b < N; b++, j++){
                temp[i][j] = map[b][a];
            }
        }
        map = temp;
    }
    public static void main(String[] args) throws IOException {

        input();

        search();
        while(!pq.isEmpty()){
            Cell cur = pq.poll();

            destroy(cur.group);
            gravity();
            rotate();
            gravity();

            search();
        }
        System.out.println(score);
    }
}