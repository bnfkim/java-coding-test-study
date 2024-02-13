import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17144_미세먼지_안녕 {

    static class Dust {
        int x, y, amount;

        public Dust(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }

    static int R, C, T;
    static int[][] A;
    static int[][] spreadA;
    static int[] cleaner = new int[2];
    static List<Dust> dustList = new ArrayList<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        A = new int[R][C];
        spreadA = new int[R][C];

        int cleanerIndex = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                if (A[i][j] == -1) {
                    cleaner[cleanerIndex++] = i;
                } else if (A[i][j] != 0) {
                    dustList.add(new Dust(i, j, A[i][j]));
                }
            }
        }

        for (int time = 0; time < T; time++) {
            spreadDust();
            startCleaner();
            dustList.clear();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (A[i][j] > 0) {
                        dustList.add(new Dust(i, j, A[i][j]));
                    }
                }
            }
        }

        int answer = 0;
        for (Dust dust : dustList) {
            answer += dust.amount;
        }

        System.out.println(answer);
    }

    private static void spreadDust() {
        for (Dust dust : dustList) {
            int spreadAmount = dust.amount / 5;
            int spreadCount = 0;

            for (int i = 0; i < 4; i++) {
                int nx = dust.x + dx[i];
                int ny = dust.y + dy[i];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && A[nx][ny] != -1) {
                    spreadA[nx][ny] += spreadAmount;
                    spreadCount++;
                }
            }

            A[dust.x][dust.y] -= spreadAmount * spreadCount;
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                A[i][j] += spreadA[i][j];
                spreadA[i][j] = 0;
            }
        }
    }

    private static void startCleaner() {
        int top = cleaner[0];
        int bottom = cleaner[1];

        for (int i = top - 1; i > 0; i--) {
            A[i][0] = A[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            A[0][i] = A[0][i + 1];
        }
        for (int i = 0; i < top; i++) {
            A[i][C - 1] = A[i + 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            A[top][i] = A[top][i - 1];
        }
        A[top][1] = 0;

        for (int i = bottom + 1; i < R - 1; i++) {
            A[i][0] = A[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            A[R - 1][i] = A[R - 1][i + 1];
        }
        for (int i = R - 1; i > bottom; i--) {
            A[i][C - 1] = A[i - 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            A[bottom][i] = A[bottom][i - 1];
        }
        A[bottom][1] = 0;
    }
}
