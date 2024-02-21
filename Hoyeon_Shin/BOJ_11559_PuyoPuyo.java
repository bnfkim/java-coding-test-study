import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_11559_PuyoPuyo {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static final int WIDTH = 6, HEIGHT = 12;
    static char[][] map = new char[HEIGHT][WIDTH];
    static boolean[][] check = new boolean[HEIGHT][WIDTH];

    public static void main(String[] args) throws IOException {
        // map 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < HEIGHT; ++i) {
            String line = br.readLine();

            for (int j = 0; j < WIDTH; ++j) {
                map[i][j] = line.charAt(j);
            }
        }

        int combo = 0;
        while (checkCombo()) {
            popPuyo();
            ++combo;
        }

        System.out.println(combo);
    }

    // 콤보가 있는지 여부를 boolean으로 반환
    // 콤보가 있다면, 해당 위치의 뿌요들 자리를 check에 true로 표시
    static boolean checkCombo() {
        boolean hasCombo = false;

        for(int i = HEIGHT - 1; i >= 0; --i) {
            int dotCount = 0;

            for (int j = 0; j < WIDTH; ++j) {

                // 뿌요 없는 곳 탐색 안함
                if (map[i][j] == '.') {
                    // 한 줄이 모두 . 이라면 더이상 뿌요가 없으므로 종료
                    if (++dotCount == 6)
                        return hasCombo;
                    continue;
                }

                // 확인한 뿌요 건너뛰기
                if (check[i][j])
                    continue;

                // 콤보가 있는지 확인
                Queue<Integer[]> q = new ArrayDeque<>();
                Queue<Integer[]> uncheck = new ArrayDeque<>();

                int connectingCount = 0;

                q.offer(new Integer[] {i, j});
                check[i][j] = true;

                while (!q.isEmpty()) {
                    int x = q.peek()[0];
                    int y = q.poll()[1];

                    // 뿌요가 터지는 상황이 아닌 경우 check 해제를 위해 좌표 기억
                    if (++connectingCount < 4)
                        uncheck.offer(new Integer[] {x, y});

                    for (int k = 0; k < 4; ++k) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if (nx < 0 || HEIGHT <= nx) continue;
                        if (ny < 0 || WIDTH <= ny) continue;
                        if (check[nx][ny] || map[nx][ny] != map[i][j]) continue;

                        check[nx][ny] = true;
                        q.offer(new Integer[] {nx, ny});
                    }
                }

                if (connectingCount < 4)
                    for (Integer[] point: uncheck)
                        check[point[0]][point[1]] = false;
                else
                    hasCombo = true;
            }
        }

        return hasCombo;
    }

    // check[x][y] == true 인 puyo 들을 터뜨린 후 위쪽에 쌓인 뿌요를 내린다.
    static void popPuyo() {
        for (int y = 0; y < WIDTH; ++y) {
            int x = HEIGHT - 1;
            int popCount = 0;

            while (x >= 0 && map[x][y] != '.') {
                // 터뜨릴 곳이라면
                if (check[x][y]) {
                    check[x][y] = false;
                    map[x][y] = '.';
                    popCount++;
                }

                // 떨어질 뿌요라면
                else if (popCount > 0) {
                    map[x + popCount][y] = map[x][y];
                    map[x][y] = '.';
                }

                x--;
            }
        }
    }
}
