import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2583_영역구하기 {
    // static boolean[] visited;
    static boolean[][] arr;
    static int N, M;
    static List<Integer> ans_arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        arr = new boolean[M][N];
        
        int m1, m2, n1, n2;
        for (int k =0; k < K; k++ ) {
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            m1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());
            m2 = Integer.parseInt(st.nextToken());
            
            for (int m = m1; m < m2; m++ ) {
                for (int n = n1; n < n2; n++ ) {
                    arr[m][n] = true;
                }
            }
        }

        ans_arr = new ArrayList<Integer>();
        for (int i = 0; i < M; i++ ) {
            for (int j = 0; j < N; j++ ) {
                if (arr[i][j]) continue;
                ans_arr.add(bfs(i, j));
            }
        }

        System.out.println(ans_arr.size());

        Collections.sort(ans_arr);
        for (int a : ans_arr) {
            System.out.print(a + " ");
        }
    }

    static int[] di = {0, 0, -1, 1};
    static int[] dj = {-1, 1, 0, 0};
    private static int bfs(int i, int j) {
        Queue<Integer[]> queue = new ArrayDeque<Integer[]>();
        queue.add(new Integer[] {i, j});

        int tmp_i, tmp_j, si, sj;
        arr[i][j] = true;
        int ans = 1;
        Integer[] tmp;
        while(!queue.isEmpty()) {
            tmp = queue.poll();
            tmp_i = tmp[0];
            tmp_j = tmp[1];
            

            for (int idx = 0; idx < 4; idx++ ) {
                si = tmp_i + di[idx];
                sj = tmp_j + dj[idx];
                
                if (0 <= si && si < M && 0 <= sj && sj < N) {
                    if (!arr[si][sj]) {
                        arr[si][sj] = true;
                        ans++;
                        queue.add(new Integer[] {si, sj});
                    }
                }
            }
        }

        return ans;
    }
}
