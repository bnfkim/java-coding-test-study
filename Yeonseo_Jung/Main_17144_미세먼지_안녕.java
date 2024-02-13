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
    static int[][] map;
    static int[][] spread_map;
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

        map = new int[R][C];
        spread_map = new int[R][C];

        int cleanerIndex = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    cleaner[cleanerIndex++] = i;
                } else if (map[i][j] != 0) {
                    dustList.add(new Dust(i, j, map[i][j]));
                }
            }
        }

        for (int time = 0; time < T; time++) {
            spreadDust();
            startCleaner();
            dustList.clear();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        dustList.add(new Dust(i, j, map[i][j]));
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

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != -1) {
                    spread_map[nx][ny] += spreadAmount;
                    spreadCount++;
                }
            }

            map[dust.x][dust.y] -= spreadAmount * spreadCount;
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += spread_map[i][j];
                spread_map[i][j] = 0;
            }
        }
    }

    private static void startCleaner() {
        int top = cleaner[0];
        int bottom = cleaner[1];

        for (int i = top - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < top; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            map[top][i] = map[top][i - 1];
        }
        map[top][1] = 0;

        for (int i = bottom + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        for (int i = R - 1; i > bottom; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            map[bottom][i] = map[bottom][i - 1];
        }
        map[bottom][1] = 0;
    }
}
