package Baekjoon;

import java.io.*;
import java.util.*;

/*
시작 시간 : 24-02-24
종료 시간 : 24-02-24
실행 시간 : 2324ms
메 모 리 : 329520KB
*/

public class BOJ_1647_도시분할계획_G4 {

    static class Edge implements Comparable<Edge>{
        int x,y,weight;

        public Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "x=" + x +
                    ", y=" + y +
                    ", weight=" + weight +
                    '}';
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static Edge[] edges;
    static int[] parents;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        for (int i = 1; i <=N ; i++) {
            parents[i]=i;
        }
        edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(a, b, w);
        }
    }


    private static int find(int v) {
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        if (aRoot < bRoot) {
            parents[aRoot] = bRoot;
        }else{
            parents[bRoot] = aRoot;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        input();

        Arrays.sort(edges);

        int largestVal = 0;
        int cnt = 0;
        int sum = 0;
        for (Edge edge : edges) {
            if(!union(edge.x, edge.y)) continue;

            sum += edge.weight;
            largestVal = edge.weight;

            if(++cnt == N-1) break;
        }

        sum -= largestVal;
        bw.write(sum+"");
        bw.close();
        br.close();
    }
}
