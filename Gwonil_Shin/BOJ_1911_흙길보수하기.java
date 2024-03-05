import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,L;
    static List<Water> waters;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());

        waters=new ArrayList<>(N);

        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());

            waters.add(new Water(from,to-from));
        }

        Collections.sort(waters);

        int result=0;
        int lastIdx=-1;

        for(int i=0;i<N;i++) {
            Water water=waters.get(i);
            int start=water.start;
            int length=water.length;

            if(start<lastIdx) {
                length-=lastIdx-start;
                start=lastIdx;

                if(length<=0) continue;
            }

            int adder=(length%L==0)?length/L:length/L+1;

            lastIdx=start+L*adder;
            result+=adder;
        }

        System.out.println(result);
    }

    static class Water implements Comparable<Water>{
        int start,length;

        public Water(int start,int length) {
            this.start=start;
            this.length=length;
        }

        @Override
        public int compareTo(Water o) {
            return start-o.start;
        }
    }

}
