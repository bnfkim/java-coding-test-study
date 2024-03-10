import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_31235_올라올라 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken());
        int k = 1, answer = 1;
        for (int i = 1; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (prev > cur) {
                ++k;
            } else {
                prev = cur;
                answer = Math.max(k, answer);
                k = 1;
            }
        }
        System.out.println(Math.max(k, answer));
    }
}
