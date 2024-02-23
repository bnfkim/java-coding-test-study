package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1976_여행_가자 {
    // 144ms
    static int N, M;
    static boolean isConnected;
    static boolean[][] cities;
    static int[] parents;
    static int[] plans;

    static void init(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    static int find(int v) {
        if (v==parents[v]) {
            return v;
        }
        return parents[v] = find(parents[v]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return false;
        }

        parents[rootB] = rootA;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        cities = new boolean[N][N];
        init(N);
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (input[j].equals("1")) {
                    union(i, j);
                }
            }
        }

        String[] input = br.readLine().split(" ");
        plans = new int[M];
        for (int i = 0; i < M; i++) {
            plans[i] = Integer.parseInt(input[i]) - 1;
        }

        isConnected = true;
        for (int i = 0; i < M - 1; i++) {
            int from = plans[i];
            int to = plans[i+1];

            if (find(from) != find(to)) {
                isConnected = false;
                break;
            }
        }
        System.out.println(isConnected? "YES" : "NO");
    }
}
