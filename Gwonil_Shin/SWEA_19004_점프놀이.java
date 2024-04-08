import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution
{
    static int N,K;
    static List<int[]>[] numbers;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());

        next:
        for(int test_case = 1; test_case <= T; test_case++)
        {
            st=new StringTokenizer(br.readLine());
            N=Integer.parseInt(st.nextToken());
            K=Integer.parseInt(st.nextToken());

            numbers=new List[K+1];
            for(int i=1;i<=K;i++){
                numbers[i]=new ArrayList<>();
            }


            for(int i=0;i<N;i++){
                st=new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    int num=Integer.parseInt(st.nextToken());
                    if(num==1){
                        numbers[num].add(new int[]{i,j,0});
                    }
                    else{
                        numbers[num].add(new int[]{i,j,N*N*N});
                    }
                }
            }

            for(int i=1;i<=K;i++){
                if(numbers[i].isEmpty()){
                    sb.append("#").append(test_case).append(" ").append(-1).append('\n');
                    continue next;
                }
            }

            for(int i=2;i<=K;i++){
                for(int[] cur:numbers[i]){
                    for(int[] prev:numbers[i-1]){
                        cur[2]=Math.min(cur[2],Math.abs(cur[0]-prev[0])+Math.abs(cur[1]-prev[1])+prev[2]);
                    }
                }
            }

            int result=Integer.MAX_VALUE;
            for(int[] last:numbers[K]){
                result=Math.min(last[2],result);
            }

            sb.append("#").append(test_case).append(" ").append(result).append('\n');
        }

        System.out.println(sb);
    }
}