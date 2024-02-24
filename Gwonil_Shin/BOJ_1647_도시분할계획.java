import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static List<Edge> edges;
    static int[] parents;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        edges=new ArrayList<>(M);
        parents=new int[N+1];

        for(int i=1;i<=N;i++){
            parents[i]=i;
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());
            edges.add(new Edge(from,to,weight));
        }

        Collections.sort(edges);

        int result=0;
        int cnt=0;

        if(N>2) {
            for (Edge edge : edges) {
                if (!union(edge.from, edge.to))
                    continue;
                result += edge.weight;
                if (++cnt == N - 2)
                    break;
            }
        }

        //최소값을 출력
        System.out.println(result);
    }

    static boolean union(int a,int b){
        int aRoot=find(a);
        int bRoot=find(b);

        if(aRoot==bRoot) return false;

        parents[bRoot]=aRoot;

        return true;
    }

    static int find(int x){
        if(parents[x]==x) return x;

        return parents[x]=find(parents[x]);
    }

    static class Edge implements Comparable<Edge> {
        int from,to,weight;

        public Edge(int from,int to,int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight,o.weight);
        }
    }
}