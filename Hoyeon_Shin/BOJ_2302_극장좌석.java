import java.io.*;
import java.util.*;

/**
 * 14228KB  120ms
 */
class Main {
    static int N, M;
    static int maxSeq;
    static List<Integer> seqList = new ArrayList<>();
    static List<Integer> vipList = new ArrayList<>();
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // VIP 석의 번호를 저장
        for (int i = 0; i < M; ++i) {
            vipList.add(Integer.parseInt(br.readLine()));
        }

        //VIP를 제외한 나머지 사람들이 연속으로 있는 수를 저장
        if (M > 0)
            Collections.sort(vipList);

        int prev = 0;
        for (int vip: vipList) {
            int curSeq = vip - prev - 1;
            prev = vip;

            maxSeq = Math.max(maxSeq, curSeq);
            seqList.add(curSeq);
        }
        maxSeq = Math.max(maxSeq, N - prev);
        seqList.add(N - prev);

        // 최대 연속으로 존재할 수 있는 만큼
        // 연속으로 앉았을 때 앉을 수 있는 경우의 수 구하기
        dp = new int[maxSeq + 1];
        dp[0] = 1;
        if (maxSeq > 0) dp[1] = 1;
        if (maxSeq > 1) dp[2] = 2;

        for (int i = 3; i <= maxSeq; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int answer = 1;
        for (int seq: seqList) {
            answer *= dp[seq];
        }

        System.out.println(answer);
    }
}
