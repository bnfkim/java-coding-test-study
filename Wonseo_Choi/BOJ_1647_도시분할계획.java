import java.io.*;
import java.util.*;

public class BOJ_1647_도시분할계획 {
    static int[] arr;
    static int N, M;

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void make() {
        for (int n = 0; n < N; n++ ) {
            arr[n] = n;
        }
    }

    public static int find(int v) {
        if (arr[v] == v) {
            return v;
        }

        return arr[v] = find(arr[v]);
    }

    public static boolean union(int a, int b) {
        int RootA = find(a);
        int RootB = find(b);

        if (RootA == RootB) return false;

        arr[RootB] = RootA;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        make();
        
        Edge[] edgeList = new Edge[M];
        int from, to, weight;
        for (int m = 0; m < M; m++ ) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken())-1;
            to = Integer.parseInt(st.nextToken())-1;
            weight = Integer.parseInt(st.nextToken());
            edgeList[m] = new Edge(from, to, weight);
        }

        Arrays.sort(edgeList);
        if (N == 2) {
            System.out.println(0);
        } else {
            int ans = 0, cnt=0;
            for (Edge e : edgeList ) {
                if (!union(e.from, e.to)) continue;
                ans += e.weight;
                if (++cnt == N-2) break;
            }
    
            System.out.println(ans);
        }

    }
}
