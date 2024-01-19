import java.util.Arrays;
import java.util.Scanner;

// union-find 활용
public class Main {
    static int [] root;
    static int count = 0;
    static int checkRoot(int x) {
        if (x == root[x]) {
            return x;
        }
        else {
            return root[x] = checkRoot(root[x]);
        }
    }
    static void find (int a, int b) {
        a = checkRoot(a);
        b = checkRoot(b);
        if(a!=b) root[b] = a;
    }
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        root = new int[N];
        for (int i = 0; i < N; i++) root[i] = i;
        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            if (a < b) find(a, b);
            else find(b, a);
        }
        int x = checkRoot(root[0]);
        for (int i = 1; i < N; i++) {
            if (x == checkRoot(root[i])) count+=1;
        }
        System.out.println(count);
    }
}