import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2447_별_찍기 {
    // 272ms
    static int N;
    static char[][] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stars = new char[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(stars[i], '*');
        }
        getPattern(N);
        printPattern();
    }

    private static void getPattern(int n) {
        n /= 3;
        int x = 0;
        int y = 0;
        punch(n, x, y);

        while (n > 1) {
            for (int i = 0; i < N; ) {
                for (int j = 0; j < N; ) {
                    punch(n/3, x+i, y+j);
                    j += n;
                }
                i += n;
            }
            n /= 3;
        }
    }

    private static void punch(int n, int x, int y) {
        for (int i = n + x; i < 2*n + x; i++) {
            for (int j = n + y; j < 2*n + y; j++) {
                stars[i][j] = ' ';
            }
        }
    }

    private static void printPattern() {
        StringBuilder pattern = new StringBuilder("");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pattern.append(stars[i][j]);
            }
            pattern.append("\n");
        }
        System.out.println(pattern);
    }
}

