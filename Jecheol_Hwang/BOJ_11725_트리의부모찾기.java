package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11725_트리의부모찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Map<Integer, HashSet<Integer>> graphM = new HashMap<>();
        Map<Integer, Integer> parentM = new HashMap<>();
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graphM.putIfAbsent(a, new HashSet<>());
            graphM.putIfAbsent(b, new HashSet<>());
            graphM.get(a).add(b);
            graphM.get(b).add(a);
            parentM.put(a, -1);
            parentM.put(b, -1);
        }
        q.add(1); // root node

        while (!q.isEmpty()) {
            Integer parent = q.poll();
            HashSet<Integer> edges = graphM.get(parent);
            for (Integer e : edges) {
                if (parentM.get(e) == -1) {
                    q.add(e);
                    parentM.put(e, parent);
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            System.out.println(parentM.get(i));
        }
    }
}
