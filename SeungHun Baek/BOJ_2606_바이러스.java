import java.io.*;
import java.util.*;

public class BOJ_2606_바이러스 {

  static int N, M, virusCount;
  static boolean graph[][], visited[];
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());
    graph = new boolean[N + 1][N + 1];
    visited = new boolean[N + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      graph[x][y] = true;
      graph[y][x] = true;
    }

    bfs();
    System.out.println(virusCount);
  }

  private static void bfs() {
    Deque<Integer> d = new ArrayDeque<>();
    d.add(1);
    visited[1] = true;

    while (!d.isEmpty()) {
      int com = d.remove();
      for (int i = 1; i < N+1; i++) {
        if (!graph[com][i] || visited[i]) continue;
        visited[i] = true;
        virusCount++;
        d.add(i);
      }
    }
  }
}
