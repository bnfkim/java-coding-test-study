import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 9 30 start
// 9 43 finish
public class 뿌요뿌요 {
    static char [][] arr = new char[12][6];
    static Queue <Point> q = new LinkedList<>();
    static boolean [][] visited;
    static ArrayList<Point> pList;
    static Boolean isclean;
    static int [][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<12; i++) {
            String str = br.readLine();
            for(int j=0; j<6; j++) {
                arr[i][j] = str.charAt(j);
            }
        }
        int count = 0;
        while(true) {
            visited = new boolean[12][6];
            isclean = false;
            for (int i = 0; i< 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (arr[i][j] != '.' && !visited[i][j]) {
                        pList = new ArrayList<>();
                        pList.add(new Point(i, j,arr[i][j]));
                        visited[i][j] = true;
                        q.add(new Point(i, j,arr[i][j]));
                        bfs(arr[i][j]);
                    }
                }
            }
            clean();
            if (!isclean) break;
            count++;
        }
        System.out.println(count);
    }
    static void bfs (char c) {
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int [] d : directions) {
                int dx = p.x + d[0];
                int dy = p.y + d[1];
                if (0<=dx && dx < 12 && 0<=dy && dy < 6 && arr[dx][dy] == c && !visited[dx][dy]){
                    visited[dx][dy] = true;
                    pList.add(new Point(dx,dy,arr[dx][dy]));
                    q.add(new Point(dx,dy,arr[dx][dy]));
                }
            }
        }
        if (pList.size() >= 4) {
            for (int i = 0; i < pList.size(); i++) {
                Point p = pList.get(i);
                arr[p.x][p.y] = '.';
                isclean = true;
            }
        }

    }

    static void clean () {
        for (int j = 0; j < 6; j++) {
            Queue<Point> pq = new LinkedList<>();
            int idx = 11;

            for (int i = 11; i >= 0; i--) {
                if (arr[i][j] != '.') {
                    pq.add(new Point(i, j, arr[i][j]));
                    arr[i][j] = '.';
                }
            }

            while (!pq.isEmpty()) {
                Point p = pq.poll();
                arr[idx][j] = p.c;
                idx--;
            }

        }
    }
    static class Point {
        int x;
        int y;
        char c;
        Point (int x, int y,char c){
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
}
