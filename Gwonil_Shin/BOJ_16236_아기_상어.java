import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[][]arr,mv={{1,0},{0,-1},{0,1},{-1,0}};
    static boolean[][] visited;
    static int shark_size=2,shark_eat=0,answer=0,n;
    static Point shark;

    static boolean problem(){
        visited=new boolean[n][n];
        Deque<Point> dq=new ArrayDeque<>();
        List<Point> eatable=new ArrayList<>();
        dq.add(shark);
        visited[shark.x][shark.y]=true;
        while(!dq.isEmpty()){
            Point cur=dq.pollFirst();

            for(int[] next:mv){
                int nx=cur.x+next[0];
                int ny=cur.y+next[1];

                if(nx<0||nx==n||ny<0||ny==n||visited[nx][ny]||arr[nx][ny]>shark_size){
                    continue;
                }

                visited[nx][ny]=true;
                if(arr[nx][ny]==0|| arr[nx][ny]==shark_size){
                    dq.addLast(new Point(nx,ny, cur.level+1));
                }
                else{
                    if(!eatable.isEmpty()&&eatable.get(0).level<cur.level+1){
                        continue;
                    }
                    eatable.add(new Point(nx,ny,cur.level+1));
                }
            }
        }


        if(!eatable.isEmpty()){
            Collections.sort(eatable);
            Point fish=eatable.get(0);
            arr[fish.x][fish.y]=0;
            answer+=fish.level;
            shark_eat+=1;
            if(shark_size==shark_eat){
                shark_size+=1;
                shark_eat=0;
            }
            arr[shark.x][shark.y]=0;
            shark.x=fish.x;
            shark.y=fish.y;

            return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        arr=new int[n][n];

        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
                if(arr[i][j]==9){
                    shark=new Point(i,j,0);
                }
            }
        }

        while(true){
            if(problem()){
                break;
            }
        }

        System.out.println(answer);
    }

    static class Point implements Comparable<Point>{
        int x,y,level;
        public Point(int x,int y,int level){
            this.x=x;
            this.y=y;
            this.level=level;
        }

        @Override
        public int compareTo(Point o){
            if(this.x==o.x){
                return this.y-o.y;
            }
            return this.x-o.x;
        }
    }
}
