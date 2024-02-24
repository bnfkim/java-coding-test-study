import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * memory : 16452kb
 * time : 196ms
 */
public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; ++i) {
            if(check(br.readLine()))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    static boolean check(String str) {
        char[] s = str.toCharArray();
        int i = 0;
        int len = s.length;
        if (len < 2) return false;

        boolean canFirstPattern = false;  // 01 혹은 100+1+ 모두 가능한 경우
        boolean isFirstPattern = false;  // 100+1+ 패턴이어야만 하는 경우

        while (true) {
            // 아래 조건을 모두 통과하고 돌아왔을 때 끝이라면 통과
            if (i >= len) return true;

            if (!canFirstPattern && s[i] == '1') {
                isFirstPattern = true;
                i++;
                if (i >= len) return false;
            }

            if (s[i] != '0') return false;

            i++;
            if (i >= len) return false;

            // 0이 연달아 2개 나왔을 때, 첫 번째 패턴을 따라야함 (100+1+)
            if (s[i] == '0') {
                if (!canFirstPattern && !isFirstPattern) return false;
                while (i < len && s[i] == '0') i++;
                // 0으로 끝나면 false
                if (i >= len) return false;

                // 1 한 개는 무조건 있어야 한다.
                i++;
              
                // 1이 여러개 나온다면 다음 사이클에서 첫 번째 패턴과 매칭될 가능성이 있음을 저장
                canFirstPattern = false;
                while (i < len && s[i] == '1') {
                    canFirstPattern = true;
                    i++;
                }
                isFirstPattern = false;
            }

            // 01 패턴
            else {
                // 01 패턴이어야하는데 확정적으로 첫번째 패턴이어야 하는 경우는 모순
                if (isFirstPattern) return false;
              
                canFirstPattern = false;
                i++;
            }
        }
    }
}
