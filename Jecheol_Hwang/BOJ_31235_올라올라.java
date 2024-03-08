package 알고리즘연습.boj;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @intuition 각 elem에 대한 최대값 갱신에 필요한 최대 index 수를 계산하면 될 것으로 봤다.
 * 즉, 매 elem을 받고 비교 연산을 통해 최대 index 길이를 greedy하게 갱신하면 되는 문제이다.
 *
 * @algorithm greedy
 * @time O(N) -> 500 ms
 * @memory O(1) -> 115436 KB
 */
public class BOJ_31235_올라올라 {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int cnt = 1;
        int k = 0;
        int prevMax = 0;
        int num = 0;
        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(st.nextToken());
            if (num < prevMax) {
                cnt++;
            } else {
                prevMax = num;
                k = Math.max(k, cnt);
                cnt = 1;
            }
        }
        k = Math.max(k, cnt);
        bw.write(String.valueOf(k));
        br.close();
        bw.flush();
        bw.close();
    }
}
