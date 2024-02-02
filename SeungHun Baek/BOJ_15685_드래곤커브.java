import java.io.*;
import java.util.*;

public class BOJ_15685_드래곤커브 {

  static class Point {

    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static class Dragon {

    int x, y, d, g, currentG;
    ArrayDeque<Point> que;
    Point S, E;

    Dragon(int x, int y, int d, int g) {
      this.x = x;
      this.y = y;
      this.d = d;
      this.g = g;
      S = new Point(x, y);
      E = new Point(S.x + dx[d], S.y + dy[d]);
      arr[S.x][S.y] = 1;
      arr[E.x][E.y] = 1;
      que = new ArrayDeque<>();
      que.add(S);
      que.add(E);
      move();
    }

    void move() {
      while (currentG < g) {
        generate();
        make();
      }
    }

    void generate() {
      Point tmp = que.removeLast();
      E = new Point(tmp.x, tmp.y);
      que.add(tmp);
      currentG++;
    }

    void make() {
      ArrayDeque<Point> tmp = que.clone();
      S = tmp.removeLast();
      while (!tmp.isEmpty()) {
        Point newP = rotatePoint(tmp.removeLast(), E);
        if (isPossible(newP.x, newP.y)) {
          arr[newP.x][newP.y] = 1;
        }
        que.add(newP);
      }
    }

    Point rotatePoint(Point s, Point e) {
      int X = e.x + s.y - e.y;
      int Y = e.y - s.x + e.x;
      return new Point(X, Y);
    }
  }

  static int N, CNT, x, y, d, g;
  static int arr[][];
  static final int[] dx = { 0, -1, 0, 1 };
  static final int[] dy = { 1, 0, -1, 0 };
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new int[101][101];
    CNT = 0;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      x = Integer.parseInt(st.nextToken());
      y = Integer.parseInt(st.nextToken());
      d = Integer.parseInt(st.nextToken());
      g = Integer.parseInt(st.nextToken());
      new Dragon(y, x, d, g);
    }

    check();
    System.out.println(CNT);
  }

  private static void check() {
    for (int i = 0; i <= 100; i++) {
      for (int j = 0; j <= 100; j++) {
        if (arr[i][j] == 0) continue;
        if (!isPossible(i + 1, j)) continue;
        if (!isPossible(i, j + 1)) continue;
        CNT +=
          arr[i + 1][j] == 1 && arr[i][j + 1] == 1 && arr[i + 1][j + 1] == 1
            ? 1
            : 0;
      }
    }
  }

  private static boolean isPossible(int x, int y) {
    return 0 <= x && x <= 100 && 0 <= y && y <= 100;
  }
}
