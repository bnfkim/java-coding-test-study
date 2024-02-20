import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2447_별찍기10 {
    static int n;
    static StringBuilder sb;

    public static void star(int r, int c, int size) {
        if ((r / size - 1) % 3 == 0 && (c / size - 1) % 3 == 0) {
            sb.append(" ");
        } else {
            if (size / 3 == 0)
                sb.append("*");
            else
                star(r, c, size / 3);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                star(i, j, n);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
