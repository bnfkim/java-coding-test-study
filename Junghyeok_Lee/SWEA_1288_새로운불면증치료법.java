package java_codingtest_study;

import java.util.*;
import java.lang.*;
import java.io.*;

public class SWEA_1288_새로운불면증치료법 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int t=1;t<tc+1;t++){
            int n = sc.nextInt();
            System.out.printf("#%d %d%n",t,countSheep(n));
        }

	}
	
	static int countSheep(int x){
        HashSet<Integer> set = new HashSet<Integer>();
        int tmp = x;
        int cnt=1;
        while(true){
            while(tmp>0){
                int t = tmp%10;
                if(!set.contains(t)){
                    set.add(t);
                }
                tmp=tmp/10;
            }
            if(set.size()==10){
                tmp=x*cnt;
                break;
            }
            cnt++;
            tmp=x*cnt;
        }
        return tmp;
    }

}
