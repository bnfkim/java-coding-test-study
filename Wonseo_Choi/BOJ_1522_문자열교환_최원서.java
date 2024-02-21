package com.ssafy.bkjun;

import java.io.*;
import java.util.*;

public class BOJ_1522_문자열교환_최원서 {
    static int[] Arr;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cArr = br.readLine().toCharArray();
        int cnt=0, size=cArr.length;
        for (char c : cArr) if (c=='a') cnt++;
        
        int ans=Integer.MAX_VALUE, tmp, p;
        for (int i = 0; i < size; i++ ) {
            tmp=0;
            for (int j = 0; j < cnt; j++ ) {
                p = (i+j) % size;
                if (cArr[p]=='b') tmp++;
            }
            ans = Math.min(ans, tmp);
        }
        System.out.println(ans);
    }
}
