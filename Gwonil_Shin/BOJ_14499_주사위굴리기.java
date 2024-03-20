import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,X,Y,K;
    static int[][] field;

    static int[] r=new int[3];
    static int[] c=new int[4];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());
        Y=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        field=new int[N][M];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            for(int j=0;j<M;j++){
                field[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        st=new StringTokenizer(br.readLine());

        for(int i=0;i<K;i++) {
            int op = Integer.parseInt(st.nextToken()) ;
            int result=-1;
            if(op==1){
                result=east();
            }
            else if(op==2){
                result=west();
            }
            else if(op==3){
                result=north();
            }
            else {
                result = south();
            }

            if(result==-1) continue;

            sb.append(result).append('\n');
        }

        System.out.println(sb);
    }

    static boolean oor(int x,int y){
        return x<0||x==N||y<0||y==M;
    }

    static void check(){
        if(field[X][Y]==0){
            field[X][Y]=c[3];
        }
        else{
            c[3]=field[X][Y];
            field[X][Y]=0;
        }
    }

    static int east(){
        if(oor(X,Y+1)) return -1;
        Y++;

        int tmp=r[2];

        int[]nr=new int[3];
        nr[0]=c[3];
        nr[1]=r[0];
        nr[2]=r[1];

        c[1]=nr[1];
        c[3]=tmp;

        r=nr;

        check();

        return r[1];
    }

    static int west(){
        if(oor(X,Y-1)) return -1;
        Y--;

        int tmp=r[0];

        int[]nr=new int[3];

        nr[0]=r[1];
        nr[1]=r[2];
        nr[2]=c[3];

        c[1]=nr[1];
        c[3]=tmp;

        r=nr;


        check();

        return r[1];
    }

    static int north(){
        if(oor(X-1,Y)) return -1;
        X--;

        int[]nc=new int[4];

        nc[0]=c[1];
        nc[1]=c[2];
        nc[2]=c[3];
        nc[3]=c[0];

        r[1]=nc[1];
        c=nc;


        check();

        return r[1];
    }

    static int south(){
        if(oor(X+1,Y)) return -1;
        X++;

        int[]nc=new int[4];

        nc[0]=c[3];
        nc[1]=c[0];
        nc[2]=c[1];
        nc[3]=c[2];

        r[1]=nc[1];
        c=nc;

        check();

        return r[1];
    }
}