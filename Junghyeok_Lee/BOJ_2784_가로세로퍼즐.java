package java_codingtest_study;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2784_가로세로퍼즐 {
	
	static String[] s = new String[6];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<6;i++) {
			s[i]=sc.next();
		}
		
		int[] check1 = new int[6];
		int res = 0;
		loop1:
		for(int i=0; i<6;i++) {
			check1[i]=1;
			for(int j=0;j<6;j++) {
				if(check1[j]==1) {
					continue;
				}
				check1[j]=1;
				for(int k=0;k<6;k++) {
					if(check1[k]==1) {
						continue;
					}
					check1[k]=1;
					
					int[] check2 = new int[3];
					String tmp0 = ""+s[i].charAt(0)+s[j].charAt(0)+s[k].charAt(0);
					String tmp1 = ""+s[i].charAt(1)+s[j].charAt(1)+s[k].charAt(1);
					String tmp2 = ""+s[i].charAt(2)+s[j].charAt(2)+s[k].charAt(2);
					
					res=0;
					for(int l=0;l<6;l++) {
						if(check1[l]==0) {
							if(check2[0]==0&&s[l].equals(tmp0)) {
								check2[0]=1;
								res+=1;
							}
							else if(check2[1]==0&&s[l].equals(tmp1)) {
								check2[1]=1;
								res+=1;
							}else if(check2[2]==0&&s[l].equals(tmp2)) {
								check2[2]=1;
								res+=1;
							}
						}
					}
					if(res==3) {
						System.out.println(s[i]);
						System.out.println(s[j]);
						System.out.println(s[k]);
						break loop1;
					}
					
					check1[k]=0;
				}
				check1[j]=0;
			}
			check1[i]=0;
		}
		
		if(res!=3) {
			System.out.println(0);
		}
		
	}

}
