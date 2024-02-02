import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2785_체인 {

    static int N, ptr, answer;
    static int[] L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N, L 입력받기
        N = Integer.parseInt(br.readLine());
        L = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(L);
        getAnswer();
        System.out.println(answer);
    }

    private static void getAnswer() {
        // 체인이 2개인 경우는 무조건 하나의 고리가 필요
        if (N == 2) {
            answer = 1;
            return;
        }
        // 체인이 2개가 아닌 경우 체인 개수를 구해주어야 함
        countChains(N);
    }

    private static void countChains(int n) {
        // 모두 연결되어 더이상 연결할 체인이 없는 경우
        if (n <= 1) {
            return;
        }
        // 1개의 고리가 있는 체인을 가진 경우 이를 이용해 두개의 체인을 연결할 수 있다.
        if (L[0] == 1) {
            answer++;
            L[0] = L[++ptr];
            countChains(n - 2);
            return;
        }
        // 고리 하나를 빼서 다른 체인과 연결한다.
        L[0] = L[0] - 1;
        answer++;
        countChains(n-1);
    }
}