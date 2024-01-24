import java.io.*;
import java.util.*;

class Point {
  int x, y;
  
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class BOJ_16234_인구이동 {

  static int N, L, R, arr[][];
  static int[] dx = { 0, 1, 0, -1 };
  static int[] dy = { 1, 0, -1, 0 };
  static boolean visited[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    arr = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int day = 0;
    while (true) {
      visited = new boolean[N][N];
      int C = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (visited[i][j]) continue;
          C += bfs(i, j);
        }
      }

      if (C == N * N) break;
      day++;
    }

    System.out.println(day);
  }

  private static int bfs(int x, int y) {
    Deque<Point> d = new ArrayDeque<>();
    ArrayList<Point> tmp = new ArrayList<>();
    d.add(new Point(x, y));
    tmp.add(new Point(x, y));
    visited[x][y] = true;

    int sum = arr[x][y];
    int cnt = 1;
    while (!d.isEmpty()) {
      Point p = d.poll();
      int pivot = arr[p.x][p.y];
      for (int i = 0; i < 4; i++) {
        int nx = p.x + dx[i];
        int ny = p.y + dy[i];
        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
        if (visited[nx][ny]) continue;

        int diff = Math.abs(pivot - arr[nx][ny]);
        if (diff < L || diff > R) continue;

        Point now = new Point(nx, ny);
        d.add(now);
        tmp.add(now);
        visited[nx][ny] = true;
        sum += arr[nx][ny];
        cnt++;
      }
    }

    int val = sum / cnt;

    for (Point p : tmp) {
      arr[p.x][p.y] = val;
    }

    return 1;
  }
}
