import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int L,C,cur_a,cur_b; //a: 모음 수 b:자음 수
    static List<String> arr=new ArrayList<>();
    static List<String> answer=new ArrayList<>();


    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        L=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());

        for(int i=0;i<C;i++){
            String input=st.nextToken();
            arr.add(input);
        }

        Collections.sort(arr);

        dfs(0,0);
    }

    static void dfs(int level,int index){
        if(level==L){
            if(cur_a>=1 && cur_b>=2) {
                for (String x : answer) {
                    System.out.print(x);
                }
                System.out.println();
            }
            return;
        }

        for(int i=index,size=arr.size();i<size;i++){
            String cur=arr.get(i);
            answer.add(cur);
            boolean check=is_a(cur);
            if(check){
                cur_a+=1;
            }
            else{
                cur_b+=1;
            }
            dfs(level+1,i+1);
            answer.remove(level);
            if(check){
                cur_a-=1;
            }
            else{
                cur_b-=1;
            }
        }
    }

    static boolean is_a(String x){
        return x.equals("a")||x.equals("e")||x.equals("i")||x.equals("o")||x.equals("u");
    }
}
