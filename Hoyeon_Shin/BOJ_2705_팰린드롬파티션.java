import java.io.*;

public class Main_2705_S1_팰린드롬파티션 {
    static int N;
    static int[] pp = new int[1001];  // pp[x] : 자연수 x 의 재귀적 팰린드롬 파티션 개수

    // 1 ~ 1000까지 재귀적 팰린드롬 파티션 개수를 구해 pp 배열에 값을 채우는 메서드
    public static void fillpp() {
        // n 이 홀수일 경우
        // 가운데 숫자를 1부터 n까지의 홀수로 두고 나머지수를 반으로 나눠 양쪽에 둔다.
        // 그러면 양쪽에 있는 숫자의 pp를 구해 더하면 n의 pp가 된다.
        // 예) 7의 경우 : 7, 1 5 1, 2 3 2, 3 1 3
        //    자기자신 1 + 가운데 5를 두고 pp[1] + 가운데 3을 두고 pp[2] + 가운데 1을 두고 pp[3]
        // 결론: n이 홀수일 경우 pp[1] ~ pp[n / 2] 의 합 + 1

        // n 이 짝수일 경우
        // 홀수와 같은 원리로 가운데 숫자를 1부터 n까지의 짝수로 두고 나머지 수를 반으로 나눠 양쪽에 두는 경우와
        // 정확히 반으로 나눠 (n/2) 씩 양쪽에 두는 경우를 모두 더하면 된다.
        // 예) 6의 경우 : 6, 1 4 1, 2 2 2, 3 3
        //    자기자신 1 + 가운데 4를 두고 pp[1] + 가운데 2를 두고 pp[2] + 가운데 없이 pp[3]
        // 결론: n이 짝수인 경우 pp[1] ~ pp[n / 2] 의 합 + 1

        // 위 경우를 모두 종합하면, pp[n] == pp[1] ~ pp[n / 2]의 합 + 1

        pp[1] = 1;
        pp[2] = 2;
        pp[3] = 2;
        for (int i = 4; i <= 1000; ++i) {
            pp[i] = pp[i - 1];

            if (i % 2 == 0)
                pp[i] += pp[i / 2];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        fillpp();
        for (int i = 0; i < N; ++i) {
            System.out.println(pp[Integer.parseInt(br.readLine())]);
        }
    }
}
