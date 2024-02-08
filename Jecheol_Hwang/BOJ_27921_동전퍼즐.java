package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_27921_동전퍼즐 {
    /*
    * 시간복잡도 : O(N^4) ; N이 최대 10이여서 가능했습니다...
    *
    * 실행 시간 : 124 ms
    * 사용 메모리 : 14276 KB
    * */
    public static void main(String[] args) throws IOException {
        int[] arr = new int[30];
        int[] brr;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int aCnt = 0;
        int H1 = Integer.parseInt(st.nextToken());
        int W1 = Integer.parseInt(st.nextToken());
        for (int i = 0; i < H1; i++) {
            String s = br.readLine();
            for (int j = 0; j < W1; j++) {
                String ss = s.substring(j, j + 1);
                if (ss.equals("O")) {
                    arr[10 + i] |= 1 << (j + 10);
                    aCnt++;
                }

            }
        }
        st = new StringTokenizer(br.readLine());
        int H2 = Integer.parseInt(st.nextToken());
        int W2 = Integer.parseInt(st.nextToken());
        brr = new int[H2];
        for (int i = 0; i < H2; i++) {
            String s = br.readLine();
            for (int j = 0; j < W2; j++) {
                String ss = s.substring(j, j + 1);
                if (ss.equals("O")) {
                    brr[i] |= 1 << j;
                }
            }
        }
        int ans = (int) 1e9;
        for (int i = 0; i < 20; i++) {      // startY 좌표
            for (int j = 0; j < 20; j++) {  // startX 좌표
                int res = aCnt;
                for (int k = 0; k < H2; k++) {
                    for (int l = 0; l < W2; l++) {
                        if ((arr[i + k] & 1 << (j + l)) != 0 && (brr[k] & 1 << l) != 0) {
                            res--;
                        }
                    }
                }
                ans = Math.min(ans, res);
            }
        }
        System.out.println(ans);
    }
}
