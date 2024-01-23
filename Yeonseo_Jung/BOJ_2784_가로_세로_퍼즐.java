import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2784_가로_세로_퍼즐 {
    static final int INPUT_NUM = 6;
    static final int PUZZLE_NUM = 3;
    static List<String> input = new ArrayList<>(INPUT_NUM);
    static Set<String> answers = new HashSet<>();
    
    //순열
    static int[] target = new int[]{0, 1, 2, 3, 4, 5};
    static boolean[] visited = new boolean[INPUT_NUM];
    static int[] result = new int[PUZZLE_NUM];
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < INPUT_NUM; i++) {
            input.add(br.readLine());
        }

        findAndAddAnswers(0);

        String firstAnswer = answers.stream()
                .min(Comparator.naturalOrder())
                .orElse("0");

        printAnswer(firstAnswer);
    }

    static String generatePuzzle(int[] index) {
        String[][] puzzle = new String[PUZZLE_NUM][PUZZLE_NUM];
        List<String> leftInput = new ArrayList<>(input);

        for (int i = 0; i < PUZZLE_NUM; i++) {
            int idx = index[i];
            leftInput.remove(input.get(idx));
            puzzle[i] = input.get(idx).split("");
        }

        if (validatePuzzle(puzzle, leftInput)) {
            return Arrays.stream(puzzle)
                    .flatMap(Arrays::stream)
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();
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

    private static void findAndAddAnswers(int cnt) {
        if (cnt == PUZZLE_NUM) {
            String answer = generatePuzzle(result);
            if (answer != null) {
                answers.add(answer);
            }
            return;
        }

        for (int i = 0; i < INPUT_NUM; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            result[cnt] = target[i];
            findAndAddAnswers(cnt + 1);
            visited[i] = false;
        }
    }

    private static void printAnswer(String answer) {
        if (answer.equals("0")) {
            System.out.println(answer);
            return;
        }
        for (int i = 0; i < answer.length(); i++) {
            System.out.print(answer.charAt(i));
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
    }
}