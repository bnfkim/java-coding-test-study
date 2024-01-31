package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1002_터렛 {
	static int x1,y1,r1,x2,y2,r2;
	
	static double distance() {
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		for(int i=0;i<t;i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			r1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			r2 = Integer.parseInt(st.nextToken());
			double dis = distance();
			if(dis==(r1+r2)) {
				System.out.println(1);
			}else if(dis>(r1+r2)){
				System.out.println(0);
			}else {
				if(dis==Math.abs(r1-r2)) {
					if(dis==0) {
						System.out.println(-1);
					}else {
						System.out.println(1);
					}
				}else if(dis<Math.abs(r1-r2)) {
					System.out.println(0);
				}else {
					System.out.println(2);
				}
			}
			
		}
	}

}
