import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int k;
    static boolean isAnswer;
    static char[] signs;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        signs = new char[k];
        visited = new boolean[10];
        sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            signs[i] = (char) br.read();
            br.read();
        }

        solve();
    }

    private static void solve() {
        isAnswer = false;
        findMax(0);

        isAnswer = false;
        findMin(0);
    }

    private static void findMax(int depth) {
        if (isAnswer) {
            return;
        }

        if (sb.length() == k + 1) {
            System.out.println(sb);
            isAnswer = true;
            return;
        }

        for (int i = 9; i >= 0; i--) {
            if (visited[i]) {
                continue;
            }

            if (depth > 0 && isOutOfRange(signs[depth - 1], sb.charAt(depth - 1) - '0', i)) {
                continue;
            }

            check(i);
            findMax(depth + 1);
            uncheck(i);
        }
    }

    private static boolean isOutOfRange(char sign, int last, int curr) {
        return (sign == '<' && last > curr) || (sign == '>' && last < curr);
    }

    private static void check(int num) {
        sb.append(num);
        visited[num] = true;
    }

    private static void uncheck(int num) {
        sb.deleteCharAt(sb.length() - 1);
        visited[num] = false;
    }

    public static void findMin(int depth) {
        if (isAnswer) {
            return;
        }

        if (sb.length() == k + 1) {
            System.out.println(sb);
            isAnswer = true;
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (visited[i]) {
                continue;
            }

            if (depth > 0 && isOutOfRange(signs[depth - 1], sb.charAt(depth - 1) - '0', i)) {
                continue;
            }

            check(i);
            findMin(depth + 1);
            uncheck(i);
        }
    }
}
