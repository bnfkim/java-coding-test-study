import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_25685_드래곤커브 {
    static int N;
    static ArrayList<Integer> directions;
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

            getDirections(d, g);
            setDragonCurve(x, y);
        }
        System.out.println(getDragonCurve());
    }
    static void getDirections(int d, int g) {
        directions = new ArrayList<>();
        directions.add(d);

        for (int k=0; k<g; k++) {
            for(int i=directions.size()-1; i>=0; i--) {
                directions.add((directions.get(i) + 1) % 4);
            }
        }
    }

    static void setDragonCurve(int x, int y) {
        checked[y][x] = true;

        for(int dot : directions) {
            int nx = x + dx[dot];
            int ny = y + dy[dot];

            if(outOfRange(nx, ny)) continue;

            checked[ny][nx] = true;
            x = nx;
            y = ny;
        }
    }

    static int getDragonCurve() {
        int cnt = 0;
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(checked[i][j] && checked[i+1][j] && checked[i][j+1] && checked[i+1][j+1]) cnt++;
            }
        }
        return cnt;
    }

    static boolean outOfRange(int x, int y) {
        return x<0 || y<0 || x>100 || y>100;
    }
}
