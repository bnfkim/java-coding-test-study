/**
 * 	24252kb
 * 	144ms
 */
import java.util.*;
import java.io.*;

public class BOJ_21609_상어중학교 {

    static class Block implements Comparable<Block> {
        int size, rainbow, x, y;
        ArrayList<int[]> group;

        Block() {
            group = new ArrayList<>();
            x = N;
            y = N;
        }

        void add(int x, int y) {
            size++;
            group.add(new int[] { x, y });
            rainbow += arr[x][y] == 0 ? 1 : 0;

            if (arr[x][y] != 0) {
                if (this.x > x) {
                    this.x = x;
                    this.y = y;
                } else if (this.x == x) {
                    if (this.y > y) {
                        this.y = y;
                    }
                }
            }
        }

        int getScore() {
            return size * size;
        }

        @Override
        public int compareTo(Block o) {
            if (this.size != o.size) {
                return -Integer.compare(this.size, o.size);
            } else if (this.rainbow != o.rainbow) {
                return -Integer.compare(this.rainbow, o.rainbow);
            } else if (this.x != o.x) {
                return -Integer.compare(this.x, o.x);
            } else {
                return -Integer.compare(this.y, o.y);
            }
        }
    }

    static int N, M, SCORE, arr[][];
    static boolean[][] visited;
    static final int X = 6;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, -1, 0, 1 };
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        while (findAndRemove()) {
            fall();
            rotate();
            fall();
        }

        System.out.println(SCORE);
    }

    private static boolean findAndRemove() {
        ArrayList<Block> blk = new ArrayList<>();
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                if (arr[i][j] <= 0 || 6 <= arr[i][j]) continue;
                Block temp = bfs(i, j);
                if (temp.size >= 2) blk.add(temp);
            }
        }

        if (blk.size() == 0) return false;

        Collections.sort(blk);
        Block minB = blk.get(0);

        for (int i = 0; i < minB.group.size(); i++) {
            int[] pos = minB.group.get(i);
            arr[pos[0]][pos[1]] = X;
        }
        SCORE += minB.getScore();

        return true;
    }

    private static void fall() {
        for (int j = 0; j < N; j++) {
            for (int i = N - 2; i >= 0; i--) {
                if (arr[i][j] == -1)
                    continue;

                int index = i;
                while (index + 1 < N && arr[index + 1][j] == X) {
                    index++;
                }

                arr[index][j] = arr[i][j];
                if (index != i)
                    arr[i][j] = X;
            }
        }
    }

    private static void rotate() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = arr[j][N - 1 - i];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = temp[i][j];
            }
        }
    }

    private static Block bfs(int x, int y) {
        Block blocks = new Block();
        blocks.add(x, y);

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { x, y });

        visited[x][y] = true;
        int pivot = arr[x][y];

        boolean[][] inner = new boolean[N][N];
        inner[x][y] = true;

        while (!dq.isEmpty()) {
            int[] pos = dq.pop();
            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) continue;
                if (!(arr[nx][ny] == pivot || arr[nx][ny] == 0)) continue;
                if (visited[nx][ny]) continue;
                if (inner[nx][ny]) continue;

                visited[nx][ny] = arr[nx][ny] != 0;
                inner[nx][ny] = true;

                dq.add(new int[] { nx, ny });
                blocks.add(nx, ny);
            }
        }

        return blocks;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
