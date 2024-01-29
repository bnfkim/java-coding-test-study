import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWA_1288_새로운불면증치료법{
    public static int N;
    public static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine());
            nums = new int[10];

            int idx = 1;
            int result;
            while(true) {
                int num = idx * N;
                insertNum(num);

                if(checkNum()) {
                    result = num;
                    break;
                }
                idx++;
            }

            System.out.println("#" + (tc+1) + " " + result);
        }
    }

    public static void insertNum(int num) {
        while(num > 0) {
            int tmp = num%10;
            nums[tmp]++;
            num = num/10;
        }
    }

    public static boolean checkNum() {
        for(int i=0; i<10; i++) {
            if(nums[i] == 0) return false;
        }
        return true;
    }
}