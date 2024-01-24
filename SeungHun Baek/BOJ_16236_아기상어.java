import java.io.*;
import java.util.*;

public class BOJ_16236_아기상어 {

  static int[] dx = { -1, 0, 0, 1 };
  static int[] dy = { 0, -1, 1, 0 };
  static int[][] arr;
  static int N, MOVE;
  static boolean possible;
  static BabyShark shark;

  static class Point {

    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static class BabyShark {

    int x, y, size, current;

    BabyShark(int x, int y) {
      this.x = x;
      this.y = y;
      this.size = 2;
      this.current = 0;
    }

    public void move() {
      int[][] dist = new int[N][N];

      int MX = Integer.MAX_VALUE;
      int MY = Integer.MAX_VALUE;
      int MDIST = Integer.MAX_VALUE;

      Deque<Point> d = new ArrayDeque<>();
      d.add(new Point(x, y));

      while (!d.isEmpty()) {
        Point p = d.remove();

        for (int idx = 0; idx < 4; idx++) {
          int nx = p.x + dx[idx];
          int ny = p.y + dy[idx];

          if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
          if (dist[nx][ny] >= 1) continue;
          if (arr[nx][ny] > size) continue;

          dist[nx][ny] = dist[p.x][p.y] + 1;
          d.add(new Point(nx, ny));

          if (arr[nx][ny] != 0 && arr[nx][ny] < size) {
            if (MDIST > dist[nx][ny]) {
              MDIST = dist[nx][ny];
              MX = nx;
              MY = ny;
            } else if (MDIST == dist[nx][ny]) {
              if (nx < MX) {
                MX = nx;
                MY = ny;
              } else if (nx == MX) {
                if (ny < MY) {
                  MY = ny;
                }
              }
            }
          }
        }
      }

      if (MDIST == Integer.MAX_VALUE) {
        possible = false;
        return;
      }

      arr[x = MX][y = MY] = 0;
      MOVE += MDIST;

      if (++current == size) {
        size++;
        current = 0;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    arr = new int[N][N];
    possible = true;
    MOVE = 0;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int val = Integer.parseInt(st.nextToken());
        arr[i][j] = val;
        if (val == 9) {
          shark = new BabyShark(i, j);
          arr[i][j] = 0;
        }
      }
    }

    while (possible) shark.move();

    System.out.println(MOVE);
  }
}
