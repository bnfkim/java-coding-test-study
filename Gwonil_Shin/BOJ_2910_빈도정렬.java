import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static int n;
    static int[] arr;
    static List<Integer> subset=new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        int n=Integer.parseInt(st.nextToken());
        int c=Integer.parseInt(st.nextToken());

        Map<Integer,Integer> numbers=new HashMap<>();
        Map<Integer,Integer> key_index=new HashMap<>();
        st=new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++) {
            int input=Integer.parseInt(st.nextToken());

            if(!numbers.containsKey(input)) {
                numbers.put(input, 0);
                key_index.put(input,i);
            }
            numbers.put(input, numbers.get(input)+1);
        }

        List<Integer> sort=numbers.keySet().stream()
                .sorted((o1,o2)->{
                    if(numbers.get(o1)==numbers.get(o2)) {
                        return key_index.get(o1)-key_index.get(o2);
                    }
                    return numbers.get(o2).compareTo(numbers.get(o1));
                }).collect(Collectors.toList());

        for(int x:sort) {
            for(int i=0,size=numbers.get(x);i<size;i++) {
                sb.append(x).append(" ");
            }
        }

        System.out.println(sb);
    }


}
