import java.io.*;
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        final int LIMIT = 100000000;
        int[] dp = new int[LIMIT];
        
        int max = N;
        for (int i = 1; i <= 8; ++i) {
            // N를 i개 이어붙인 숫자
            dp[makeSeqNum(N, i)] = i;
            
            // 이전까지 만들 수 있던 숫자와 N의 연속으로 만든 숫자와 연산해 새로운 숫자를 확인
            for (int num = 0; num < Math.pow(10, i); ++num) {
                if (dp[num] == 0 || dp[num] == i) continue;
                
                // num 을 만드는데 N이 m회 사용되었을 때
                // N을 i-m 개 이어붙인 수(=operand)와 num을 조합해 N이 i개 사용된 수를 만든다.
                int m = dp[num];
                int operand = makeSeqNum(N, i - m);
                
                if (dp[num * operand] == 0) dp[num * operand] = i;
                if (dp[num + operand] == 0) dp[num + operand] = i;
                if (dp[Math.abs(num - operand)] == 0) dp[Math.abs(num - operand)] = i;
                if (dp[num / operand] == 0) dp[num / operand] = i;
                if (num != 0 && dp[operand / num] == 0) dp[operand / num] = i;
            }
            System.out.println();
            if (dp[number] != 0) return dp[number];
        }
        
        return -1;
    }
    
    // N을 i개 이어붙여 만든 숫자 반환
    public int makeSeqNum(int N, int i) {
        int result = N;
        
        while (i-- > 1)
            result = result * 10 + N;
        
        return result;
    }
}
