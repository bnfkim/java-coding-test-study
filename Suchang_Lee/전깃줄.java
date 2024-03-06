//2040
//2053
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class 전깃줄 {

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int [][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int [] length = new int[N];
        for (int i = 0; i < N; i++) {
            length[i] = 1;
        }
//        System.out.println(Arrays.deepToString(arr));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j][1] < arr[i][1]) length[i] = Math.max(length[j]+1, length[i]);
            }
        }
        int answer =0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(length[i],answer);
        }
        System.out.println(N-answer);
    }
}
