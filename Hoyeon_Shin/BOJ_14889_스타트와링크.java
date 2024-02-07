import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] matrix;
    static boolean[] picked;
    static int minDiff = Integer.MAX_VALUE;

    public static void combination(int idx, int start) {
        // 절반의 인원을 선택하면 점수차를 계산한다.
        if (idx == N / 2) {
            int teamA = 0, teamB = 0;

            // 두 명을 뽑아 두 명이 같은 팀이라면 해당 팀의 능력치를 더한다.
            for(int i = 0; i < N - 1; ++i) {
                for(int j = i + 1; j < N; ++j) {
                    if (picked[i] && picked[j]) {
                        teamA += matrix[i][j];
                        teamA += matrix[j][i];
                    }
                    else if (!picked[i] && !picked[j]) {
                        teamB += matrix[i][j];
                        teamB += matrix[j][i];
                    }
                }
            }

            int diff = Math.abs(teamB - teamA);
            minDiff = Math.min(minDiff, diff);
            return;
        }

        for (int i = start; i < N; ++i) {
            picked[i] = true;
            combination(idx + 1,  i + 1);
            picked[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {

        // 능력치 matrix 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        picked = new boolean[N];
        picked[0] = true;  // 첫 번째 선수가 속한 곳을 teamA로 지정.
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; ++j) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(1, 1);
        System.out.println(minDiff);
    }
}
