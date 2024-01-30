import java.io.*;
import java.util.*;

public class BOJ_21608_상어초등학교 {

  static int N, score;
  static int[] keyOrder;
  static int[][] arr;
  static HashMap<Integer, ArrayList<Integer>> map;
  static StringTokenizer st;
  static final int[] dx = { 1, 0, -1, 0 };
  static final int[] dy = { 0, -1, 0, 1 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    keyOrder = new int[N * N];
    arr = new int[N][N];
    map = new HashMap<>();
    score = 0;

    int size = 0;
    for (int idx = 0; idx < N * N; idx++) {
      st = new StringTokenizer(br.readLine());
      int student_num = Integer.parseInt(st.nextToken());
      keyOrder[size++] = student_num;
      for (int f = 0; f < 4; f++) {
        int friend_num = Integer.parseInt(st.nextToken());
        ArrayList<Integer> friends = map.getOrDefault(
          student_num,
          new ArrayList<>()
        );
        friends.add(friend_num);
        map.put(student_num, friends);
      }
    }

    for (int idx = 0; idx < N * N; idx++) {
      getPosition(keyOrder[idx]);
    }

    calcScore();

    System.out.println(score);
  }

  private static void getPosition(int index) {
    ArrayList<Integer> check1 = condition1(index);
    if (check1 != null && check1.size() == 1) {
      int num = check1.get(0);
      arr[num / N][num % N] = index;
      return;
    }

    ArrayList<Integer> check2 = condition2(check1);
    if (check2 != null && check2.size() == 1) {
      int num = check2.get(0);
      arr[num / N][num % N] = index;
      return;
    }

    int MIN = N * N + 1;
    for (int v : check2) {
      MIN = Math.min(MIN, v);
    }
    int num = MIN;
    arr[num / N][num % N] = index;
  }

  private static ArrayList<Integer> condition1(int index) {
    int[] check = new int[N * N];
    int MAX = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (arr[i][j] != 0) continue;
        int cnt = 0;
        for (int idx = 0; idx < dx.length; idx++) {
          int nx = i + dx[idx];
          int ny = j + dy[idx];
          if (!isPossible(nx, ny)) continue;
          if (arr[nx][ny] == 0) continue;
          for (int friend : map.get(index)) {
            if (arr[nx][ny] == friend) {
              cnt++;
              break;
            }
          }
        }
        MAX = Math.max(MAX, cnt);
        check[N * i + j] = cnt;
      }
    }

    ArrayList<Integer> lst = new ArrayList<>();
    for (int idx = 0; idx < N * N; idx++) {
      if (arr[idx / N][idx % N] != 0) continue;
      if (check[idx] == MAX) {
        lst.add(idx);
      }
    }

    return lst;
  }

  private static ArrayList<Integer> condition2(ArrayList<Integer> lst) {
    int[] check = new int[lst.size()];
    int MAX = 0;

    int i = 0;
    for (int num : lst) {
      int row = num / N;
      int col = num % N;
      int cnt = 0;
      for (int idx = 0; idx < dx.length; idx++) {
        int nx = row + dx[idx];
        int ny = col + dy[idx];
        if (!isPossible(nx, ny)) continue;
        if (arr[nx][ny] != 0) continue;
        cnt++;
      }
      check[i++] = cnt;
      MAX = Math.max(MAX, cnt);
    }

    ArrayList<Integer> newLst = new ArrayList<>();
    for (int idx = 0; idx < lst.size(); idx++) {
      if (check[idx] == MAX) {
        newLst.add(lst.get(idx));
      }
    }

    return newLst;
  }

  private static void calcScore() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int cnt = 0;
        boolean[] near = new boolean[N * N];
        for (int v : map.get(arr[i][j])) {
          near[v - 1] = true;
        }
        for (int idx = 0; idx < dx.length; idx++) {
          int nx = i + dx[idx];
          int ny = j + dy[idx];
          if (!isPossible(nx, ny)) continue;
          if (near[arr[nx][ny] - 1]) cnt++;
        }
        score += square(cnt);
      }
    }
  }

  private static int square(int iter) {
    int s = 1;
    if (iter == 0) return 0;
    for (int i = 0; i < iter - 1; i++) {
      s *= 10;
    }
    return s;
  }

  private static boolean isPossible(int x, int y) {
    return 0 <= x && x < N && 0 <= y && y < N;
  }
}
