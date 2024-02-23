import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static int N, M, answer;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }

        solve();
    }

    private static void solve() {
        // 1. MST 만들기
        int sum = 0;
        int max = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        boolean[] visited = new boolean[N + 1];

        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (visited[curr.v]) {
                continue;
            }
            visited[curr.v] = true;
            sum += curr.w;
            max = Math.max(max, curr.w);
            for (Node next : graph[curr.v]) {
                if (visited[next.v]) {
                    continue;
                }
                pq.add(next);
            }
        }

        // 2. 비용이 가장 큰 간선 지우기
        System.out.println(sum - max);
    }

    static class Node {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public String toString() {
            return "(" + v + "," + w + ")";
        }
    }
}

