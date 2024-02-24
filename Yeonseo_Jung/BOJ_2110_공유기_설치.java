import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2110_공유기_설치 {
    // 264ms
    static int N, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputNC = br.readLine().split(" ");
        N = Integer.parseInt(inputNC[0]);
        C = Integer.parseInt(inputNC[1]);

        int[] homes = new int[N];
        for (int i = 0; i < N; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(homes);
        System.out.println(getAnswer(homes));
    }

    private static int getAnswer(int[] homes) {
        int minDistance = 1;
        int maxDistance = homes[N - 1] - homes[0];

        int answer = 0;
        while (minDistance <= maxDistance) {
            int mid = (minDistance + maxDistance) / 2;
            // 첫번째 집에 공유기 설치
            int count = 1;
            int start = homes[0];

            for (int i = 1; i < N; i++) {
                int distance = homes[i] - start;
                if (distance >= mid) {
                    count++;
                    start = homes[i];
                }
            }

            if (count >= C) { // 공유기의 개수가 목표치보다 많은 경우
                answer = mid;
                minDistance = mid + 1;
            } else { // 공유기의 개수가 목표치보다 적은 경우
                maxDistance = mid - 1;
            }
        }

        return answer;
    }
}
