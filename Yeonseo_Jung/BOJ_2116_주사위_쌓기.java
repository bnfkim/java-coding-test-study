import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2116_주사위_쌓기 {
    final static int DICE_NUM = 6;
    static int N, answer;
    static int[][] dices, copyDices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dices = new int[N][DICE_NUM];
        copyDices = new int[N][DICE_NUM];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < DICE_NUM; j++) {
                dices[i][j] = Integer.parseInt(st.nextToken());
                copyDices[i][j] = dices[i][j];
            }
        }
        getMaxAnswer();
    }

    private static void getMaxAnswer() {
        for (int i = 0; i < DICE_NUM; i++) {
            simulation(0, dices[0][i]);
        }
        System.out.println(answer);
    }

    private static void clearPair(int curDice, int firstIdx, int pairIdx) {
        dices[curDice][firstIdx] = 0;
        dices[curDice][pairIdx] = 0;
    }

    private static void simulation(int curDice, int curNum) {
        if (curDice == N) {
            int value = calculateValue();
            initDices();
            answer = Math.max(answer, value);
            return;
        }

        for (int i = 0; i < DICE_NUM; i++) {
            if (dices[curDice][i] == curNum) {
                int pairIdx = findPairIdx(i);
                int nextNum = dices[curDice][pairIdx];
                clearPair(curDice, i, pairIdx);
                simulation(curDice + 1, nextNum);
            }
        }
    }

    private static void initDices() {
        for (int i = 0; i < N; i++) {
            dices[i] = copyDices[i].clone();
        }
    }

    private static int calculateValue() {
        int value = 0;
        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 0; j < DICE_NUM; j++) {
                if (max < dices[i][j]) {
                    max = dices[i][j];
                }
            }
            value += max;
        }
        return value;
    }

    private static int findPairIdx(int idx) {
        if (idx == 0) {
            return 5;
        } else if (idx < 3) {
            return (idx + 2);
        } else if (idx < 5) {
            return (idx - 2);
        }
        return 0;
    }
}
