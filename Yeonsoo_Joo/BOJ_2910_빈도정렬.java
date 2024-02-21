package Yeonsoo_Joo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2910_빈도정렬 {

    /*
     * 수행 시간: 156 ms
     * 메모리:  14644 KB
     * 푼 방법: LinkedHashMap 사용
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        LinkedHashMap<Integer, Integer> lhs = new LinkedHashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(st.nextToken());
            lhs.put(k, lhs.getOrDefault(k, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        List<Integer> keySet = new ArrayList<>(lhs.keySet());

        keySet.sort((o1, o2) -> lhs.get(o2).compareTo(lhs.get(o1)));

        for(Integer key : keySet) {
            for (int i = 0; i < lhs.get(key); i++) {
                sb.append(key + " ");
            }
        }
        System.out.println(sb);
    }

}

