import java.util.*;

public class 과자나눠주기 {
    public static void main(String[] args) {


        Scanner sc= new Scanner(System.in);

        int M=sc.nextInt();
        int N=sc.nextInt();

        int[] arr = new int[N];

        for(int i=0;i<N;i++)
        {
            arr[i]=sc.nextInt();
        }

        Arrays.sort(arr);

        long left=1;
        long right=arr[N-1];
        long answer=0;


        while(left<=right)
        {
            int cnt=0;
            long mid= (right+left)/2;

            for(int i=0;i<N;i++) cnt+=arr[i]/mid;

            if(cnt >=M){
                if(answer <mid) answer=mid;
                left=mid+1;
            }else
            {
                right=mid-1;
            }
        }
        System.out.println(answer);
    }
}
