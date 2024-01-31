import java.util.*;

public class 바이러스_2606 {
    static int N;
    static int[][] arr;
    static boolean[] v;
    static int cnt=0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        int p=sc.nextInt();

        arr=new int[N+1][N+1];
        v=new boolean[N+1];
        for(int i=1;i<p+1;i++)
        {
            int x= sc.nextInt();
            int y= sc.nextInt();

            arr[x][y]=1;
            arr[y][x]=1;

        }
        bfs(1);
        System.out.println(cnt);
    }
    public static void bfs(int start)
    {

        Queue<Integer> q=new LinkedList();
        q.add(start);

        v[start]=true;

        while(!q.isEmpty())
        {
            int now =q.poll();
            //System.out.println(now);
            for(int i=1;i<N+1;i++)
            {
                if(arr[now][i]==1 && v[i]==false)
                {
                    q.add(i);
                    v[i]=true;
                    cnt++;
                   // System.out.println(cnt);
                }
            }
        }


        // [ [0,1,0] [1,0,1] [0,1,0]

    }

}

