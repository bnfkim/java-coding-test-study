import java.util.*;
import java.io.*;

public class BOJ_2615_오목 {
    static final int N = 19;
    static int arr[][];
    static int[] dx = {0,1,1,-1};
    static int[] dy = {1,0,1,1};
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BOJ_2615_오목.solution();
    }
    
    private static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int pivot = arr[j][i];
                if (pivot == 0) continue;
                if (!check(j, i, pivot)) continue;
                System.out.println(pivot);
                System.out.println((j+1)+" "+(i+1));
                return;
            }
        }
        System.out.println(0);
    }

    private static boolean check(int x, int y, int pivot) {
        for (int idx = 0; idx < 4; idx++) {
            int px = x - dx[idx];
            int py = y - dy[idx];
            if (is_possible(px, py) && arr[px][py] == pivot) continue;
            int s = 1;
            while (true) {
                int nx = x + s * dx[idx];
                int ny = y + s * dy[idx];
                if (!is_possible(nx, ny) || (arr[nx][ny] != pivot)) {
                    if (s == 5) return true;
                    else break;
                } 
                s++;
                if (s > 5) break;
            }
        }
        return false;
    }

    private static boolean is_possible(int x, int y) {
        return (0<=x && x<N) && (0<=y && y<N);
    }
}
