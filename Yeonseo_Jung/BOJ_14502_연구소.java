import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {
    // 76648kb,	296ms
    final static int WALL_NUMBER = 3;

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, maxSafetyZone;
    static int virusCnt, wallCnt;
    static int[][] lab;
    static List<Pos> candidateWall;
    static Pos[] walls;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];
        candidateWall = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 0) {
                    candidateWall.add(new Pos(i, j));
                }
                if (lab[i][j] == 1) {
                    ++wallCnt;
                }
            }
        }
        wallCnt += WALL_NUMBER;
        getAnswer();
    }

    private static void getAnswer() {
        walls = new Pos[WALL_NUMBER];
        findWall(0, 0);

        System.out.println(maxSafetyZone);
    }

    private static void findWall(int idx, int start) {
        if (idx == WALL_NUMBER) {
            // 벽 세우기
            setWall(1);
            // 바이러스 퍼뜨리기
            spreadVirus();
            // 벽 복원
            setWall(0);
            return;
        }

        for (int i = start, size = candidateWall.size(); i < size; i++) {
            walls[idx] = candidateWall.get(i);
            findWall(idx + 1, i + 1);
        }
    }

    private static void spreadVirus() {
        virusCnt = 0;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 2 && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        int safeZ = N * M - virusCnt - wallCnt;
        maxSafetyZone = Math.max(maxSafetyZone, safeZ);
    }

    private static void bfs(int strX, int strY) {
        Queue<Pos> virus = new ArrayDeque<>();
        virus.add(new Pos(strX, strY));
        visited[strX][strY] = true;

        int nx, ny;
        while (!virus.isEmpty()) {
            Pos curV = virus.poll();
            ++virusCnt;
            for (int i = 0; i < 4; i++) {
                nx = curV.x + dx[i];
                ny = curV.y + dy[i];

                if (nx < 0 || nx == N || ny < 0 || ny == M) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;

                if (lab[nx][ny] != 1) {
                    virus.add(new Pos(nx, ny));
                }
            }
        }
    }

    private static void setWall(int sep) {
        for (int i = 0; i < WALL_NUMBER; i++) {
            lab[walls[i].x][walls[i].y] = sep;
        }
    }
}
