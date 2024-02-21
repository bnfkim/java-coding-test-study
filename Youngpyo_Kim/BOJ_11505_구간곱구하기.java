import java.util.*;
import java.io.*;

public class BOJ_11505_구간곱구하기 {
    static final long MOD = 1000000007L;
    static int n, m, k, a, b, c;
    static long[] arr;
    static long[] segtree;
    
    static void build() {
        for (int cur = n; cur >= 1; cur--) {
            segtree[cur] = (segtree[cur << 1] * segtree[(cur << 1) + 1]) % MOD;
        }
    }

    static void update(int target) {
        int cur = target + n;
        segtree[cur] = arr[target];
        for (cur >>= 1; cur >= 1; cur >>= 1) {
            segtree[cur] = (segtree[cur << 1] * segtree[(cur << 1) + 1]) % MOD;
        }
    }

    static long query(int l, int r) {
        long res = 1;
        for (l += n, r += n; l <= r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) {
                res = (res * segtree[l++]) % MOD;
            }
            if ((r & 1) == 0) {
                res = (res * segtree[r--]) % MOD;
            }
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n+1];
        segtree = new long[300000];
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
            segtree[i + n] = arr[i];
        }
        build();
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                arr[b] = c;
                update(b);
            } else if (a == 2) {
                System.out.println(query(b, c));
            }
        }
    }

}
