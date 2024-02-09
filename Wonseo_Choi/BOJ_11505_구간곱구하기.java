package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11505_구간곱구하기 {
    static long[] tree;
    static long[] input;
    public static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = input[start];
        } else {
            int mid = (start + end)>>1;
            return tree[node] = (init(start, mid, node*2) * init(mid+1, end, node*2+1))%1000000007L;
        }
    }

    public static long mul(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return 1;
        }
        if ( left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end)>>1;
        return (mul(start, mid, node*2, left, right) * mul(mid+1, end, node*2+1, left, right))%1000000007L;
    }

    public static long update(int start, int end, int node, int index, long change) {
        if (start > index || end < index) {
            return tree[node];
        }

        
        
        if (start == end ) {
            
            return tree[node] = change;
        }

        int mid = (start + end) >> 1;
        return tree[node] = (update(start, mid, node*2, index, change) * update(mid+1, end, node*2+1, index, change))%1000000007L;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        input = new long[N+1];
        tree = new long[N<<2];
        for (int i = 1; i <= N; i++ ) {
            input[i] = Long.parseLong(br.readLine());
        }
        init(1, N, 1);

        int a=0, b=0;
        long d=0L, c=0L;
        for (int i = 0; i < M+K; i++ ) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (a == 1) {
                c = Long.parseLong(st.nextToken());
                update(1, N, 1, b, c);                
            } else if (a==2) {
                c = Integer.parseInt(st.nextToken());
                sb.append(mul(1, N, 1, b, (int) c) + "\n");
            }
        }
        System.out.println(sb);
    }
}
