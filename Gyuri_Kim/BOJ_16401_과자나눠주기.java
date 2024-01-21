import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16401_과자나눠주기 {
    //방법 : 정렬 + 이분탐색 + 몇 명에게 나누어줄 수 있는지 체크
    public static int n,m;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); //조카의 수 (1 ≤ M ≤ 1,000,000)
        n = Integer.parseInt(st.nextToken()); //과자의 수
        arr = new int[n]; //막대 과자 길이 배열

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int result = solve();
        System.out.println(result);
    }

    private static int solve() {
        //이분탐색을 위한 정렬
        Arrays.sort(arr);

        int left = 1;
        int right = arr[arr.length-1];
        int result = 0;
        //이분탐색
        while(left <= right) {
            int cnt = 0;
            int mid = (left+right)/2;

            //해당 막대과자로 몇 명이 나누어 먹을 수 있는지 확인
            for(int i=0; i<n; i++) {
                //과자의 길이를 임의값 길이만큼 나누면
                //해당 길이로 못 먹는지 (0), 한 명만 먹을 수 있는지(1)
                //한 명이상 먹을 수 있는지 (2+) 확인 가능하다
                cnt += arr[i]/mid;
            }

            if(cnt >= m) { //많으면 -> 길이를 더 늘려야함
                result = mid;
                left = mid +1;
            } else { //부족하면 -> 길이를 더 줄여야함
                right = mid-1;
            }
        }
        return result;
    }
}