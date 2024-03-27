import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1063_킹 {
    final static int BOARD_SIZE = 8;

    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder("");
            str.append((char) (y + 'A'));
            str.append(8 - x);
            return  str.toString();
        }
    }

    static Pos king, stone;
    static Pos[] dir = {new Pos(0, 1), new Pos(0, -1), new Pos(1, 0), new Pos(-1, 0),
            new Pos(-1, 1), new Pos(-1, -1), new Pos(1, 1), new Pos(1, -1)};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String kPos = st.nextToken();
        int kingY = kPos.charAt(0) - 'A';
        int kingX = '8' - (kPos.charAt(1));
        king = new Pos(kingX, kingY);

        String sPos = st.nextToken();
        int stoneY = sPos.charAt(0) - 'A';
        int stoneX = '8' - sPos.charAt(1);
        stone = new Pos(stoneX, stoneY);

        int command = Integer.parseInt(st.nextToken());
        for (int i = 0; i < command; i++) {
            String cmd = br.readLine();
            int dIdx = setDir(cmd);
            move(dIdx);
        }
        System.out.println(king);
        System.out.println(stone);
    }

    private static void move(int dIdx) {
        int kNx = king.x + dir[dIdx].x;
        int kNy = king.y + dir[dIdx].y;

        if (kNx >= 0 && kNx < BOARD_SIZE && kNy >= 0 && kNy < BOARD_SIZE) {
            if (kNx == stone.x && kNy == stone.y) {
                int sNx = stone.x + dir[dIdx].x;
                int sNy = stone.y + dir[dIdx].y;

                if (sNx >= 0 && sNx < BOARD_SIZE && sNy >= 0 && sNy < BOARD_SIZE) {
                    stone.x = sNx;
                    stone.y = sNy;
                } else {
                    return;
                }
            }
            king.x = kNx;
            king.y = kNy;
        }
    }

    private static int setDir(String cmd) {
        switch (cmd) {
            case "R":
                return 0;
            case "L":
                return 1;
            case "B":
                return 2;
            case "T":
                return 3;
            case "RT":
                return 4;
            case "LT":
                return 5;
            case "RB":
                return 6;
            case "LB":
                return 7;
        }
        return -1;
    }
}
