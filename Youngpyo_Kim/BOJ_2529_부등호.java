import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2529_부등호 {
    static int k;
    static char[] question = new char[10];
    static int number[] = {0,1,2,3,4,5,6,7,8,9};
    static ArrayList<String> ans = new ArrayList<>(100);

    static boolean check(int charidx, int curN, int prevN){
        if(question[charidx] == '<')
            return prevN < curN;
        else
            return prevN > curN;
    }

    static void bt(int charidx, int prev, String cur, boolean[] visited){
        if(charidx == k){
            ans.add(cur);
            return;
        }

        for(int i = 0; i< 10; i++){
            if(!visited[i] && check(charidx, i, prev)){
                visited[i] = true;
                bt(charidx + 1, i, cur+i, visited);
                visited[i] = false;
            }
        }

        return;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(in.readLine());
        
        StringTokenizer st = new StringTokenizer(in.readLine());

        for(int i = 0; i < k; i++){
            question[i] = st.nextToken().charAt(0);
        }
        
        for(int i = 0; i < 10; i++){
            boolean[] visited = new boolean[10];
            visited[i] = true;
            bt(0, i, Integer.toString(i), visited);
        }

        Collections.sort(ans);
        System.out.println(ans.get(ans.size()-1));
        System.out.println(ans.get(0));
    }
}