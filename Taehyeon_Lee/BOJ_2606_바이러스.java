package Baekjoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

// 바이러스 2606 S3
public class Main {
    static int node_num, edge_num;
    static ArrayList<ArrayList<Integer>> edges;
    static boolean[] visited;
    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("input.txt"));

        Scanner sc = new Scanner(System.in);

        node_num = sc.nextInt();
        edge_num = sc.nextInt();

        visited = new boolean[node_num + 1];
        edges = new ArrayList<ArrayList<Integer>>(node_num + 1);
        for(int i = 0; i < node_num + 1; i++) edges.add(new ArrayList<Integer>());

        for(int i = 0; i < edge_num; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        visited[1] = true;
        bfs(1);

        int res = 0;
        for(int i = 0; i < visited.length; i++){
            if(visited[i]) res++;
        }
        System.out.println(res - 1);
    }

    private static void bfs(int node) {
        Queue<Integer> q = new LinkedList<>();

        q.add(node);

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int adj : edges.get(cur)){
                if(!visited[adj]){
                    q.add(adj);
                    visited[adj] = true;
                }
            }
        }
    }
}