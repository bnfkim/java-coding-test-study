package 문제풀이;

import java.io.*;
import java.util.*;

public class BOJ_1911 {

    static class Pool implements Comparable<Pool> {
        int start, end;
        public Pool(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pool p) {
            return this.start - p.start;
        }
    }
    static int N, L;
    static ArrayList<Pool> poolList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //널빤지 갯수
        L = Integer.parseInt(st.nextToken()); //널빤지 길이
        poolList = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            poolList.add(new Pool(start, end));
        }

        Collections.sort(poolList)
        System.out.println(getAnswer());
    }

    private static int getAnswer() {
        int answer = 0;
        int fill = 0;

        for(Pool pool : poolList) {
            if(fill > pool.end) continue; //이미 pool 까지 채워진 경우
            if(fill < pool.start) fill = pool.start;

            int cnt = (pool.end - fill) / L;
            int remain = (pool.end - fill) % L;

            if(remain == 0) { //딱 맞으면
                fill = pool.end;
                answer += cnt;
            } else {
                fill += L * (cnt+1);
                answer += (cnt+1);
            }
        }
        return answer;
    }
}
