package Yeonsoo_Joo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class SWEA_1288_새로운_불면증_치료법 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            int N = Integer.parseInt(br.readLine());
            Set<Character> set = new HashSet<>();
            int iter = 0;
            String tmp = null;
            while (set.size() < 10) {
                iter++;
                tmp = Integer.toString(N * iter);
                for (int i = 0; i < tmp.length(); i++) {
                    if (!set.contains(tmp.charAt(i))) {
                        set.add(tmp.charAt(i));
                    }
                }
            }
            System.out.printf("#%d ", tc);
            System.out.println(tmp);
        }
    }
}