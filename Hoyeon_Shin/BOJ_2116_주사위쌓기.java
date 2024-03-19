import java.io.*;
import java.util.*;

/**
 * 23224 KB     236 ms
 */
class Main {
    static int N;
    static int[][] dices;

    // match[x] = y : 주사위의 x번째 수가 y와 반대되는 위치에 있다.
    static int[] match = {5, 3, 4, 1, 2, 0};
    static int maxNum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dices = new int[N][6];

        // 주사위 입력
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; ++j) {
                dices[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= 6; ++i) {
            search(0, i, 0);
        }

        System.out.println(maxNum);
    }

    // idx 번째 주사위의 숫자 x가 아래 오도록 쌓고
    // 옆에 위치한 수 중 최대 값을 curMax에 추가한다.
    static void search(int idx, int x, int curMax) {
        if (idx == N) {
            maxNum = Math.max(maxNum, curMax);
            return;
        }

        // idx 번째 주사위에서 숫자 x가 위치한 인덱스 찾기
        int xIdx = 0;
        for (int i = 0; i < 6; ++i) {
            if (dices[idx][i] == x) {
                xIdx = i;
                break;
            }
        }

        curMax += maxWithout(dices[idx][xIdx], dices[idx][match[xIdx]]);
        search(idx + 1, dices[idx][match[xIdx]], curMax);
    }

    // 1 ~ 6 중 a, b를 제외한 최대값을 반환
    static int maxWithout(int a, int b) {
        int result;
        for (result = 6; result >= 1; --result) {
            if (result == a || result == b) continue;
            break;
        }
        return result;
    }
}
