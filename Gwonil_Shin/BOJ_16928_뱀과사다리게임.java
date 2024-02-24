import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static Map<Integer,Integer> graph=new HashMap<>();
    static boolean[]visited=new boolean[101];
    public static void main(String[] args) throws Exception {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        for(int i=0;i<N+M;i++){
            st=new StringTokenizer(br.readLine());
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            graph.put(from,to);
        }

        ArrayDeque<Integer> q=new ArrayDeque<>();
        q.add(1);
        int answer=0;


        end:
        while(!q.isEmpty()){
            int size=q.size();

            for(int i=0;i<size;i++){
                int cur=q.poll();
                if(cur>=94){
                    break end;
                }

                for(int dice=1;dice<=6;dice++){
                    int next=cur+dice;


                    if(graph.containsKey(next)){
                        visited[next]=true;
                        int input=graph.get(next);

                        if(!visited[input]){
                            visited[input]=true;
                            q.offer(input);
                        }
                    }
                    else{
                        if(!visited[next]){
                            visited[next]=true;
                            q.offer(next);
                        }
                    }
                }
            }

            answer++;
        }

        System.out.println(answer+1);
    }
}