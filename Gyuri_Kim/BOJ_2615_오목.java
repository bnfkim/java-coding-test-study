import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2615_오목 {
    //9:30 시작
    public static int[][] board;
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
        solve();
    }
    public static void solve() {
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
        //오른쪽, 아래쪽, 아래쪽 대각선, 위쪽 대각선
        int[] dx = {1, 0, 1, 1};
        int[] dy = {0, 1, 1, -1};

        //4방향으로 탐색
        for(int dir = 0; dir < 4; dir++) {
            // (1) 직전이 같은 색인지 탐색 -> 이미 탐색한 경우이므로
            int px = x - dx[dir];
            int py = y - dy[dir];
            if(!isOutOfRange(px, py) && board[y][x] == board[py][px]) {
                continue;
            }
            /**
             * 주의할 점 1
             * 단순히 break나 return false 를 해버리면
             * 이게 네 방향을 체크 해야하는데 다 무시하는게 되어버려서
             * 지금 이 방향은 아니야! 라고 하고 다른 방향으로 넘겨야해서
             * break, false 가 아닌 continue 로 하기
             */

            /**
             * 주의할 점 2
             * 여기부턴 "오"목이 되는지 체크하는 부분
             * 4방향으로 탐색 중인데(1차 for문), 5개 연속으로 붙었는지(2차 for문)
             * 연속으로 안 되거나, 범위를 벗어나면 2차 for문을 벗어나 새로운 방향을
             * 탐색해야되기 때문에 continue가 아닌 break 로 할 것
             */
            int count = 1; //현재 위치 돌부터 체크
            for(int k = 1; k <= 5; k++) {
                int nx = x + dx[dir] * k;
                int ny = y + dy[dir] * k;

                //(2) 범위를 벗어나는 경우 + (3) 오목이 되지 않는 경우 -> 오목체크 메서드 탈출
                if(isOutOfRange(nx, ny) || board[y][x] != board[ny][nx]) {
                    //5개 연속으로 나온 상태로 끝났으면 오목인 것
                    if(count == 5) return true;
                    break;
                }
                count++;
                //(4)육목인 경우
                if(count>5) break;
            }
        }
        //다 탐색 했는데도 true를 못 만나면 아닌 것
        return false;
    }

    //오목판을 벗어나는 경우 체크
    public static boolean isOutOfRange(int x, int y) {
        return x < 0 || y < 0 || x >= 19 || y >= 19;
    }
}

