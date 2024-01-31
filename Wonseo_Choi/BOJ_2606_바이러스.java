import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class BOJ_2606_바이러스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int num = Integer.parseInt(br.readLine());
        int g_n = Integer.parseInt(br.readLine());
        
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= num; i++ ) {
            graph.add(new ArrayList<>());
        }
        
        int n1, n2;
        for (int i = 0; i < g_n; i++ ) {
            String[] nv = br.readLine().split(" ");
            n1 = Integer.parseInt(nv[0]);
            n2 = Integer.parseInt(nv[1]);            
            
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
        
        int ans = 0, st;
        Stack<Integer> stack = new Stack<>();
        Boolean[] visited = new Boolean[num];
        Arrays.fill(visited, false);
        stack.push(1);
        while (!stack.isEmpty()) {
            st = stack.pop();
            if (visited[st-1] == true) continue;
            else {
                ans++;
                visited[st-1] = true;
                for (int stt : graph.get(st)) {
                    stack.push(stt);
                }
            }
        }
        ans--;
        System.out.println(ans);
    }
}
