import java.io.*;
import java.util.*;

/**
 * 119392kb     500ms
 * 첫 시도: 투 포인터 사용 -> 시간 초과 발생
 * 두 번째 시도: 작은 수를 커버할 수 있을 만큼의 범위로 k를 설정하면 된다는 것을 깨우쳐 코드에 반영 -> 성공
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        // (앞에 나온 수보다 작은 수가 연속해서 나오는 횟수 + 1) == 정답
        int k = 1;
        int curSeq = 1;
        int curMax = 0;
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());

            if (curMax > A[i])
                k = Math.max(k, ++curSeq);
            else {
                curSeq = 1;
                curMax = A[i];
            }
        }

        System.out.println(k);
    }
}
