import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 최대 나올 수 있는 결과값인 100 C 100이 long의 범위를 벗어나므로 BigInteger 사용
        BigInteger nf = new BigInteger("1");  // n!
        BigInteger mf = new BigInteger("1");  // m!
        BigInteger n_mf = new BigInteger("1");  // (n - m)!

        // n!, m!, (n - m)! 구하기
        for (int i = 2; i <= n; ++i) {
            nf = nf.multiply(new BigInteger(Integer.toString(i)));

            if (i <= m)
                mf = mf.multiply(new BigInteger(Integer.toString(i)));

            if (i <= n - m)
                n_mf = n_mf.multiply(new BigInteger(Integer.toString(i)));
        }

        // nCm = n! / (m! * (n - m)!) 구하기
        BigInteger result = nf.divide(mf.multiply(n_mf));
        System.out.println(result);
    }
}
