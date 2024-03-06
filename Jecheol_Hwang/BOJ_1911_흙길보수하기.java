package 알고리즘연습.boj;

import java.io.*;
import java.util.*;

/**
 * N : 물웅덩이 개수 (1 이상 10,000 이하)
 * L : 널빤지 길이 (1 이상 1백만 이하)
 *
 * 웅덩이 위치는 0 이상 1십억 이하 정수 (겹치지 않음)
 *
 * 물웅덩이를 모두 덮기 위해 필요한 널빤지의 최소 개수
 *
 * @intuition 그리디/Meeting-room-problem과 유사해보임.
 * 웅덩이 시작 좌표와 끝 좌표를 long[] 으로 저장해두고
 *
 * 시작 좌표를 기준으로 배치한다면?
 * 이게 최소가 되는지 아닌지만 판별하면 됨 (예외가 있나?)
 * -> 0 1 1 1 2 2 2 0 3 3 3 4 4 4
 *
 * 널빤지를 최소로 깔아야 하니까 최대한 길게 배치하는게 좋고,
 * 근데 또 전 범위를 커버해야 하는 건 맞으니까 시작점을 간과할 수 없음
 * -> 시작점 기준으로 그리디하게 배치하면 될듯.
 *
 * @algorithm 그리디
 * @time O(N * log N) -> 260 ms
 * @memory O(N) -> 20876 KB
 */
public class BOJ_1911_흙길보수하기 {
    private static int N, L;
    private static int[][] arr;
    private static long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 그리디를 위한 정렬
        Arrays.sort(arr, Comparator.comparingLong(o -> o[0]));

        int lastIdx = 0; // 널빤지를 마지막으로 둔 위치
        for (int[] ptr : arr) {
            // 웅덩이 좌표 [s,e)
            int s = ptr[0];
            int e = ptr[1];

            // 다시 차곡차곡 쌓으면 되는 경우
            if (s >= lastIdx) {
//                System.out.println("=========s >= lastIdx============");
                lastIdx = s;
                int length = e - s;
//                System.out.println("length = " + length);
                int share = length / L;
//                System.out.println("share = " + share);
                int remainder = length % L;
//                System.out.println("remainder = " + remainder);
                ans += share + (remainder > 0 ? 1 : 0); // 널빤지 개수 추가
//                System.out.println("ans = " + ans);
                lastIdx += (share + (remainder > 0 ? 1 : 0)) * L;
//                System.out.println("lastIdx = " + lastIdx);
            } else if (lastIdx < e) { // 중간에 마무리가 된 상태에서 널빤지를 더 깔아야 하는 상황인 경우
//                System.out.println("=========lastIdx < e============");
                int length = e - lastIdx;
//                System.out.println("length = " + length);
                int share = length / L;
//                System.out.println("share = " + share);
                int remainder = length % L;
//                System.out.println("remainder = " + remainder);
                ans += share + (remainder > 0 ? 1 : 0); // 널빤지 개수 추가
//                System.out.println("ans = " + ans);
                lastIdx += (share + (remainder > 0 ? 1 : 0)) * L;
//                System.out.println("lastIdx = " + lastIdx);
            }
        }
        sb.append(ans);
        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
