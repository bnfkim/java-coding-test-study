import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N,result=Integer.MAX_VALUE;
    static int[][]arr;
    static int[][]mv={{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N=Integer.parseInt(br.readLine());

        arr=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int num=-1;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(arr[i][j]==1){
                    fill(i,j,num--);
                }
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(arr[i][j]==1){
                    fill(i,j,num--);
                }
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N-1;j++){
                if(arr[i][j]+arr[i][j+1]!=2*arr[i][j]){
                    int finder=(arr[i][j]==0)?find(i,j,arr[i][j+1]):find(i,j+1,arr[i][j]);
                    result=Math.min(result,finder);
                }
            }
        }

        for(int i=0;i<N-1;i++){
            for(int j=0;j<N;j++){
                if(arr[i][j]+arr[i+1][j]!=2*arr[i][j]){
                    int finder=(arr[i][j]==0)?find(i,j,arr[i+1][j]):find(i+1,j,arr[i][j]);
                    result=Math.min(result,finder);
                }
            }
        }

        System.out.println(result);
    }

    static void fill(int x,int y,int num){
        Deque<int[]> dq=new ArrayDeque<>();
        dq.add(new int[]{x,y});
        arr[x][y]=num;
        while(!dq.isEmpty()){
            int[]cur=dq.poll();

            for(int[] next:mv){
                int nx=cur[0]+next[0];
                int ny=cur[1]+next[1];

                if(nx<0||nx==N||ny<0||ny==N) continue;

                if(arr[nx][ny]!=1) continue;

                arr[nx][ny]=num;
                dq.add(new int[]{nx,ny});
            }
        }
    }

    static int find(int x,int y,int num){
        boolean[][]visited=new boolean[N][N];
        Deque<int[]> dq=new ArrayDeque<>();
        dq.add(new int[]{x,y});
        visited[x][y]=true;

        int level=0;
        while(!dq.isEmpty()){
            int size=dq.size();

            for(int i=0;i<size;i++){
                int[] cur=dq.poll();

                for(int[] next:mv){
                    int nx=cur[0]+next[0];
                    int ny=cur[1]+next[1];

                    if(nx<0||nx==N||ny<0||ny==N) continue;

                    if(visited[nx][ny]) continue;

                    if(arr[nx][ny]!=0){
                        if(arr[nx][ny]!=num){
                            return level+1;
                        }
                    }
                    else{
                        dq.add(new int[]{nx,ny});
                        visited[nx][ny]=true;
                    }

                }
            }

            if(++level>=result) break;
        }

        return Integer.MAX_VALUE;
    }
}