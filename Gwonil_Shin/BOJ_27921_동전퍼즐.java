import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static List<Point> from=new ArrayList<>();
    static Set<Point> to=new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int h=Integer.parseInt(st.nextToken());
        int w=Integer.parseInt(st.nextToken());
        int coins=0;

        for(int i=0;i<h;i++) {
            String input=br.readLine();
            for(int j=0;j<w;j++) {
                if(input.charAt(j)=='O') {
                    from.add(new Point(i,j));
                    coins++;
                }
            }
        }

        st=new StringTokenizer(br.readLine());
        h=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());
        boolean [][]board=new boolean[h][w];

        for(int i=0;i<h;i++) {
            String input=br.readLine();
            for(int j=0;j<w;j++) {
                if(input.charAt(j)=='O') {
                    to.add(new Point(i,j));
                    board[i][j]=true;
                }
            }
        }

        if(from.size()==0) {//동전이 없는 경우
            System.out.println(coins);
        }
        else {
            int answer=0;
            for(Point from_start:from) {
                int max=0;

                for(Point to_start:to) { //시작점을 세팅
                    int cnt=0;

                    for(Point next:from) {
                        int dx=next.x-from_start.x;
                        int dy=next.y-from_start.y;

                        int nx=to_start.x+dx;
                        int ny=to_start.y+dy;


                        if(nx<0||nx>=h||ny<0||ny>=w||!board[nx][ny]) continue;

                        cnt++;
                    }

                    max=Math.max(max, cnt);
                }
                answer=Math.max(answer, max);
            }
            System.out.println(coins-answer);
        }

    }

    static class Point{
        public int x,y;

        public Point(int x,int y) {
            this.x=x;
            this.y=y;
        }
    }
}
