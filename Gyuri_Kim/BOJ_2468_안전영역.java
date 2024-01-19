import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BO468_안전영역 {
    //9:00 시작
    static int n, maxHeight, maxCnt;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); //2<=n<=100
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j ++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(map[i][j], maxHeight);
            }
        }
        //완전 탐색 + bfs
        int h = 0;
        while(h <= maxHeight) {
            int cnt = 0; //안전한 영역 갯수
            visit = new boolean[n][n]; //방문 초기화

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(map[i][j] <= h || visit[i][j]) continue; //잠기는 영역

                    bfs(j, i, h);
                    cnt++;
                }
            }
            h++;
            maxCnt = Math.max(maxCnt, cnt);
        }
        System.out.println(maxCnt);
    }
    public static void bfs(int x, int y, int height) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        visit[y][x] = true;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
                if(map[ny][nx] <= height || visit[ny][nx]) continue;

                queue.add(new int[]{nx, ny});
                visit[ny][nx] = true;
            }
        }
    }
}