import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ27921 {

    static int H1, W1, H2, W2;
    static boolean[][] map1;
    static boolean[][] map2;
    static boolean[][] map3;
    static int max;
    static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H1 = Integer.parseInt(st.nextToken());
        W1 = Integer.parseInt(st.nextToken());
        map1 = new boolean[H1][W1];
        for (int i = 0; i < H1; i++) {
            String temp = br.readLine();
            for (int j = 0; j < W1; j++) {
                if (temp.charAt(j) == 'O') {
                    map1[i][j] = true;
                    total++;
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        H2 = Integer.parseInt(st.nextToken());
        W2 = Integer.parseInt(st.nextToken());
        map2 = new boolean[H2][W2];
        for (int i = 0; i < H2; i++) {
            String temp = br.readLine();
            for (int j = 0; j < W2; j++) {
                if (temp.charAt(j) == 'O') {
                    map2[i][j] = true;
                }
            }
        }

        solve();
    }

    private static void solve() {
        map3 = new boolean[2 * H2 + H1][2 * W2 + W1];
        for (int i = 0; i < H2; i++) {
            for (int j = 0; j < W2; j++) {
                map3[i][j] = map2[i][j];
            }
        }

        for (int i = 0; i < H1 + H2 - 1; i++) {
            moveRow();

            boolean[][] temp = new boolean[2 * H2 + H1][2 * W2 + W1];
            for (int j = 0; j < 2 * H2 + H1; j++) {
                temp[j] = map3[j].clone();
            }

            for (int j = 0; j < W1 + W2 - 1; j++) {
                moveCol();
                updateCount();
            }

            map3 = temp;
        }

        System.out.println(total - max);
    }

    private static void moveRow() {
        for (int i = 2 * H2 + H1 - 1; i > 0; i--) {
            for (int j = 0; j < 2 * W2 + W1; j++) {
                map3[i][j] = map3[i - 1][j];
            }
        }
        for (int j = 0; j < 2 * W2 + W1; j++) {
            map3[0][j] = false;
        }
    }

    private static void moveCol() {
        for (int i = 0; i < 2 * H2 + H1; i++) {
            for (int j = 2 * W2 + W1 - 1; j > 0; j--) {
                map3[i][j] = map3[i][j - 1];
            }
        }
        for (int i = 0; i < 2 * H2 + H1; i++) {
            map3[i][0] = false;
        }
    }

    private static void updateCount() {
        int count = 0;
        for (int i = 0; i < H1; i++) {
            for (int j = 0; j < W1; j++) {
                if (map1[i][j] && map1[i][j] == map3[i + H2][j + W2]) {
                    count++;
                }
            }
        }

        max = Math.max(max, count);
    }
}
