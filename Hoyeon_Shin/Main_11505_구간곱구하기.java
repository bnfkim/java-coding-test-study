import java.io.*;
import java.util.*;

public class Main_11505_구간곱구하기 {
    static int N, M, K;
    static long[] data;
    static long[] segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        data = new long[N];
        segTree = new long[4 * N];

        for (int i = 0; i < N; ++i) {
            data[i] = Long.parseLong(br.readLine());
        }

        initSegTree(0, N - 1, 0);

        for (int i = 0; i < M + K; ++i) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1)
                updateSegTree(0, N - 1, 0, b - 1, c);

            else
                sb.append(getMult(0, N - 1, 0, b - 1, (int)c - 1)).append("\n");
        }

        System.out.println(sb);
    }

    public static long initSegTree(int start, int end, int node) {
        // leaf node
        if (start == end) {
            segTree[node] = data[start];
        }

        // non-leaf node
        else {
            int mid = (start + end) >> 1;
            segTree[node] = 1;
            segTree[node] *= initSegTree(start, mid, 2 * node + 1);
            segTree[node] *= initSegTree(mid + 1, end, 2 * node + 2);
            segTree[node] %= 1_000_000_007;
        }

        return segTree[node];
    }

    // data[idx] = val 으로 수정하는 명령을 처리, seg tree에 해당 내용을 반영
    public static long updateSegTree(int start, int end, int node, int idx, long val) {
        // 현재 노드가 바뀐 노드에 대해 책임이 있다면 값을 변경
        if (start <= idx && idx <= end) {

            // leaf node
            if (start == end) {
                segTree[node] = val;
            }

            // non-leaf node
            else {
                int mid = (start + end) >> 1;
                segTree[node] = 1;
                segTree[node] *= updateSegTree(start, mid, 2 * node + 1, idx, val);
                segTree[node] *= updateSegTree(mid + 1, end, 2 * node + 2, idx, val);
                segTree[node] %= 1_000_000_007;
            }
        }

        return segTree[node];
    }

    // data[targetStart] 부터 data[targetEnd] 까지의 곱을 seg tree 로부터 계산
    public static long getMult(int start, int end, int node, int targetStart, int targetEnd) {
        // 계산할 범위에 대해 책임이 없는 노드라면 건너뜀
        if (targetEnd < start || end < targetStart)
            return 1;

        // 해당 노드가 책임지는 값의 범위가 계산 범위에 모두 포함 되는 경우
        if (targetStart <= start && end <= targetEnd)
            return segTree[node];

        // 위 두 경우가 모두 아니라면
        int mid = (start + end) >> 1;
        return (getMult(start, mid, 2 * node + 1, targetStart, targetEnd)
                * getMult(mid + 1, end, 2 * node + 2, targetStart, targetEnd))
                    % 1_000_000_007;
    }
}
