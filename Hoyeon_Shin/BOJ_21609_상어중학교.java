import java.util.*;
import java.io.*;

/**
 * 15244KB   164ms
 */
class Group implements Comparable<Group> {
    int size;
    int rainbow;
    int row, col;

    public Group(int size, int rainbow, int row, int col) {
        this.size = size;
        this.rainbow = rainbow;
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Group another) {
        if (this.size == another.size) {
            if (this.rainbow == another.rainbow) {
                if (this.row == another.row)
                    return another.col - this.col;

                return another.row - this.row;
            }
            return another.rainbow - this.rainbow;
        }
        return another.size - this.size;
    }
}

public class Main {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static int N, M;
    static List<Group> groups = new ArrayList<>();
    static int totalScore;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 처리
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 오토 플레이 진행
        while (true) {
            findBlockGroup();

            if (groups.isEmpty()) break;
            Collections.sort(groups);

            popBlockGroup();
            gravity();
            rotate();
            gravity();
        }

        System.out.println(totalScore);
    }

    public static boolean invalidCoord(int x, int y) {
        return x < 0 || N <= x || y < 0 || N <= y;
    }

    // 현재 맵 상태에서 블록 그룹을 찾는다.
    public static void findBlockGroup() {
        groups.clear();

        Queue<int[]> rainbowQ = new ArrayDeque<>();
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (visit[i][j] || map[i][j] <= 0) continue;

                int size = 0, rainbow = 0;  // 블록 그룹 크기, 무지개 블록 수
                int standardRow = N, standartCol = N;  // 기준블록의 행, 열
                int groupColor = map[i][j];

                q.offer(new int[] {i, j});
                visit[i][j] = true;

                while (!q.isEmpty()) {
                    int x = q.peek()[0];
                    int y = q.poll()[1];

                    if (map[x][y] == 0) rainbow++;
                    else if (x < standardRow || (x == standardRow && y < standartCol)) {
                        standardRow = Math.min(standardRow, x);
                        standartCol = Math.min(standartCol, y);
                    }
                    size++;


                    for (int d = 0; d < 4; ++d) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (invalidCoord(nx, ny)) continue;
                        if (visit[nx][ny]) continue;
                        if (map[nx][ny] != 0 && map[nx][ny] != groupColor) continue;

                        visit[nx][ny] = true;

                        // 무지개 블록은 다른 그룹에 속할 수도 있으므로 좌표를 기억해놨다가 방문체크를 해제해준다.
                        if (map[nx][ny] == 0)
                            rainbowQ.offer(new int[] {nx, ny});

                        q.offer(new int[] {nx, ny});
                    }
                }

                if (size >= 2)
                    groups.add(new Group(size, rainbow, standardRow, standartCol));

                while (!rainbowQ.isEmpty())
                    visit[rainbowQ.peek()[0]][rainbowQ.poll()[1]] = false;
            }
        }
    }

    // 현재 맵을 90도 반시계 방향으로 회전
    public static void rotate() {
        int[][] newMap = new int[N][N];

        for (int x = 0; x < N; ++x) {
            for (int y = 0; y < N; ++y) {
                newMap[N - 1 - y][x] = map[x][y];
            }
        }

        map = newMap;
    }

    // 터뜨릴 블록 그룹을 찾아 터뜨린다. (터뜨려 빈 공간이 된 곳은 -2로 처리)
    public static void popBlockGroup() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];

        Group cur = groups.get(0);
        int x = cur.row;
        int y = cur.col;

        q.offer(new int[] {x, y});
        visit[x][y] = true;
        int groupColor = map[x][y];

        totalScore += cur.size * cur.size;

        while (!q.isEmpty()) {
            x = q.peek()[0];
            y = q.poll()[1];

            map[x][y] = -2;

            for (int d = 0; d < 4; ++d) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (invalidCoord(nx, ny)) continue;
                if (visit[nx][ny]) continue;
                if (map[nx][ny] != 0 && map[nx][ny] != groupColor) continue;

                visit[nx][ny] = true;
                q.offer(new int[] {nx, ny});
            }
        }

    }

    // 중력을 적용하는 함수
    public static void gravity() {
        int row, col = 0;
        int space;

        while (col < N) {
            row = N - 1;
            space = 0;

            while (row >= 0) {
                if (map[row][col] == -2) space++;
                else if (map[row][col] == -1) space = 0;
                else if (space > 0) {
                    map[row + space][col] = map[row][col];
                    map[row][col] = -2;
                }
                row--;
            }
            col++;
        }
    }
}
