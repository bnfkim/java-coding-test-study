package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/*
시작 시간 : 24-02-08 09:35
종료 시간 : 24-02-09 01:37
실행 시간 : 496ms
메 모 리 : 118476KB

고려사항
세그먼트 트리를 이용하여 해결하였습니다.
업데이트를 0으로 하는 경우, 중간 노드의 값이 모두 0으로 바뀌기 때문에
update를 할 때마다 해당 노드의 상위 노드 값을 갱신하는 것이 포인트였습니다.
*/

public class BOJ_11505_구간곱구하기_G1 {

    static final long MOD = 1_000_000_007;
    static int N, M, K;
    static long[] input;
    static long[] tree;
    static long[][] cmds;
    static StringBuilder sb = new StringBuilder();

    private static void makeTree(int arrSize){
        int requiredNodeNum = arrSize << 2;
        tree = new long[requiredNodeNum];
    }

    private static long init(long[] arr, int node, int start, int end){
        // 리프 노드
        if(start == end){
            return tree[node] = arr[start];
        }

        // 중간 노드
        int mid = (start+end) / 2;
        return tree[node] =
                (init(arr, node*2, start, mid)
                        * init(arr, node*2+1, mid+1, end)) % MOD;
    }

    private static long update(int node, int start, int end, int idx, long changed){
        if(idx < start || idx > end) return tree[node];

        if(start == end) return tree[node] = changed;

        int mid = (start+end)/2;
        return tree[node] = (update(node*2, start, mid, idx, changed)
                * update(node*2+1, mid+1, end, idx, changed)) % MOD;
    }

    /**
     *
     * @param node 현재 노드
     * @param start 배열의 시작
     * @param end 배열의 끝
     * @param left 원하는 누적합의 시작
     * @param right 원하는 누적합의 끝
     */
    private static long mul(int node, int start, int end, int left, int right){
        // 범위를 벗어나는 경우, 더이상 탐색x
        if(left > end || right < start) return 1L;

        // 범위 내에 완전히 포함되는 경우
        if(left <= start && end <= right) return tree[node];

        // 이외의 경우
        int mid = (start+end)/2;
        return (mul(node*2, start, mid, left, right)
                * mul(node*2+1, mid+1, end, left, right)) % MOD;
    }

    private static void input() throws IOException {
        System.setIn(Files.newInputStream(Paths.get("input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        input = new long[N];
        cmds = new long[M+K][3];

        for(int i = 0; i < N; i++) input[i] = Long.parseLong(br.readLine());

        for(int i = 0; i < M+K; i++){
            st = new StringTokenizer(br.readLine());
            cmds[i][0] = Long.parseLong(st.nextToken());
            cmds[i][1] = Long.parseLong(st.nextToken());
            cmds[i][2] = Long.parseLong(st.nextToken());
        }
    }

    private static void execute(long[] cmd) {
        if(cmd[0] == 1){ // update
            update(1,1, N, (int)cmd[1], cmd[2]);
        }else{ // mul
            sb.append(mul(1,1,N, (int)cmd[1],(int)cmd[2])).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {

        input();
        makeTree(N);
        init(input, 1, 0, N-1);

        for(int i = 0; i < M+K; i++){
            execute(cmds[i]);
        }
        System.out.print(sb);
    }
}