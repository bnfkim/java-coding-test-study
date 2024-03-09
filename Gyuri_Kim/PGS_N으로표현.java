import java.util.*;

class Solution {

    public int solution(int N, int number) {
        if(N == number) return 1;

        //가능한 숫자들의 집합을 담을 리스트 초기화
        ArrayList<Set<Integer>> dp = new ArrayList<>();
        for(int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }

        //i만큼 붙인 숫자 넣기
        for(int i = 1; i <= 8; i++) {
            int num = Integer.parseInt(String.valueOf(N).repeat(i));
            dp.get(i).add(num);
        }

        //i = N의 사용횟수
        for(int i = 2; i <= 8; i++) {
            //i보다 더 작은 숫자끼리 연산을 해야함
            for(int j = 1; j < i; j++) {
                int k = i - j;

                Set<Integer> set1 = dp.get(j);
                Set<Integer> set2 = dp.get(k);

                for(int num1 : set1) {
                    for(int num2 : set2) {
                        dp.get(i).add(num1 + num2);
                        dp.get(i).add(num1 - num2);
                        dp.get(i).add(num1 * num2);

                        if(num2 != 0) dp.get(i).add(num1 / num2);
                    }
                }
            }
            //해당 값이 있으면 갯수 반환
            if(dp.get(i).contains(number)) {
                return i;
            }
        }

        return -1;
    }
}
