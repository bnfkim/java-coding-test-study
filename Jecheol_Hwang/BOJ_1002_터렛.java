package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1002_터렛 {
    private static int y1;
    private static int x1;
    private static int y2;
    private static int x2;
    private static int r1;
    private static int r2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            y1 = Integer.parseInt(st.nextToken());
            x1 = Integer.parseInt(st.nextToken());
            r1 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());

            setFormerBigger();
            if (r1 + r2 < distance()) {
                System.out.println(0);
            } else if (r1 > distance() + r2) {
                System.out.println(0);
            } else if (r1 == r2 && distance() == 0) {
                System.out.println(-1);
            } else if (r1 + r2 == distance()) {
                System.out.println(1);
            } else if (r1 == distance() + r2) {
                System.out.println(1);
            } else if (r1 + r2 > distance()) {
                System.out.println(2);
            }
        }
    }
    private static void setFormerBigger() {
        if (r1 < r2) {
            int temp = r1;
            r1 = r2;
            r2 = temp;

            temp = y1;
            y1 = y2;
            y2 = temp;

            temp = x1;
            x1 = x2;
            x2 = temp;
        }
    }

    public static double square(int n) {
        return Math.pow(n, 2);
    }

    public static double distance() {
        double num = square(y1 - y2) + square(x1 - x2);
        return Math.sqrt(num);
    }
}
