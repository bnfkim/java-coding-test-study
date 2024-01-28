package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719_빗물 {
    private static int h, w;
    private static int[] arr;
    private static int left, right;
    private static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        arr = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        res = 0;

        for (int i = 0; i < w; i++) {
            if (arr[i] > 0) {
                left = i;
                break;
            }
        }

        right = left + 1;
        for (int i = left + 1; i < w; i++) {
            right = arr[i] >= arr[right] ? i : right;
            if (i == w - 1 || arr[left] <= arr[right]) {
                int std = arr[left] < arr[right] ? arr[left] : arr[right];
                for (int j = left + 1; j < right; j++) {
                    res += std - arr[j];
                }
                left = i;
                right = i + 1 < w ? i + 1 : i;
            }
        }
        System.out.println(res);
    }
}
