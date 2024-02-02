import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int r, c,answer;
    static char[][] arr;
    static int[][] mv = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    static void find(int x, int y, int cnt, int bits) {
        for(int[]next:mv) {
            int nx=x+next[0];
            int ny=y+next[1];

            if(nx<0||nx==r||ny<0||ny==c) continue;

            if((bits&1<<arr[nx][ny]-'A')!=0) continue;

            find(nx,ny,cnt+1,bits|1<<arr[nx][ny]-'A');
        }

        if(cnt>answer) {
            answer=cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[r][c];

        for (int i = 0; i < r; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        find(0,0,1,1<<arr[0][0]-'A');

        System.out.println(answer);

    }

}
