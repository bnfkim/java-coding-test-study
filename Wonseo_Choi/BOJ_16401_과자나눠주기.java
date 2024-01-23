import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16401_과자나눠주기 {
    static int m, n, ans;
	static int[] i_array;
	
	public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        
        i_array = new int[n];
        st = new StringTokenizer(br.readLine());
        int input=0, max=0, min=1;
        for (int i = 0; i < n; i++ ) {
            input = Integer.parseInt(st.nextToken());
        	i_array[i] = input;
            if (input > max) {
                max = input;
            }
        }


        int mid = 0, cnt = 0;
        while (min <= max) {
            mid = (min + max) / 2;
            cnt = 0;

            for (int i = 0; i < n; i++ ) {
                cnt += i_array[i] / mid;
            }
            if (cnt >= m) {
                min = mid+1;
                ans = mid;
            } else {
                max = mid-1;
            }
        }
        System.out.println(ans);        
    }
}
