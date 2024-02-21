import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이만들기2630
{
    static int N;
    static int[][] arr;
    static boolean[][] v;
    static int white;
    static int black;
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N= Integer.parseInt(bf.readLine());

        StringTokenizer st;
        arr=new int[N][N];
        v=new boolean[N][N];
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(bf.readLine());
            for(int j=0;j<N;j++)
            {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        divide(0,0,N);
        System.out.println(white);
        System.out.println(black);
        }

        public static void divide(int x,int y,int n)
        {
            boolean flag= true;
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(arr[x][y]!=arr[x+i][y+j])
                    {
                        flag=false;
                        break;
                    }
                    if(flag==false)
                    {
                        break;
                    }
                }
            }
            if(flag)
            {
                if(arr[x][y]==0) white++;
                else black++;
            }
            기
            {
                divide(x+n/2,y+n/2,n/2);
                divide(x+n/2,y,n/2);
                divide(x,y+n/2,n/2);
                divide(x,y,n/2);

            }
        }
    }

// 2630, 116 실버2 색종이만들
