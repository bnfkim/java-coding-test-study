import java.io.*;
import java.util.*;
import java.lang.Math;

class Point {

  int x, y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class BOJ_2468_안전영역 {

  static int N, cnt, M, safe, arr[][], visited[][];
  static StringTokenizer st;
  static int[] dx = { 1, 0, -1, 0 };
  static int[] dy = { 0, 1, 0, -1 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    arr = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int val = Integer.parseInt(st.nextToken());
        arr[i][j] = val;
        M = Math.max(M, val);
      }
    }
    
    /* 비가 c만큼 내림 */
    for (int c = 0; c < M; c++) {
      visited = new int[N][N];
      cnt = 0;

      /*각 블럭을 탐색*/
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (visited[i][j] == 1) continue;
          if (arr[i][j] <= c) continue;
          cnt += bfs(i, j, c);
        }
      }
      safe = Math.max(safe, cnt);
    }

    System.out.println(safe);
  }

  private static int bfs(int x, int y, int floor) {
    Deque<Point> d = new ArrayDeque<>();
    d.add(new Point(x, y));
    visited[x][y] = 1;
    while (!d.isEmpty()) {
      Point p = d.remove();
      int i = p.x;
      int j = p.y;
      for (int idx = 0; idx < 4; idx++) {
        int nx = i + dx[idx];
        int ny = j + dy[idx];
        if (nx < 0 || nx >= N) continue;
        if (ny < 0 || ny >= N) continue;
        if (arr[nx][ny] <= floor) continue;
        if (visited[nx][ny] == 1) continue;
        visited[nx][ny] = 1;
        d.add(new Point(nx, ny));
      }
    }
    return 1;
  }
}
