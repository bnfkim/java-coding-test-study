package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @algorithm BFS
 * @time O(N)
 * @memory O(N)
 */
public class BOJ_16928_뱀과사다리게임 {
    // 1번부터 100번 칸까지 가는 문제
    // 규칙 1 : 사다리가 있는 칸에 '도착'하면 자동으로 올라감
    // 규칙 2 : 뱀이 있는 칸에 '도착'하면 자동으로 내려감

    private static int N, M;
    private static int[] map;
    private static boolean[] visited;
    private static Map<Integer, Integer> link = new HashMap<>(); // 희소할 수 있어서 배열보단 딕셔너리가 적절할 것으로 보았음
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[101];
        Arrays.fill(map, (int) 1e9);
        map[1] = 0;
        visited = new boolean[101];

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            link.put(from, to);
        }
        bfs();
        System.out.println(map[100]);
    }
    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1); // start point
        while (!q.isEmpty()) {
            Integer now = q.poll();
            if (visited[now]) {
                continue;
            }
            visited[now] = true;
            if (now == 100) {
                return;
            }
            if (link.containsKey(now)) {
                Integer next = link.get(now);
                q.add(next);
                map[next] = Math.min(map[next],map[now]);
                continue; // 사다리나 뱀을 반드시 타야하므로, 해당 칸이 링크면 주사위 안굴리고 종료
            }
            for (int i = 1; i <= 6; i++) {
                if (now + i <= 100) {
                    q.add(now + i);
                    map[now + i] = Math.min(map[now] + 1, map[now + i]);
                }
            }
        }
    }
}
