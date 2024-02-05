import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2910 {

    static int N, C;
    static StringBuilder sb = new StringBuilder();
    static Map<Integer, Info> order = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (!order.containsKey(n)) {
                order.put(n, new Info(n, 0, i));
            }
            Info info = order.get(n);
            info.count++;
        }

        PriorityQueue<Info> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.count == o2.count) {
                return o1.priority - o2.priority;
            }
            return o2.count - o1.count;
        });

        queue.addAll(order.values());

        while (!queue.isEmpty()){
            Info info = queue.poll();
            for (int i = 0; i < info.count; i++) {
                sb.append(info.number + " ");
            }
        }

        System.out.println(sb);
    }

    static class Info {
        int number;
        int count;
        int priority;

        public Info(int number, int count, int priority) {
            this.number = number;
            this.count = count;
            this.priority = priority;
        }
    }
}
