import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map;
    static int[][] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // map 입력
        map = new int[N][M];
        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < M; ++j) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        // initiating
        boolean[][] check = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> adjacentWalls;
        count = new int[N][M];
        int curCount;

        // 지도의 0인 부분마다 인접한 0이 몇개인지 세어, 해당 지역과 인접한 벽에 횟수를 추가한다.
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (map[i][j] > 0 || check[i][j]) continue;

                curCount = 1;
                check[i][j] = true;
                q.offer(new int[] {i, j});
                adjacentWalls = new ArrayList<>();

                while (!q.isEmpty()) {
                    int r = q.peek()[0];
                    int c = q.poll()[1];

                    for (int d = 0; d < 4; ++d) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (invalidCoordination(nr, nc)) continue;
                        if (check[nr][nc]) continue;

                        if (map[nr][nc] > 0)  {
                            check[nr][nc] = true;
                            adjacentWalls.add(new int[] {nr, nc});
                            continue;
                        }

                        check[nr][nc] = true;
                        q.offer(new int[] {nr, nc});
                        curCount++;
                    }
                }

                for (int[] coordination: adjacentWalls) {
                    int r = coordination[0];
                    int c = coordination[1];

                    map[r][c] += curCount;
                    check[r][c] = false;
                }
            }
        }

        // 정답 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                sb.append(map[i][j] % 10);
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    public static boolean invalidCoordination(int r, int c) {
        return r < 0 || N <= r || c < 0 || M <= c;
    }
}
