import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1002_터렛 {
    public static int T;
    public static int x1, y1, r1, x2, y2, r2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            r1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());

            getResult(x1, y1, r1, x2, y2, r2);
        }
    }

    private static void getResult(int x1, int y1, int r1, int x2, int y2, int r2) {
        double dx = Math.pow((x1 - x2), 2);
        double dy = Math.pow((y1 - y2), 2);
        double d = Math.sqrt(dx + dy);

        int dif = Math.abs(r1 - r2);
        int sum = r1 + r2;

        if (d == 0 && r1 == r2) {
            System.out.println(-1);
        } else if (dif < d && d < sum) {
            System.out.println(2);
        } else if (d == sum || d == dif) {
            System.out.println(1);
        } else if (d > sum || d < dif) {
            System.out.println(0);
        } else {
            System.out.println(2);
        }
    }
}
