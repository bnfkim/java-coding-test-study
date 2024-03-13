import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18808_스티커_붙이기 {
    static int N, M, K;
    static boolean[][] board;
    static Queue<boolean[][]> stickers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new boolean[N][M];
        stickers = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            boolean[][] sticker = new boolean[x][y];
            for (int j = 0; j < x; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < y; k++) {
                    sticker[j][k] = st.nextToken().equals("1");
                }
            }
            stickers.add(sticker);
        }
        simulation();
    }

    static void simulation() {
        while (!stickers.isEmpty()) {
            boolean[][] sticker = stickers.peek();
            stick:
            for (int i = 0; i < 4; i++) {
                if (i > 0) {
                    sticker = rotateSticker(sticker);
                }
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        if (canStick(j, k, sticker)) {
                            putSticker(j, k, sticker);
                            break stick;
                        }
                    }
                }
            }
            stickers.poll();
        }
        printAnswer();
    }

    private static boolean[][] rotateSticker(boolean[][] arr) {
        int x = arr.length;
        int y = arr[0].length;
        boolean[][] rotArr = new boolean[y][x];

        int rX = rotArr.length;
        int rY = rotArr[0].length;
        for (int i = 0; i < rX; i++) {
            for (int j = 0; j < rY; j++) {
                rotArr[i][j] = arr[rY - 1 - j][i];
            }
        }
        return rotArr;
    }

    private static boolean canStick(int row, int col, boolean[][] sticker) {
        if (row + sticker.length > N || col + sticker[0].length > M) {
            return false;
        }

        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[i].length; j++) {
                if (sticker[i][j] && board[row + i][col + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void putSticker(int row, int col, boolean[][] sticker) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[i].length; j++) {
                if (sticker[i][j]) {
                    board[row + i][col + j] = true;
                }
            }
        }
    }

    private static void printAnswer() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
