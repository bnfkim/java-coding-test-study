import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1043_거짓말 {
    static int N, M, P;
    static int[] person;

    static void init() {
        person = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            person[i] = i;
        }
    }

    static int find(int v) {
        if (person[v] == v) {
            return v;
        }
        return person[v] = find(person[v]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return false;
        }

        if (rootA < rootB) {
            person[rootB] = rootA;
        } else {
            person[rootA] = rootB;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        init();
        // 진실을 아는 사람 수의 정보 받기
        P = Integer.parseInt(st.nextToken());
        int[] curP = new int[P];
        for (int i = 0; i < P; i++) {
            int num = Integer.parseInt(st.nextToken());
            person[num] = 0;
        }
        // 파티에서 진실 확산
        List<int[]> parties = new ArrayList<>();
        for (int partyIdx = 0; partyIdx < M; partyIdx++) {
            st = new StringTokenizer(br.readLine());
            int pNum = Integer.parseInt(st.nextToken());
            int[] party = new int[pNum];
            for (int peopleIdx = 0; peopleIdx < pNum; peopleIdx++) {
                int p = Integer.parseInt(st.nextToken());
                party[peopleIdx] = p;
            }
            parties.add(party);
            if (pNum >= 2) {
                for (int i = 0; i < pNum - 1; i++) {
                    union(party[i], party[i + 1]);
                }
            }
        }
        // 거짓말 할 수 있는 파티 확인
        int answer = 0;
        for (int[] party : parties) {
            boolean canLie = true;
            for (int i = 0, size = party.length; i < size; i++) {
                int p = party[i];
                if (find(p) == 0) {
                    canLie = false;
                }
            }
            if (canLie) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
