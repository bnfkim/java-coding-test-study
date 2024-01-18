import java.io.*;
import java.util.*;

public class Main {
    public static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        //n에 따른 방문 배열 초기화.
        visited=new boolean[n+1];

        //인접 리스트
        List<List<Integer>> arr=new ArrayList<>();

        for(int i=0;i<n+1;i++) {
            arr.add(new ArrayList<>());
        }

        int m=Integer.parseInt(br.readLine());

        for(int i=0;i<m;i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        //BFS
        Queue<Integer> q=new LinkedList<>();
        q.add(1);
        visited[1]=true;

        while(!q.isEmpty()) {
            int x=q.poll();
            for(int next:arr.get(x)) {
                if(visited[next]) {
                    continue;
                }
                visited[next]=true;
                q.add(next);

            }
        }

        //값 찾기
        int answer=0;

        for(int i=2;i<=n;i++) {
            if(visited[i]) {
                answer+=1;
            }
        }

        System.out.println(answer);
    }
}