import java.io.*;
import java.util.*;

public class BOJ_15683_감시 {

  static class CCTV {

    int x, y, num;

    CCTV(int x, int y, int num) {
      this.x = x;
      this.y = y;
      this.num = num;
    }

    void rotateAndMonitor(int dir) {
      for (int i = 0; i < vectors[num - 1].length; i++) {
        int dx = vectors[num - 1][i][0];
        int dy = vectors[num - 1][i][1];
        for (int j = 0; j < dir; j++) {
          int tmp = dx;
          dx = dy;
          dy = -tmp;
        }
        monitor(dx, dy);
      }
    }

    void monitor(int dx, int dy) {
      for (int i = 1; i < Math.max(N, M); i++) {
        int nx = this.x + i * dx;
        int ny = this.y + i * dy;
        if (!isPossible(nx, ny)) continue;

        if (test[nx][ny] == 6) break;
        if (test[nx][ny] != 0) continue;
        test[nx][ny] = -1;
      }
    }
  }

  static int N, M, MIN;
  static int[][] arr, test;
  static ArrayList<CCTV> CCTVs;
  static StringTokenizer st;
  static int[][][] vectors = {
    { { 0, 1 } },
    { { 0, 1 }, { 0, -1 } },
    { { -1, 0 }, { 0, 1 } },
    { { -1, 0 }, { 0, 1 }, { 0, -1 } },
    { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } },
  };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    MIN = Integer.MAX_VALUE;
    arr = new int[N][M];
    CCTVs = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int val = Integer.parseInt(st.nextToken());
        arr[i][j] = val;
        if (val >= 1 && val != 6) {
          CCTVs.add(new CCTV(i, j, val));
        }
      }
    }

    ArrayList<Integer> rot = new ArrayList<>();
    backTracking(0, rot);

    System.out.println(MIN);
  }

  private static void backTracking(int iter, ArrayList<Integer> rotates) {
    if (iter == CCTVs.size()) {
      testCCTV(rotates);
      return;
    }

    for (int i = 1; i <= 4; i++) {
      rotates.add(i);
      backTracking(iter + 1, rotates);
      rotates.remove(rotates.size() - 1);
    }
  }

  private static void testCCTV(ArrayList<Integer> rotates) {
    test = new int[N][M];
    for (int i = 0; i < N; i++) {
      test[i] = Arrays.copyOf(arr[i], M);
    }

    for (int i = 0; i < rotates.size(); i++) {
      CCTVs.get(i).rotateAndMonitor(rotates.get(i));
    }

    checkBlindSpot();
  }

  private static void checkBlindSpot() {
    int cnt = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (test[i][j] == 0) cnt++;
      }
    }

    MIN = Math.min(MIN, cnt);
  }

  private static boolean isPossible(int x, int y) {
    return 0 <= x && x < N && 0 <= y && y < M;
  }
}
