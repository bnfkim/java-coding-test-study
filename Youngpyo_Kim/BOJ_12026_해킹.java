import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static final int INF = 0x3f3f3f3f;
    static int n, d, start;
    static ArrayList<ArrayList<Pair>> arr = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        
        for (int test = 1; test <= t; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            
            arr.clear();
            dist = new int[n + 1];
            
            for (int i = 0; i <= n; i++) {
                arr.add(new ArrayList<>());
                dist[i] = INF;
            }
            
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                arr.get(b).add(new Pair(a, s));
            }
            
            dijkstra();
        }
    }

    public static void dijkstra() {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Pair(start, 0));
        
        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int curNode = current.node;
            int curDist = current.cost;
            
            if (dist[curNode] < curDist) continue;
            
            for (Pair next : arr.get(curNode)) {
                int nxtDist = curDist + next.cost;
                
                if (nxtDist < dist[next.node]) {
                    dist[next.node] = nxtDist;
                    pq.offer(new Pair(next.node, nxtDist));
                }
            }
        }
        
        int ans = 0, cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] != INF) {
                cnt++;
                ans = Math.max(ans, dist[i]);
            }
        }
        System.out.println(cnt + " " + ans);
    }

    static class Pair implements Comparable<Pair> {
        int node, cost;
        
        public Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Pair o) {
            return this.cost - o.cost;
        }
    }
}
