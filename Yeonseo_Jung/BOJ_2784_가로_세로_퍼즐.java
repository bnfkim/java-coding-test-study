import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2784_가로_세로_퍼즐 {
    static final int INPUT_NUM = 6;
    static final int PUZZLE_NUM = 3;
    static List<String> input = new ArrayList<>(INPUT_NUM);
    static boolean solved = false;

    //순열
    static boolean[] visited = new boolean[INPUT_NUM];
    static int[] result = new int[PUZZLE_NUM];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < INPUT_NUM; i++) {
            input.add(br.readLine());
        }

        findAnswers(0);

        if(!solved) {
            System.out.println(0);
        }
    }

    static String[][] generatePuzzle(int[] index) {
        String[][] puzzle = new String[PUZZLE_NUM][PUZZLE_NUM];
        List<String> leftInput = new ArrayList<>(input);

        for (int i = 0; i < PUZZLE_NUM; i++) {
            int idx = index[i];
            leftInput.remove(input.get(idx));
            puzzle[i] = input.get(idx).split("");
        }

        if (validatePuzzle(puzzle, leftInput)) {
            solved = true;
            return puzzle;
        }
        return null;
    }

    private static boolean validatePuzzle(String[][] puzzle, List<String> leftInput) {
        for (int i = 0; i < PUZZLE_NUM; i++) {
            StringBuilder word = new StringBuilder();
            for (int j = 0; j < PUZZLE_NUM; j++) {
                word.append(puzzle[j][i]);
            }
            if (!leftInput.contains(word.toString())) {
                return false;
            }
            leftInput.remove(word.toString());
        }

        return leftInput.isEmpty();
    }

    private static void findAnswers(int cnt) {
        if (cnt == PUZZLE_NUM) {
            String[][] answer = generatePuzzle(result);
            if (answer != null) {
                printAnswer(answer);
            }
            return;
        }

        for (int i = 0; i < INPUT_NUM; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            result[cnt] = i;
            findAnswers(cnt + 1);
            visited[i] = false;

            if(solved) {
                return;
            }
        }
    }

    private static void printAnswer(String[][] answer) {
        for (int i = 0; i <PUZZLE_NUM; i++) {
            for (int j = 0; j < PUZZLE_NUM; j++) {
                System.out.print(answer[i][j]);
            }
            System.out.println();
        }
    }
}