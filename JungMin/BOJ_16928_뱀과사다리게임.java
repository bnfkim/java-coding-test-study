import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀과사다리게임 {
    static int N,M;
    static int[] arr;
    static int[][] ladder;
    static int[][] snake;
    static int answer= Integer.MAX_VALUE;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;


        st= new StringTokenizer(bf.readLine());


        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());

        ladder = new int[N][2];
        snake= new int[M][2];
        arr = new int[101];
        visited = new boolean[101];
        for(int i=0;i<N;i++)
        {st= new StringTokenizer(bf.readLine());
            int x= Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladder[i][0]=x;
            ladder[i][1]=y;
        }
        for(int i=0;i<M;i++)
        {st= new StringTokenizer(bf.readLine());
            int x= Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            snake[i][0]=x;
            snake[i][1]=y;
        }

        bfs();
        System.out.println(answer);

    }

    public static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        visited=new boolean[101];
        q.add(new int[]{1,0});
        visited[1]=true;

        while(!q.isEmpty())
        {
            int[] current= q.poll();

            int currentPos= current[0];
            int diceCount= current[1];

            if(currentPos==100)
            {
                //System.out.println("@@@");
                answer=Math.min(answer,diceCount);
            }



            for(int i=1;i<=6;i++)
            {
                int nx = currentPos+i;

                // is it ladder?
                for(int x =0;x<N;x++) {
                    if (nx == ladder[x][0]) {
                        nx = ladder[x][1];
                    }
                }
                // is it snake?
                for(int x=0;x<M;x++) {
                    if (nx==snake[x][0]) {
                        nx = snake[x][1];
                    }
                }

                if(nx<=100 && !visited[nx]) {

                    q.add(new int[]{nx, diceCount + 1});
                    visited[nx] = true;
                 }
                }
            }
        }
    }


