import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BOJ_11559_PuyoPuyo {
    static int ROW = 12, COL = 6;
    static char[][] map = new char[12][6];
    static boolean[][] visit;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean isBoom = true;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        while (isBoom()) {
            drop();
            result++;
        }

        System.out.println(result);
    }

    static boolean isBoom() {
        isBoom = false;
        visit = new boolean[ROW][COL];

        for(int i=0; i<ROW; i++) {
            for(int j=0; j<COL; j++) {
                if(map[i][j] == '.') continue;
                bfs(j, i);
            }
        }
        return isBoom;
    }

    static void bfs(int x, int y) {
        List<int[]> puyo = new ArrayList<>(); //연결 되어진 블록
        Deque<int[]> deque = new ArrayDeque<>();

        puyo.add(new int[]{x, y});
        deque.add(new int[]{x, y});

        visit[y][x] = true;
        char color = map[y][x];

        while (!deque.isEmpty()) {
            int[] pos = deque.poll();

            for(int i=0; i<4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if(nx<0 || ny<0 || nx>=COL || ny>=ROW) continue; //(1)범위를 벗어나면 패스
                if(map[ny][nx] != color) continue; //(2)색이 다르면 패스
                if(visit[ny][nx]) continue; //(3)이미 방문한 곳이면 패스

                visit[ny][nx] = true;
                deque.add(new int[]{nx, ny});
                puyo.add(new int[]{nx, ny});
            }
        }

        if(puyo.size() >= 4) { //4개 이상 이면 뿌여 제거
            isBoom = true;
            for(int[] p : puyo) map[p[1]][p[0]] = '.';
        }
    }

    static void drop() {
        for (int j=0; j<COL; j++) {
            for (int i=ROW-1; i>0; i--) {
                if(map[i][j] != '.') continue;

                /*
                여기까지 오면 map[i][j] == '.' 이다.
                . 인 것은, 빈 칸이므로, 위에 뿌요가 있다면 현재 여기 위치에 떨어트려야 한다.
                뿌요를 떨어트릴 위치 = map[i][j] 인 것
                 */

                int idx = i - 1;
                while(true) {
                    if(idx < 0) break; //map 범위 벗어나면 끝
                    if(map[idx][j] != '.') break; //drop 시킬 뿌요 찾음
                    idx--;
                }

                if(idx >= 0) { //범위를 벗어나지 않은 경우에만 drop
                    map[i][j] = map[idx][j]; //떨어트림
                    map[idx][j] = '.'; //떨어진 뿌요 위치는 . 으로 변경
                }
            }
        }
    }

    static void print() {
        for(int i=0; i<ROW; i++) {
            for(int j=0; j<COL; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
