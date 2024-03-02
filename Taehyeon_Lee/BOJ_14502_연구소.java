package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/*
실행시간 : 904ms
메 모 리 : 300244KB

고려사항
BFS, 완전탐색, 조합
N,M의 제한 값의 최대수가 8로 작기때문에 완전탐색으로 가능한 풀이법이였습니다.

1. 초기의 안전영역에서 중복조합으로 3개의 칸을 선택합니다.
2. 3개의 칸을 벽으로 설정한 후 바이러스가 퍼지는 상황을 시뮬레이션 합니다
3. 남은 안전영역의 수를 체크하고, 최대값인 경우, 정답을 업데이트 합니다
4. 모든 조합에 대해 탐색한 후 최종의 최대 안전영역의 구합니다.
*/

public class BOJ_14502_연구소_G4 {

    static class Point{
        int x,  y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, ans = Integer.MIN_VALUE;
    static int[][] map, resultMap;
    static List<Point> virus = new ArrayList<>();
    static List<Point> initialSafe = new ArrayList<>();
    static Point[] walls = new Point[3];
    static int[][] d = {{1,0},{-1,0},{0,1},{0,-1}};

    private static void input() throws IOException {
        System.setIn(Files.newInputStream(Paths.get("input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) initialSafe.add(new Point(i, j));
                else if(map[i][j] == 2) virus.add(new Point(i, j));
            }
        }
    }

    private static void createWall(int cnt, int start) {
        
        if(cnt == 3){

            // deepcopy
            resultMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    resultMap[i][j] = map[i][j];
                }
            }

            // create wall
            for (Point wall : walls) {
                resultMap[wall.x][wall.y] = 1;
            }
            // get maximum safezone count
            ans = Math.max(ans, getSafezone());
            return;
        }

        for (int i = start; i < initialSafe.size(); i++) {
            walls[cnt] = initialSafe.get(i);
            createWall(cnt+1, i+1);
        }
        
    }

    private static int getSafezone() {
        bfs();

        int safeCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(resultMap[i][j] == 0) safeCnt++;
            }
        }
        return safeCnt;
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>(virus);

        while (!q.isEmpty()) {
            Point cur = q.poll();
            resultMap[cur.x][cur.y] = 2;

            for (int i = 0; i < 4; i++) {
                int nextI = cur.x + d[i][0], nextJ = cur.y + d[i][1];
                if(nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) continue;
                if(resultMap[nextI][nextJ] == 0){
                    q.add(new Point(nextI, nextJ));
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {

        input();

        createWall(0,0);
        System.out.println(ans);
    }
}
