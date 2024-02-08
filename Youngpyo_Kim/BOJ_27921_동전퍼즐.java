import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_27921_동전퍼즐 {
    static char[][] arr1 = new char[11][11];
    static char[][] arr2 = new char[11][11];
    static int n1, m1, n2, m2, ocnt, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        m1 = Integer.parseInt(st.nextToken());
        ocnt = 0;
        ans = 0x3f3f3f3f;

        for (int i = 0; i < n1; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m1; j++) {
                arr1[i][j] = tmp.charAt(j);
                if (arr1[i][j] == 'O')
                    ocnt++;
            }
        }

        st = new StringTokenizer(br.readLine());
        n2 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n2; i++) {
            String tmp = br.readLine();
            for(int j = 0; j < m2; j++){
                arr2[i][j] = tmp.charAt(j);
            }
        }

        if (ocnt == 0) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                if (arr1[i][j] == 'O') {
                    for (int p = 0; p < n2; p++) {
                        for (int q = 0; q < m2; q++) {
                            if (arr2[p][q] == 'O') {
                                int dr = p - i, dc = q - j, tmp = 0;
                                for (int r = 0; r < n1; r++) {
                                    for (int c = 0; c < m1; c++) {
                                        if (arr1[r][c] == 'O') {
                                            int nr = r + dr, nc = c + dc;
                                            if (nr < 0 || nr >= n2 || nc < 0 || nc >= m2 || arr2[nr][nc] == '.')
                                                continue;
                                            tmp++;
                                        }
                                    }
                                }
                                ans = Math.min(ans, ocnt - tmp);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }
}

