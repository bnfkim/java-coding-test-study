import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            solve(str);
        }
    }
    private static void solve(String str) {
        if (Pattern.matches("(100+1+|01)+", str)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
