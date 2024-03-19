import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,K;
    static Set<Integer> multiTap;
    static Map<Integer,Deque<Integer>> types;
    static int[] sequence;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        multiTap=new HashSet<>(N);
        types=new HashMap<>(K);
        sequence=new int[K];

        st=new StringTokenizer(br.readLine());

        for(int i=0;i<K;i++){
            int cur=Integer.parseInt(st.nextToken());
            Deque<Integer> seq=types.getOrDefault(cur,new ArrayDeque<>());
            seq.add(i);
            types.put(cur,seq);
            sequence[i]=cur;
        }
        int result=0;
        for(int x:sequence){
            if(!multiTap.contains(x)){
                if(multiTap.size()==N){
                    //우선순위 비교
                    List<Integer> current_multiTap=new ArrayList<>(multiTap);
                    current_multiTap.sort((o1,o2)->{
                        if (types.get(o1).isEmpty()) {
                            return -1;
                        }
                        else if(types.get(o2).isEmpty()){
                            return 1;
                        }
                        else{
                            int a=types.get(o1).peek();
                            int b=types.get(o2).peek();

                            if(a>b){
                                return -1;
                            }
                            return 1;
                        }


                    });

                    int rv=current_multiTap.get(0);
                    multiTap.remove(rv);

                    result++;
                }
                multiTap.add(x);
            }

            types.get(x).poll();
        }

        System.out.println(result);

    }
}