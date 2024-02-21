import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_2407_조합 {
    static int n, m;

    public static BigInteger func(int a){
        BigInteger res = new BigInteger("1");

        for(int i = 1; i <= a; i++)
            res = res.multiply(new BigInteger(i+""));
        
        return res;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        System.out.println(func(n).divide(func(m).multiply(func(n-m))));
    }
}
