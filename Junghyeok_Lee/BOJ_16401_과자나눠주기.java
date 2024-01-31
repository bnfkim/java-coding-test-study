package java_codingtest_study;

import java.util.*;

public class BOJ_16401_과자나눠주기 {
	
	static int[] arr;
	static int m;
	static int n;
	
	static int divide(int mid) {
		int tmp=0;
		for(int i=0;i<n;i++) {
			tmp+=arr[i]/mid;
			if(tmp>=m) {
				break;
			}
		}
		return tmp;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		m=sc.nextInt(); //조카의 수
		n=sc.nextInt(); //과자의 수
		
		arr = new int[n];
		int left=1;
		int right=1;
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
			if(right<arr[i]) {
				right=arr[i];
			}
		}
		
		int res=0;
		while(left<=right) {
			int mid = (left+right)/2;
			int tmp=divide(mid);
			if(tmp>=m) {
				left=mid+1;
				res=mid;
			}else {
				right=mid-1;
			}
		}
		
		System.out.println(res);
	}

}
