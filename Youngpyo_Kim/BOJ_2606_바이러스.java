import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2606_바이러스 {
    static int parent[] = new int[101];

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(in.readLine()), m = Integer.parseInt(in.readLine());
        initiallize(n);

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        int ans = 0;
        for (int i = 2; i <= n; i++) {
            if(find(i) == 1)
                ans++;
        }

        System.out.println(ans);
    }

    static void initiallize(int n){
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    static int find(int a){
        if(parent[a] == a)
            return a;
        
        return find(parent[a]);
    }
    
    static void union(int a, int b){
        a = find(a);
        b = find(b);
    
        if(a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }
}