import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] answer;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        int N=Integer.parseInt(br.readLine());

        answer=new boolean[N][N];

        check(0,0,N);

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(answer[i][j]) sb.append('*');
                else sb.append(' ');
            }
            sb.append('\n');
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }

    static void check(int x,int y,int n) {
        if(n==1) {
            answer[x][y]=true;
            return;
        }

        int nn=n/3;

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(i==1 && j==1) {
                    continue;
                }
                check(x+i*nn,y+j*nn,nn);
            }
        }

    }
}
