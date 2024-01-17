package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2606_바이러스 {
    /*
    * 시간 : DFS 124ms, BFS 132ms
    * 메모리 : DFS 14292kb, BFS 14272kb
    * 로직 : graph size 구하는 문제여서, 일단 dfs로 먼저 풀고, bfs로도 해 봤습니다.
    * */

    static List<Integer>[] adj;
    static boolean[] visited;
    static int graphSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        adj = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 무방향 == 양방향
            adj[a].add(b);
            adj[b].add(a);
        }
//        graphSize = 1; // for dfs
//        visited[1] = true; // for dfs
//        dfs(1);
        bfs(1);
        System.out.println(graphSize - 1); // 정답이 "1번 컴퓨터를 통해 원 바이러스에 걸리게 되는 컴퓨터의 수 이므로 -1"
    }

    static void dfs(int v) {
        for (Integer node : adj[v]) {
            if (!visited[node]) {
                visited[node] = true;
                graphSize++;
                dfs(node);
            }
        }
    }

    static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);

        while (!q.isEmpty()) {
            Integer node = q.poll();
            if (!visited[node]) {
                visited[node] = true;
                graphSize++;
                for (Integer nextNode : adj[node]) {
                    q.add(nextNode);
                }
            }
        }
    }
}
