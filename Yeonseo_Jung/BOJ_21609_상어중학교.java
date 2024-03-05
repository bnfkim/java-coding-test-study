import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21609_상어중학교 {

    static class Block implements Comparable<Block> {
        int x;
        int y;
        int rainbow;
        int size;
        List<int[]> cells;

        public Block(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.rainbow = 0;
            this.size = size;
            this.cells = new ArrayList<>();
        }

        @Override
        public int compareTo(Block o) {
            int compareSize = o.size - this.size;
            int compareRb = o.rainbow - this.rainbow;
            int compareRow = o.x - this.x;
            int compareCol = o.y - this.y;

            if (compareSize == 0) {
                if (compareRb == 0) {
                    if (compareRow == 0) {
                        return compareCol;
                    } else {
                        return compareRow;
                    }
                } else {
                    return compareRb;
                }

            }
            return compareSize;
        }
    }

    static int N, M, score;
    static int[][] board;
    static boolean[][] visited;
    static List<Block> blocks;
    static List<int[]> rainbowBlock;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        autoPlay();
        System.out.println(score);
    }

    private static void autoPlay() {
        while (true) {
            // 가장 큰 블럭 찾기
            blocks = new ArrayList<>();
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    rainbowBlock = new ArrayList<>();
                    if (board[i][j] > 0 && !visited[i][j]) {
                        visited[i][j] = true;
                        Block block = new Block(i, j, 0);
                        block.cells.add(new int[]{i, j});
                        int size = bfs(i, j, board[i][j], block);

                        block.rainbow = rainbowBlock.size();
                        if (size > block.rainbow && size > 1) {
                            blocks.add(block);
                        }
                    }
                    // 무지개 블록 방문처리 초기화
                    if (!rainbowBlock.isEmpty()) {
                        for (int[] rB : rainbowBlock) {
                            visited[rB[0]][rB[1]] = false;
                        }
                    }
                }
            }

            if (blocks.isEmpty()) {
                break;
            }
            Collections.sort(blocks);
            Block maxBlock = blocks.get(0);
            int maxSize = maxBlock.size;
            score += maxSize * maxSize;
            // 블럭 삭제
            removeBlock(maxBlock);
            // 중력 작용
            gravity();
            // 회전
            rotate();
            // 중력 작용
            gravity();
        }
    }

    private static void rotate() {
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[N - j - 1][i] = board[i][j];
            }
        }

        board = temp;
    }

    public static void gravity() {
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= 1; i--) {
                if (board[i][j] != -2) continue;
                int nx = i;
                while (true) {
                    nx -= 1;
                    if (nx < 0) break;
                    if (board[nx][j] == -1) break;
                    if (board[nx][j] != -2) {
                        board[i][j] = board[nx][j];
                        board[nx][j] = -2;
                        break;
                    }
                }
            }
        }
    }

    private static void removeBlock(Block maxBlock) {
        for (int[] pos : maxBlock.cells) {
            board[pos[0]][pos[1]] = -2;
        }
    }

    private static int bfs(int i, int j, int color, Block block) {
        int size = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i, j});

        int nx, ny;
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            size++;

            for (int dir = 0; dir < 4; dir++) {
                nx = pos[0] + dx[dir];
                ny = pos[1] + dy[dir];

                if (nx < 0 || nx == N || ny < 0 || ny == N) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                if (board[nx][ny] != color && board[nx][ny] != 0) {
                    continue;
                }

                if (board[nx][ny] == 0) {
                    rainbowBlock.add(new int[]{nx, ny});
                }
                visited[nx][ny] = true;
                block.cells.add(new int[]{nx, ny});
                q.add(new int[]{nx, ny});
            }
        }
        block.size = size;
        return size;
    }
}