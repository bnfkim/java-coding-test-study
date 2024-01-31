package Yeonsoo_Joo;

import java.io.*;
import java.util.*;

class Pos {

    int x;
    int y;

    Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BOJ_16234_인구이동 {

    static final int dx[] = {0, 0, 1, -1};
    static final int dy[] = {1, -1, 0, 0};
    static int N, L, R;
    static int map[][];
    static boolean visited[][];
    static int days;
    static Queue<Pos> queue;
    static ArrayList<Pos> union;
    static boolean isMovable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move();

        System.out.println(days);
    }

    static void move() {
        while (true) {
            isMovable = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }
            if (!isMovable) break;
            else days++;
        }
    }

    static void bfs(int x, int y) {
        queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new Pos(x, y));

        union = new ArrayList<>();
        union.add(new Pos(x, y));

        while (!queue.isEmpty()) {
            Pos p = queue.poll();
            x = p.x;
            y = p.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    int diff = Math.abs(map[x][y] - map[nx][ny]);
                    if (!visited[nx][ny] && L <= diff && diff <= R) {
                        visited[nx][ny] = true;
                        queue.add(new Pos(nx, ny));
                        union.add(new Pos(nx, ny));
                    }
                }
            }
        }

        if (union.size() > 1) {
            isMovable = true;
        }
        // 연합의 인구수 구하기
        int sum = 0;
        for (int i = 0; i < union.size(); i++) {
            Pos p = union.get(i);
            sum += map[p.x][p.y];
        }
        // 인구 이동
        for (int i = 0; i < union.size(); i++) {
            Pos p = union.get(i);
            map[p.x][p.y] = sum / union.size();
        }
    }
}
