import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_24954_물약_구매 {
    static int N, answer;
    static int[] orders;
    static int[] prices;

    static class Discount {
        int potionNum;
        int discountPrice;

        public Discount(int potionNum, int discountPrice) {
            this.potionNum = potionNum;
            this.discountPrice = discountPrice;
        }
    }

    static List<Discount>[] discounts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        discounts = new ArrayList[N];
        orders = new int[N];
        prices = new int[N];
        // 물약별 가격 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int price = Integer.parseInt(st.nextToken());
            orders[i] = i;
            prices[i] = price;
        }
        // 물약의 할인 정보 받기
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            discounts[i] = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                st = new StringTokenizer(br.readLine());
                int potionNum = Integer.parseInt(st.nextToken()) - 1;
                int discountPrice = Integer.parseInt(st.nextToken());

                discounts[i].add(new Discount(potionNum, discountPrice));
            }
        }
        getCoinCount();
    }

    private static void getCoinCount() {
        answer = Integer.MAX_VALUE;
        do {
            int[] discountPrices = prices.clone();
            int curCoin = 0;
            for (int i = 0; i < N; i++) {
                for (Discount d : discounts[orders[i]]) {
                    discountPrices[d.potionNum] -= d.discountPrice;
                    if (discountPrices[d.potionNum] <= 0) {
                        discountPrices[d.potionNum] = 1;
                    }
                }
                curCoin += discountPrices[orders[i]];
            }
            if (curCoin < answer) {
                answer = curCoin;
            }
        } while (nextPermutation());
        System.out.println(answer);
    }

    private static boolean nextPermutation() {
        int i = N - 1;
        while (i > 0 && orders[i - 1] >= orders[i]) {
            i--;
        }
        if (i <= 0) {
            return false;
        }

        int j = N - 1;
        while (orders[j] <= orders[i - 1]) {
            j--;
        }

        int temp = orders[i - 1];
        orders[i - 1] = orders[j];
        orders[j] = temp;

        j = N - 1;
        while (i < j) {
            temp = orders[i];
            orders[i] = orders[j];
            orders[j] = temp;
            i++;
            j--;
        }
        return true;
    }
}
