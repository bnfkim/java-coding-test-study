
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_14940_쉬운최단거리 {
    static int[][] ans_ar;
    static int[][]  direct = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static Deque<Integer[]> dq = new ArrayDeque<>();
    static int n, m;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        int[][] i_ar = new int[n][m];
        ans_ar = new int[n][m];
        int temp = 0, sx=0, sy=0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                temp = Integer.parseInt(st.nextToken());
                i_ar[i][j] =  temp;
                if (temp == 2) {
                    sx = i;
                    sy = j;
                } else if (temp == 1) {
                    ans_ar[i][j] = -1;
                }
                
            }
        }
        int x=0, y=0,dx=0, dy=0, dist=0;
        dq.add(new Integer[] {sx, sy});
        Integer[] temp1;
        while (!dq.isEmpty()) {
            temp1 = dq.remove();
            x = temp1[0];
            y = temp1[1];
            dist = ans_ar[x][y];
            for (int idx = 0; idx < 4; idx++ ) {
                dx = x + direct[idx][0];
                dy = y + direct[idx][1];
                if (0<=dx && dx<n && 0<=dy && dy<m && ans_ar[dx][dy] == -1) {
                    ans_ar[dx][dy] = dist+1;
                    dq.add(new Integer[] {dx, dy});
                }
            }
        }
        
            
        for (int i = 0; i < n; i++ ) {
            for (int j = 0; j < m; j++ ) {
                System.out.print(ans_ar[i][j] + " ");
            }
            System.out.println();
        }
    }
}