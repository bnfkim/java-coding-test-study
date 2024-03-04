import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N,M,result,cur_rainbow;
    static int[][] board;
    static int[][] mv={{1,0},{0,-1},{-1,0},{0,1}};

    static Set<Point> alreadyCheck;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        board=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                board[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        Point find;
        int gravity_dir=0;
        while((find=get_groupPoint(gravity_dir))!=null){
            //블록 제거 및 점수 추가
            result+=remove(find,board[find.x][find.y]);

            //중력
            gravity(gravity_dir++);

            if(gravity_dir==4){
                gravity_dir=0;
            }
            //중력
            gravity(gravity_dir);
        }

        System.out.println(result);
    }

    static void gravity(int dir){
        switch(dir){
            case 0:
                down();
                break;
            case 1:
                left();
                break;
            case 2:
                up();
                break;
            case 3:
                right();
                break;
        }
    }

    static void swap(int fx,int fy,int tx,int ty){
        int tmp=board[fx][fy];
        board[fx][fy]=-2;
        board[tx][ty]=tmp;
    }

    static void down(){
        for(int i=N-1;i>=0;i--){
            for(int j=0;j<N;j++){
                if(board[i][j]==-2){
                    for(int x=i-1;x>=0;x--){
                        if(board[x][j]==-1){
                            break;
                        }
                        else if(board[x][j]>-1){
                            swap(x,j,i,j);
                            break;
                        }
                    }
                }
            }
        }
    }

    static void left(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(board[j][i]==-2){
                    for(int y=i+1;y<N;y++){
                        if(board[j][y]==-1){
                            break;
                        }
                        else if(board[j][y]>-1){
                            swap(j,y,j,i);
                            break;
                        }
                    }
                }
            }
        }
    }

    static void up(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(board[i][j]==-2){
                    for(int x=i+1;x<N;x++){
                        if(board[x][j]==-1){
                            break;
                        }
                        else if(board[x][j]>-1){
                            swap(x,j,i,j);
                            break;
                        }
                    }
                }
            }
        }
    }

    static void right(){
        for(int i=N-1;i>=0;i--){
            for(int j=0;j<N;j++){
                if(board[j][i]==-2){
                    for(int y=i-1;y>=0;y--){
                        if(board[j][y]==-1){
                            break;
                        }
                        else if(board[j][y]>-1){
                            swap(j,y,j,i);
                            break;
                        }
                    }
                }
            }
        }
    }

    static int remove(Point s,int start) {
        Set<Point> visited = new HashSet<>(N * N);
        Deque<Point> q = new ArrayDeque<>();

        q.add(s);
        visited.add(s);
        int size=0;

        while(!q.isEmpty()){
            Point p=q.poll();

            size++;
            board[p.x][p.y]=-2;

            for(int[] next:mv){
                int nx=p.x+next[0];
                int ny=p.y+next[1];

                //범위 초과
                if(nx<0||nx==N||ny<0||ny==N) continue;

                Point np=new Point(nx,ny);
                //방문
                if(visited.contains(np)) continue;

                if(board[nx][ny]==0||board[nx][ny]==start){
                    visited.add(np);
                    q.add(np);
                }
            }
        }



        return size*size;
    }


    static Point get_groupPoint(int dir){
        alreadyCheck=new HashSet<>(N*N);

        Point find=null;
        int size=2;
        int rainbow=0;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int x=i;
                int y=j;

                if(dir==1){
                    x=j;
                    y=N-1-i;
                }
                else if (dir==2){
                    x=N-1-i;
                    y=N-1-j;
                }
                else if(dir==3){
                    x=N-1-j;
                    y=i;
                }

                if(board[x][y]>0){
                    int cur_size=get_group(x,y,board[x][y]);

                    if(cur_size>size){
                        find=new Point(x,y);
                        size=cur_size;
                        rainbow=cur_rainbow;
                    }
                    else if (cur_size==size && rainbow<=cur_rainbow) {
                        find=new Point(x,y);
                        size=cur_size;
                        rainbow=cur_rainbow;
                    }
                }
            }
        }

        return find;
    }

    static int get_group(int x,int y,int start){
        Point s=new Point(x,y);
        if(alreadyCheck.contains(s)) return 0;
        alreadyCheck.add(s);

        int size=1;
        cur_rainbow=0;
        Set<Point> visited=new HashSet<>(N*N);
        Deque<Point> q=new ArrayDeque<>();

        q.add(s);
        visited.add(s);

        while(!q.isEmpty()){
            Point p=q.poll();

            for(int[] next:mv){
                int nx=p.x+next[0];
                int ny=p.y+next[1];

                //범위 초과
                if(nx<0||nx==N||ny<0||ny==N) continue;

                Point np=new Point(nx,ny);
                //방문
                if(alreadyCheck.contains(np)||visited.contains(np)) continue;

                if(board[nx][ny]==0){
                    visited.add(np);
                    q.add(np);
                    size++;
                    cur_rainbow++;
                }
                else if(board[nx][ny]==start){
                    alreadyCheck.add(np);
                    visited.add(np);
                    q.add(np);
                    size++;
                }
            }
        }

        return size;
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
            return Objects.hash(x,y);
        }
    }
}