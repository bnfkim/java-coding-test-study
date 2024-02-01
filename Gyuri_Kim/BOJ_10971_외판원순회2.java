import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971_외판원순회2 {
    static int N;
    static boolean[] visit;
    static int[][] map;
    static int result;

    /**
     * 고민 해야할 점
     * - 다시 첫번째 자리로 돌아와야함
     * - 첫번째 자리를 기억하고 있어야 함
     * - 마지막 도시에서 다시 start로 돌아오는 비용도 해줘야 함
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N];
        result = Integer.MAX_VALUE;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //어느 도시에서 부터 시작할 지 모름
        for(int i=0; i<N; i++) {
            visit[i] = true;
            dfs(i, i, 0, 0);
        }
        System.out.println(result);
    }
    public static void dfs(int start, int now, int dep, int cost) {
        if(dep == N-1) {
            if(map[now][start] != 0) {
                cost += map[now][start]; //출발지로 다시 돌아가는 비용
                result = Math.min(cost, result);
            }
            return;
        }

        for(int i=0; i<N; i++) {
            if(visit[i] || map[now][i] == 0) continue;

            visit[i] = true;
            dfs(start, i, dep+1, cost + map[now][i]);
            visit[i] = false;
        }
    }
}
