import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_14501_퇴사 {

    static int N;
    static int[][] schedules;
    static List<Integer> profits;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        schedules = new int[N + 1][2];
        profits = new ArrayList<>(N);

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            schedules[i][0] = Integer.parseInt(input[0]);
            schedules[i][1] = Integer.parseInt(input[1]);
        }

        for (int i = 1; i <= N; i++) {
            getProfit(i, 0);
        }

        int maxProfit = Collections.max(profits).intValue();
        System.out.println(maxProfit);
    }

    private static void getProfit(int start, int totalProfit) {
        if (start > N) {
            profits.add(totalProfit);
            return;
        }

        // 현재 일을 포함하는 경우
        if (start + schedules[start][0] - 1 <= N) {
            getProfit(start + schedules[start][0], totalProfit + schedules[start][1]);
        }

        // 현재 일을 포함하지 않는 경우
        getProfit(start + 1, totalProfit);
    }

}
