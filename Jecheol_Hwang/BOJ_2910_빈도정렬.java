package 알고리즘연습.boj;

import java.io.*;
import java.util.*;

public class BOJ_2910_빈도정렬 {
    /**
     * 로직 : 빈도수랑 등장순서 저장해두고 해당 순서대로 정렬하는 자료구조 사용
     *
     * time complexity : O(N * logN); 힙 연산을 n번 수행
     * 수행 시간 : 180 ms
     * 메모리 : 14996 KB
     */

    static HashMap<Integer, Integer> freqMap = new HashMap<>(); // 빈도수 체크 <number, freq>
    static HashMap<Integer, Integer> orderMap = new HashMap<>(); // 등장순서 체크 <number, order>

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        // 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            freqMap.putIfAbsent(num, 0);
            freqMap.put(num, freqMap.get(num) + 1);
            orderMap.putIfAbsent(num, i);
        }

        // 출력
        for (Integer num : orderMap.keySet()) {
            Integer freq = freqMap.get(num);
            Integer order = orderMap.get(num);
            pq.add(new Pair(freq, order, num));
        }

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int freq = p.freq;
            int num = p.num;
            for (int i = 0; i < freq; i++) {
                System.out.print(num + " ");
            }
        }

        br.close();
    }

    public static class Pair implements Comparable<Pair> {
        int freq;
        int order;
        int num;

        private Pair(int freq, int order, int num) {
            this.freq = freq;
            this.order = order;
            this.num = num;
        }

        @Override
        public int compareTo(Pair pair) {
            if (this.freq == pair.freq) {
                return this.order - pair.order; // 등장순 오름차순
            }
            return pair.freq - this.freq; // 빈도수 내림차순
        }

    }
}
