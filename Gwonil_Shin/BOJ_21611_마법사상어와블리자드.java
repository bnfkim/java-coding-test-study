import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,M,result,cx,cy;
    static int [][] board;
    static int [][]mv={{0,-1},{1,0},{0,1},{-1,0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        board=new int[N][N];
        cx=cy=N/2;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                board[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int d=change_dir(Integer.parseInt(st.nextToken()));
            int s=Integer.parseInt(st.nextToken());

            blizzard(d,s);
            do{
                sweep();
            }while(explode());
            arrange();

        }

        System.out.println(result);
    }

    static int change_dir(int x){
        if(x==1)
            return 3;
        if(x==2)
            return 1;
        if(x==3)
            return 0;
        return 2;
    }

    static void blizzard(int dir, int size){
        int blizzard_dist=Math.min(size,N/2);
        int x=cx;
        int y=cy;

        do{
            x+=mv[dir][0];
            y+=mv[dir][1];

            board[x][y]=0;
        }while(--blizzard_dist>0);
    }

    static void sweep(){
        int dir=0;
        int dist=1;
        int level=0;
        int cnt=0;

        int x=cx;
        int y=cy;

        Deque<int[]> emptyPoints=new ArrayDeque<>();
        while(true){
            //좌표 이동
            x+=mv[dir][0];
            y+=mv[dir][1];

            if(y==-1){
                break;
            }

            if(++cnt==dist){
                if(++level==2) {
                    dist++;
                    level = 0;
                }

                if(++dir==4){
                    dir=0;
                }
                cnt = 0;

            }

            //0체크
            if(board[x][y]==0){
                emptyPoints.add(new int[]{x,y});
            }
            else if(!emptyPoints.isEmpty()){
                int []ep=emptyPoints.poll();

                board[ep[0]][ep[1]]=board[x][y];
                board[x][y]=0;
                emptyPoints.add(new int[]{x,y});
            }
        }

    }

    static boolean explode(){
        int dir=0;
        int dist=1;
        int level=0;
        int cnt=0;

        int x=cx;
        int y=cy;

        boolean find=false;

        Deque<int[]> samePoints=new ArrayDeque<>();
        int flag=0;
        //기준은 4개

        while(true){
            //좌표 이동
            x+=mv[dir][0];
            y+=mv[dir][1];

            if(y==-1){
                break;
            }

            if(++cnt==dist){
                if(++level==2) {
                    dist++;
                    level = 0;
                }

                if(++dir==4){
                    dir=0;
                }


                cnt = 0;

            }


            if(board[x][y]==flag){
                samePoints.add(new int[]{x,y});
            }
            else{
                if(samePoints.size()>=4){
                    //파괴
                    while(!samePoints.isEmpty()){
                        int []p=samePoints.poll();
                        board[p[0]][p[1]]=0;
                        result+=flag;
                    }
                    find=true;
                }
                else{
                    samePoints.clear();
                }
                samePoints.add(new int[]{x,y});
                flag=board[x][y];
            }
        }

        //마지막에도 확인
        if(flag!=0 && samePoints.size()>=4){
            while(!samePoints.isEmpty()){
                int []p=samePoints.poll();
                board[p[0]][p[1]]=0;
                result+=flag;
            }

            find=true;
        }

        return find;
    }

    static void arrange(){
        //재 정렬값 배치
        List<Integer> arrangeValues=new ArrayList<>();

        int dir=1;
        int dist=1;
        int level=1;
        int cnt=0;

        int x=cx;
        int y=cy-1;

        int samePoint=1;
        int flag=board[x][y];
        //기준은 4개

        while(true){
            //좌표 이동
            x+=mv[dir][0];
            y+=mv[dir][1];

            if(y==-1){
                break;
            }

            if(++cnt==dist){
                if(++level==2) {
                    dist++;
                    level = 0;
                }

                if(++dir==4){
                    dir=0;
                }


                cnt = 0;

            }


            if(board[x][y]==flag){
                samePoint++;
            }
            else{
                if(flag!=0) {
                    arrangeValues.add(samePoint);
                    arrangeValues.add(flag);
                }
                samePoint=1;
                flag=board[x][y];
            }
        }

        if(flag!=0 && samePoint!=0){
            arrangeValues.add(samePoint);
            arrangeValues.add(flag);
        }

        //다시 널기
        init(arrangeValues);
    }

    static void init(List<Integer> values){
        int[][] nextBoard=new int[N][N];
        int value_size=values.size();
        int idx=-1;

        int dir=0;
        int dist=1;
        int level=0;
        int cnt=0;

        int x=cx;
        int y=cy;

        while(++idx<value_size){

            //좌표 이동
            x+=mv[dir][0];
            y+=mv[dir][1];

            if(y==-1){
                break;
            }

            if(++cnt==dist){
                if(++level==2) {
                    dist++;
                    level = 0;
                }

                if(++dir==4){
                    dir=0;
                }


                cnt = 0;

            }

            nextBoard[x][y]= values.get(idx);
        }

        board=nextBoard;
    }
}