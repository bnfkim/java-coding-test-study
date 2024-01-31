import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int n,arr[][],students[][],mv[][]={{-1,0},{1,0},{0,-1},{0,1}};
    static Map<Integer,Integer> numToIdx=new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        arr=new int[n][n];
        students= new int [n*n][5];

        for(int i=0;i<n*n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<5;j++){
                students[i][j]=Integer.parseInt(st.nextToken());
            }
            numToIdx.put(students[i][0],i);
        }

        arr[1][1]=students[0][0];

        for(int i=1;i<n*n;i++){
            List<Point> ps=new ArrayList<>();
            for(int x=n-1;x>=0;x--){
                for(int y=n-1;y>=0;y--){
                    if(arr[x][y]==0){
                        //4방으로
                        int cur_fav=0;
                        int cur_blank=0;

                        for(int[] next:mv){
                            int nx=x+next[0];
                            int ny=y+next[1];

                            if(nx<0||nx==n||ny<0||ny==n){
                                continue;
                            }

                            if(arr[nx][ny]==0){
                                cur_blank+=1;
                            }
                            else {
                                for (int fav_idx = 1; fav_idx <= 4; fav_idx++) {
                                    if (arr[nx][ny] == students[i][fav_idx]) {
                                        cur_fav += 1;
                                        break;
                                    }
                                }
                            }
                        }
                        ps.add(new Point(x,y,cur_fav,cur_blank));
                    }
                }
            }
            Collections.sort(ps);
            Point find=ps.get(0);
            arr[find.x][find.y]=students[i][0];
        }

        int answer=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                int s_idx=numToIdx.get(arr[i][j]);
                int cur_fav=0;
                int adder=0;
                for (int[] next : mv) {
                    int nx = i + next[0];
                    int ny = j + next[1];

                    if(nx<0||nx==n||ny<0||ny==n){
                        continue;
                    }

                    for (int fav_idx = 1; fav_idx <= 4; fav_idx++) {
                        if (arr[nx][ny] == students[s_idx][fav_idx]) {
                            cur_fav += 1;
                            break;
                        }
                    }
                }

                if(cur_fav>0){
                    adder=1;

                    for(int mul=0;mul<cur_fav-1;mul++){
                        adder*=10;
                    }
                }
                answer+=adder;

            }
        }

        System.out.println(answer);
    }

    public static class Point implements Comparable<Point>{
        int x,y,favor_num,blank_num;

        public Point(int x,int y,int favor_num,int blank_num){
            this.x=x;
            this.y=y;
            this.favor_num=favor_num;
            this.blank_num=blank_num;
        }

        @Override
        public int compareTo(Point o) {
            if(favor_num==o.favor_num){
                if(blank_num==o.blank_num){
                    if(x==o.x){
                        return y-o.y;
                    }
                    return x-o.x;
                }
                return o.blank_num-blank_num;
            }
            return o.favor_num-favor_num;
        }
    }
}

