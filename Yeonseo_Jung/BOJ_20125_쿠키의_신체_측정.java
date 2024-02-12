import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class BOJ_20125_쿠키의_신체_측정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] input = new char[N][N];
        for (int i = 0; i < N; i++) {
            input[i] = br.readLine().toCharArray();
        }

        int a = -1;
        int b = -1;

        outerLoop:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (input[i][j] == '*') {
                    a = i + 1;
                    b = j;
                    break outerLoop;
                }
            }
        }

        int heartX = a;
        int heartY = b;

        int leftArm = IntStream.range(0, heartY)
                .filter(x -> input[heartX][x] == '*')
                .toArray()
                .length;

        int rightArm = IntStream.range(heartY + 1, input.length)
                .filter(y -> input[heartX][y] == '*')
                .toArray()
                .length;


        int waist = IntStream.range(heartX + 1, input.length)
                .filter(x -> input[x][heartY] == '*')
                .toArray()
                .length;

        int waistX = heartX + waist;
        int waistY = heartY;

        int leftLeg = IntStream.range(waistX, input.length)
                .filter(x -> input[x][waistY - 1] == '*')
                .toArray()
                .length;

        int rightLeg = IntStream.range(waistX, input.length)
                .filter(x -> input[x][waistY + 1] == '*')
                .toArray()
                .length;

        System.out.println((heartX + 1) + " " + (heartY + 1));
        System.out.println(leftArm + " " + rightArm + " " + waist + " " + leftLeg + " " + rightLeg);
    }
}
