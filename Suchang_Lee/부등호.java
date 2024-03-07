import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class 부등호 {
    static int K;
    static String []k ;
    static long min = Long.MAX_VALUE;
    static long max = Long.MIN_VALUE;

    public static void permutation (int[] num,int [] s,int count,int size) -{
        if (count == K + 1) {
            if(check(k,s)){
                String x = "";
                for (int i = 0; i < s.length; i++) {
                    s[i] = (char)s[i];
                    x+=s[i];
                }
                long number = Long.parseLong(x);
//                System.out.println(number);
                if (min > number) min =number;
                if (max < number) max =number;
            }
            return;
        }

        for (int i = 0; i <size; i++) {
            s[count] = num[i];
            int temp = num[i];
            num[i] = num[size-1];
            permutation(Arrays.copyOf(num, size - 1), s, count + 1, size - 1);
            num[i] = temp;
        }
    }
    public static void main (String [] args) {
//        System.out.println(min);
        int [] num =  { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        sc.nextLine();
        k = new String[K];
        k = sc.nextLine().split(" ");
        for (int i = 0 ; i < 10; i++) {
            int[] s = new int[K+1];
            s[0] = i;
            int temp = num[i];
            num[i] = num[num.length-1];
            permutation(num,s,1,9);
            num[i] = temp;
        }
        if (Long.toString(max).length() <= K) System.out.println("0"+max);
        else System.out.println(max);
        if (Long.toString(min).length() <= K)
            System.out.println("0"+min);
        else System.out.println(min);
    }
    public static boolean check (String [] a, int [] b) {
        for (int i = 0 ; i < K; i++) {
            if (a[i].equals("<")) {
                if (b[i] >= b[i+1]) return false;
            }
            else {
                if (b[i] <= b[i+1]) return false;
            }
        }
        return true;
    }
}
