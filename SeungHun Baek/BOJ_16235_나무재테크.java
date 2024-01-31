import java.io.*;
import java.util.*;

public class BOJ_16235_나무재테크 {

  static int N, M, K, current[][], energy[][];
  static ArrayList<Integer> tree[][];
  static StringTokenizer st;
  static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
  static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    energy = new int[N][N];
    current = new int[N][N];
    tree = new ArrayList[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        tree[i][j] = new ArrayList<Integer>();
        energy[i][j] = Integer.parseInt(st.nextToken());
        current[i][j] = 5;
      }
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int z = Integer.parseInt(st.nextToken());
      tree[x - 1][y - 1].add(z);
    }

    for (int i = 0; i < K; i++) {
      year();
    }

    int cnt = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        cnt += tree[i][j].size();
      }
    }

    System.out.println(cnt);
  }

  private static void year() {
    springAndSummer();
    authumAndWinter();
  }

  private static void springAndSummer() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        Collections.sort(tree[i][j]);
        ArrayList<Integer> tmp = new ArrayList<>();
        int death = 0;
        for (int val : tree[i][j]) {
          if (current[i][j] - val < 0) {
            death += val / 2;
          } else {
            current[i][j] -= val;
            tmp.add(val + 1);
          }
        }
        tree[i][j].clear();
        tree[i][j].addAll(tmp);
        current[i][j] += death;
      }
    }
  }

  private static void authumAndWinter() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        current[i][j] += energy[i][j];
        for (int val : tree[i][j]) {
          if (val % 5 != 0) continue;
          for (int idx = 0; idx < 8; idx++) {
            int nx = i + dx[idx];
            int ny = j + dy[idx];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            tree[nx][ny].add(1);
          }
        }
      }
    }
  }
}
