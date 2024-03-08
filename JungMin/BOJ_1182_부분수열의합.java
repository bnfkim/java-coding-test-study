import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /*  5 0
        -7 -3 -2 5 8
    * */
    static int N;
    static int S;
    static int answer;
    static int[] num;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(bf.readLine());


        N=Integer.parseInt(st.nextToken());
        S= Integer.parseInt(st.nextToken());

        num= new int[N];

        st= new StringTokenizer(bf.readLine());

        for(int i=0;i<N;i++)
        {
            num[i]=Integer.parseInt(st.nextToken());
        }
        isVisited = new boolean[N];


    generate(0);
        if (S == 0) { // S가 0일 때 result--
            answer--;// --;
        }

        System.out.println(answer);

    }
    public static void generate(int cnt )
    {
        if(cnt==N)
        {
            int temp=0;
            for(int i=0;i<N;i++){
                if(isVisited[i])
                {
               //     System.out.print(num[i]+" ");
                    temp+=num[i];
                }

            }
         //   System.out.println(temp);
            if(temp==S)
            {
                answer++;
            }        //System.out.println();
            return;
        }

        isVisited[cnt]=true;
        generate(cnt+1);
        isVisited[cnt]=false;
        generate(cnt+1);
    }
    private static void generateSubset(int cnt) {
        if(cnt == N) {

            int temp=0;
            for(int i=0;i<N;i++){
                if(isVisited[i])
                {
                    //     System.out.print(num[i]+" ");
                    temp+=num[i];
                }

            }
            //   System.out.println(temp);
            if(temp==S)
            {
                answer++;
            }        //System.out.println();
            return;

        }
        // 부분집합에 현재 원소를 선택
        isVisited[cnt] = true;
        generateSubset(cnt+1);
        // 부분집합에 현재 원소를 비선택
        isVisited[cnt] = false;
        generateSubset(cnt+1);
    }


}
