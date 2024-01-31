import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683_감시 {

    static int N, M;
    static int min = 100;
    static int[][] map;
    static boolean[][] visited;
    static List<CCTV> cctvs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0 && map[i][j] < 6) {
                    cctvs.add(new CCTV(map[i][j], i, j));
                }
            }
        }

        permutation(0, new int[cctvs.size()]);
        System.out.println(min);
    }

    private static void permutation(int idx, int[] directions) {
        if (idx == directions.length) {
            monitor(directions);
            return;
        }

        for (int i = 0; i < cctvs.get(idx).getDirectionCount(); i++) {
            directions[idx] = i;
            permutation(idx + 1, directions);
        }
    }

    private static void monitor(int[] directions) {
        visited = new boolean[N][M];

        for (int i = 0; i < directions.length; i++) {
            int direction = directions[i];
            CCTV cctv = cctvs.get(i);
            cctv.check(map, direction);
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || map[i][j] != 0) {
                    continue;
                }
                count++;
            }
        }

        min = Math.min(min, count);
    }


    static class CCTV {
        int type;
        int x;
        int y;

        public CCTV(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }

        public int getDirectionCount() {
            if (type == 2) {
                return 2;
            }
            if (type == 5) {
                return 1;
            }
            return 4;
        }

        public void check(int[][] map, int direction) {
            int[] dx = {0, 1, 0, -1};
            int[] dy = {1, 0, -1, 0};

            if (type == 1) {
                int nx = x;
                int ny = y;
                while (true) {
                    nx += dx[direction];
                    ny += dy[direction];
                    if (isOutOfRange(nx, ny) || map[nx][ny] == 6) {
                        break;
                    }
                    visited[nx][ny] = true;
                }
            }
            if (type == 2) {
                for (int i = 0; i < 4; i++) {
                    if (i == direction || i == (direction + 2)) {
                        continue;
                    }
                    int nx = x;
                    int ny = y;
                    while (true) {
                        nx += dx[i];
                        ny += dy[i];
                        if (isOutOfRange(nx, ny) || map[nx][ny] == 6) {
                            break;
                        }
                        visited[nx][ny] = true;
                    }
                }
            }
            if (type == 3) {
                for (int i = 0; i < 4; i++) {
                    if (i == direction || i == (direction + 3) % 4) {
                        continue;
                    }
                    int nx = x;
                    int ny = y;
                    while (true) {
                        nx += dx[i];
                        ny += dy[i];
                        if (isOutOfRange(nx, ny) || map[nx][ny] == 6) {
                            break;
                        }
                        visited[nx][ny] = true;
                    }
                }
            }
            if (type == 4) {
                for (int i = 0; i < 4; i++) {
                    if (i == direction) {
                        continue;
                    }
                    int nx = x;
                    int ny = y;
                    while (true) {
                        nx += dx[i];
                        ny += dy[i];
                        if (isOutOfRange(nx, ny) || map[nx][ny] == 6) {
                            break;
                        }
                        visited[nx][ny] = true;
                    }
                }
            }
            if (type == 5) {
                for (int i = 0; i < 4; i++) {
                    int nx = x;
                    int ny = y;
                    while (true) {
                        nx += dx[i];
                        ny += dy[i];
                        if (isOutOfRange(nx, ny) || map[nx][ny] == 6) {
                            break;
                        }
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        private boolean isOutOfRange(int x, int y) {
            return x < 0 || x >= N || y < 0 || y >= M;
        }

    }
}