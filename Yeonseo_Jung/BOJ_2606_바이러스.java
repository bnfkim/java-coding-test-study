import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2606_바이러스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computerNum = Integer.parseInt(br.readLine());
        int connectionNum = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> connections = new HashMap<>();
        for (int i = 0; i < connectionNum; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = edge[0];
            int to = edge[1];

            connections.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            connections.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
        }

        boolean[] visited = new boolean[computerNum + 1];
        int result = bfs(1, connections, visited);

        System.out.println(result);
        br.close();
    }

    static int bfs(int start, Map<Integer, List<Integer>> graph, boolean[] visited) {
        int visitedNum = 0;
        Queue<Integer> q = new LinkedList<>();

        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int node = q.poll();

            if (graph.get(node) == null) {
                continue;
            }

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    visitedNum++;
                    q.offer(neighbor);
                }
            }
        }
        return visitedNum;
    }
}
