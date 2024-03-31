import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int TC,N,D,C,INF=1_000_000_001;
    static List<int[]>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();

        TC=Integer.parseInt(br.readLine());

        for(int tc=0;tc<TC;tc++){
            st=new StringTokenizer(br.readLine());

            N=Integer.parseInt(st.nextToken());
            D=Integer.parseInt(st.nextToken());
            C=Integer.parseInt(st.nextToken());

            edges=new List[N+1];

            for(int i=1;i<=N;i++){
                edges[i]=new ArrayList<>();
            }

            for(int i=0;i<D;i++){
                st=new StringTokenizer(br.readLine());
                int to=Integer.parseInt(st.nextToken());
                int from=Integer.parseInt(st.nextToken());
                int dist=Integer.parseInt(st.nextToken());

                edges[from].add(new int[]{to,dist});
            }

            int[] result=dijkstra();

            sb.append(result[0]).append(' ').append(result[1]).append('\n');
        }

        System.out.println(sb);
    }

    static int[] dijkstra(){
        int[] cost =new int[N+1];
        Arrays.fill(cost,INF);

        PriorityQueue<Point> pq=new PriorityQueue<>();
        pq.add(new Point(C,0));
        cost[C]=0;

        while(!pq.isEmpty()){
            Point cur=pq.poll();

            if(cost[cur.to]<cur.dist) continue;

            for(int[] e: edges[cur.to]){

                int nextDist=cur.dist+e[1];
                if(nextDist>=cost[e[0]]) continue;

                pq.add(new Point(e[0],nextDist));
                cost[e[0]]=nextDist;
            }
        }

        int[]result=new int[2];//개수,마지막 시간

        for(int i=1;i<=N;i++){
            if(cost[i]==INF) continue;
            result[0]++;

            result[1]=Math.max(result[1],cost[i]);
        }

        return result;
    }

    static class Point implements Comparable<Point>{
        int to,dist;

        public Point(int to,int dist){
            this.to=to;
            this.dist=dist;
        }

        @Override
        public int compareTo(Point o) {
            return dist-o.dist;
        }
    }
}