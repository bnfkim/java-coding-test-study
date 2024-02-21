import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_2910_빈도정렬 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
        int N = Integer.parseInt(st.nextToken());
        st.nextToken();
        
        st = new StringTokenizer(br.readLine());
        int tmp, size=0, tmp2=0;
        boolean flag=false;
        ArrayList<Integer> arl1 = new ArrayList<Integer>();
        ArrayList<Integer> arl2 = new ArrayList<Integer>();
        
        for (int i = 0; i < N; i++ ) {
            tmp = Integer.parseInt(st.nextToken());
            if (size == 0) {
                arl1.add(tmp);
                arl2.add(1);
                size++;
                continue;
            } 
            flag=false;
            for (int j=0; j < size; j++ ) {
                if (arl1.get(j) == tmp) {
                    tmp2 = arl2.get(j);
                    arl2.set(j, tmp2+1);
                    flag=true;
                    break;
                }
            }
            if (!flag) {
                arl1.add(tmp);
                arl2.add(1);
                size++;
            }
        }
        Integer[] indexes = new Integer[size];
        Integer[] arl22 = new Integer[size];
        for (int i = 0; i < size; i++ ) {
            arl22[i] = arl2.get(i);
            indexes[i] = i;
        }

        Arrays.sort(indexes, new Comparator<Integer>() {
		    @Override public int compare(Integer o1, Integer o2) {
		        return Integer.compare(arl22[o2], arl22[o1]);
		    }
		});

        int cnt;
        for (int i : indexes) {
            cnt = arl2.get(i);
            while (cnt > 0) {
                sb.append(arl1.get(i) + " ");
                cnt--;
            }
        }
        System.out.println(sb);
    }
}
