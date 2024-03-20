import java.util.*;
import java.io.*;
public class BOJ_14499_주사위굴리기 {
    static int N, M, X, Y, K;
    static int[][] map;
    static int[] dice;
    static int[] dx = {0, 0, 0, -1, 1}; // 동, 서, 북, 남 방향으로의 x 이동
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //세로
        M = Integer.parseInt(st.nextToken()); //가로
        X = Integer.parseInt(st.nextToken()); //주사위 x
        Y = Integer.parseInt(st.nextToken()); //주사위 y
        K = Integer.parseInt(st.nextToken()); //명령의 개수

        //주사위 구현
        dice = new int[7]; //주사위

        //지도입력받기
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) { //
            int command = Integer.parseInt(st.nextToken());
            moviceDice(command);
        }
    }
    public static void moviceDice(int direction) throws IOException {
        int nx = X + dx[direction];
        int ny = Y + dy[direction];

        //경계처리
        if (outOfRange(nx, ny)) return;

        //이동명령처리
        int[] tmp = Arrays.copyOf(dice, dice.length);
        switch (direction) {
            case 1: //동쪽
                dice[1] = tmp[4];
                dice[4] = tmp[6];
                dice[3] = tmp[1];
                dice[6] = tmp[3];
                break;
            case 2: //서쪽
                dice[1] = tmp[3];
                dice[4] = tmp[1];
                dice[3] = tmp[6];
                dice[6] = tmp[4];
                break;
            case 3: //북쪽
                dice[1] = tmp[5];
                dice[5] = tmp[6];
                dice[6] = tmp[2];
                dice[2] = tmp[1];
                break;
            case 4: //남쪽
                dice[1] = tmp[2];
                dice[5] = tmp[1];
                dice[6] = tmp[5];
                dice[2] = tmp[6];
                break;
        }
        //이동 후 처리
        X = nx;
        Y = ny;

        if(map[X][Y] == 0) map[X][Y] = dice[6];
        else {
            dice[6] = map[X][Y];
            map[X][Y] = 0;
        }

        //윗면 숫자 출력
        System.out.println(dice[1]);
    }

    static private boolean outOfRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
}
