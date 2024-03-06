import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[][] board=new long[8][8];
    static int N=8;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());

            for(int j=0;j<N;j++) {
                board[i][j]=Long.parseLong(st.nextToken());
            }
        }

        String op=br.readLine();

        if(op.equals("U")) {
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(board[i][j]==0) continue;

                    int nx=i+1;

                    while(nx<N&&board[nx][j]==0) {
                        nx++;
                    }

                    if(nx!=N&&board[nx][j]==board[i][j]) {
                        board[i][j]*=2;
                        board[nx][j]=0;
                    }

                    int px=i-1;

                    while(px>=0&&board[px][j]==0){
                        px--;
                    }

                    long tmp=board[i][j];
                    board[i][j]=0;
                    board[px+1][j]=tmp;
                }
            }
        }
        else if(op.equals("D")) {
            for(int i=N-1;i>=0;i--) {
                for(int j=0;j<N;j++) {
                    if(board[i][j]==0) continue;

                    int nx=i-1;

                    while(nx>=0&&board[nx][j]==0) {
                        nx--;
                    }

                    if(nx!=-1&&board[nx][j]==board[i][j]) {
                        board[i][j]*=2;
                        board[nx][j]=0;
                    }

                    int px=i+1;

                    while(px<N&&board[px][j]==0){
                        px++;
                    }

                    long tmp=board[i][j];
                    board[i][j]=0;
                    board[px-1][j]=tmp;
                }
            }
        }
        else if(op.equals("L")) {
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(board[j][i]==0) continue;

                    int ny=i+1;

                    while(ny<N&&board[j][ny]==0) {
                        ny++;
                    }

                    if(ny!=N&&board[j][ny]==board[j][i]) {
                        board[j][i]*=2;
                        board[j][ny]=0;
                    }

                    int py=i-1;

                    while(py>=0&&board[j][py]==0){
                        py--;
                    }

                    long tmp=board[j][i];
                    board[j][i]=0;
                    board[j][py+1]=tmp;
                }
            }
        }
        else {
            for(int i=N-1;i>=0;i--) {
                for(int j=0;j<N;j++) {
                    if(board[j][i]==0) continue;

                    int ny=i-1;

                    while(ny>=0&&board[j][ny]==0) {
                        ny--;
                    }

                    if(ny!=-1&&board[j][ny]==board[j][i]) {
                        board[j][i]*=2;
                        board[j][ny]=0;
                    }

                    int py=i+1;

                    while(py<N&&board[j][py]==0){
                        py++;
                    }

                    long tmp=board[j][i];
                    board[j][i]=0;
                    board[j][py-1]=tmp;
                }
            }
        }

        //출력
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

}
