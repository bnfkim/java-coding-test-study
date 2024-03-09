import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class 흙길보수하기 {
    public static int N;
    public static int L;
    public static void main (String [] args) {
        Scanner sc= new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        ArrayList<Pool> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(new Pool(sc.nextInt(), sc.nextInt() - 1));
        }

        Collections.sort(arr,new Comparator<Pool>() {
            @Override
            public int compare(Pool o1, Pool o2) {
                // TODO Auto-generated method stub
                return o1.start - o2.start;
            }
        });
        int target = 0;
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            Pool p = arr.get(i);
//			System.out.println(p.start+":"+p.end);
//			System.out.println("?");
            if (p.start <= target && target <= p.end) {
                int distance = p.end - target;
                int temp  = (distance) / L;
                if ((distance) % L == 0) {
                    target += temp*L ;
                    count += temp;
                }
                else {
                    target += (temp + 1) * L ;
                    count += temp + 1;
                }
            }
            else if (target > p.end) continue;
            else {
                target = p.start;
                int temp  = (p.end - p.start + 1) / L;
                if ((p.end - p.start + 1) % L == 0) {
                    target+= temp*L - 1;
                    count += temp;
                }
                else {
                    target += (temp + 1) * L - 1;
                    count += temp + 1;
                }
            }
//			System.out.println(target+":"+count);
        }
        System.out.println(count);
    }

    public static class Pool {
        int start;
        int end;
        Pool(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
