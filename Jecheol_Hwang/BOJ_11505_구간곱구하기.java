package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11505_구간곱구하기 {
    /*
    * 수행 시간 : 524 ms
    *
    * 메모리 : 95092 KB
    * */
    static long[] tree;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 숫자 입력량
        int m = Integer.parseInt(st.nextToken()); // 업데이트 획수
        int k = Integer.parseInt(st.nextToken()); // 구간 곱 횟수
        arr = new long[n];

        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(br.readLine());
            arr[i] = num;
        }

        int h = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
        int treeSize = (int) Math.pow(2, h + 1);
        tree = new long[treeSize];

        init(1, 0, n - 1);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long diff = mod(c - arr[b]);
                arr[b] = c;
                update(1, 0, n - 1, b, diff);

            } else {
                long res = mul(1, 0, n - 1, b, (int) c - 1);
                sb.append(res).append("\n");
            }
        }
        System.out.println(sb);

    }

    public static long init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) >> 1;
        return tree[node] = mod(init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end));
    }

    public static void update(int node, int start, int end, int idx, long diff) {
        if (idx < start || idx > end) {
            return;
        }

        if (start == end) {
            tree[node] += diff;
            return;
        }

        int mid = (start + end) >> 1;
        update(node * 2, start, mid, idx, diff);
        update(node * 2 + 1, mid + 1, end, idx, diff);

        tree[node] = mod(tree[node * 2] * tree[node * 2 + 1]); // mul() 메서드에서 범위 내 return tree[node] 하기 위해선 업데이트 코드가 돌 때 internal node를 업데이트 해야합니다.
    }

    public static long mul(int node, int start, int end, int left, int right) {
        // 범위 나가는 곱은 무효 처리
        if (left > end || right < start) {
            return 1;
        }

        // 범위 내 값은 이미 있는 값으로 처리

        if (left <= start && end <= right) {
            return tree[node];
        }
        // 시간 초과의 원흉은 아래 코드입니다. 이걸 위 코드로 바꾸면 됩니다.
//        if (start == end) {
//            return tree[node];
//        }

        int mid = (start + end) >> 1;
        return mod(mul(node * 2, start, mid, left, right) * mul(node * 2 + 1, mid + 1, end, left, right));
    }

    public static long mod(long v) {
        return v % ((long) 1e9 + 7);
    }
}
