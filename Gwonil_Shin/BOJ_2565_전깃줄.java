import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N=Integer.parseInt(br.readLine());
        int[] dp=new int[N];
        List<Line> lines=new ArrayList<>();

        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());
            lines.add(new Line(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        Collections.sort(lines);

        dp[0]=1;
        int answer=0;

        for(int i=1;i<N;i++) {
            int max=0;
            Line cur=lines.get(i);
            for(int j=0;j<i;j++) {
                Line check=lines.get(j);

                if(cur.to<check.to) continue;

                max=Math.max(dp[j], max);
            }

            dp[i]=max+1;
            answer=Math.max(answer, dp[i]);
        }

        System.out.println(N-answer);
    }


    static class Line implements Comparable<Line>{
        int from,to;

        public Line(int from,int to) {
            this.from=from;
            this.to=to;
        }

        @Override
        public int compareTo(Line o) {
            return this.from-o.from;
        }
    }
}
