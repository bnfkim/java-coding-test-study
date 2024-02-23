import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1647_도시_분할_계획 {

    static int N, M;
    static int[] parents;
    static Edge[] edges;

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static void init() {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    static int find(int v) {
        if (v == parents[v]) {
            return v;
        }
        return parents[v] = find(parents[v]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;
        parents[rootB] = rootA;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N];
        edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, weight);
        }

        getCost();
    }

    private static void getCost() {
        init();

        int maxEdge = 0, cnt = 0;
        long cost = 0;
        Arrays.sort(edges);
        for (Edge edge : edges) {
            if (!union(edge.from, edge.to)) {
                continue;
            }

            cost += edge.weight;
            maxEdge = edge.weight;
            if (++cnt == N - 1) {
                break;
            }
        }
        cost -= maxEdge;
        System.out.println(cost);
    }
}
