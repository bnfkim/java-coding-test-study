import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;


class Main
{
    static int[][] field;
    static int N,M,blank,result=Integer.MAX_VALUE;
    static List<int[]> virus;
    static int[][]mv= {{1,0},{-1,0},{0,1},{0,-1}};
    static int[][] selected;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        field=new int[N][N];
        virus=new ArrayList<>(10);
        selected=new int[M][2];

        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                field[i][j]=Integer.parseInt(st.nextToken());

                if(field[i][j]==0) {
                    blank++;
                }
                else if(field[i][j]==2) {
                    virus.add(new int[] {i,j});
                }
            }
        }

        find(0,0);

        System.out.println(result==Integer.MAX_VALUE?-1:result);
    }


    static void find(int idx,int cnt) {
        if(cnt==M) {
            result=Math.min(result, check());
            return;
        }

        for(int i=idx,size=virus.size();i<size;i++) {
            selected[cnt]=virus.get(i);
            find(i+1,cnt+1);
        }
    }

    static int check() {
        boolean [][]visited=new boolean[N][N];
        Deque<int[]> dq=new ArrayDeque<>(M*4);

        for(int [] v:selected) {
            dq.add(v);
            visited[v[0]][v[1]]=true;
        }

        int turn=0;
        int changeBlank=0;

        while(!dq.isEmpty()&&changeBlank!=blank) {
            int size=dq.size();

            for(int i=0;i<size;i++) {
                int[] cur=dq.poll();

                for(int[] next:mv) {
                    int nx=cur[0]+next[0];
                    int ny=cur[1]+next[1];

                    if(nx<0||nx==N||ny<0||ny==N) continue;

                    if(visited[nx][ny]||field[nx][ny]==1) continue;

                    if(field[nx][ny]==0) {
                        changeBlank++;
                    }


                    dq.add(new int[] {nx,ny});
                    visited[nx][ny]=true;

                }
            }
            turn++;
        }

        return changeBlank==blank?turn:Integer.MAX_VALUE;
    }
}