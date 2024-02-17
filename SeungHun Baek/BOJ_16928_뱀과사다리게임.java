import java.io.*;
import java.util.*;

public class BOJ_16928_뱀과사다리게임 {
    static int N, M, route[];
    static HashMap<Integer, Integer> map;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        route = new int[101];
        map = new HashMap<>();

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.put(a, b);
        }
        
        bfs();
        System.out.println(route[100]);
    }

    private static void bfs() {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(1);
        
        while (!que.isEmpty()) {
            int pos = que.pop();

            for (int i = 1; i <= 6; i++) {
                int next = pos + i;
                if (next > 100 || route[next] != 0) continue;
                next = map.getOrDefault(next, next);
                if (route[next] != 0) continue;
                route[next] = route[pos] + 1;
                if (next == 100) return;
                que.add(next);
            }
        }
    }

}