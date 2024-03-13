import java.io.*;
import java.util.*;

/**
 * 14992KB  132ms
 */
class Main {
    static int N, M;
    static int[] status = new int[51];  // 0: 아무 얘기도 듣지 못한 상태, 1: 진실만 들은 상태, 2: 과장된 얘기만 들은 상태
    static List<Integer>[] party;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        party = new ArrayList[M];

        // 진실을 아는 사람들의 상태 반영
        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        for (int n = 0; n < num; ++n) {
            status[Integer.parseInt(st.nextToken())] = 1;
        }

        // 파티에 오는 사람 입력 받기
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());

            num = Integer.parseInt(st.nextToken());
            party[i] = new ArrayList<>();
            for (int j = 0; j < num; ++j) {
                party[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 과장할 수 있는 최대 파티 개수를 구해 출력
        check(0, 0);
        System.out.println(max);
    }

    // idx 번째 파티에서 과장된 얘기를 할 수 있는지 체크하며 재귀적으로 과장된 얘기를 할 수 있는 최대 값을 구한다.
    static void check(int idx, int count) {
        // 남은 모든 파티에서 과장되게 얘기해도 현재 max 값을 넘을 수 없는 경우 가지치기
        if (M - idx + count <= max)
            return;

        // 기저조건
        if (idx == M) {
            max = count;
            return;
        }

        // 과장해 말할 수 있다면
        if (isPossible(idx, 2)) {
            int[] temp = Arrays.copyOf(status, status.length);

            // 과장해서 말한 후 다음 파티 조사
            for (int person: party[idx]) status[person] = 2;
            check(idx + 1, count + 1);

            status = temp;
        }

        // 이번 파티에서는 진실을 말할 수 있다면
        if (isPossible(idx, 1)) {
            for (int person: party[idx]) status[person] = 1;
            check(idx + 1, count);
        }
    }

    // idx 번째 파티에서
    // flag == 1 : 진실을 말할 수 있는지 체크
    // flag == 2 : 과장해 말할 수 있는지 체크
    static boolean isPossible(int idx, int flag) {
        for (int person: party[idx]) {
            if (flag == 1 && status[person] == 2) return false;
            if (flag == 2 && status[person] == 1) return false;
        }
        return true;
    }
}
