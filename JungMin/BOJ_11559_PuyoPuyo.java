import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뿌요뿌요 {
    static int N=12;
    static int M=6;
    static int[] dx={0,0,-1,1};
    static int[] dy={1,-1,0,0};
    static char[] colors=new char[]{'R','G','B','P','Y'};
    static char[][] field;
    static int e;
    static int answer;
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st;

        field=new char[N][M];
        for(int i=0;i<N;i++)
        {
            String str= bf.readLine();
            for(int j=0;j<M;j++) {
                field[i][j] = str.charAt(j);
            }
        }

        int aa=0;
        while(true)
        {
            boolean isBomb=false;

            // 같은색깔 뿌요들 터트리기
              for(Character c:colors)
              {

                  isBomb=bomb(c);

              }

           // 빈칸내려오게하기

            for(int i=N-2;i>-1;i--)
            {
                for(int j=0;j<M;j++)
                {
                    if(field[i][j]!='.')
                    {

                        int idx=1;
                        char temp=field[i][j];
                        while(true)
                        {
                            if(i+idx>N-1)
                            {
                                break;
                            }
                            if(field[i+idx][j]=='.') {
                                idx++;
                            }

                            else
                            {
                                break;
                            }
                        }
                        field[i][j]='.';
                        //System.out.println(i+" "+idx);
                        field[i+idx-1][j]=temp;
                    }


                }
            }
           //print(field);
           if(e==0)
           {
               break;
           }

           answer+=1;
           e=0;
           // System.out.println(e);//aa++;
            //if(aa==10) break;

        }
        System.out.println(answer);

    }
    public static boolean bomb(char color)
    {
        boolean check= false;

       ArrayList<int []> colorCnt;

        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {
                boolean[][] isVisited= new boolean[N][M];
                 colorCnt= new ArrayList<>();

                if(field[i][j]==color && !isVisited[i][j])
                {
                   // System.out.println(i+" "+j);
                    colorCnt.add(new int[]{i,j});
                    Queue<int []> q = new LinkedList<>();
                    q.add(new int[]{i,j});

                    isVisited[i][j]=true;

                    while(!q.isEmpty())
                    {
                        int[] now = q.poll();
                        for (int idx=0;idx<4;idx++)
                        {
                            int nx=now[0]+dx[idx];
                            int ny=now[1]+dy[idx];

                           // System.out.println(nx+" "+ny);
                            if( isBoundary(nx,ny) && !isVisited[nx][ny] && field[nx][ny]==color)
                            {

                            colorCnt.add(new int[]{nx,ny});
                            isVisited[nx][ny]=true;
                            q.add(new int[]{nx,ny});
                            }
                        }

                    }
//                    for(int ai=0;ai<colorCnt.size();ai++)
//                    {
//                        System.out.print(Arrays.toString(colorCnt.get(ai))+" ");
//                    }
                    //System.out.println();
                    if(colorCnt.size()>=4)
                    {
                        for(int b=0;b<colorCnt.size();b++ )
                        {
                            int[] bombed=colorCnt.get(b);
                            field[bombed[0]][bombed[1]]='.';
                            check=true;
                        }
                        e++;
                    }

                }

            }
        }
        return check;

    }
    public static boolean isBoundary(int x,int y)
    {
        return x>=0 && x<N &&y>=0 && y<M;
    }

    public static void print(char[][] arr)
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }


}

