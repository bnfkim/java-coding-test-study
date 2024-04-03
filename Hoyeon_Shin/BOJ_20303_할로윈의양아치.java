import java.io.*;
import java.util.*;

/**
 * 
 * 40224KB	468ms
 *
 */
class Main {
	static int N, M, K;
	static int[] parent;
	static int[] c;  // c[parent[i]] == parent[i]로 대변되는 집단이 가진 총 사탕 수
	static int[] num;  // num[parent[i]] == parent[i]로 대변되는 집단의 총 명수
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력 및 초기화
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        parent = new int[N + 1];
        c = new int[N + 1];
        num = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
        	c[i] = Integer.parseInt(st.nextToken());
        	parent[i] = i;
        	num[i] = 1;
        }
        
        // 관계 입력받으며 친구관계, c, num 갱신
        for (int i = 1; i <= M; ++i) {
        	st = new StringTokenizer(br.readLine());
        	
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	union(a, b);
        }
        
        // 각 친구 그룹당 아이들의 수를 무게, 사탕을 값어치로 보고 Knapsack 알고리즘 적용 
        List<int[]> groups = new ArrayList<>();
        boolean[] check = new boolean[N + 1];
        for (int i = 1; i <= N; ++i) {
        	if (check[find(i)]) continue;
        	
        	check[find(i)] = true;
        	groups.add(new int[] {num[find(i)], c[find(i)]});
        }
        
        int[] dp = new int[K];
        for (int[] group: groups) {
        	int weight = group[0];
        	int value = group[1];
        	
        	for (int i = K - weight - 1; i >= 0; --i) {
        		if (dp[i + weight] < dp[i] + value)
        			dp[i + weight] = dp[i] + value;
        	}
        }
        
        System.out.println(dp[K - 1]);
    }
    
    public static int find(int x) {
    	if (x == parent[x]) return x;
    	
    	return parent[x] = find(parent[x]);
    }
    
    public static void union(int x, int y) {
    	int rootX = find(x);
    	int rootY = find(y);
    	
    	if (rootX != rootY) {
    		parent[rootX] = rootY;
    		c[rootY] += c[rootX];
    		num[rootY] += num[rootX];
    	}
    }
}
