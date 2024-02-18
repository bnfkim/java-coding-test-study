import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] move = new int[101];  // move[x] == y : x번째 칸을 밟으면 y로 감

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; ++i) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            move[from] = to;
        }

        System.out.print(searchMinMove());
    }

    // bfs를 통해 현재 100번칸에 도달하는 최소 이동 횟수를 구해 반환
    public static int searchMinMove() {
        // [0]: 현재 칸 번호
        // [1]: 이동 횟수
        Queue<Integer[]> q = new ArrayDeque<>();
        boolean[] visit = new boolean[101];

        q.offer(new Integer[] {1, 0});
        visit[1] = true;
        while (!q.isEmpty()) {
            int position = q.peek()[0];
            int count = q.poll()[1];

            // 94 이상은 주사위를 1번 굴려 100으로 도착
            if (94 <= position)
                return count + 1;

            int maxNormal = 0;  // 현재 위치에서 사다리 혹은 뱀을 타지 않고 최대한 멀리 갈 수 있는 위치
            for (int dice = 1; dice <= 6; ++dice) {
                int nextPosition = position + dice;

                if (move[nextPosition] == 0) {
                    maxNormal = nextPosition;
                    continue;
                }

                nextPosition = move[nextPosition];

                if (visit[nextPosition])
                    continue;

                visit[nextPosition] = true;
                q.offer(new Integer[] {nextPosition, count + 1});
            }

            if (maxNormal != 0 && !visit[maxNormal]) {
                visit[maxNormal] = true;
                q.offer(new Integer[]{maxNormal, count + 1});
            }
        }

        throw new RuntimeException("잘못된 입력");
    }
}
