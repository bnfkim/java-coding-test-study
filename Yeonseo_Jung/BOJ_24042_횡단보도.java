import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_24042_횡단보도 {

    final static int START = 1;
    static int NodeNum, Cycle, END;

    static class Node implements Comparable<Node> {
        int to;
        long cost;

        public Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    static List<Node>[] adjs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        NodeNum = Integer.parseInt(st.nextToken());
        Cycle = Integer.parseInt(st.nextToken());
        END = NodeNum;

        adjs = new ArrayList[NodeNum + 1];
        for (int i = 0; i <= NodeNum; i++) {
            adjs[i] = new ArrayList<>();
        }

        for (int i = 0; i < Cycle; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjs[from].add(new Node(to, i));
            adjs[to].add(new Node(from, i));
        }

        dijkstra();
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[NodeNum + 1];
        long[] dist = new long[NodeNum + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[START] = 0;
        pq.offer(new Node(START, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentNode = current.to;

            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            for (Node next : adjs[currentNode]) {
                // 현재 시간 이후 주기를 고려하여 비용 계산
                // next.cost 대신 (next.cost - dist[currentNode] % Cycle + Cycle) % Cycle + 1 로 계산
                long nextCost = dist[currentNode] + (next.cost - dist[currentNode] % Cycle + Cycle) % Cycle + 1;

                if (dist[next.to] > nextCost) {
                    dist[next.to] = nextCost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

        System.out.println(dist[END]);
    }
}
