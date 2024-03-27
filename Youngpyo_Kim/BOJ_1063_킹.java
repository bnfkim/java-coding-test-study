import java.util.Scanner;

public class Main {
    static int[] dr = {0, 0, -1, 1, 1, 1, -1, -1};
    static int[] dc = {1, -1, 0, 0, 1, -1, 1, -1};
    static int[] king = new int[2];
    static int[] rock = new int[2];
    static int n;

    public static int change(String str) {
        switch (str) {
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
            default:
                return 7;
        }
    }

    public static boolean[] canMove(int dir, int[] check) {
        int nr = check[0] + dr[dir];
        int nc = check[1] + dc[dir];

        boolean isOut = false, isRock = false;
        if (nr < 1 || nr >= 9 || nc < 1 || nc >= 9)
            isOut = true;
        if (nr == rock[0] && nc == rock[1])
            isRock = true;

        return new boolean[]{isOut, isRock};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String tmp = scanner.next();
        king[1] = tmp.charAt(0) - 'A' + 1;
        king[0] = tmp.charAt(1) - '0';
        tmp = scanner.next();
        rock[1] = tmp.charAt(0) - 'A' + 1;
        rock[0] = tmp.charAt(1) - '0';

        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            tmp = scanner.next();
            int dir = change(tmp);
            boolean[] checker = canMove(dir, king);

            if (checker[0])
                continue;

            if (!checker[0] && checker[1]) {
                int nr = rock[0] + dr[dir], nc = rock[1] + dc[dir];
                if (nr < 1 || nr >= 9 || nc < 1 || nc >= 9)
                    continue;

                king[0] += dr[dir];
                king[1] += dc[dir];
                rock[0] += dr[dir];
                rock[1] += dc[dir];
            } else if (!checker[0] && !checker[1]) {
                king[0] += dr[dir];
                king[1] += dc[dir];
            }
        }

        System.out.println((char)(king[1] + 'A' - 1) + "" + king[0]);
        System.out.println((char)(rock[1] + 'A' - 1) + "" + rock[0]);
        scanner.close();
    }
}
