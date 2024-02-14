import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    static int R,C,T;
    static Map<Point,Integer> dusts=new HashMap<>();
    static List<Point> cleaner=new ArrayList<>();
    static int[][]mv= {{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        T=Integer.parseInt(st.nextToken());

        for(int i=0;i<R;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++) {
                int input=Integer.parseInt(st.nextToken());

                if(input==-1) {
                    cleaner.add(new Point(i,j));
                }
                else if(input!=0) {
                    dusts.put(new Point(i,j), input);
                }
            }
        }

        for(int i=0;i<T;i++) {
            spread();
            move();
        }


        int answer=0;
        for(Point p:dusts.keySet()) {
            answer+=dusts.get(p);
        }

        System.out.println(answer);
    }

    static void spread() {
        Map<Point,Integer> nextDusts=new HashMap<>();

        for(Point p:dusts.keySet()) {
            int spreadAmount=dusts.get(p)/5;
            int spreadCnt=0;

            for(int[]next: mv) {
                int nx=p.x+next[0];
                int ny=p.y+next[1];

                Point np=new Point(nx,ny);

                if(nx<0||nx==R||ny<0||ny==C||cleaner.contains(np)) continue;

                if(!nextDusts.containsKey(np)) {
                    nextDusts.put(np, 0);
                }

                nextDusts.put(np, nextDusts.get(np)+spreadAmount);
                spreadCnt++;
            }

            if(!nextDusts.containsKey(p)) {
                nextDusts.put(p, 0);
            }

            nextDusts.put(p, nextDusts.get(p)+dusts.get(p)-spreadAmount*spreadCnt);
        }

        dusts=nextDusts;
    }

    static void move() {
        Map<Point,Integer> nextDusts=new HashMap<>();

        for(Point p:dusts.keySet()) {
            //오른
            if((p.x==cleaner.get(0).x||p.x==cleaner.get(1).x)&&p.y!=C-1) {
                nextDusts.put(new Point(p.x,p.y+1),dusts.get(p));
                continue;
            }
            //위 (오른끝쪽)
            if((p.x<=cleaner.get(0).x && p.x>0) &&p.y==C-1) {
                nextDusts.put(new Point(p.x-1,p.y),dusts.get(p));
                continue;
            }
            //위 (왼쪽 위)
            if(p.x>cleaner.get(1).x&&p.y==0) {
                if(p.x!=cleaner.get(1).x+1) {
                    nextDusts.put(new Point(p.x-1,p.y),dusts.get(p));
                }
                continue;
            }

            //아래 (오른끝)
            if((p.x>=cleaner.get(1).x&&p.x<R-1) &&p.y==C-1) {
                nextDusts.put(new Point(p.x+1,p.y),dusts.get(p));
                continue;
            }
            //아래 (오른끝)
            if(p.x<cleaner.get(0).x &&p.y==0) {
                if(p.x!=cleaner.get(0).x-1) {
                    nextDusts.put(new Point(p.x+1,p.y),dusts.get(p));
                }
                continue;
            }

            //왼
            if((p.x==0||p.x==R-1)&&p.y!=0) {
                nextDusts.put(new Point(p.x,p.y-1),dusts.get(p));
                continue;
            }

            nextDusts.put(p,dusts.get(p));
        }

        dusts=nextDusts;
    }


    static class Point{
        int x,y;

        public Point(int x,int y) {
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals(Object obj) {
            Point other=(Point) obj;

            return x==other.x&&y==other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }

        @Override
        public String toString() {
            return this.x+" "+this.y;
        }
    }
}
