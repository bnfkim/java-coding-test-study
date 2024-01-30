import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int k;
    static String[] sign;
    static int[] nums;
    static boolean[] visit;
    static ArrayList<String> results = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력받기
        k = Integer.parseInt(br.readLine());
        sign = br.readLine().split(" ");

        nums = new int[k+1]; //숫자저장 배열
        visit = new boolean[10];

        insert(0);

        Collections.sort(results);
        System.out.println(results.get(results.size()-1));
        System.out.println(results.get(0));
    }
    public static void insert(int idx) {
        if(idx == k+1) {
            results.add(ConvertToString(nums));
            return;
        }

        for(int i=0; i<10; i++) {
            if(visit[i]) continue;
            if(idx != 0 && isNotInvalidSign(idx, i)) continue;

            visit[i] = true;
            nums[idx] = i;

            insert(idx+1);

            //백 트래킹
            nums[idx] = 0;
            visit[i] = false;
        }
    }

    public static boolean isNotInvalidSign(int idx, int currentNum) {
        if (sign[idx-1].equals("<")) {
            return nums[idx-1] > currentNum;
        } else {
            return nums[idx-1] < currentNum;
        }
    }

    static String ConvertToString(int[] nums) {
        return Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }
}