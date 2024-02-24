import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] graph;
    static int[] plan;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        plan = new int[M];
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        if (solve()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (graph[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        for (int i = 0; i < M - 1; i++) {
            if (find(plan[i] - 1) != find(plan[i + 1] - 1)) {
                return false;
            }
        }
        return true;
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x > y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}