import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();

        int r=Math.max(m, n-m);

        BigDecimal answer=multiply(n,r).divide(multiply(n-r,0));

        System.out.println(answer);
    }

    static BigDecimal multiply(int n,int r) {
        BigDecimal cur=new BigDecimal(n);

        for(int i=n-1;i>r;i--) {
            cur=cur.multiply(new BigDecimal(i));
        }

        return cur;
    }
}
