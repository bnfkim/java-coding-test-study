import java.io.*;
import java.util.*;

public class BOJ_27921_동전퍼즐 {

  static int H1, W1, H2, W2, coinCount, MIN;
  static char[][] orgn, copy, canvas;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    coinCount = 0;
    MIN = Integer.MAX_VALUE;
    st = new StringTokenizer(br.readLine());
    H1 = Integer.parseInt(st.nextToken());
    W1 = Integer.parseInt(st.nextToken());
    orgn = new char[H1][W1];

    for (int i = 0; i < H1; i++) {
      orgn[i] = br.readLine().toCharArray();
      for (int j = 0; j < W1; j++) {
        if (orgn[i][j] == 'O') coinCount++;
      }
    }

    st = new StringTokenizer(br.readLine());
    H2 = Integer.parseInt(st.nextToken());
    W2 = Integer.parseInt(st.nextToken());
    copy = new char[H2][W2];

    for (int i = 0; i < H2; i++) {
      copy[i] = br.readLine().toCharArray();
    }

    solution();
  }

  private static void solution() {
    canvas = makeCanvas();
    for (int i = 0; i < H1 + H2; i++) {
      for (int j = 0; j < W1 + W2; j++) {
        simulate(i, j);
      }
    }
    System.out.println(MIN);
  }

  private static void simulate(int r, int c) {
    int cnt = 0;
    for (int i = 0; i < H1; i++) {
      for (int j = 0; j < W1; j++) {
        if (orgn[i][j] != 'O') continue;
        if (canvas[r + i][c + j] == orgn[i][j]) {
          cnt++;
        }
      }
    }
    MIN = Math.min(MIN, coinCount - cnt);
  }

  private static char[][] makeCanvas() {
    char[][] canvas = new char[H1 + H2 + H1][W1 + W2 + W1];
    for (int i = 0; i < H2; i++) {
      for (int j = 0; j < W2; j++) {
        canvas[H1 + i][W1 + j] = copy[i][j];
      }
    }
    return canvas;
  }
}

/**
 *
 *          가로    :   H1 + H2 + H1
 *          세로    :   W1 + W2 + W1
 *
 *          이동
 *          가로로 H1 + H2
 *          세로로 W1 + W2
 */
