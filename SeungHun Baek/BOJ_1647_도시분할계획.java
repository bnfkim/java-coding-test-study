import java.util.*;
import java.io.*;

public class BOJ_1647_도시분할계획 {

    static class Edge implements Comparable<Edge> {
        int stt, end, weight;

        Edge(int stt, int end, int weight) {
            this.stt = stt;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int N, M, parents[];
    static Edge edges[];
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(A, B, C);
        }

        System.out.println(kruscal());
    }

    private static int kruscal() {
        Arrays.sort(edges);

        int MAX = 0;
        int dist = 0;
        for (int i = 0; i < M; i++) {
            Edge e = edges[i];
            if (find(e.stt) != find(e.end)) {
                union(e.stt, e.end);
                dist += e.weight;
                MAX = Math.max(MAX, e.weight);
            }
        }

        return dist - MAX;
    }

    private static void init() {
        edges = new Edge[M];
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int x) {
        return parents[x] = (parents[x] == x) ? x : find(parents[x]);
    }

    private static void union(int x, int y) {
        int p1 = find(x);
        int p2 = find(y);

        if (p1 > p2) {
            parents[p1] = p2;
        } else {
            parents[p2] = p1;
        }
    }
}
