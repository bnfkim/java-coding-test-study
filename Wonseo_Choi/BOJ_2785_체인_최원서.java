package com.ssafy.bkjun;

import java.io.*;
import java.util.*;

public class BOJ_2785_체인_최원서 {
    static int[] Arr;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++ ) {
            Arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(Arr);

        int size = N-1, point=0;
        int ans=0;
        while (true) {
            if (point >= size) break;
            if (--Arr[point] == 0) point++;
            
            if (size == 0) break;
            Arr[size-1] += Arr[size]+1;
            Arr[size--] = 0;
            ans++;
        }
        System.out.println(ans);
    }
}
