import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10164_격자상의경로 {
    // static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if (K == 0) {
            System.out.println(fac(N+M-2, N) / fac(M-1, 2)); 
        }else {
            int divX = K%M;
            int divY = K/M + 1;
            if (divX == 0) {
                divX = M;
                divY--;
            }
            System.out.println(fac(M-divX+N-divY, N-divY+1) / fac(M-divX, 2)
                                * fac(divX-1+divY-1, divY) / fac(divX-1, 2));
        }
    }

    public static long fac(int n, int c) {
        if (n == 0 | n == 1) return 1;
        if (n < c) return 1;
        return n * fac(n-1, c);
    }
}
