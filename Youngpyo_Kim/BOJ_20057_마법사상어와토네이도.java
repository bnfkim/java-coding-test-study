import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20057_마법사상어와토네이도 {
    static int n, ans;
    static int[][] board = new int[501][501];

    // 방향은 서 남 동 북
    static int[][] delta = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static int[][][] sand_dir = {
        {{-1, 0, 1}, {1, 0, 1}, {-1, -1, 7}, {1, -1, 7}, {-1, -2, 10}, {1, -2, 10}, {-2, -1, 2}, {2, -1, 2}, {0, -3, 5}, {0, -2, 0}},
        {{0, -1, 1}, {0, 1, 1}, {1, -1, 7}, {1, 1, 7}, {2, -1, 10}, {2, 1, 10}, {1, -2, 2}, {1, 2, 2}, {3, 0, 5}, {2, 0, 0}},
        {{-1, 0, 1}, {1, 0, 1}, {-1, 1, 7}, {1, 1, 7}, {-1, 2, 10}, {1, 2, 10}, {-2, 1, 2}, {2, 1, 2}, {0, 3, 5}, {0, 2, 0}},
        {{0, -1, 1}, {0, 1, 1}, {-1, -1, 7}, {-1, 1, 7}, {-2, -1, 10}, {-2, 1, 10}, {-1, -2, 2}, {-1, 2, 2}, {-3, 0, 5}, {-2, 0, 0}}};
    
    static void calc(int r, int c, int dir) {
        if(r == 0 && c == 0) return;
        int target_r = r + delta[dir][0], target_c = c + delta[dir][1];
        int total_sand = board[target_r][target_c];
        int total_distributed = 0;
        board[target_r][target_c] = 0;
    
        for (int i = 0; i < 10; i++) {
            int nr = r + sand_dir[dir][i][0];
            int nc = c + sand_dir[dir][i][1];
            int amount = (total_sand * sand_dir[dir][i][2]) / 100;
            total_distributed += amount;
    
            if (i < 9) {
                if (nr < 0 || nr >= n || nc < 0 || nc >= n)
                    ans += amount;
                else
                    board[nr][nc] += amount;
            } else {
                if (nr < 0 || nr >= n || nc < 0 || nc >= n)
                    ans += total_sand - total_distributed;
                else
                    board[nr][nc] += total_sand - total_distributed;
            }
        }
    }
        
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //초기값, 방향은 서쪽시작
        int r = n/2, c = n/2, dir = 0, cnt = 1;

        while(true){
            // 방향을 따라 두 번 간 후에 가야하는 길이가 늘어남, 근데 이렇게 하면 마지막 한줄이 남아서 따로 처리해야함.
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < cnt; j++)
                {
                    calc(r, c, dir);
                    r += delta[dir][0];
                    c += delta[dir][1];
                }
                dir = (dir + 1) % 4;
            }

            cnt++;

            // 마지막 한줄
            if (cnt == n)
            {
                for (int j = 0; j < cnt; j++)
                {
                    calc(r, c, dir);
                    r += delta[dir][0];
                    c += delta[dir][1];
                }
                break;
            }
        }

        System.out.println(ans);
    }
}
