import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


public class Main {
    static class Point{
        int x, y;
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    public static int n,l,r,arr[][],mv[][]={{-1,0},{1,0},{0,-1},{0,1}};
    public static boolean visited[][];
    public static Deque<Point> lands=new ArrayDeque<>();

    public static int find(Point p){
        Deque<Point> dq=new ArrayDeque<>();
        dq.add(p);
        lands.add(p);
        visited[p.x][p.y]=true;
        int sum=0;

        while(!dq.isEmpty()) {
            Point cur=dq.poll();
            sum+=arr[cur.x][cur.y];
            for (int[] next : mv) {
                int nx = cur.x + next[0];
                int ny = cur.y + next[1];

                if (nx < 0 || nx == n || ny < 0 || ny == n || visited[nx][ny]) {
                    continue;
                }
                int diff = Math.abs(arr[cur.x][cur.y] - arr[nx][ny]);
                if (diff < l || diff > r) {
                    continue;
                }

                visited[nx][ny] = true;
                Point np=new Point(nx,ny);
                lands.add(np);
                dq.add(np);
            }
        }

        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        l=Integer.parseInt(st.nextToken());
        r=Integer.parseInt(st.nextToken());
        int answer=0;

        arr=new int [n][n];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            boolean find=false;
            visited=new boolean[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(!visited[i][j]) {
                        int sum=find(new Point(i,j));
                        int div=sum/lands.size();

                        if(arr[i][j]<sum){
                            find=true;
                        }

                        while(!lands.isEmpty()){
                            Point cur=lands.poll();
                            arr[cur.x][cur.y]=div;
                        }
                    }
                }
            }


            if(!find){
                break;
            }
            answer+=1;
        }

        System.out.println(answer);
    }
}
