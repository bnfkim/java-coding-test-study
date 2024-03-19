import java.io.*;
import java.util.*;

/**
 * 16380KB      452ms
 */
class Main {
    static int N;
    static int[] price;
    static boolean[] check;
    static int[][] discount;  // x 물약을 구매하면 y 물약이 discount[x][y] 만큼 할인
    static int minPrice = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 가격정보 입력받기
        price = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        check = new boolean[N + 1];
        discount = new int[N + 1][N + 1];

        // 할인 정보 입력
        for (int i = 1; i <= N; ++i) {
            int p = Integer.parseInt(br.readLine());

            for (int j = 0; j < p; ++j) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                discount[i][a] = d;
            }
        }

        permutation(0, 0);
        System.out.println(minPrice);
    }

    // 가능한 순서를 모두 탐방하며 최소 가격을 구한다.
    static void permutation(int idx, int curPrice) {
        System.out.println("cp: " + curPrice + " / check: " + Arrays.toString(check));

        // 이미 최소값보다 커진경우 더 구할 필요 없이 가지치기
        if (curPrice >= minPrice)
            return;

        // 기저 조건
        if (idx == N) {
            minPrice = curPrice;
            return;
        }

        // idx 번째에 물약 i를 고른다.
        for (int i = 1; i <= N; ++i) {
            if (check[i]) continue;

            check[i] = true;
            for (int j = 1; j <= N; ++j)
                price[j] -= discount[i][j];

            permutation(idx + 1, curPrice + Math.max(price[i], 1));

            check[i] = false;
            for (int j = 1; j <= N; ++j)
                price[j] += discount[i][j];
        }
    }
}
