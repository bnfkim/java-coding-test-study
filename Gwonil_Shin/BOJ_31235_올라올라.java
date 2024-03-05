import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int []arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr=new int[N];

        st=new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        int start=0;
        int size=1;

        for(int i=1;i<N;i++){
            if(arr[start]>arr[i]){
                if(i==N-1){
                    size=Math.max(N-start,size);
                }
                continue;
            }

            size=Math.max(i-start,size);
            start=i;
        }


        System.out.println(size);
    }
}