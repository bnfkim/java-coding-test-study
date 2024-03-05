package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @algorithm parametric search
 * @time O(N * log N) ; 정렬 + 이분탐색 --> 320 ms
 * @memory O(N) ; 정수배열 --> 29428 KB
 *
 * N : 집 개수
 * C : 공유기 개수
 */
public class BOJ_2110_공유기설치 {
    private static int N, C;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        arr[0] = 0;

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        // 거리 이진탐색
        int ans = binarySearch();
        System.out.println(ans);
    }

    private static int binarySearch() {
        int left = 0;
        int right = arr[N];
        int targetDist = 0; // "가장 인접한" 두 라우터 간 거리
        int ansDist = 0;
        while (left <= right) {
            targetDist = (left + right) / 2;
            long curDist = 0;
            int routerCnt = 1; // 첫 번째 집에 설치하고 시작.
            for (int i = 2; i <= N; i++) {
                curDist += arr[i] - arr[i-1];
                if (curDist >= targetDist) {
                    curDist = 0;
                    routerCnt++;
                }
            }

            // 가능한 경우에는 더 늘려서 -> 최대가 되게
            if (routerCnt >= C) {
                left = targetDist + 1;
                ansDist = Math.max(ansDist, targetDist); // 수정 부분
            } else {
                right = targetDist - 1;
            }
        }
        return ansDist;
    }

}
