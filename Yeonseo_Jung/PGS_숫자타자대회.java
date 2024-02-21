import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PGS_숫자타자대회 {
    /**
     * 테스트 1 〉	통과 (0.15ms, 72.7MB)
     * 테스트 2 〉	통과 (0.18ms, 88.7MB)
     * 테스트 3 〉	통과 (0.16ms, 69.9MB)
     * 테스트 4 〉	통과 (0.16ms, 79.3MB)
     * 테스트 5 〉	통과 (0.17ms, 81.8MB)
     * 테스트 6 〉	통과 (0.13ms, 80.8MB)
     * 테스트 7 〉	통과 (0.20ms, 88.7MB)
     * 테스트 8 〉	통과 (0.13ms, 74.6MB)
     * 테스트 9 〉	통과 (0.17ms, 72.7MB)
     * 테스트 10 〉	통과 (0.17ms, 74.7MB)
     * 테스트 11 〉	실패 (0.38ms, 74.1MB)
     * 테스트 12 〉	실패 (0.19ms, 85.2MB)
     * 테스트 13 〉	실패 (0.34ms, 75.2MB)
     * 테스트 14 〉	통과 (0.31ms, 74MB)
     * 테스트 15 〉	통과 (0.49ms, 76.4MB)
     * 테스트 16 〉	실패 (시간 초과)
     * 테스트 17 〉	실패 (시간 초과)
     * 테스트 18 〉	실패 (시간 초과)
     * 테스트 19 〉	실패 (런타임 에러)
     * 테스트 20 〉	실패 (런타임 에러)
     */
    public static Map<Character, int[]> keypad = new HashMap<>();
    public static String input;
    public static int[] left = {1, 0};
    public static int[] right = {1, 2};
    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        initKeypad();
        System.out.println(getAnswer());
    }

    private static void initKeypad() {
        keypad.put('1', new int[]{0, 0});
        keypad.put('2', new int[]{0, 1});
        keypad.put('3', new int[]{0, 2});
        keypad.put('4', new int[]{1, 0});
        keypad.put('5', new int[]{1, 1});
        keypad.put('6', new int[]{1, 2});
        keypad.put('7', new int[]{2, 0});
        keypad.put('8', new int[]{2, 1});
        keypad.put('9', new int[]{2, 2});
        keypad.put('*', new int[]{3, 0});
        keypad.put('0', new int[]{3, 1});
        keypad.put('#', new int[]{3, 2});
    }

    private static int getAnswer() {
        answer = Integer.MAX_VALUE;
        pushKeypad(left, right, 0, 0);
        return answer;
    }

    private static void pushKeypad(int[] left, int[] right, int idx, int weight) {
        if (idx >= input.length()) {
            answer = Math.min(answer, weight);
            return;
        }

        if (weight > answer) {
            return;
        }

        int[] pos = keypad.get(input.charAt(idx));

        int leftWeight = calculateWeight(left, pos);
        int rightWeight = calculateWeight(right, pos);

        if (leftWeight == rightWeight) {
            pushKeypad(pos, right, idx + 1, weight+leftWeight);
            pushKeypad(left, pos, idx + 1, weight+leftWeight);
        } else if (leftWeight < rightWeight) {
            pushKeypad(pos, right, idx + 1, weight + leftWeight);
        } else {
            pushKeypad(left, pos, idx + 1, weight + rightWeight);
        }
    }

    private static int calculateWeight(int[] pos, int[] cur) {
        int deltaX = Math.abs(cur[0] - pos[0]);
        int deltaY = Math.abs(cur[1] - pos[1]);

        int weight = deltaX + deltaY;

        if (deltaX + deltaY >= 3) {
            weight += 2;
        } else if (deltaX + deltaY == 2 && (deltaX == 0 || deltaY == 0)) {
            weight += 2;
        } else {
            weight += 1;
        }

        return weight;
    }
}
