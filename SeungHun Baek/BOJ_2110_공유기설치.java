import java.util.*;
import java.io.*;

public class BOJ_2110_공유기설치 {
    static int N, C, arr[];
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        solve();
    }

    private static void solve() {
        int stt = 1;
        int end = arr[N - 1] - arr[0];

        while (stt <= end) {
            int mid = (stt + end) >> 1;

            if (isPossible(mid)) {
                stt = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(end);
    }

    private static boolean isPossible(int interval) {
        int cnt = 1;
        int pivot = arr[0];
        for (int i = 1; i < N; i++) {
            if (arr[i] - pivot >= interval) {
                cnt++;
                pivot = arr[i];
            }
            if (cnt >= C) return true;
        }

        return false;
    }
}
