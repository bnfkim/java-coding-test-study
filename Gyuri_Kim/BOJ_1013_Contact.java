import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 16616 kb
 * 200 ms
 */
public class BOJ_1013_Contact {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            String input = br.readLine();

            if(input.matches("(100+1+|01)+")) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.println(sb.toString().trim());
    }
}
