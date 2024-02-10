import java.io.*;
import java.util.*;

public class BOJ_11505_구간곱구하기 {

  static final int mod = 1_000_000_007;
  static int N, M, K;
  static long datas[], tree[];
  static StringTokenizer st;
  static StringBuilder sb;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    sb = new StringBuilder();

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    datas = new long[N];
    tree = new long[N << 2];

    for (int i = 0; i < N; i++) {
      datas[i] = Long.parseLong(br.readLine());
    }

    initialize(0, N - 1, 1);

    for (int i = 0; i < M + K; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      long c = Long.parseLong(st.nextToken());
      solution(a, b, c);
    }
    System.out.println(sb.toString());
  }

  private static void solution(int a, int b, long c) {
    if (a == 1) {
      datas[b - 1] = c;
      update(0, N - 1, 1, b - 1, c);
    } else {
      long sol = productInterval(0, N - 1, 1, b - 1, (int) c - 1);
      sb.append(sol).append("\n");
    }

  }

  private static long initialize(int stt, int end, int node) {
    if (stt == end) {
      return tree[node] = datas[stt];
    }

    int mid = (stt + end) >> 1;
    long i1 = initialize(stt, mid, node << 1);
    long i2 = initialize(mid + 1, end, 1 + (node << 1));
    return tree[node] = multiplyAndModulate(i1, i2);
  }

  private static void update(int stt, int end, int node, int index, long diff) {
    if (stt == end) {
      tree[node] = diff;
      return;
    }

    int mid = (stt + end) >> 1;

    if (index <= mid) {
      update(stt, mid, node << 1, index, diff);
    } else {
      update(mid + 1, end, 1 + (node << 1), index, diff);
    }
    tree[node] = multiplyAndModulate(tree[node << 1], tree[1 + (node << 1)]);
  }

  private static long productInterval(int stt, int end, int node, int left, int right) {
    if (right < stt || end < left) {
      return 1;
    }

    if (left <= stt && end <= right) {
      return tree[node];
    }

    int mid = (stt + end) >> 1;
    long p1 = productInterval(stt, mid, node << 1, left, right);
    long p2 = productInterval(mid + 1, end, 1 + (node << 1), left, right);

    return multiplyAndModulate(p1, p2);
  }

  private static long multiplyAndModulate(long a, long b) {
    return (a * b) % mod;
  }
}
