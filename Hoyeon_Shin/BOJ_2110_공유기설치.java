import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 29164kb  296ms
 */
public class Main {
    static int N, C;
    static int[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        house = new int[N];
        for (int i = 0; i < N; ++i) {
            house[i] = Integer.parseInt(br.readLine());
        }

        solve();
    }

    public static void solve() {
        Arrays.sort(house);

        int end2end = house[house.length - 1] - house[0];

        // 공유기가 2개라면 가장 먼 곳 2곳에 설치하면 된다.
        if (N == 2) {
            System.out.println(end2end);
            return;
        }

        // 가장 인접한 두 공유기 사이 거리를 이분탐색으로 구한다.
        int end = end2end / (C - 1);
        int start = 0;

        while (start <= end) {
            int mid = (start + end) / 2;

            // 설치가 가능하다면 더 긴 거리로 설치를 시도해본다.
            if (isPossible(mid)) {
                start = mid + 1;
            }

            // 불가능하다면 거리를 줄여서 시도해본다.
            else {
                end = mid - 1;
            }
        }

        System.out.println(end);
    }

    // 최소거리 d를 보장하여 C개의 공유기 설치가 가능한지 확인
    public static boolean isPossible(int d) {
        int prev = house[0];
        int idx = 1;
        int left = C - 1;  // 첫 집에 하나 설치했으므로 C - 1개 설치 가능한지 확인

        while (idx < N && left > 0) {
            if (house[idx] - prev >= d) {
                prev = house[idx];
                left--;
            }
            idx++;
        }

        return left == 0;
    }
}
