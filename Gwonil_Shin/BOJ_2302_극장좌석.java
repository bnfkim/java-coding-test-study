import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,result;
    static Set<Integer> fix=new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        M=Integer.parseInt(br.readLine());


        for(int i=0;i<M;i++){
            int x=Integer.parseInt(br.readLine());
            fix.add(x);
        }

        find(1);

        System.out.println(result);
    }

    static void find(int idx){
        if(idx==N+1){
            result++;
            return;
        }

        if(fix.contains(idx)){
            find(idx+1);
            return;
        }

        find(idx+1);

        if(idx<N&&!fix.contains(idx+1)) {
            find(idx + 2);
        }
    }
}