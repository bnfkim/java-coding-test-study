import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴 {

    final static int MAGNET_NUMBER = 4;
    final static int EDGE_NUMBER = 8;

    static class Command {
        int magnetNum;
        int isClockWise;

        public Command(int magnetNum, int isClockWise) {
            this.magnetNum = magnetNum;
            this.isClockWise = isClockWise;
        }
    }

    static int T, K, score;
    static int[][] magnets;
    static Command[] commands;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        magnets = new int[MAGNET_NUMBER][EDGE_NUMBER];
        for (int i = 0; i < MAGNET_NUMBER; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < EDGE_NUMBER; j++) {
                magnets[i][j] = Integer.parseInt(input[j]);
            }
        }
        K = Integer.parseInt(br.readLine());
        commands = new Command[K];
        for (int c = 0; c < K; c++) {
            st = new StringTokenizer(br.readLine());
            int magnetNum = Integer.parseInt(st.nextToken());
            int isClockWise = Integer.parseInt(st.nextToken());
            commands[c] = new Command(magnetNum, isClockWise);
        }
        executeCommands();
        calculateScore();
        System.out.println(score);
    }

    private static void executeCommands() {
        for (Command c : commands) {
            // 회전해야 하는 자석 체크
            int[] rotates = new int[MAGNET_NUMBER]; // -1 반시계, 1 시계, 0 안돌림
            int magIdx = c.magnetNum - 1;
            rotates[magIdx] = c.isClockWise;
            for (int dif = 0; dif < MAGNET_NUMBER - 1; dif++) {
                int checkLeftIdx = magIdx - dif;
                if (checkLeftIdx <= 0 || !needRotate(checkLeftIdx, true)) {
                    break;
                }
                rotates[checkLeftIdx - 1] = -rotates[checkLeftIdx];
            }
            for (int dif = 0; dif < MAGNET_NUMBER - 1; dif++) {
                int checkRightIdx = magIdx + dif;
                if (checkRightIdx >= MAGNET_NUMBER - 1 || !needRotate(checkRightIdx, false)) {
                    break;
                }
                rotates[checkRightIdx + 1] = -rotates[checkRightIdx];
            }
            // 회전
            rotateMagnets(rotates);
        }
    }

    private static boolean needRotate(int magIdx, boolean isLeft) {
        if (isLeft) {
            return magnets[magIdx - 1][2] != magnets[magIdx][6];
        } else {
            return magnets[magIdx][2] != magnets[magIdx + 1][6];
        }
    }

    private static void rotateMagnets(int[] rotates) {
        for (int i = 0; i < MAGNET_NUMBER; i++) {
            int[] temp = new int[EDGE_NUMBER];

            if (rotates[i] == 0) {
                continue;
            }
            if (rotates[i] == -1) {
                System.arraycopy(magnets[i], 1, temp, 0, EDGE_NUMBER - 1);
                temp[EDGE_NUMBER - 1] = magnets[i][0];
            } else {
                System.arraycopy(magnets[i], 0, temp, 1, EDGE_NUMBER - 1);
                temp[0] = magnets[i][EDGE_NUMBER - 1];
            }
            magnets[i] = temp;
        }
    }

    private static void calculateScore() {
        score = 0;

        for (int i = 0; i < MAGNET_NUMBER; i++) {
            score += magnets[i][0] == 1 ? (int) Math.pow(2, i) : 0;
        }
    }
}
