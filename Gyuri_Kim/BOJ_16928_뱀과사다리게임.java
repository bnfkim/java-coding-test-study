import java.io.*;
import java.util.*;

/**
 * 메모리 14248 kb
 * 시간 128ms
 */
public class BOJ_16928 {
    static int N, M;
    static int RESULT = Integer.MAX_VALUE;
    static int[][] move;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        move = new int[N+M][2];

        for(int i=0; i<N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            move[i][0] = from;
            move[i][1] = to;
        }

        bfs();

        System.out.println(RESULT);
    }

    public static void bfs() {
        boolean[] visit = new boolean[101];
        Deque<int[]> pos = new ArrayDeque<>();
        pos.add(new int[]{1,0});
        visit[1] = true;

        while (!pos.isEmpty()) {
            int[] now = pos.poll();

            if(now[0] == 100) {
                RESULT = Math.min(RESULT, now[1]);
            }

            for(int dice=1; dice<=6; dice++) {
                int next = now[0] + dice;

                //사다리 혹은 뱀을 만날 경우 해당 위치로 이동
                for(int i=0; i<M+N; i++) {
                    if(next == move[i][0]) {
                        next = move[i][1];
                    }
                }

                if(next > 100) continue;
                if(visit[next]) continue;

                pos.add(new int[]{next, now[1]+1});
                visit[next] = true;
            }
        }
    }
}
