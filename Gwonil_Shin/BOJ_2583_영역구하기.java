import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N,M,K;
    static boolean[][] field;
    static int[][] mv= {{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        field=new boolean[N][M];
        for(int i=0;i<K;i++) {
            st=new StringTokenizer(br.readLine());
            int y1=Integer.parseInt(st.nextToken());
            int x1=Integer.parseInt(st.nextToken());
            int y2=Integer.parseInt(st.nextToken());
            int x2=Integer.parseInt(st.nextToken());

            for(int x=x1;x<x2;x++) {
                for(int y=y1;y<y2;y++) {
                    field[x][y]=true;
                }
            }
        }

        List<Integer> answer=new ArrayList<>();

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(!field[i][j]) answer.add(bfs(i,j));
            }
        }

        Collections.sort(answer);
        sb.append(answer.size()).append('\n');

        for(int x:answer) {
            sb.append(x).append(" ");
        }

        System.out.println(sb);
    }


    static int bfs(int x,int y) {
        Queue<int[]> q=new ArrayDeque<>();

        q.add(new int[] {x,y});
        field[x][y]=true;
        int amount=1;

        while(!q.isEmpty()) {
            int[] cur=q.poll();

            for(int []next:mv) {
                int nx=cur[0]+next[0];
                int ny=cur[1]+next[1];

                if(nx<0||nx==N||ny<0||ny==M) continue;

                if(field[nx][ny]) continue;

                q.add(new int[] {nx,ny});
                field[nx][ny]=true;
                amount++;
            }
        }

        return amount;
    }
}
