import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main {

    static int N,M;
    static int[][] board;
    static ArrayList<int []> possible;
    static int[] v;
    static boolean[] isSelected;
    static int[] numbers = new int[3];
    static int answer= Integer.MIN_VALUE;
    static ArrayList<int []> pos= new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        board= new int[N][M];
        possible = new ArrayList<>();
        for(int i=0;i<N;i++)
        // 입력부분
        {
            st=new StringTokenizer(bf.readLine());
            for(int j=0;j<M;j++)
            {
                board[i][j]=Integer.parseInt(st.nextToken());
                if(board[i][j]==0)
                {
                    possible.add(new int[] {i,j});
                }

            }
        }

        //print(board);


        v = new int[possible.size()];
        for(int i=0;i<possible.size();i++)
        {
            v[i]=i;
        }
        isSelected = new boolean[possible.size()];



        permutation(0);

//        for(int i=0;i<possible.size();i++)
//        {
//            System.out.print(Arrays.toString(possible.get(i))+" ");
//        }
//        System.out.println();
        // pos에 다 들어감 이제 체크
        // {[0,1,2],[0,1,3] } 이런식으로
//        for(int i=0;i<pos.size();i++)
//        {
//          //  System.out.print(Arrays.toString(pos.get(i))+" ");
//        }


        // 벽세우기
        for(int k=0;k<pos.size();k++)
        {
            int[] now = pos.get(k);

//            System.out.println(Arrays.toString(now));

            // 매번 벽세울 지도
            int[][] temp_board = new int[N][M];

            // 배열 복제
            for(int a=0;a<N;a++)
            {
                for(int b=0;b<M;b++)
                {
                    temp_board[a][b]=board[a][b];
                }
            }


            // 벽 세움
            //System.out.println(now.length);
            for(int idx=0;idx<now.length;idx++)
            {

                int[] nowPos= possible.get(now[idx]);
               //System.out.print(Arrays.toString(possible.get(now[idx]))+" ");
                temp_board[nowPos[0]][nowPos[1]]=1;
            }
           // print(temp_board);

            int resultNum = check(temp_board);
            //System.out.println(resultNum);
            answer= Math.max(resultNum, answer);
        }
       System.out.println(answer);

    }
    public static int check(int[][] b)
    {
        int[] dx= new int[] {0,0,-1,1};
        int[] dy= new int[] {1,-1,0,0};
        boolean[][] visited= new boolean[N][M];

        for(int x=0;x<N;x++)
        {
            for(int y=0;y<M;y++)
            {
                if(b[x][y]==2 && visited[x][y]==false)
                {

                    Queue<int []> q = new LinkedList<>();
                    q.add(new int[] {x,y});
                    visited[x][y]=true;

                    while(!q.isEmpty())
                    {
                        int[] now = q.poll();

                        for(int i=0;i<4;i++)
                        {
                            int nx=now[0]+dx[i];
                            int ny=now[1]+dy[i];

                            if(isBoundary(nx,ny) && b[nx][ny]==0)
                            {
                                visited[nx][ny]=true;

                                b[nx][ny]=2;
                                q.add(new int[] {nx,ny});

                            }
                        }
                    }


                }
            }
        }

        int sum=0;
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {
                if(b[i][j]==0)
                {
                    sum++;
                }
            }
        }

        //System.out.println(sum);


        //System.out.println("바이러스 후");
        ///print(b);
        //System.out.println(sum);

        return sum;

    }
    public static boolean isBoundary(int x,int y)
    {
        return x>=0 && x<N && y>=0 && y<M;
    }
    public static void permutation(int depth)
    {
        if(depth==3)
        {
            int[] tpos= numbers.clone();
            pos.add(tpos);
            return;
        }

        for(int i=0;i<possible.size();i++)
        {
            if(isSelected[i]) continue;

            isSelected[i]=true;
            numbers[depth]=v[i];
            permutation(depth+1);
            isSelected[i]=false;
        }

    }
public static void print(int[][] printArr)
{ for(int i=0;i<N;i++)
{
    for(int j=0;j<M;j++)
    {
        System.out.print(printArr[i][j]);
    }

    System.out.println();
}

}

}
