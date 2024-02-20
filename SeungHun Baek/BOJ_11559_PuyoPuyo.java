import java.io.*;
import java.util.*;

public class BOJ_11559_PuyoPuyo {

  static final int N = 12;
  static final int[] dx = { 1, 0, -1, 0 };
  static final int[] dy = { 0, -1, 0, 1 };
  static char arr[][], tmp[][];
  static int CNT;
  static boolean isChanged;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    arr = new char[N][N >> 1];

    for (int i = 0; i < N; i++) {
      arr[i] = br.readLine().toCharArray();
    }

    solution();
  }

  private static void solution() {
    CNT = -1;

    do {
      drop();
      pop();
      CNT++;
    } while (isChanged);

    System.out.println(CNT);
  }

  private static void pop() {
    tmp = copy();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N >> 1; j++) {
        if (tmp[i][j] == '.') continue;
        bfs(i, j);
      }
    }
    arr = tmp;
  }

  private static void drop() {
    for (int j = 0; j < N >> 1; j++) {
      for (int i = N - 1; i > 0; i--) {
        if (arr[i][j] != '.') continue;
        int idx = i - 1;
        while (idx >= 0 && arr[idx][j] == '.') {
          idx--;
        }
        if (idx >= 0) {
          arr[i][j] = arr[idx][j];
          arr[idx][j] = '.';
        }
      }
    }
    isChanged = false;
  }

  private static void bfs(int i, int j) {
    Deque<int[]> que = new ArrayDeque<>();
    ArrayList<int[]> save = new ArrayList<>();
    boolean[][] visited = new boolean[N][N >> 1];
    int cnt = 1;

    que.add(new int[] { i, j });
    visited[i][j] = true;

    while (!que.isEmpty()) {
      int[] pos = que.remove();
      for (int idx = 0; idx < 4; idx++) {
        int nx = pos[0] + dx[idx];
        int ny = pos[1] + dy[idx];

        if (nx < 0 || nx >= N || ny < 0 || ny >= N >> 1) continue;
        if (tmp[nx][ny] != arr[i][j]) continue;
        if (visited[nx][ny]) continue;

        visited[nx][ny] = true;
        que.add(new int[] { nx, ny });
        save.add(new int[] { nx, ny });
        cnt++;
      }
    }

    if (cnt >= 4) {
      tmp[i][j] = '.';
      for (int idx = 0; idx < save.size(); idx++) {
        int[] pos = save.get(idx);
        tmp[pos[0]][pos[1]] = '.';
      }
      isChanged = true;
    }
  }

  private static char[][] copy() {
    char[][] next = new char[N][N >> 1];
    for (int i = 0; i < N; i++) {
      next[i] = Arrays.copyOf(arr[i], N >> 1);
    }
    return next;
  }
}
