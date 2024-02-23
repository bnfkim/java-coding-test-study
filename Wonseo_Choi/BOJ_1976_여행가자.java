import java.io.*;
import java.util.*;


public class BOJ_1976_여행가자 {
    static int[] arr2;
    static int N, M;
    static int[] parents;

    public static void make() {
        for (int i = 0; i < N; i++ ) {
            parents[i] = i;
        }
    }

    public static int find(int v) {
        if (parents[v] == v) {
            return v;
        }
        return parents[v] = find(parents[v]);
    }

    public static boolean union(int a, int b) {
        int RootA = find(a);
        int RootB = find(b);

        if (RootA == RootB) return false;

        parents[RootB] = RootA;
        
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        parents = new int[N];
        make();
        for (int ni = 0; ni < N; ni++ ) {
            st = new StringTokenizer(br.readLine());
            for (int nj = 0; nj < N; nj++ ) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(ni, nj);
                }
            }
        }

        arr2 = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++ ) {
            arr2[m] = Integer.parseInt(st.nextToken())-1;
        }

        boolean flag = true;
        for (int m = 0; m < M-1; m++ ) {
            int a = find(arr2[m]);
            int b = find(arr2[m+1]);

            if (a != b) {
                flag = false;
                break;
            }
        }

        if (flag) System.out.println("YES");
        else System.out.println("NO");
    }
}
