import java.io.*;
import java.util.*;

/**
 *  136092 KB   1308 ms
 */
class Main {
    static int T;
    static int n, d, c;
    static Node[] adjList;
    static int infectedComputers;
    static final int INF = Integer.MAX_VALUE;

    static class Node {
        int vertex;
        int weight;
        Node next;

        public Node (int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            // 의존성을 입력받아 인접 리스트 구성
            adjList = new Node[n + 1];
            for (int i = 0; i < d; ++i) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                adjList[b] = new Node(a, s, adjList[b]);
            }

            infectedComputers = 0;
            int time = dijkstra();
            sb.append(infectedComputers).append(" ").append(time).append("\n");
        }
        System.out.println(sb.toString());
    }

    // 다익스트라 알고리즘을 이용해 c로부터 다른 노드의 최소 비용을 모두 갱신
    public static int dijkstra() {

        // dist 초기화
        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            dist[i] = INF;
        }
        dist[c] = 0;

        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i < n; ++i) {
            int minDist = INF;
            int stopOver = -1;

            // 방문하지 않은 정점까지 가는 길 중 최소거리인 곳을 선택
            for (int j = 1; j <= n; ++j) {
                if (!visited[j] && minDist > dist[j]) {
                    minDist = dist[j];
                    stopOver = j;
                }
            }

            if (stopOver == -1) break;

            visited[stopOver] = true;
            infectedComputers++;
            for (Node node = adjList[stopOver]; node != null; node = node.next) {
                dist[node.vertex] = Math.min(dist[stopOver] + node.weight, dist[node.vertex]);
            }
        }

        int maxDist = 0;
        for (int i = 1; i <= n; ++i) {
            if (dist[i] == INF) continue;
            maxDist = Math.max(dist[i], maxDist);
        }

        return maxDist;
    }
}
