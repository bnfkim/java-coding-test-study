import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
    static int[][] stats = new int[21][21];
    static boolean[] check = new boolean[21];
    static int n;
    static int ans = 0x3f3f3f3f;

    public static void bt(int cnt, int idx)
    {
        if (cnt == n / 2)
        {
            int start = 0, link =0;
            
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (check[i] && check[j]) start += stats[i][j];
                    else if (!check[i] && !check[j]) link += stats[i][j];
                }
            }
            
            ans = (ans > Math.abs(start - link)) ? Math.abs(start - link) : ans;
            return;
        }

        for (int i = idx; i < n; i++) {
            check[i] = true;
            bt(cnt + 1, i + 1);
            check[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                stats[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bt(0, 1);
        System.out.println(ans);
    }
}
