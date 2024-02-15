import java.io.*;

public class BOJ_2447_별찍기10 {

    static int N;
    static StringBuilder[] sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder[N];
        for (int i = 0; i < N; i++) {
            sb[i] = new StringBuilder();
        }
        solve();
    }

    private static void solve() {
        divideAndConquer(0, 0, N, true);
        for (int i = 0; i < N; i++) {
            System.out.println(sb[i].toString());
        }
    }

    private static void divideAndConquer(int x, int y, int size, boolean print) {
        if (size == 1) {
            sb[x].append(print ? '*' : ' ');
            return;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                divideAndConquer(x + i * size / 3, y + j * size / 3, size / 3, !(i == 1 && j == 1) && print);
            }
        }
    }

}
