import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사 {
    static int n, temp, ans;
    static int[][] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        for (int i = 0; i < n; i++ ) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            if (i+arr[i][0]>n) {
                arr[i][0] = -1;
                continue;
            }
            arr[i][1] = Integer.parseInt(st.nextToken());
            
        }
        
        boolean[] select;
        int idx, t;
        for (int i = 1; i < 1<<n; i++ ) {
            
            select = new boolean[n];
            idx = 0;
            t = i;
            while (t != 0) {
                if (t % 2 == 1) {
                    select[idx] = true;
                }
                idx++;
                t /= 2;
            }
            
            int j = 0;
            temp = 0;
            while (j < n) {
                if (!select[j] || arr[j][0] == -1) j++;
                else {
                    temp += arr[j][1];
                    j += arr[j][0];
                }
            }
            
            if (ans < temp) {
                ans = temp;
            }
        }
        System.out.println(ans);
    }
}