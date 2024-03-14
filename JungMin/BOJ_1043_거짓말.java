import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_거짓말_1043 {

    static int N,M;
    static ArrayList<Integer> knowTrue;
    static ArrayList<Integer>[] partys;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N= Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());



        knowTrue= new ArrayList<>();
        partys=new ArrayList[M];
        isVisited=new boolean[M];


        st= new StringTokenizer(bf.readLine());

        int tn= Integer.parseInt(st.nextToken());

        for(int i=0;i<tn;i++)
        {
            knowTrue.add(Integer.parseInt(st.nextToken()));
        }

        int idx=0;
        for(int i=0;i<M;i++)
        {
            st=new StringTokenizer(bf.readLine());
            ArrayList<Integer> temp =new ArrayList<>();

            tn = Integer.parseInt(st.nextToken());

            for(int j=0;j<tn;j++)
            {
                temp.add(Integer.parseInt(st.nextToken()));
            }

            partys[idx++]=temp;
        }


        while(true)
        {
            boolean isEnd =true;

            // 파티 하나당 다 파악
            ArrayList<Integer> t = new ArrayList<>();

            for(int i=0;i<knowTrue.size();i++)
            {
                t.add(knowTrue.get(i));
            }

            for(int i=0;i<M;i++)
            {
                ArrayList<Integer> tempParty= partys[i];
                boolean f= false;

                for(int a=0;a<tempParty.size();a++)
                {
                    for(int b=0;b<t.size();b++)
                    {
                        if(t.get(b) == tempParty.get(a) && !isVisited[i])
                        {
                            isEnd=false;
                            f=true;
                        }
                    }

                }

                if(f)
                {
                    isVisited[i]=true;

                    for(int aa=0;aa<tempParty.size();aa++)
                    {
                        knowTrue.add(tempParty.get(aa));
                    }
                }

            }
            if(isEnd)
            {
               // System.out.println(Arrays.toString(isVisited));
                break;
            }
        }
        int answer=0;
        for(int z=0;z<isVisited.length;z++)
        {
            if(isVisited[z]==false)
            {
                answer++;
            }
        }
        System.out.println(answer);
    }
}

