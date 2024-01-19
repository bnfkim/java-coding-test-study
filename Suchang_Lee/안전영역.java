import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    // 많은 비가 내렸을 때 물에 잠기지 않는 안전한 최대 영역
    // 비의 양에 따라 일정한 높이 이하의 모든 지점은 물에 잠김
    // 높이정보 2차원 배열
    static int [][] arr;
    static int N;
    static int count;
    static int [][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static Queue<Point> queue = new LinkedList<>();
    public static void bfs(int x, int y, int[][] a) {
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int[] direction : directions) {
                int dx = p.x + direction[0];
                int dy = p.y + direction[1];
                if (0<=dx&&dx<N&&0<=dy&&dy<N&&a[dx][dy]!=0) {
                    queue.add(new Point(dx,dy));
                    a[dx][dy] = 0;
                }
            }
        }
    }

    public static class Point{
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int [][] makeArray(int a) {
        int [][] newArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] > a) newArr[i][j] = arr[i][j];
                else newArr[i][j] = 0;
            }
        }
        return newArr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N][N];
        int m = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int x = sc.nextInt();
                arr[i][j] = x;
                m = Math.max(m, x);
            }
        }

        for (int k = 0; k < m; k++) {
            int [][] a = makeArray(k);
            int c = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (a[i][j] != 0) {
                        a[i][j] = 0;
                        c += 1;
                        queue.add(new Point(i,j));
                        bfs(i,j,a);
                    }
                }
            }
            count = Math.max(count, c);
        }

        System.out.println(count);
    }

}
