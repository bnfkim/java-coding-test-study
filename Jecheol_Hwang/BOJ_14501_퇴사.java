package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14501_퇴사 {
    /*
    *
    * */

    private static int n;
    private static int ans = 0;

    private static Map<Integer, Pair> m = new HashMap<>();

    private static class Pair {
        private int skip, weight;

        private Pair(int skip, int weight) {
            this.skip = skip;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int skip = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            m.put(i, new Pair(skip, weight));
        }
        m.put(n, new Pair(1, 0));

        for (int i = 0; i < n; i++) {
            int res = 0;
            if (i + m.get(i).skip <= n) {
                res += m.get(i).weight;
                go(i + m.get(i).skip, res);
            }
        }
        System.out.println(ans);


    }

    private static void go(int now, int res) {
        if (now + m.get(now).skip > n) {
            ans = Math.max(ans, res);
//            return;
        }
        for (int i = now; i < n; i++) {
            if (i + m.get(i).skip <= n) {

                res += m.get(i).weight;
                go(i + m.get(i).skip, res);
                res -= m.get(i).weight;
            }
        }
    }
}
