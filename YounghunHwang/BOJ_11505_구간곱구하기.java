import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] tree;
    static int[] input;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        tree = new long[N * 4];
        init(0, N - 1, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                update(0, N - 1, 1, b - 1, c);
            } else {
                sb.append(mult(0, N - 1, 1, b - 1, c - 1)).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = input[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % 1000000007;
    }

    private static void update(int start, int end, int node, int target, int change) {
        if (start == end) {
            tree[node] = change;
            return;
        }


        int mid = (start + end) / 2;
        if (target <= mid) {
            update(start, mid, node * 2, target, change);
        } else {
            update(mid + 1, end, node * 2 + 1, target, change);
        }

        tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % 1000000007;
    }

    private static long mult(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return 1;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return (mult(start, mid, node * 2, left, right) * mult(mid + 1, end, node * 2 + 1, left, right)) % 1000000007;
    }
}
