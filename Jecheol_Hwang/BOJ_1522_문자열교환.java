package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1522_문자열교환 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        char[] charArray = s.toCharArray();
        int length = s.length();

        int totalA = 0;
        for (char c : charArray) {
            if (c == 'a') {
                totalA++;
            }
        }

        int ans = (int) 1e9;
        for (int i = 0; i < length; i++) {
            int bCnt = 0;
            for (int j = 0; j < totalA; j++) {
                int jdx = (i + j) % length;
                if (charArray[jdx] == 'b') {
                    bCnt++;
                }
            }
            ans = Math.min(ans, bCnt);
        }
        System.out.println(ans);
    }
}