import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2565_전깃줄 {
    // 232ms
    static class Wire implements Comparable<Wire> {
        int from;
        int to;

        public Wire(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Wire o) {
            return this.from - o.from;
        }
    }

    static int N;
    static Wire[] wires;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        wires = new Wire[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            wires[i] = new Wire(from, to);
        }

        Arrays.sort(wires);
        findMinCut();
    }

    static void findMinCut() {
        dp = new int[N];

        for (int i = 0; i < wires.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (wires[i].to > wires[j].to) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = Arrays.stream(dp).max().getAsInt();
        System.out.println(N - max);
    }
}
