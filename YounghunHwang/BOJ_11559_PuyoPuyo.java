import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int answer;
    static char[][] map = new char[12][6];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        while (canPop()) {
            answer++;
            sort();
        }

        System.out.println(answer);
    }

    private static boolean canPop() {
        boolean result = false;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] != '.' && BFS(i, j)) {
                    result = true;
                }
            }
        }

        return result;
    }

    private static boolean BFS(int x, int y) {
        boolean[][] visited = new boolean[12][6];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            count++;
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (isOutOfRange(nx, ny) || visited[nx][ny] || map[nx][ny] != map[x][y]) {
                    continue;
                }
                queue.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        if (count < 4) {
            return false;
        }

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (visited[i][j]) {
                    map[i][j] = '.';
                }
            }
        }

        return true;
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= 12 || y < 0 || y >= 6;
    }

    private static void sort() {
        for (int c = 0; c < 6; c++) {
            List<Character> chars = new ArrayList<>();
            for (int r = 0; r < 12; r++) {
                if (map[r][c] == '.') {
                    continue;
                }
                chars.add(map[r][c]);
                map[r][c] = '.';
            }
            if (chars.isEmpty()) {
                continue;
            }
            for (int i = 0; i < chars.size(); i++) {
                map[i + (12 - chars.size())][c] = chars.get(i);
            }
        }
    }
}