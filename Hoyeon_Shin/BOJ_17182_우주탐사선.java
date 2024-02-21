import java.io.*;
import java.util.*;

// ana호의 현재 위치, 총 이동한 시간을 저장하는 객체
class Path implements Comparable<Path> {
    int pos;
    int time;
    Path(int pos, int time) {
        this.pos = pos;
        this.time = time;
    }

    // PriorityQueue 내부에서 이동시간이 적은 객체가 우선순위가 높게 정렬되도록 설정
    @Override
    public int compareTo(Path otherPath) {
        return this.time - otherPath.time;
    }
}

class Main {
    static int N, K;
    static int[][] T;
    static int[][] minTimes;  // i 노드부터 j노드까지 가는데 필요한 최소 시간을 반환
    static int answer = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = new int[N][N];
        minTimes = new int[N][N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; ++j) {
                T[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    static void solve() {
        // i 부터 j 행성까지 걸리는 최소 시간을 모두 구해 새로운 그래프를 생성한 뒤
        // dfs 탐색으로 모든 행성을 방문하는 최소 시간을 구한다.
        for (int i = 0; i < N - 1; ++i) {
            for (int j = i + 1; j < N; ++j) {
                minTimes[i][j] = getMinDist(i, j);
                minTimes[j][i] = getMinDist(j, i);
            }
        }

        dfs(1, K, 0, 1 << K);
        System.out.println(answer);
    }

    // stt 노드로부터 dst 노드까지의 최소 시간을 반환한다. (with 다익스트라 알고리즘)
    static int getMinDist(int stt, int dst) {
        boolean[] visit = new boolean[N];

        PriorityQueue<Path> pq = new PriorityQueue<>();
        pq.offer(new Path(stt, 0));

        while (!pq.isEmpty()) {
            int curPos = pq.peek().pos;
            int curTime = pq.poll().time;

            if (curPos == dst)
                return curTime;

            visit[curPos] = true;

            for (int i = 0; i < N; ++i) {
                if (visit[i])
                    continue;
                pq.offer(new Path(i, curTime + T[curPos][i]));
            }
        }

        return 0;
    }

    // minTimes 그래프를 dfs 로 탐방하며 최소시간을 구한다.
    static void dfs(int idx, int curPos, int totalTime, int mask) {
        // backtracking
        if (totalTime > answer)
            return;

        if (idx == N) {
            answer = totalTime;
            return;
        }

        for (int i = 0; i < N; ++i) {
            if ((mask & 1 << i) != 0) continue;

            dfs(idx + 1, i, totalTime + minTimes[curPos][i], mask | 1 << i);
        }
    }
}
