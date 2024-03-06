import java.util.*;
import java.io.*;

/**
 * 11644kb
 * 80ms
 */
public class BOJ_23796_2147483648게임 {

    static final int N = 8;
    static int cnt;
    static long arr[][];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        move();
        back();
        print();
    }

    private static void move() {
        long[][] temp = new long[N][N];

        for (int j = 0; j < N; j++) {
            int size = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i][j] == 0) continue;
                
                int index = i + 1;
                while (index < N && arr[index][j] == 0) index++;

                if (index == N) {
                    temp[size++][j] = arr[i][j];
                    break;
                }

                boolean p = arr[i][j] == arr[index][j];

                temp[size++][j] = arr[i][j] * (p ? 2 : 1);
                i = index - (p ? 0 : 1);
            }
        }

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.copyOf(temp[i], N);
        }
    }

    private static void back() {
        for (int i = 0; i < N - cnt; i++) rotate();
    }

    private static void init() throws IOException {
        cnt = 0;
        arr = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        char c = br.readLine().charAt(0);
        if (c == 'L') {
            cnt = 1;
        } else if (c == 'D') {
            cnt = 2;
        } else if (c == 'R') {
            cnt = 3;
        }

        for (int i = 0; i < cnt; i++) {
            rotate();
        }
    }

    private static void rotate() {
        long[][] temp = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = arr[N - 1 - j][i];
            }
        }
        
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.copyOf(temp[i], N);
        }
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}