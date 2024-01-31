import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1288_새로운불면증치료법 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());

    for (int tc = 1; tc < TC + 1; tc++) {
      int N = Integer.parseInt(br.readLine());
      boolean[] digits = new boolean[10];
      boolean check = false;
      int cnt = 0;

      do {
        int tmp = N * ++cnt;
        check = false;
        while (tmp > 0) {
          digits[tmp % 10] = true;
          tmp /= 10;
        }
        for (int i = 0; i < 10; i++) {
          if (!digits[i]) check = true;
        }
      } while (check);
      
      System.out.println("#" + tc + " " + cnt * N);
    }
  }
}
