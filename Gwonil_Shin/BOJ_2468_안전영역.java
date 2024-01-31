import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static int n,arr[][],mv[][]={{1,0},{-1,0},{0,1},{0,-1}};
    public static boolean visited[][];
    public static class Point{
        int x,y;

        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int max=0,min=101,answer=1;

        n=Integer.parseInt(br.readLine());
        arr=new int[n][n];

        for(int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                max=Math.max(max,arr[i][j]);
                min=Math.min(min,arr[i][j]);
            }
        }

        for(int level=min;level<max;level++){
            visited=new boolean[n][n];
            int cur=0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(arr[i][j]>level&&!visited[i][j]){
                        Deque<Point> dq=new ArrayDeque<>();
                        dq.add(new Point(i,j));
                        visited[i][j]=true;

                        while(!dq.isEmpty()) {
                            Point p = dq.poll();

                            for (int[] next : mv) {
                                int nx = p.x + next[0];
                                int ny = p.y + next[1];

                                if (nx < 0 || nx == n || ny < 0 || ny == n || arr[nx][ny] <= level || visited[nx][ny]) {
                                    continue;
                                }

                                visited[nx][ny] = true;
                                dq.add(new Point(nx, ny));
                            }
                        }
                        cur+=1;
                    }
                }
            }
            answer=Math.max(answer,cur);
        }

        System.out.println(answer);
    }
}
