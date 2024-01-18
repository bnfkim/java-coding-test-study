import java.io.*;
import java.util.*;

public class Main {
    public static class Point{
        int x,y;

        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }


    }

    public static int arr[][],mv[][]={{-1,1},{0,1},{1,0},{1,1}};//가장 왼쪽을 기준으로 우상단,우측,하단,우하단으로 방향 설정

    public static boolean find(List<Point> rocks,int code){
        for(Point cur:rocks){
            for(int idx=0;idx<4;idx++){
                int[] dir=mv[idx];

                //6개 확인용 로직(시작돌의 설정방향의 바로이전에도 돌이 있는지 체크)
                boolean prev=true;
                int prev_x=cur.x-dir[0];
                int prev_y=cur.y-dir[1];

                if(prev_x<0||prev_x>=19||prev_y<0||prev_y>=19||arr[prev_x][prev_y]!=code){
                    prev=false;
                }

                //방향에 따른 5돌 및 6돌 확인
                boolean check=true;
                for(int i=1;i<=4;i++){
                    int nx=cur.x+dir[0]*i;
                    int ny=cur.y+dir[1]*i;

                    if(nx<0||nx>=19||ny<0||ny>=19||arr[nx][ny]!=code){
                        check=false;
                        break;
                    }

                }

                //6돌 체크 로직
                if(!prev&&check){
                    int nx=cur.x+dir[0]*5;
                    int ny=cur.y+dir[1]*5;
                    //범위 밖 상관x
                    //범위 안: not code

                    if(nx<0||nx>=19||ny<0||ny>=19||arr[nx][ny]!=code){
                        System.out.println(code);
                        System.out.println(cur.x+1+" "+(cur.y+1));
                        return true;
                    }

                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        arr=new int[19][19];
        List<Point> black=new ArrayList<>();
        List<Point> white=new ArrayList<>();

        for(int i=0;i<19;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<19;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 1) {
                    black.add(new Point(i, j));
                } else if (arr[i][j] == 2) {
                    white.add(new Point(i, j));
                }
            }
        }

        if(!find(black,1) && !find(white,2)) {
            System.out.println(0);
        }
    }
}