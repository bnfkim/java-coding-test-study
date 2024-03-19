import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,K,result=Integer.MAX_VALUE;
    static int[][]arr;
    static int[][] rotate_op;
    static int[] sequence;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        rotate_op=new int[K][3];
        sequence=new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rotate_op[i][0]=Integer.parseInt(st.nextToken()) - 1;
            rotate_op[i][1]=Integer.parseInt(st.nextToken()) - 1;
            rotate_op[i][2]=Integer.parseInt(st.nextToken());
        }

        dfs(0,0);

        System.out.println(result);
    }

    static void dfs(int depth,int bit){
        if(depth==K){
            rotate_all();
            return;
        }

        for(int i=0;i<K;i++){
            if((bit&1<<i)!=0) continue;
            sequence[depth]=i;
            dfs(depth+1,bit|1<<i);
        }
    }

    static void rotate_all(){
        int[][] t_arr=new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                t_arr[i][j]=arr[i][j];
            }
        }

        for(int idx:sequence){
            rotate(t_arr,rotate_op[idx][0],rotate_op[idx][1],rotate_op[idx][2]);
        }

        result=Math.min(result,calc(t_arr));
    }


    static void rotate(int[][] arr,int r,int c,int s){
        for(int i=s;i>0;i--){
            rotate_each(arr,r-i,c-i,i*2);
        }
    }

    static void rotate_each(int[][]arr,int r,int s,int size){
        int tmp=arr[r][s];
        //왼
        for(int i=0;i<size;i++){
            arr[r+i][s]=arr[r+i+1][s];
        }
        //하
        for(int i=0;i<size;i++){
            arr[r+size][s+i]=arr[r+size][s+i+1];
        }
        //우
        for(int i=0;i<size;i++){
            arr[r+size-i][s+size]=arr[r+size-i-1][s+size];
        }
        //상
        for(int i=0;i<size;i++){
            arr[r][s+size-i]=arr[r][s+size-i-1];
        }
        //빈곳 채우기
        arr[r][s+1]=tmp;
    }

    static int calc(int[][]arr){
        int result=Integer.MAX_VALUE;

        for(int i=0;i<N;i++){
            int sum=0;
            for(int j=0;j<M;j++){
                sum+=arr[i][j];
            }

            result=Math.min(result,sum);
        }

        return result;
    }
}