import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2615_오목 {
    //9:30 시작
    public static int[][] board;
    //오른쪽,아래쪽,오른쪽아래 대각선, 오른쪽 위쪽 대각선
    public static int[] dx = {1,0,1,1};
    public static int[] dy = {0,1,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //검은 바둑알은 1, 흰 바둑알은 2, 알이 놓이지 않는 자리는 0
        board = new int[19][19];

        //바둑판 입력받기
        for(int i=0; i<19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<19; i++) {
            for(int j=0; j<19; j++) {
                if(board[i][j] == 0) continue;

                if(checkWinner(j,i)) {
                    System.out.println(board[i][j]);
                    System.out.println((i+1) + " " + (j+1));
                    return;
                }
            }
        }
        System.out.println(0);
    }
    public static boolean checkWinner(int x, int y) {
        //가로,세로,왼쪽 대각선,오른쪽 대각선 총 4가지 경우 탐색
        return checkRight(x, y) || checkBottom(x, y) || checkDiagonalTop(x, y) || checkDiagonalBottom(x,y);
    }
    //오른쪽
    public static boolean checkRight(int x, int y){
        //직전 오목이 같은 색이거나, 5개를 둘 수 없는 경우 false
        if((x-1>=0 && board[y][x] == board[y][x - 1]) || x+4 >= 19) return false;

        for(int i=1; i<5; i++) {
            if(board[y][x] != board[y][x+i]) return false; //연속되지 않으면 아웃
        }
        if(x+5 < 19 && board[y][x] == board[y][x+5]) return false;
        return true; //6개 이상 연결이면 아웃
    }
    //아래
    public static boolean checkBottom(int x, int y){
        if((y-1>=0 && board[y][x] == board[y-1][x]) || y+4 >= 19) return false;

        for(int i=1; i<5; i++) {
            if(board[y][x] != board[y+i][x]) return false; //연속되지 않으면 아웃
        }
        //6개 연속인지 체크
        if(y+5<19 && board[y][x] == board[y+5][x]) return false;
        return true; //6개 이상 연결이면 아웃
    }
    //아래 대각선
    public static boolean checkDiagonalBottom(int x, int y){
        if(x+4>=19 || y+4>=19) return false;
        if(x-1>=0 && y-1>=0 && board[y][x] == board[y-1][x-1]) return false;
        for(int i=1; i<5; i++) {
            if(board[y][x] != board[y+i][x+i]) return false; //연속되지 않으면 아웃
        }
        if(x+5<19 && y+5<19 && board[y][x] == board[y+5][x+5]) {
            return false;
        }
        return true;
    }
    //위 대각선
    public static boolean checkDiagonalTop(int x, int y) {
        if(x+4>=19 || y-4<0) return false;
        if(y+1<19 && x-1>=0  && board[y][x] == board[y+1][x-1]) return false;

        for (int i = 1; i < 5; i++) {
            if (board[y][x] != board[y - i][x + i]) return false; //연속되지 않으면 아웃
        }
        if (x+5<19 && y-5>=0 && board[y][x] == board[y-5][x+5]) return false;
        return true;
    }
}

