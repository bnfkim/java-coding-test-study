package Yeonsoo_Joo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2606_바이러스 {
    static int v;
    static int[][] graph;
    static boolean[] visited;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());
        int e = Integer.parseInt(br.readLine());

        graph = new int[v+1][v+1];
        visited = new boolean[v+1];

        for (int i = 0; i < e; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = 1;
        }

        dfs(1);

        System.out.println(count);

    }

    private static void dfs(int node) {
        visited[node] = true;

        for (int i = 1; i <= v; i++) {
            if (graph[node][i] == 1 && !visited[i]) {
                dfs(i);
                count++;
            }
        }
    }
}