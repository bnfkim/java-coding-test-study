import java.io.*;
import java.util.*;

/**
 * 	22660kb	
 *  352ms	
 */
public class BOJ_1911_흙길보수하기 {

    static int N, L, cnt;
    static int[][] p;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        
        int stt = p[0][0];
        int end = p[0][1];

        for (int i = 0; i < N; i++) {
            int s = (int) Math.ceil((end - stt) / (double)L);
            int CURRENT = stt + s * L;
            cnt += s;
            
            int index = i + 1;
            while (index < N && p[index][1] < CURRENT) index++;

            if (index == N) break;

            stt = Math.max(p[index][0], CURRENT);
            end = p[index][1];
            i = index - 1;
        }

        System.out.println(cnt);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        p = new int[N][2];
        cnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            p[i] = new int[] { s1, s2 };
        }

        Arrays.sort(p, (x, y) -> {
            return x[0] - y[0];
        });
    }

}
