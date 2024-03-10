import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_23796_2147483648_게임 {

    final static int BOARD_SIZE = 8;
    static long[][] board = new long[BOARD_SIZE][BOARD_SIZE];
    static long[][] nextBoard = new long[BOARD_SIZE][BOARD_SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < BOARD_SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = Long.parseLong(st.nextToken());
            }
        }
        String command = br.readLine();
        getNextBoard(command);
        printBoard(nextBoard);
    }

    private static void getNextBoard(String command) {
        switch (command) {
            case "L" :
                moveLeft();
                break;
            case "R" :
                moveRight();
                break;
            case "U" :
                moveUp();
                break;
            case "D" :
                moveDown();
                break;
        }
    }

    private static void moveDown() {
        for (int i = BOARD_SIZE-1; i >= 0; i--) {
            int idx = BOARD_SIZE-1;
            long prev = 0;
            for (int j = BOARD_SIZE-1; j >= 0; j--) {
                if (board[j][i] != 0) {
                    if (prev == 0) {
                        prev = board[j][i];
                        nextBoard[idx--][i] = prev;
                    } else if (prev == board[j][i]) {
                        prev = prev * 2;
                        nextBoard[idx+1][i] = prev;
                        prev += 1;  // 한 타일이 두 번 이상 합쳐지는 것 방지
                    } else {
                        prev = board[j][i];
                        nextBoard[idx--][i] = prev;
                    }
                }
            }
        }
    }

    private static void moveUp() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            int idx = 0;
            long prev = 0;
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[j][i] != 0) {
                    if (prev == 0) {
                        prev = board[j][i];
                        nextBoard[idx++][i] = prev;
                    } else if (prev == board[j][i]) {
                        prev = prev * 2;
                        nextBoard[idx-1][i] = prev;
                        prev += 1; // 한 타일이 두 번 이상 합쳐지는 것 방지
                    } else {
                        prev = board[j][i];
                        nextBoard[idx++][i] = prev;
                    }
                }
            }
        }
    }

    private static void moveLeft() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            int idx = 0;
            long prev = 0;
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != 0) {
                    if (prev == 0) {
                        prev = board[i][j];
                        nextBoard[i][idx++] = prev;
                    } else if (prev == board[i][j]) {
                        prev = prev * 2;
                        nextBoard[i][idx-1] = prev;
                        prev += 1; // 한 타일이 두 번 이상 합쳐지는 것 방지
                    } else {
                        prev = board[i][j];
                        nextBoard[i][idx++] = prev;
                    }
                }
            }
        }
    }

    private static void moveRight() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            int idx = BOARD_SIZE-1;
            long prev = 0;
            for (int j = BOARD_SIZE-1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    if (prev == 0) {
                        prev = board[i][j];
                        nextBoard[i][idx--] = prev;
                    } else if (prev == board[i][j]) {
                        prev = prev * 2;
                        nextBoard[i][idx+1] = prev;
                        prev += 1; // 한 타일이 두 번 이상 합쳐지는 것 방지
                    } else {
                        prev = board[i][j];
                        nextBoard[i][idx--] = prev;
                    }
                }
            }
        }
    }



    private static void printBoard(long board[][]) {
        StringBuilder contents = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                contents.append(board[i][j]).append(" ");
            }
            contents.append("\n");
        }
        System.out.println(contents);
    }
}
