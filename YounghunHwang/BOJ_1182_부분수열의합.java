import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S;
    static int[] nums;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        subset(0,0, 0);
        System.out.println(answer);
    }

    private static void subset(int depth, int start, int sum) {
        if (depth > N) {
            return;
        }

        for (int i = start; i < N; i++) {
            if ((sum + nums[i]) == S) {
                answer++;
            }
            subset(depth + 1, i + 1, sum + nums[i]);
        }
    }
}