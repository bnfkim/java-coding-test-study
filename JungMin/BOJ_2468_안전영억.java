package doit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 안전영역_2468 {

    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int maxNum=Integer.MIN_VALUE;
    static int[] dx ={0,0,-1,1};
    static int[] dy ={1,-1,0,0};
    public static void main(String[] args)
    {


        Scanner sc= new Scanner(System.in);

        N=sc.nextInt();
        arr=new int[N][N];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                arr[i][j]=sc.nextInt();

            }
        }
        while(true)
        {
            find(); // 안전영역 개수 찾기 
            rain(); // 1씩 비옴

            boolean flag=false;

            for(int i=0;i<N;i++)
            {
                for(int j=0;j<N;j++)
                {

                    if(arr[i][j]!=0)
                    {
                        flag=true;
                    }
                }
            }
            // 모두다 잠길때까지 비가오면 루프종료
            if(flag==false)
            { 
                System.out.println(maxNum);
                break;
            }
        }
    }
    public static void rain()
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                arr[i][j]-=1;
                if(arr[i][j]<=0)
                {
                    arr[i][j]=0;
                }
            }
        }
    }
    public static void find()
    {
        int cnt=0;
        visited= new boolean[N][N];

        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {   
                // 잠기지않았고 방문하지 않은곳이라면 
                if(arr[i][j]>0 && visited[i][j]==false)

                {
                    // 안전마을 무리 +1 
                    cnt+=1;
                    Queue<int[]> q= new LinkedList<int[]>();
                    q.add(new int[]{i,j});

                    visited[i][j]=true;

                    while(!q.isEmpty())
                    {
                        int[] now=q.poll();
                        int x=now[0];
                        int y=now[1];

                        for(int idx=0;idx<4;idx++)
                        {
                            int nx=x+dx[idx];
                            int ny=y+dy[idx];

                            if(nx>=0 && nx<N && ny>=0 && ny<N)
                            {
                                if(visited[nx][ny]==false && arr[nx][ny]>0)
                                {
                                    q.add(new int[]{nx,ny});

                                    visited[nx][ny]=true;
                                }
                            }
                        }

                    }

                }
            }
        }
        maxNum=Math.max(cnt,maxNum);

    }

}

