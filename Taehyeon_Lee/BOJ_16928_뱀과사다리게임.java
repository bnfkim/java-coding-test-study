package Baekjoon;

import java.io.*;
import java.util.*;

/*
시작 시간 : 24-02-17 01:15
종료 시간 : 24-02-17 15:30
실행시간 : 164ms / 실패
메 모 리 : 11964KB
*/

public class BOJ_16928_뱀과사다리게임_G5 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, M, ans;
    static boolean[] isVisited = new boolean[101];
    static Map<Integer, Integer> moveTo = new HashMap<>();

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int from, to;
        for (int i = 0; i < N+M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            moveTo.put(from, to);
        }
    }

    private static void bfs(){

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        isVisited[1] = true;

        int now;
        while(!q.isEmpty()){
            ans++;
            int q_size = q.size();
            for(int i = 0; i < q_size; i++){

                now = q.poll();
                if(now == 100) return;
                for(int dice = 1; dice <= 6; dice++){
                    int current = now + dice;

                    if(current > 100) continue;
                    current = moveTo.getOrDefault(current, current);

                    if(isVisited[current]) continue;
                    isVisited[current] = true;

                    if(current == 100) return;
                    q.add(current);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {

        input();
        bfs();
        int s;
        bw.write(ans+"");
        bw.close();
    }
}
