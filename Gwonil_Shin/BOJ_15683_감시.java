import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m,answer,amount,arr[][], mv[][]={{-1,0},{1,0},{0,-1},{0,1}}
            ,tv_move[][][]={
            {{}},
            {{0},{1},{2},{3}},
            {{2,3},{0,1}},
            {{0,3},{1,3},{1,2},{0,2}},
            {{0,2,3},{0,1,3},{1,2,3},{0,1,2}},
            {{0,1,2,3}}
    };
    static List<Point> cctvs=new ArrayList<>();

    static int remain_space(){
        int cur=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]==0){
                    cur+=1;
                }
            }
        }

        return cur;
    }
    static void dfs(int index){
        if(index==amount){
            answer=Math.min(answer,remain_space());
            return;
        }

        Point cctv=cctvs.get(index);
        int op=arr[cctv.x][cctv.y];
        int[][] dir_op=tv_move[op];

        for(int[] time :dir_op) {
            Deque<Point> checker = new LinkedList<>();

            for (int idx : time) {
                for (int i = 1; i < 10; i++) {
                    int nx = cctv.x + mv[idx][0] * i;
                    int ny = cctv.y + mv[idx][1] * i;

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m || arr[nx][ny] == 6) {
                        break;
                    }
                    if (arr[nx][ny] == 0) {
                        arr[nx][ny] = -1;
                        checker.add(new Point(nx, ny));
                    }
                }
            }

            dfs(index + 1);

            for (Point p : checker) {
                arr[p.x][p.y] = 0;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        arr=new int[n][m];
        answer=n*m;

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                int input=Integer.parseInt(st.nextToken());
                if(input>0&&input<6){
                    cctvs.add(new Point(i,j));
                    amount+=1;
                }
                arr[i][j]=input;
            }
        }


        dfs(0);

        System.out.println(answer);

    }

    static class Point{
        public int x,y;

        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
}


