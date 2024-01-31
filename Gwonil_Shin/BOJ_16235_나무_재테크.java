import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Point implements Comparable<Point>{
        int x,y,level;

        public Point(int x,int y,int level){
            this.x=x;
            this.y=y;
            this.level=level;
        }

        @Override
        public int compareTo(Point o) {
            return this.level-o.level;
        }
    }

    public static int n,m;
    public static int[][] arr,add,mv={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

    public static List<Point>[][] tree;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        PriorityQueue<Point> trees=new PriorityQueue<>();
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        arr=new int[n][n];
        add=new int[n][n];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                add[i][j]=Integer.parseInt(st.nextToken());
                arr[i][j]=5;
            }
        }

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int level=Integer.parseInt(st.nextToken());
            trees.add(new Point(x-1,y-1,level));
        }


        for(int year=0;year<k;year++){
            PriorityQueue<Point> new_trees=new PriorityQueue<>();
            Deque<Point> dead_tree=new ArrayDeque<>();
            //봄
            while (!trees.isEmpty()){
                Point cur=trees.poll();

                if(arr[cur.x][cur.y]<cur.level) {
                    dead_tree.add(cur);
                }
                else{
                    arr[cur.x][cur.y]-=cur.level;
                    cur.level+=1;
                    new_trees.add(cur);
                }
            }
            //여름
            while (!dead_tree.isEmpty()){
                Point cur=dead_tree.poll();

                arr[cur.x][cur.y]+=cur.level/2;
            }
            //가을
            while (!new_trees.isEmpty()){
                Point p=new_trees.poll();

                if(p.level%5==0){
                    for(int[] next:mv){
                        int nx=p.x+next[0];
                        int ny=p.y+next[1];

                        if(nx<0||nx==n||ny<0||ny==n){
                            continue;
                        }
                        trees.add(new Point(nx,ny,1));
                    }
                }
                trees.add(p);
            }
            //겨울
            for(int i=0;i<n;i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] += add[i][j];
                }
            }
        }

        System.out.println(trees.size());
    }
}
