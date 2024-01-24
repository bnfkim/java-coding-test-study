import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2529_부등호 {

    static int k;
    static String[] sign;
    static ArrayList<String> results = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력받기
        k = Integer.parseInt(br.readLine());
        sign = br.readLine().split(" ");

        int[] nums = new int[k+1]; //숫자저장 배열
        boolean[] visit = new boolean[10];

        insert(0, nums, visit);

        Collections.sort(results);
        System.out.println(results.get(results.size()-1));
        System.out.println(results.get(0));
    }
    public static void insert(int idx, int[] nums, boolean[] visit) {

        //종료조건1
        if(idx == k+1) {
            results.add(convertString(nums));
            return;
        }

        for(int i=0; i<10; i++) {
            if(visit[i]) continue;

            if(idx != 0) {
                if(sign[idx-1].equals("<")) {
                    if (nums[idx-1] > i) continue;
                }
                else {
                    if (nums[idx-1] < i) continue;
                }
            }

            visit[i] = true;
            nums[idx] = i;
            insert(idx+1, nums, visit);
            nums[idx] = 0;
            visit[i] = false;
        }
    }

    static String convertString(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for(int num : nums) {
            sb.append(num);
        }
        return sb.toString();
    }
}