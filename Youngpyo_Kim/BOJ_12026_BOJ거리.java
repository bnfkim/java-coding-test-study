import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static final int INF = 0x3f3f3f3f;
    static int n;
    static int[] dp;
    static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        str = br.readLine();
        dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = -1;
        }

        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == -1)
                continue;
            char next = chartochar(str.charAt(i));

            for (int j = i + 1; j < n; j++) {
                if (str.charAt(j) == next) {
                    int cost = (j - i) * (j - i);
                    if (dp[j] == -1)
                        dp[j] = dp[i] + cost;
                    else
                        dp[j] = Math.min(dp[j], dp[i] + cost);
                }
            }
        }

        System.out.println(dp[str.length() - 1] == -1 ? -1 : dp[str.length() - 1]);
    }

    public static char chartochar(char c) {
        if (c == 'B')
            return 'O';
        else if (c == 'O')
            return 'J';
        else
            return 'B';
    }
}
