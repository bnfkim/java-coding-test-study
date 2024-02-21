package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class Point{
	int x;
	int y;
	
	Point (int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class BOJ_11556_PuyoPuyo {
	
	static char[][] arr;
	static Deque<Point> dq;
	static List<Point> tmpList; 
	static boolean[][] visited;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new char[12][6];
		
		for(int i=0;i<12;i++) {
			String s = br.readLine();
			for(int j=0;j<6;j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		
		int res=0;
		while(true) {
			visited = new boolean[12][6];
			boolean flag=false;
			
			for(int i=0;i<12;i++) {
				for(int j=0;j<6;j++) {
					if(arr[i][j]!='.') {
						dq = new ArrayDeque<>();
						tmpList = new ArrayList<>();
						
						visited[i][j]=true;
						int cnt=1;
						dq.offer(new Point(i,j));
						tmpList.add(new Point(i,j));
						while(!dq.isEmpty()) {
							Point p = dq.poll();
							
							for(int k=0;k<4;k++) {
								int xx = dx[k]+p.x;
								int yy = dy[k]+p.y;
								if((0<=xx)&&(xx<12)&&(yy>=0)&&(yy<6)&&(arr[xx][yy]==arr[p.x][p.y])&&(!visited[xx][yy])) {
									visited[xx][yy]=true;
									cnt+=1;
									dq.offer(new Point(xx,yy));
									tmpList.add(new Point(xx,yy));
								}
							}
						}
						
						if(cnt>=4) {
							for(int k=0;k<cnt;k++) {
								Point p = tmpList.get(k);
								arr[p.x][p.y]='.';
								flag=true;
							}
						}	
					}
				}
			}
			
			if(!flag) {
				System.out.println(res);
				break;
			}
			
			res+=1;
			for(int i=10;i>=0;i--) {
				for(int j=0;j<6;j++) {
					if(((arr[i][j]!='.')&&(arr[i+1][j]=='.'))) {
						int idx=i;
						while((idx+1<12)&&(arr[idx+1][j]=='.')) {
							idx++;
						}
						arr[idx][j]=arr[i][j];
						arr[i][j]='.';
					}
				}
			}
			
		}
	}

}
