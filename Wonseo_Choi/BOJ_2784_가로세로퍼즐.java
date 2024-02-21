
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2784_가로세로퍼즐 {
    static int[] per_ar = new int[3];
    static String[] temp = new String[3];
    static String[] s_ar = new String[6];
    static boolean[] visited;
    static boolean flag;
    
    public static void main(String[] args) throws IOException {
        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 6; i++ ) {
            s_ar[i] = bw.readLine();
        }
        perumutation(0, 0);
        System.out.println(0);
    }

    public static void perumutation(int cnt, int idx) {
        if (cnt == 3) {
            solve();
            return;
        }

        for (int i = 0; i < 6; i++ ) {
            if ((idx & 1<<i) != 0) continue;
            per_ar[cnt] = i;
            perumutation(cnt+1,idx|1<<i);
        }
    }

    public static void solve() {
        String t="", tt="",temp1="", temp2="", temp3="";
        visited = new boolean[6];
        
        int idx=0;
        for (int i = 0; i < 3; i++ ) {
            idx = per_ar[i];
            tt = s_ar[idx];
            temp[i] = tt;
            temp1 += tt.charAt(0);
            temp2 += tt.charAt(1);
            temp3 += tt.charAt(2);
            visited[idx] = true;
        }

        boolean flag1 = false, flag2 = false, flag3 = false;
        for (int i = 0; i < 6; i++) {
            if (!visited[i]) {
                t = s_ar[i];
                if (!flag1 && t.equals(temp1)) {
                    flag1 = true;
                    continue;
                }
                if (!flag2 && t.equals(temp2)) {
                    flag2 = true;
                    continue;
                }
                if (!flag3 && t.equals(temp3)) {
                    flag3 =true;
                    continue;
                }
                break;
            }
        }
        
        if (flag1 && flag2 && flag3) {
            for (int i = 0; i < 3; i++ ){
                System.out.println(temp[i]);
            }
            System.exit(0);
        }
    }
}
