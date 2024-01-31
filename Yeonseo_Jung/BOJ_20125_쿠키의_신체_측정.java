import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class BOJ_20125_쿠키의_신체_측정 {
    public static void main(String[] args) throws IOException {
        // 1. 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] input = new char[N][N];
        for (int i = 0; i < N; i++) {
            input[i] = br.readLine().toCharArray();
        }
        // 2. 심장 좌표 찾기
        // 2-1 머리 위치 찾기
        // 2-2 머리 위치(x,y)에서 (x+1, y)가 심장 좌표
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

        // 3. 왼쪽 탐색 -> 왼팔
        int heartX = a;
        int heartY = b;

        int leftArm = IntStream.range(0, heartY)
                .filter(x -> input[heartX][x] == '*')
                .toArray()
                .length;

        // 4. 오른쪽 탐색 -> 오른팔
        int rightArm = IntStream.range(heartY + 1, input.length)
                .filter(y -> input[heartX][y] == '*')
                .toArray()
                .length;

        // 5. 아래 탐색 ->
        // 허리 -> 허리 좌표
        int waist = IntStream.range(heartX + 1, input.length)
                .filter(x -> input[x][heartY] == '*')
                .toArray()
                .length;

        // 왼다리, 오른다리
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
