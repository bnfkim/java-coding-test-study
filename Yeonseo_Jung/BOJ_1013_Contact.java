import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class BOJ_1013_Contact {
    // 216ms
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        String pattern = "(100+1+|01)+";
        for (int i = 0; i < T; i++) {
            System.out.println(Pattern.matches(pattern, br.readLine())? "YES":"NO");
        }
    }
}