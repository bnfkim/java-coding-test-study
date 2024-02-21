import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_10164_격자상의경로 {

    static int N, M, K;
    static BigInteger answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        solve();
        System.out.println(answer);
    }

    private static void solve() {
        if (K == 0) {
            answer = calculate(N - 1, M - 1);
            return;
        }

        int kRow = (K - 1) / M;
        int kCol = (K - 1) % M;

        answer = calculate(kRow, kCol).multiply(calculate(N - kRow - 1, M - kCol - 1));
    }

    private static BigInteger calculate(int n, int m) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n + m; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        for (int i = 1; i <= n; i++) {
            result = result.divide(BigInteger.valueOf(i));
        }
        for (int i = 1; i <= m; i++) {
            result = result.divide(BigInteger.valueOf(i));
        }
        return result;
    }
}
