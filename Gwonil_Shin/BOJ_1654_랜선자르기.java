import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N,K;
    static long[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new long[K];

        for (int i = 0; i < K; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        long start = 1;
        long end = 1L <<31;

        while (start+1 <end) {
            long mid = (start + end) / 2;

            long cur = 0;
            for (int i = 0; i < K; i++) {
                cur += arr[i] / mid;
            }

            if (cur < N) {
                end = mid;
            } else {
                start = mid;
            }
        }

        System.out.println(start);
    }
}