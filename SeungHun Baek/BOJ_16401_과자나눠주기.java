import java.io.*;
import java.util.*;

public class BOJ_16401_과자나눠주기 {

  static int M, N, sol, MAX, arr[];
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());

    arr = new int[N];
    MAX = 0;
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      MAX = Math.max(arr[i],MAX);
    }

    sol = 0;
    int stt = 1;
    int end = MAX;
    while (stt <= end) {
      int mid = (stt + end) / 2;
      boolean result = isPossible(mid);
      if (result) {
        sol = mid;
        stt = mid + 1;
      } else {
        end = mid - 1;
      }
    }

    System.out.println(sol);
  }

  private static boolean isPossible(int c) {
    int cnt = 0;
    for (int i = 0; i < N; i++) {
      cnt += arr[i] / c;
      if (cnt >= M) {
        return true;
      }
    }
    return false;
  }
}
