import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 14372kb  136ms
 */
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

        for (int i = 0; i < N; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        solve(0, 0 , 0);

        System.out.println(answer);
    }

    // 수열을 정렬했으므로 앞에서부터 더해가며 목표한 숫자보다 값이 커지는 경우를 가지치기 할 수 있다.
    public static void solve(int idx, int curSum, int cnt) {
        if (idx == N) {
            if (cnt != 0 && curSum == S)
                answer++;
            return;
        }

        // 현재 합이 S 보다 큰데 num[idx] 값이 0 이상이라면 더 진행해도 가능성이 없다.
        if (curSum > S && nums[idx] >= 0)
            return;

        solve(idx + 1, curSum + nums[idx], cnt + 1);
        solve(idx + 1, curSum, cnt);
    }
}
