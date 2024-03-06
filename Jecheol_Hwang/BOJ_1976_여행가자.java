package 알고리즘연습.boj;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * @algorithm union-find
 * @time O(M * log N)
 *
 * N : 도시 개수 (200 이하)
 * 길이 있을 수도, 없을 수도
 *
 * M : 여행 계획에 속한 도시들의 수 (1000 이하)
 *
 * 중간에 다른 도시를 경유해서 여행 가능
 *
 * 입력
 * 1. N (도시의 수)
 * 2. M (여행 계획에 속한 도시들의 수)
 * 3. loop N (인접행렬)
 *
 * 출력
 * -> 여행 계획이 가능한가? (어떻게든 연결되기만 하면 됨)
 * 유니온파인드 해서 나중에 find 값을 hashset에 넣었는데 1이 되면 되는 것 아닌가?
 */
public class BOJ_1976_여행가자 {
    private static int N, M;
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];

        /* root 노드 초기화 */
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        /* 연결 노드 초기화 */
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < M; i++) {
            int v = Integer.parseInt(st.nextToken());
            hs.add(find(v));
        }
        bw.write(hs.size() == 1 ? "YES" : "NO");

        br.close();
        bw.flush();
        bw.close();
    }

    private static void union(int u, int v) {
        int uRoot = find(u);
        int vRoot = find(v);
        if (uRoot == vRoot) {
            return;
        }
        if (uRoot < vRoot) {
            parent[vRoot] = uRoot;
        } else {
            parent[uRoot] = vRoot;
        }
    }

    private static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }
}
