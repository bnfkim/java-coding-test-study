package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
시작 시간 : 24-01-20 22:30
종료 시간 : 24-01-20 02:50
실행 시간 : 100ms / 실패
메 모 리 : 13100KB

고려사항
정답 참조
문제는 간단하게 조합의 수를 구하는 문제였지만
입력값의 범위가 100까지이기 때문에 자료형 범위를 고려해야하는 문제였습니다

long범위도 넘어가는 숫자가 존재하기 때문에 BigInteger를 사용하여
계산해야합니다. 범위가 무한대라고 할 수 있는 BigInteger를 사용했습니다.
*/

public class BOJ_2407_조합_S3 {

    static int N, M, cnt;
    static Map<String, BigInteger> map = new HashMap<>();
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        System.out.println(combi(N, M));
    }

    private static BigInteger combi(int n, int m) {

        if(m == 0 || n == m) return BigInteger.ONE;

        String key = Integer.toString(n) + ','+m;
        if(!map.containsKey(key)){
            map.put(key, combi(n-1,m-1).add(combi(n-1,m)));
        }else{
            cnt++;
        }
        return map.get(key);

    }
}