package 알고리즘연습.boj;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17182_우주탐사선 {
    /*
    * 티어 : 골드3
    *
    * 수행 시간 : 188 ms
    *
    * 메모리 : 15912 MB
    *
    * 시간복잡도 : O(N^3 * N!); 플로이드와셜 * 넥퍼
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] adj = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = solution(n, k, adj);

        bw.write(String.valueOf(ans));

        br.close();
        bw.flush();
        bw.close();
    }

    static int K;
    static int N;
    static Pair[][] dist;
    static int ans;
    private static int solution(int n, int start, int[][] arr) {
        // k는 0부터 시작
        // dist도 0번 인덱스 ~ n-1번 인덱스로 구성

        dist = new Pair[n][n];
        K = start;
        N = n;

        int[] nodes = new int[n - 1];
        // nodes 정보 init
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (i == start) { // 시작 노드 제외하고
                continue;
            }
            nodes[idx++] = i; // 나머지 노드들 저장
        }

        // init dist
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = new Pair(arr[i][j], 1 << j);
            }
        }

        // floyd
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int joinWeight = dist[i][k].weight + dist[k][j].weight;
                    if (dist[i][j].weight >= joinWeight) {
                        dist[i][j] = new Pair(joinWeight, dist[i][k].visit | dist[k][j].visit); // 방문한 노드 정보까지 같이 저장
                    }
                }
            }
        }

//        printDistance(n);

        ans = (int) 1e9;

        getAns(nodes);

        // 넥퍼 수행하여 ans 업데이트
        while (true) {
            if (!np(nodes)) {
                break;
            }
//            printNodesPerm(nodes);
        }

        return ans;
    }

    private static void printNodesPerm(int[] nodes) {
        System.out.println(Arrays.toString(nodes));
    }

    private static void printDistance(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dist[i][j].weight + " ");
            }
            System.out.println();
        }
    }

    private static boolean np(int[] nodes) {
        final int LAST_IDX = nodes.length - 1;

        int i = LAST_IDX;
        while (i > 0 && nodes[i - 1] >= nodes[i]) {
            --i;
        }

        if (i == 0) {
            return false;
        }

        int j = LAST_IDX;
        while (nodes[i - 1] >= nodes[j]) {
            --j;
        }

        swap(nodes, i - 1, j);

        int k = LAST_IDX;
        while (i < k) {
            swap(nodes, i++, k--);
        }

        // 순열 완성
        getAns(nodes);

        return true;
    }

    private static void getAns(int[] nodes) {
        int visited = (1 << K); // 시작 노드 방문하고 시작
        int fin = (1 << N) - 1; // 모든 노드 방문
        int res = 0; // 이동 weight 합
        int prev = K;
        for (int node : nodes) { // 이동 경로를 나타냄
            if ((visited & fin) == fin) {
                break;
            }
            if ((visited & 1 << node) != 0) {
                continue;
            }
            res += dist[prev][node].weight;
            visited |=  dist[prev][node].visit;
            prev = node;
        }
        ans = Math.min(ans, res);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static class Pair {
        int weight, visit;

        private Pair(int w, int v) {
            weight = w;
            visit = v;
        }
    }
}
