import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import javax.swing.plaf.IconUIResource;

public class 빗물14719 {
    static int[] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());

        arr = new int[M];

        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum=0;

        for(int i=1;i<arr.length-1;i++)
        {
            int current=arr[i];


            int leftMax=0;

            for(int j=0;j<=i;j++)
            {
                leftMax=Math.max(leftMax,arr[j]);

            }

            int rightMax=0;
            for(int j=i;j<M;j++)
            {
                rightMax=Math.max(rightMax,arr[j]);
            }

            sum+=Math.min(leftMax,rightMax)-current;

        }

        if(sum<0) sum=0;

        System.out.println(sum);

    }
}

// 14719 , 100ms

