import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11559_뿌요뿌요 {
    static String[] board = new String[13];
    static List<int[]> points = new ArrayList<>();
    static int ans = 0;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static boolean isPossible() {
        boolean[][] visited = new boolean[13][13];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (board[i].charAt(j) != '.' && !visited[i][j]) {
                    Queue<int[]> q = new LinkedList<>();
                    List<int[]> res = new ArrayList<>();
                    char curc = board[i].charAt(j);
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    res.add(new int[]{i, j});

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();

                        for (int k = 0; k < 4; k++) {
                            int nr = dr[k] + cur[0], nc = dc[k] + cur[1];

                            if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && !visited[nr][nc] && board[nr].charAt(nc) == curc) {
                                visited[nr][nc] = true;
                                res.add(new int[]{nr, nc});
                                q.add(new int[]{nr, nc});
                            }
                        }
                    }
                    if (res.size() >= 4) {
                        points.addAll(res);
                    }
                }
            }
        }

        return !points.isEmpty();
    }

    static void dropdown() {
        Set<Integer> checker = new HashSet<>();
        for (int[] p : points) {
            board[p[0]] = board[p[0]].substring(0, p[1]) + '.' + board[p[0]].substring(p[1] + 1);
            checker.add(p[1]);
        }

        for (int c : checker) {
            for (int r = 11; r >= 0; r--) {
                if (board[r].charAt(c) == '.') {
                    int tmp = r;
                    while (board[tmp].charAt(c) == '.') {
                        tmp--;
                        if (tmp < 0) break;
                    }
                    if (tmp < 0) continue;
                    board[r] = board[r].substring(0, c) + board[tmp].charAt(c) + board[r].substring(c + 1);
                    board[tmp] = board[tmp].substring(0, c) + '.' + board[tmp].substring(c + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            board[i] = br.readLine();
        }

        while (isPossible()) {
            dropdown();
            ans++;
            points.clear();
        }

        
        System.out.println(ans);
    }
}
