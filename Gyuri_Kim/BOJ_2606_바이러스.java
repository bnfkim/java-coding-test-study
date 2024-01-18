import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2606_바이러스 {
    public static int n,m;
    public static ArrayList<ArrayList<Integer>> map;
    public static boolean[] visit;
    public static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); //컴퓨터의 수
        m = Integer.parseInt(br.readLine());  //컴퓨터의 쌍
        visit = new boolean[n+1];
        map = new ArrayList<>();

        //(1) 그래프 입력받기
        for(int i=0; i<n+1; i++) map.add(new ArrayList<>());
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.get(a).add(b);
            map.get(b).add(a);
        }
        bfs();
        System.out.println(cnt);
    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visit[1] = true;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(int i=0; i<map.get(now).size(); i++) {
                int next = map.get(now).get(i);
                if(visit[next]) continue;

                queue.add(next);
                visit[next] = true;
                cnt++;
            }
        }
    }
}

