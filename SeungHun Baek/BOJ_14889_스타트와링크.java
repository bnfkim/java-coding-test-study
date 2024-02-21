import java.io.*;
import java.util.*;

public class BOJ_14889_스타트와링크 {

  static int N, MIN;
  static int[][] arr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    MIN = Integer.MAX_VALUE;
    arr = new int[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    solution();
  }

  private static void solution() {
    comb(0, 0);
    System.out.println(MIN);
  }

  private static void comb(int index, int visited) {
    if (Integer.bitCount(visited) == N / 2) {
      calc(visited);
      return;
    }

    for (int i = index; i < N; i++) {
      comb(i + 1, visited | 1 << i);
    }
  }

  private static void calc(int visited) {
    int score1 = 0;
    int score2 = 0;

    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        boolean c1 = (visited & 1 << i) != 0;
        boolean c2 = (visited & 1 << j) != 0;

        score1 += c1 && c2 ? arr[i][j] + arr[j][i] : 0;
        score2 += !(c1 || c2) ? arr[i][j] + arr[j][i] : 0;
      }
    }

    MIN = Math.min(MIN, Math.abs(score1 - score2));
  }
}
