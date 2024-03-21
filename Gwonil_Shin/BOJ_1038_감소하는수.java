import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Long> arr=new ArrayList<>(100000);
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());

        dfs(0,9);
        Collections.sort(arr);

        if(arr.size()<=N){
            System.out.println(-1);
        }
        else {
            System.out.println(arr.get(N));
        }
    }

    static void dfs(long value,int cur){
        for(int i=cur;i>=0;i--){
            arr.add(value*10+i);
            dfs(value*10+i,i-1);
        }
    }
}