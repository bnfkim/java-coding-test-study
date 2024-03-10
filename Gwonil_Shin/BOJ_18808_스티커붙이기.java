import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,M,K;
    static boolean[][]notebook;
    static int[][]mv={{-1,0},{1,0},{0,-1},{0,1}};
    static int result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        notebook=new boolean[N][M];

        for(int k=0;k<K;k++){
            st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());

            List<int[]> sticker=new ArrayList<>(n*m);
            for(int i=0;i<n;i++){
                st=new StringTokenizer(br.readLine());
                for(int j=0;j<m;j++){
                    if(Integer.parseInt(st.nextToken())==1){
                        sticker.add(new int[]{i,j});
                    }

                }
            }

            if(stick(n,m,sticker)){
                result+=sticker.size();
            }

        }
        System.out.println(result);
    }

    static boolean stick(int n,int m,List<int[]> sticker){
        int r=n;
        int c=m;

        for(int cnt=0;cnt<4;cnt++){//회전
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(check(i,j,sticker)){
                        return true;
                    }
                }
            }

            if(cnt==3){
                break;
            }

            //회전
            sticker=rotate(sticker,r);

            //값도 바꿔줌
            int tmp=r;
            r=c;
            c=tmp;
        }


        return false;
    }

    static boolean check(int x,int y,List<int[]> sticker){
        next:
        for(int[] s:sticker){
            //s는 기준점에 놓일 곳
            for(int []p:sticker) {
                int diffX=p[0]-s[0];
                int diffY=p[1]-s[1];

                int px = x + diffX;
                int py = y + diffY;

                if (px < 0 || px >= N || py < 0 || py >= M)
                    continue next;

                if (notebook[px][py])
                    continue next;
            }

            //채우기
            for(int []p:sticker) {
                int diffX=p[0]-s[0];
                int diffY=p[1]-s[1];

                int px = x + diffX;
                int py = y + diffY;

                notebook[px][py]=true;
            }

            return true;
        }

        return false;
    }

    static List<int[]> rotate(List<int[]> sticker,int r){
        List<int[]> nSticker=new ArrayList<>(sticker.size());

        for(int[] p:sticker){
            nSticker.add(new int[]{p[1],r-1-p[0]});
        }


        return nSticker;
    }
}