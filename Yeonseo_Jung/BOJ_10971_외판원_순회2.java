import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971_외판원_순회2 {

    static int N, sumCost, minCost;
    static boolean[] visited;
    static int[] visitOrder;
    static int[][] cost;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        minCost = Integer.MAX_VALUE;
        visited = new boolean[N];
        visitOrder = new int[N];
        cost = new int[N][N];

        // 비용 행렬 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 순회
        travel(0);
        System.out.println(minCost);
    }

    private static void travel(int cnt) {

        if (cnt == N) {
            calculateCost();
            minCost = Math.min(minCost, sumCost);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visitOrder[cnt] = i;
            visited[i] = true;
            travel(cnt + 1);
            visited[i] = false;
        }
    }

    private static void calculateCost() {
        sumCost = 0;
        int fst = visitOrder[0];
        int lst = visitOrder[N - 1];
        int returnCost = cost[lst][fst];
        if (returnCost == 0) {
            sumCost = Integer.MAX_VALUE;
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            int src = visitOrder[i];
            int tar = visitOrder[i + 1];
            if (cost[src][tar] == 0) {
                sumCost = Integer.MAX_VALUE;
                return;
            }
            sumCost += cost[src][tar];
        }

        sumCost += returnCost;
    }
}
