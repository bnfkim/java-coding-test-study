
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 미세먼지안녕17144 {
    static int R,C,T;
    static int rx,ry;
    static int[][] board;
    static int[][] airMachine;
    static int[] dx= {0,-1,0,1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(bf.readLine());

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        T=Integer.parseInt(st.nextToken());

        board=new int[R][C];
        airMachine =new int [2][2];


        boolean f=false;
        for(int i=0;i<R;i++)
        {
            st=new StringTokenizer(bf.readLine());
            for(int j=0;j<C;j++)
            {
                board[i][j]=Integer.parseInt(st.nextToken());
                if(!f && board[i][j]==-1)
                {
                    airMachine[0][0]=i;
                    airMachine[0][1]=j;
                    f=true;
                }
                else if (f &&board[i][j]==-1)
                {
                    airMachine[1][0]=i;
                    airMachine[1][1]=j;
                }
            }
        }

        int time=0;
        //spread();
        //print(board);
        while(time<T)
        {
            time++;
            spread();

            On();


        }

        int answer=0;
        for(int i=0;i<R;i++)
        {
            for(int j=0;j<C;j++)
            {
                if(board[i][j]==-1) continue;;
                answer+=board[i][j];
            }
        }

        System.out.println(answer);
    }
    public static void spread()
    {
        int[][] temp= new int[R][C];


        for(int i=0;i<R;i++)
        {
            for(int j=0;j<C;j++)
            {
                int spreadCnt=0;
                if(board[i][j]>=1)
                {

                    for(int idx =0;idx<4;idx++)
                    {
                        int nx=i+dx[idx];
                        int ny=j+dy[idx];

                        if(isBoundary(nx, ny) && board[nx][ny]!=-1)
                        {
                            temp[nx][ny]+=board[i][j]/5;
                            spreadCnt++;
                        }
                    }

                    temp[i][j]+= board[i][j]- (board[i][j]/5)*spreadCnt;

                }
            }
        }

        //	print(temp);
        //System.out.println();
        for(int i=0;i<R;i++)
        {
            for(int j=0;j<C;j++)
            {
                if(board[i][j]==-1) continue;
                board[i][j]=temp[i][j];
            }
        }

    }

    public static void On()
    {

        int[] now = airMachine[0];
        

        for(int i=now[0]-1;i>0;i--)
        {
            board[i][0]=board[i-1][0];
        }

        for(int i=0;i<C-1;i++)

        {
            board[0][i]=board[0][i+1];

        }

        for(int i=0;i<now[0];i++)
        {
            board[i][C-1]=board[i+1][C-1];
        }

        for(int i=C-1;i>1;i--)
        {
            board[now[0]][i]=board[now[0]][i-1];
        }
        board[now[0]][1]=0;


        now = airMachine[1];



        for (int i = now[0] + 1; i < R - 1; i++) {
            board[i][0] = board[i+1][0];
        }

        for (int i = 0; i < C - 1; i++) {
            board[R-1][i] = board[R-1][i+1];
        }

        for (int i = R - 1; i > now[0]; i--) {
            board[i][C-1] = board[i-1][C-1];
        }

        for (int i = C - 1; i > 1; i--) {
            board[now[0]][i] = board[now[0]][i-1];
        }

        board[now[0]][1] = 0;

    }

    public static boolean isBoundary(int x,int y)
    {

        return x>=0 && x<R && y>=0 && y<C;
    }
    public static void print(int[][] a)
    {
        for(int[] b : a)
        {
            System.out.println(Arrays.toString(b));
        }
    }
}
