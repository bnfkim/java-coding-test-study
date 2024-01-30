import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2630_색종이_만들기 {

    static int N, blueCnt, whiteCnt;
    static String[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        paper = new String[N][N];

        for (int i = 0; i < N; i++) {
            paper[i] = br.readLine().split(" ");
        }

        search(0, 0, N);

        System.out.println(whiteCnt);
        System.out.println(blueCnt);
    }

    private static void search(int x, int y, int n) {
        String curColor = paper[x][y];

        if (n == 1) {
            if (curColor.equals("0")) {
                whiteCnt++;
            } else {
                blueCnt++;
            }
            return;
        }

        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (!paper[i][j].equals(curColor)) {
                    search(x, y, n / 2);
                    search(x + n / 2, y, n / 2);
                    search(x, y + n / 2, n / 2);
                    search(x + n / 2, y + n / 2, n / 2);
                    return;
                }
            }
        }

        if (curColor.equals("0")) {
            whiteCnt++;
        } else {
            blueCnt++;
        }
    }
}
