import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Number implements Comparable<Number> {
    int num;
    int cnt;
    int idx;

    public Number(int num, int cnt, int idx) {
        this.num = num;
        this.cnt = cnt;
        this.idx = idx;
    }

    @Override
    public int compareTo(Number o) {
        if (this.cnt == o.cnt) {
            return this.idx - o.idx;
        }
        return o.cnt - this.cnt;
    }
}
public class BOJ_2910_빈도정렬 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Map<Integer, Number> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (map.containsKey(num)) {
                map.get(num).cnt++;
            } else {
                map.put(num, new Number(num, 1, i));
            }
        }

        PriorityQueue<Number> pq = new PriorityQueue<>();
        pq.addAll(map.values());

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Number number = pq.poll();
            for (int i = 0; i < number.cnt; i++) {
                sb.append(number.num).append(" ");
            }
        }

        System.out.println(sb.toString().trim());
    }
}