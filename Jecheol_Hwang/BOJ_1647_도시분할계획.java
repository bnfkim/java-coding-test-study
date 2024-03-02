package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @algorithm kruskal
 * @time O(M * logM) ; 모든 간선 정렬 O(M * logM) + 각 간선에 대한 union-find O(E * logN)
 *
 * N : 노드의 개수 (2이상 100,000이하)
 * M : 간선의 개수 (1이상 1,000,000이하)
 */
public class BOJ_1647_도시분할계획 {
    private static int N, M;
    private static int[] parent;
    private static Queue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));

    private static class Edge {
        int u,v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, w));
        }

        int cnt = 0;
        int ans = 0;
        int bridge = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int u = e.u;
            int v = e.v;
            int w = e.w;
            if (find(u) == find(v)) {
                continue;
            }
            ans += w;
            cnt++;
            if (cnt == N - 1) {
                bridge = w;
                break;
            }
            union(u, v);
        }
        System.out.println(ans - bridge);
    }

    private static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    private static void union(int u, int v) {
        int uRoot = find(u);
        int vRoot = find(v);
        if (uRoot == vRoot) {
            return;
        }
        if (uRoot < vRoot) {
            parent[vRoot] = uRoot;
        } else {
            parent[uRoot] = vRoot;
        }
    }
}
