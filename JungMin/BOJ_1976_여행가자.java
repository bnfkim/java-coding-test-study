import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_여행가자_1976 {
    static int N;
    static int M;
    static int[][] board;
    static int[] parents;
    //static  plan;
    public static void main(String[] args) throws IOException {
        //E - A - B - C
        //    D---'

        // E,C,B,C,D

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st ;

        N= Integer.parseInt(bf.readLine());
        M= Integer.parseInt(bf.readLine());

        board= new int[N][N];
        for(int i=0;i<N;i++)
        {
            st= new StringTokenizer(bf.readLine());
            for(int j=0;j<N;j++)
            {
                board[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        parents =new int[N];

        make();

        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(i!=j&& board[i][j]==1)
                {
                    union(i,j);
                }
            }
        }

        boolean flag = true;
        String str= bf.readLine();
        String[] s = str.split(" ");
        //System.out.println(Arrays.toString(s));

        int[] plan = new int[s.length];
        //System.out.println(s.length);
        for(int i=0;i<s.length;i++)
        {
            plan[i]=Integer.parseInt(s[i])-1;
        }

        for(int i=0;i<s.length-1;i++)
        {
            if (parents[plan[i]]!=parents[plan[i+1]])
            {
                flag=false;
            }
        }
        if (flag) {
            System.out.println("YES");
        }
        else
        {
            System.out.println("NO");
        }

    }
    public static void make()
    {
        for(int i=0;i<N;i++)
        {
            parents[i]=i;
        }
    }
    public static int find(int x)
    {
        if (x==parents[x])
            return x;
        return parents[x]=find(parents[x]);
    }
    public static void union(int x,int y)
    {
        int aRoot= find(x);
        int bRoot= find(y);



        if(aRoot>bRoot)
        {
            parents[aRoot]=bRoot;
        }
        else
        {
            parents[bRoot]=aRoot;
        }

    }


}

