import java.io.*;
import java.util.*;

public class BOJ_11505_구간곱구하기 {

    static long[] input;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        input = new long[N + 1];
        for(int i=1; i<=N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        tree = new long[N << 2];
        init(1, N, 1);

        for(int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            //a가 1인 경우 -> b번째 수를 c로 바꾸고
            //a가 2인 경우 -> b부터 c까지의 곱을 구하여 출력하면 된다.
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a==1) {
                input[b] = c;
                update(1, N, 1, b, c);
            }
            else sb.append(mul(1, N, 1, b, c) ).append("\n");
        }
        System.out.println(sb.toString().trim());
    }

    static long init(int s, int e, int n) {
        if(s == e) {
            return tree[n] = input[s];
        }
        int m = (s+e) >> 1;
        return tree[n] = (init(s, m, n*2) * init(m+1, e, n*2+1)) % 1_000_000_007;
    }

    //핵심 : 말단 노드까지 따라가면서 값을 차례로 업데이트
    static void update(int s, int e, int n, int idx, long change) {
        if(s == e) {
            tree[n] = change;
            return;
        }

        int m = (s + e) >> 1;

        if (idx <= m) update(s, m, n*2, idx, change);
        else update(m+1, e, n*2+1, idx, change);

        tree[n] = (tree[n * 2] * tree[n * 2 + 1]) % 1_000_000_007;;
    }

    static long mul(int s, int e, int n, int left, int right) {
        if(e < left || s > right) return 1;

        if(left <= s && e <= right) {
            return tree[n]; //말단은 어차피 Integer
        }

        int m = (s + e) >> 1;
        return (mul(s, m, n*2, left, right) * mul(m+1, e, n*2+1, left, right)) % 1_000_000_007;
    }
}
