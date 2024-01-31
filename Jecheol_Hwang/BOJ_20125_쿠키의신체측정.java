package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20125_쿠키의신체측정 {
    /*
    * 시간 : 400ms
    * 메모리 : 70184KB
    * 로직 : 머리 찾고, 머리 바로 아래를 심장으로 지정 -> 심장 기준으로 재귀로 팔다리몸통 길이측정
    * */
    static int n;
    static String[][] arr;
    static final int Y = 0;
    static final int X = 1;

    static int[] dx = new int[]{-1, 1, 0}; // 좌 우 하
    static int[] dy = new int[]{0, 0, 1};
    static final int LEFT = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static int[] bodies = new int[]{0, 0, 0, 0, 0}; // 왼팔 오른팔 몸통 왼다리 오른다리
    static final int LEFT_ARM = 0;
    static final int RIGHT_ARM = 1;
    static final int TORSO = 2;
    static final int LEFT_LEG = 3;
    static final int RIGHT_LEG = 4;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        int[] heart = null;
        arr = new String[n][n];

        // init array
        for (int y = 0; y < n; y++) {
            String s = br.readLine();
            for (int x = 0; x < n; x++) {
                String target = s.substring(x, x + 1);
                if (heart == null && target.equals("*")) {
                    heart = new int[]{y + 1, x};
                }
                arr[y][x] = target;
            }
        }
        go(heart[Y], heart[X]-1, LEFT_ARM);
        go(heart[Y], heart[X]+1, RIGHT_ARM);
        go(heart[Y], heart[X], TORSO);
        int pelvisY = heart[Y] + bodies[TORSO];
        go(pelvisY, heart[X]-1, LEFT_LEG);
        go(pelvisY, heart[X]+1, RIGHT_LEG);
        System.out.println((heart[Y]+1) + " " + (heart[X]+1));
        bodies[TORSO]--;
        Arrays.stream(bodies).forEach(i -> System.out.print(i + " "));
    }

    private static void go(int y, int x, int part) {
        if (inRange(y, x) && arr[y][x].equals("*")) {
            bodies[part]++;
            int nextY = y + dy[direction(part)];
            int nextX = x + dx[direction(part)];
            go(nextY, nextX, part);
        }
    }

    private static boolean inRange(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }

    private static int direction(int part) {
        switch (part) {
            case LEFT_ARM: {
                return LEFT;
            }
            case RIGHT_ARM: {
                return RIGHT;
            }
            default: {
                return DOWN;
            }
        }
    }

}
