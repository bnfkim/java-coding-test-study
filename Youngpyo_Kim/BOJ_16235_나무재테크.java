import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_16235_나무재테크 {
    static int n, m, k;
    static int[][] food, robot;
    static ArrayList<Integer>[][] soil;

    static void springToSummer() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (soil[i][j].size() == 0) continue;

                ArrayList<Integer> afterSpring = new ArrayList<>();
                int deadToFood = 0;
                soil[i][j].sort(null);

                for (int p = 0; p < soil[i][j].size(); p++) {
                    if (food[i][j] >= soil[i][j].get(p)) {
                        afterSpring.add(soil[i][j].get(p) + 1);
                        food[i][j] -= soil[i][j].get(p);
                    } else {
                        deadToFood += (soil[i][j].get(p) / 2);
                    }
                }

                food[i][j] += deadToFood;
                soil[i][j].clear();
                soil[i][j].addAll(afterSpring);
            }
        }
    }

    static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    static void fall() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (soil[i][j].size() == 0) continue;

                for (int p = 0; p < soil[i][j].size(); p++) {
                    if (soil[i][j].get(p) % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int nr = i + delta[d][0], nc = j + delta[d][1];
                            if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                                soil[nr][nc].add(1);
                            }
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                food[i][j] += robot[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        food = new int[n][n];
        robot = new int[n][n];
        soil = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                robot[i][j] = Integer.parseInt(st.nextToken());
                food[i][j] = 5;
                soil[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());
            soil[r][c].add(t);
        }

        while (k-- > 0) {
            springToSummer();
            fall();
            winter();
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += soil[i][j].size();
            }
        }
        System.out.println(ans);
    }
}