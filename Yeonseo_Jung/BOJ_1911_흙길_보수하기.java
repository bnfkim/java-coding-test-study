import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1911_흙길_보수하기 {
    static int N, L;

    static class Whole implements Comparable<Whole> {
        int start;
        int end;

        public Whole(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Whole o) {
            return this.start - o.start;
        }
    }

    static Whole[] wholes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        wholes = new Whole[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            wholes[i] = new Whole(start, end);
        }
        System.out.println(getMinLNum());
    }

    private static int getMinLNum() {
        Arrays.sort(wholes);
        int cnt = 0;
        int otherS = 0;

        for (Whole w : wholes) {
            int st = Math.max(w.start, otherS);
            if (st > w.end) {
                continue;
            }
            int en = st;
            while (en < w.end) { // 널빤지가 웅덩이 길이보다 짧은 경우
                en += L;   // 널빤지 두기
                cnt++;

                if (en > w.end) {
                    otherS = en;
                }
            }
        }
        return cnt;
    }
}
