import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806_부분합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] sequence = new int[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(input[i]);
        }
        int answer = Integer.MAX_VALUE;
        int str = 0, end = 0, sum = 0;
        while (end < n) {
            sum += sequence[end];
            while (sum >= target) {
                answer = Math.min(answer, end - str + 1);
                sum -= sequence[str];
                str++;
            }
            end++;
        }

        System.out.println(answer==Integer.MAX_VALUE? 0:answer);
    }
}
