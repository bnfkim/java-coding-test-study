import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int n=101;
    static boolean[][] arr=new boolean[n][n];
    static int[][]mv= {{1,0},{0,-1},{-1,0},{0,1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            Deque<Integer> curves=new ArrayDeque<>();


            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int dir=Integer.parseInt(st.nextToken());
            int gen=Integer.parseInt(st.nextToken());

            arr[x][y]=true;
            curves.add(dir);

            x+=mv[dir][0];
            y+=mv[dir][1];
            arr[x][y]=true;


            for(int t=1;t<=gen;t++){
                Deque<Integer> next_curves=new ArrayDeque<>();
                for(int cur_dir:curves){
                    int next=cur_dir+1;

                    if(next==4){
                        next=0;
                    }

                    x+=mv[next][0];
                    y+=mv[next][1];
                    arr[x][y]=true;

                    next_curves.addFirst(next);
                }

                for(int curve:curves){
                    next_curves.addLast(curve);
                }

                curves=next_curves;
            }
        }

        int answer=0;
        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                if(arr[i][j]&&arr[i+1][j]&&arr[i][j+1]&&arr[i+1][j+1])
                    answer+=1;
            }
        }

        System.out.println(answer);

    }


}