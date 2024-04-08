import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N,M;
    static int[][]arr;
    static int[][]mv={{1,0},{-1,0},{0,1},{0,-1}};
    static Map<Integer,Integer> group=new HashMap<>();
    static int keyIndex=-1;

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        arr=new int [N][M];

        for(int i=0;i<N;i++){
            char[] input=br.readLine().toCharArray();

            for(int j=0;j<M;j++){
                arr[i][j]=input[j]-'0';
            }
        }

        //fill
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(arr[i][j]==0){
                    fill(i,j);
                }
            }
        }



        //check
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(arr[i][j]==1){
                    int sum=0;
                    Set<Integer> s=new HashSet<>();
                    for(int[]next:mv){
                        int nx=i+next[0];
                        int ny=j+next[1];

                        if(nx<0||nx==N||ny<0||ny==M) continue;

                        if(arr[nx][ny]>0) continue;

                        s.add(arr[nx][ny]);
                    }

                    for(int x: s){
                        sum+=group.get(x);
                        sum%=10;
                    }

                    sb.append((sum+1)%10);
                }
                else{
                    sb.append(0);
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static void fill(int x,int y){
        Set<Point> visited=new HashSet<>();
        Deque<Point> dq=new ArrayDeque<>();
        dq.add(new Point(x,y));
        visited.add(new Point(x,y));


        while(!dq.isEmpty()){
            Point cur=dq.poll();

            for(int[]next:mv){
                int nx=cur.x+next[0];
                int ny=cur.y+next[1];

                if(nx<0||nx==N||ny<0||ny==M) continue;

                Point nPoint=new Point(nx,ny);

                if(visited.contains(nPoint) || arr[nx][ny]!=0) continue;
                dq.add(nPoint);
                visited.add(nPoint);
            }
        }

        for(Point p:visited){
            arr[p.x][p.y]=keyIndex;
        }

        group.put(keyIndex--,visited.size());


    }

    static class Point{
        int x,y;

        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals(Object obj) {
            Point o=(Point) obj;

            return x==o.x&&y==o.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


}