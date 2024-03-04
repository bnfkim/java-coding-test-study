package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,C;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int start = 1; //항상 참이 되는 값
        int end = arr[N - 1] - arr[0]+1; //항상 거짓이 되는 값

        while (start +1< end) { //참의 다음은 거짓보다 작아야한다.
            int mid = (start + end) >> 1;
            int cnt=1;

            int cur=arr[0]+mid;

            for(int i=1;i<N;i++){
                if(arr[i]>=cur){
                    cnt++;
                    cur=arr[i]+mid;
                }
            }

            if(cnt>=C){
                start=mid;
            }
            else if(cnt<C){
                end=mid;
            }

        }

        System.out.println(start);
    }
}