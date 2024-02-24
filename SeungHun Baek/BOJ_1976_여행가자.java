import java.util.*;
import java.io.*;

/**
 * BOJ_1976_여행가자
 */
public class BOJ_1976_여행가자 {

    static int N, M, parents[], route[];
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("1")) {
                    union(i + 1, j + 1);
                } 
            }
        }

        route = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve() ? "YES" : "NO");

    }

    private static boolean solve() {
        for (int i = 1; i < M; i++) {
            if (find(route[i]) != find(route[i-1])) {
                return false;
            }
        }

        return true;
    }

    private static void union(int x, int y) {
        int p1 = find(x);
        int p2 = find(y);

        if (p1 > p2) {
            parents[p1] = p2;
        } else {
            parents[p2] = p1;
        }
    }

    private static int find(int x) {
        return parents[x] = (parents[x] == x) ? x : find(parents[x]);
    }
    
}