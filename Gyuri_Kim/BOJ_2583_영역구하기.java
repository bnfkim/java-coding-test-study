import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 14544 kb
 * 140 ms
 */
public class BOJ_2583 {
    static int M,N,K;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            drawSquare(x1, y1, x2, y2);
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == 1) continue;
                answer.add(getRange(j, i));
            }
        }
        Collections.sort(answer);

        sb.append(answer.size()).append("\n");
        sb.append(answer.stream().map(Object::toString)
                .collect(Collectors.joining(" ")));

        System.out.println(sb);
    }

    public static void drawSquare(int x1, int y1, int x2, int y2) {
        for(int i=y1; i<y2; i++) {
            for(int j=x1; j<x2; j++) {
                map[i][j] = 1;
            }
        }
    }

    public static int getRange(int x, int y) {
        int range = 0;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        map[y][x] = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if(map[ny][nx] == 1) continue;

                queue.add(new int[]{nx, ny});
                map[ny][nx] = 1;

            }
            range++;
        }

        return range;
    }

    public static void print() {
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
