import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {

    static int N;
    static boolean[] check;
    static int[][] board;
    static StringTokenizer st;

    static int MIN_RESULT = Integer.MAX_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        check = new boolean[N+1];
        board = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 1);
        System.out.println(MIN_RESULT);
    }

    static void combination(int dep, int start) {
        if(dep == N/2) {
            MIN_RESULT = Math.min(MIN_RESULT, getResult());
            return;
        }

        for(int i=start; i<=N; i++) {

            check[i] = true;
            combination(dep+1, i+1);
            check[i] = false;
        }
    }

    static int getResult() {
        int start = 0;
        int link = 0;

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i == j) continue;

                if(check[i] && check[j]) start += board[i][j];
                if(!check[i] && !check[j]) link += board[i][j];
            }
        }
        return Math.abs(start - link);
    }
}