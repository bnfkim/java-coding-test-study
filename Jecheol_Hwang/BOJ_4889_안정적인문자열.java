package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4889_안정적인문자열 {
    public static void main(String[] args) throws IOException {
        /*
        * x : 안정적인 문자열을 만들기 위한 '최소' 연산의 수
        *
        * 1. 빈 문자열은 안정적이다.
        * 2. S가 안정적이면 {S}도 안정적이다.
        * 3. S가 안정적이고 T가 안정적이면, S.concat(T)도 안정적이다.
        *
        * stack을 이용하여 {에 대하여는 추가하고, }에 대하여는 poll 해서 비교, 만약 문제가 있으면 res++;
        * */

        Queue<String> stack = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = 1;
        while (true) {
            String s = br.readLine().trim();
            if (s.contains("-")) {
                break;
            }

            int l = s.length();
            int res = 0;
            for (int i = 0; i < l; i++) {
                String ss = s.substring(i, i + 1);
                if (stack.isEmpty() && ss.equals("}")) {
                    res++;
                    stack.add("{");
                } else if (!stack.isEmpty() && ss.equals("}")) {
                    stack.poll();
                } else if (ss.equals("{")) {
                    stack.add(ss);
                }
            }

            while (!stack.isEmpty()) {
                stack.poll();
                stack.poll();
                res++;
            }

            System.out.printf("%d. %d\n", tc++, res);
        }


    }
}