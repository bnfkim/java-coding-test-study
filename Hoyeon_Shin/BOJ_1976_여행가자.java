import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    
    // union find를 사용해 도시의 연결 정보를 표현할 때 사용할 배열
    static int[] parents;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        // union find를 위해 도시를 집합으로 초기화
        parents = new int[N];
        for (int i = 0; i < N; ++i) {
            parents[i] = i;
        }
        
        // 도시 연결 정보 입력
        StringTokenizer st;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                if (st.nextToken().equals("1")) {
                    union(i, j);
                }
            }
        }
        
        // 여행 계획 중 연결되지 않은 도시가 있다면 NO
        st = new StringTokenizer(br.readLine());
        int city;
        int prev = Integer.parseInt(st.nextToken()) - 1;
        boolean isPossible = true;
        for (int i = 0; i < M - 1; ++i) {
            city = Integer.parseInt(st.nextToken()) - 1;
            
            if (find(prev) != find(city)) {
                isPossible = false;
                break;
            }
            
            prev = city;
        }
        
        System.out.println(isPossible ? "YES" : "NO");
    }
    
    public static int find(int x) {
        if (x == parents[x])
            return x;
        
        return parents[x] = find(parents[x]);
    }
    
    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX != rootY)
            parents[rootX] = rootY;
    }
}
