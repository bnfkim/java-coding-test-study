import java.io.*;
import java.util.*;

public class BOJ_18808 {
    static int N, M, K, R, C;
    static int[][] laptop, sticker;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        init();
        solve();
    }

    private static void init() throws IOException {
        //노트북 크기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        laptop = new int[N][M];
    }

    private static void input() throws IOException {
        //스티커 크기
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        sticker = new int[R][C];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                sticker[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    public static void solve() throws IOException {
        for(int i = 0; i < K; i++) {
            input();
            simulate();
        }
        count();
    }

    public static void simulate() {
        for(int rotateCnt = 0; rotateCnt < 4; rotateCnt++) {
            if(find()) return;
            rotate();
        }
    }

    public static boolean find() {
        for(int i = 0; i <= N - R; i++) {
            for(int j = 0; j <= M - C; j++) {
                if(isValid(i, j)) {
                    attach(i, j);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValid(int r, int c) {
        for(int i = r; i < r + R; i++) {
            for(int j = c; j < c + C; j++) {
                if(laptop[i][j] == 1 && sticker[i - r][j - c] == 1) return false;
            }
        }
        return true;
    }

    public static void rotate() {
        int tmp = R;
        R = C;
        C = tmp;

        int[][] stickerRotated = new int[R][C];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                stickerRotated[i][j] = sticker[C - j - 1][i];
            }
        }
        sticker = stickerRotated;
    }

    public static void attach(int r, int c) {
        for(int i = r; i < r + R; i++) {
            for(int j = c; j < c + C; j++) {
                if(laptop[i][j] == 0 && sticker[i - r][j - c] == 1) {
                    laptop[i][j] = sticker[i - r][j - c];
                }
            }
        }
    }

    public static void count() {
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(laptop[i][j] == 1) cnt++;
            }
        }
        System.out.println(cnt);
    }
}
