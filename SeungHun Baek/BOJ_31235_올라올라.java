import java.util.*;
import java.io.*;

public class BOJ_31235_올라올라 {
    
    static int N;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        int c = 1;
        int l = 1;

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N; i++) {
            int next = Integer.parseInt(st.nextToken());

            if (p > next) {
                l++;
            } else {
                c = Math.max(c, l);
                l = 1;
                p = next;
            }
        }

        System.out.println(Math.max(c, l));
    }
}
