package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class BOJ_2785_체인 {
    /*
    * time complexity : 대애충 O(N)
    * space complexity : 4 bytes * 5 * 1e5 KB
    *
    * 시간 : 884 ms
    * 공간 : 73024 KB
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] chains = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(chains);

        int popIdx = 0;
        int temp = 0; // 체인의 값
        int cnt = 0;

        while (true) {
            temp += chains[popIdx];
            cnt++;

            if (temp >= n - cnt) {
                System.out.println(n - cnt);
                return;
            } else {
                popIdx++;
            }

        }

    }
}
