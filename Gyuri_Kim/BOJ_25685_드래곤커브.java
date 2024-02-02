import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_25685_드래곤커브 {
    static int N;
    static ArrayList<Integer> dotList;
    static boolean[][] checked;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        checked = new boolean[101][101];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            getDotList(d, g);
            checkDot(x, y);
        }
        System.out.println(getDragonCurve());
    }
    static void getDotList(int d, int g) {
        int startGeneration = 1;

        dotList = new ArrayList<>(); //[1] [2]
        dotList.add(d);

        while(startGeneration <= g) {

            for(int i=dotList.size()-1; i>=0; i--) {
                int nextDirection = dotList.get(i) + 1;
                if(nextDirection == 4) nextDirection = 0;

                dotList.add(nextDirection);
            }
            startGeneration++;
        }
    }

    static void checkDot(int x, int y) {
        checked[y][x] = true;

        for(int dot : dotList) {
            int nx = x + dx[dot];
            int ny = y + dy[dot];

            if(outOfRange(nx, ny)) continue;

            checked[ny][nx] = true;
            x = nx;
            y = ny;
        }
    }

    static int getDragonCurve() {
        int result = 0;

        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                int cnt = 0;
                //네 꼭짓점 탐색
                if(checked[i][j]) cnt+= 1;
                if(checked[i+1][j]) cnt+= 1;
                if(checked[i][j+1]) cnt+= 1;
                if(checked[i+1][j+1]) cnt+= 1;

                if(cnt==4) result++;
            }
        }
        return result;
    }

    static boolean outOfRange(int x, int y) {
        return x<0 || y<0 || x>100 || y>100;
    }
}
