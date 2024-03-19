import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T;
    static Set<Integer> exist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            exist = new HashSet<>();

            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            sb.append(bfs(from, to)).append('\n');
        }

        System.out.println(sb);
    }

    static String bfs(int from, int to){
        Deque<Pair> dq=new ArrayDeque<>();

        dq.add(new Pair(from,""));
        exist.add(from);

        while(!dq.isEmpty()){
            Pair cur=dq.poll();

            if(cur.value==to){
                return cur.op;
            }

            int d=d(cur.value);

            if(!exist.contains(d)){
                dq.add(new Pair(d,cur.op+"D"));
                exist.add(d);
            }

            int s=s(cur.value);

            if(!exist.contains(s)){
                dq.add(new Pair(s,cur.op+"S"));
                exist.add(s);
            }
            int l=l(cur.value);

            if(!exist.contains(l)){
                dq.add(new Pair(l,cur.op+"L"));
                exist.add(l);
            }
            int r=r(cur.value);

            if(!exist.contains(r)){
                dq.add(new Pair(r,cur.op+"R"));
                exist.add(r);
            }




        }

        return null;
    }

    static int d(int x){
        return (2*x)%10000;
    }

    static int s(int x){
        return (x==0)?9999:x-1;
    }

    static int l(int x){
        int f=x/1000;
        return (x*10)%10000+f;
    }

    static int r(int x){
        int f=x%10;

        return x/10+1000*f;
    }


    static class Pair{
        int value;
        String op;

        public Pair(){}
        public Pair(int value,String op){
            this.value=value;
            this.op=op;
        }
    }
}