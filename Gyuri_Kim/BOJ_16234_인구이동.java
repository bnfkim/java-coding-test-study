import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16234_인구이동 {

    static int N,L,R, cnt;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean isMove;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //입력받기
        N = Integer.parseInt(st.nextToken()); //땅 크기
        L = Integer.parseInt(st.nextToken()); //인구차이 이상
        R = Integer.parseInt(st.nextToken()); //인구차이 이하
        map = new int[N][N];
        visit = new boolean[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
        System.out.println(cnt);
    }
    static void solve() {
        while (true) {
            isMove = false;
            visit = new boolean[N][N]; //새로 시작할 때 마다 방문 초기화

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(visit[i][j]) continue;
                    bfs(i, j);
                }
            }
            if(!isMove) break; //국경이동이 없으면 종료
            else cnt++;
        }
    }
    public static void bfs(int y, int x) {
        ArrayList<int[]> positions = new ArrayList<>();
        Queue<int[]>  queue = new LinkedList<>();

        queue.add(new int[]{y,x});
        positions.add(new int[]{y, x});
        visit[y][x] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            y = pos[0];
            x = pos[1];

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                if(visit[ny][nx]) continue;
                if(Math.abs(map[y][x] - map[ny][nx]) < L || Math.abs(map[y][x] - map[ny][nx]) > R) continue;

                queue.add(new int[]{ny, nx});
                positions.add(new int[]{ny, nx});
                visit[ny][nx] = true;
                isMove = true;

            }
        }
        move(calAvg(positions), positions);
    }
    public static int calAvg(ArrayList<int[]> positions) {
        int sum = 0;
        for(int[] pos : positions) {
            sum += map[pos[0]][pos[1]];
        }
        return sum/positions.size();
    }
    public static void move(int avg, ArrayList<int[]> positions) {
        for(int[] pos : positions) {
            map[pos[0]][pos[1]] = avg;
        }
    }
}