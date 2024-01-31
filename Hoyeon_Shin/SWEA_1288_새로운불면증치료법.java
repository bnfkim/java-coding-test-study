import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    // arr[0] ~ [9] 가 모두 0이 아니라면 true, 아니라면 false 반환
    public static boolean check(int[] arr) {
        boolean result = true;
        for (int i = 0; i < 10; ++i) {
            if (arr[i] == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; ++t) {
            int[] count = new int[10];  // arr[x] : x 를 본 횟수
            int N = Integer.parseInt(br.readLine());
            int num;
            int k = 0;

            // 모든 숫자가 세어질 때까지 k를 증가
            while (!check(count)) {
                k++;

                // k번 양을 세었을 때 본 숫자를 count에 반영
                num = k * N;
                while (num > 0) {
                    count[num % 10]++;
                    num /= 10;
                }
            }
            System.out.println("#" + t + " " + (k * N));
        }
    }
}
