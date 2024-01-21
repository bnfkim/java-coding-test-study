package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16401_과자나눠주기 {
    // 시간 : 1424ms
    // 메모리 : 126496 KB
    // 로직 : 1부터 10억 까지의 길이 중 어떤 길이로 주어야 하는지를 탐색하는 문제로, 범위가 O(n)으로는 탐색이 불가하므로 이진탐색으로 원하는 숫자를 탐색하고자 함.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nephewsCnt = Integer.parseInt(st.nextToken());
        int cookiesCnt = Integer.parseInt(st.nextToken());

        // init
        int[] cookies = new int[cookiesCnt];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cookiesCnt; i++) {
            cookies[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cookies);

        // binary search
        int left = 1;
        int right = (int) 1e9;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = Arrays.stream(cookies).map(cookie -> cookie / mid).sum();
            if (cnt >= nephewsCnt) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int ans = right;
        // 항상 left가 아니라 right 가 ans인 이유 : 동일한 경우에도 최대 값을 찾기 위해 left = mid + 1;을 통해 가능한 x중 가장 큰 x를 연산하고자 했고, 가장 마지막 연산에서 left가 정답에서 + 1 되므로
        // 그렇게 처리되지 않는 right가 정답임. 혹시 만약 cnt == nephewCnt인 경우에 right = mid - 1;을 해버리면 최대인 x를 찾을 수 없으므로 정답을 얻을 수 없음.
        System.out.println(ans);
    }
}
