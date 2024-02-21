import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    static int R=12,C=6;
    static char [][]field=new char[R][C];
    static int[][] mv={{1,0},{-1,0},{0,1},{0,-1}};
    static boolean[][]visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int answer=0;

        for(int i=0;i<R;i++){
            String input=br.readLine();
            for(int j=0;j<C;j++){
                field[i][j]=input.charAt(j);
            }
        }

        while(is_break()){ //연쇄 발생의 최소조건(블록이 사라지는지 확인)
            down();
            answer++;
        }

        System.out.println(answer);
    }

    static boolean is_break(){
        boolean exist=false;
        visited=new boolean[R][C];

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(field[i][j]!='.'&&!visited[i][j]){
                    if(check(i,j,field[i][j])){ //빈칸이 아닌 블록일 경우 해당 블록이 사라지는 블록인지 확인.
                        exist=true;
                    }
                }
            }
        }

        return exist;
    }

    static boolean check(int r,int c,char kind){
        List<int[]> puyo=new ArrayList<>(); //연결되어진 블록을 모아둠.
        Deque<int[]> dq=new ArrayDeque<>();

        dq.add(new int[]{r,c});
        puyo.add(new int[]{r,c});
        visited[r][c]=true;

        while(!dq.isEmpty()) {
            int[] p=dq.pop();

            for(int []next:mv){
                int nx=p[0]+next[0];
                int ny=p[1]+next[1];

                if(nx<0||nx==R||ny<0||ny==C||visited[nx][ny]||field[nx][ny]!=kind){
                    continue;
                }

                visited[nx][ny]=true;
                dq.addLast(new int[]{nx,ny});
                puyo.add(new int[]{nx,ny});
            }
        }


        if(puyo.size()>=4){ //4개 이상 연결되어져있다면 제거
            for(int[] p:puyo){
                field[p[0]][p[1]]='.';
            }

            return true;
        }

        return false;
    }

    static void down(){
        //아래부터 시작해야 내리기 편해진다.
        for(int i=R-2;i>=0;i--){ //맨 아랫줄은 의미가 없음
            for(int j=C-1;j>=0;j--){
                if(field[i][j]!='.'){
                    int nx=i+1,ny=j;

                    while(true){
                        if(nx==R){ //범위를 초과
                            break;
                        }

                        if(field[nx][ny]!='.'){ //빈칸이 아님
                            break;
                        }
                        nx++;
                    }
                    
                    //칸을 바꾸는 과정
                    char tmp=field[i][j];
                    field[i][j]='.';
                    field[nx-1][ny]=tmp;
                }
            }
        }
    }
}