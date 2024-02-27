import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int n, s, ans = 0;
    static int[] arr;
    
    public static void solve(int cur, int sum){
        if(cur == n) return;
        if(sum+arr[cur] == s) ans++;

        solve(cur+1, sum);
        solve(cur+1, sum+arr[cur]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        solve(0, 0);

        System.out.println((ans));
    }
}

