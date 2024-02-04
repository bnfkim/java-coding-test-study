package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_색종이만들기 {
    private static int whiteCnt;
    private static int blueCnt;
    private static boolean[][] visited;
    private static int[][] ps;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        ps = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int num = Integer.parseInt(st.nextToken());
                ps[i][j] = num + ps[i-1][j] + ps[i][j-1] - ps[i-1][j-1];
            }
        }

        // 색종이 검사
        go(0, n);

        System.out.println(whiteCnt);
        System.out.println(blueCnt);
    }

    public static void go(int depth, int n) {
        int stride = n >> depth;
        if (stride == 0) {
            return;
        }
        for (int i = n; i > 0; i = i - stride) {
            for (int j = n; j > 0; j = j - stride) {
                if (!visited[i][j]) {
                    int res = ps[i][j] - ps[i - stride][j] - ps[i][j - stride] + ps[i - stride][j - stride];
                    if (res == stride * stride) {
                        blueCnt++;
                        for (int k = i; k > i - stride; k--) {
                            for (int l = j; l > j - stride; l--) {
                                visited[k][l] = true;
                            }
                        }
                        visited[i][j] = true;
                    } else if (res == 0) {
                        whiteCnt++;
                        for (int k = i; k > i - stride; k--) {
                            for (int l = j; l > j - stride; l--) {
                                visited[k][l] = true;
                            }
                        }
                        visited[i][j] = true;
                    }
                }
            }
        }
        go(depth + 1, n);

    }
}
