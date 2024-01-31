import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int result = solve(N);
            sb.append(String.format("#%d %d\n", t, result));
        }

        System.out.println(sb);
    }

    private static int solve(int N) {
        int rest = 10;
        boolean[] visited = new boolean[10];
        int n = N;
        while (rest > 0) {
            String str = String.valueOf(n);
            for (int i = 0; i < str.length(); i++) {
                int num = str.charAt(i) - '0';
                if (!visited[num]) {
                    visited[num] = true;
                    rest--;
                }
            }
            n += N;
        }

        return n - N;
    }
}