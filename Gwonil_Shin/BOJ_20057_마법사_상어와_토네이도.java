import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,arr[][],mv[][]={{0,-1},{1,0},{0,1},{-1,0}};

    static boolean outOfRange(int x,int y){
        return x<0||x>=n||y<0||y>=n;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());

        arr=new int[n][n];
        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int x=n/2,y=n/2,change_cnt=0,cnt=0,answer=0,idx=0,moving_dist=1;

        while(x!=0||y!=0){
            int nx=x+mv[idx][0];
            int ny=y+mv[idx][1];

            if(arr[nx][ny]!=0){
                int using=0; //사용량

                if(idx%2==0){ //가로
                    //1% x+-1, y
                    int adder=(int)(arr[nx][ny]*0.01);
                    for(int i=-1;i<=1;i+=2){
                        int px=x+i;

                        if(outOfRange(px,y)){
                            answer+=adder;
                        }
                        else{
                            arr[px][y]+=adder;
                        }
                        using+=adder;
                    }
                    //7% x+-1 ny
                    adder=(int)(arr[nx][ny]*0.07);
                    for(int i=-1;i<=1;i+=2){
                        int px=x+i;

                        if(outOfRange(px,ny)){
                            answer+=adder;
                        }
                        else{
                            arr[px][ny]+=adder;
                        }
                        using+=adder;
                    }
                    //2% x+-2  ny
                    adder=(int)(arr[nx][ny]*0.02);
                    for(int i=-2;i<=2;i+=4){
                        int px=x+i;

                        if(outOfRange(px,ny)){
                            answer+=adder;
                        }
                        else{
                            arr[px][ny]+=adder;
                        }
                        using+=adder;
                    }
                    //10% x+-1 ny+mv[idx][1]
                    adder=(int)(arr[nx][ny]*0.1);
                    for(int i=-1;i<=1;i+=2){
                        int px=x+i;
                        int py=y+mv[idx][1]*2;

                        if(outOfRange(px,py)){
                            answer+=adder;
                        }
                        else{
                            arr[px][py]+=adder;
                        }
                        using+=adder;
                    }
                    //5% x y+mv[idx][1]*3
                    adder=(int)(arr[nx][ny]*0.05);
                    if(outOfRange(x,y+mv[idx][1]*3)){
                        answer+=adder;
                    }
                    else{
                        arr[x][y+mv[idx][1]*3]+=adder;
                    }
                    using+=adder;
                    //a x y+mv[idx][1]*2
                    if(outOfRange(x,y+mv[idx][1]*2)){
                        answer+=arr[nx][ny]-using;
                    }
                    else{
                        arr[x][y+mv[idx][1]*2]+=arr[nx][ny]-using;
                    }
                }
                else{//세로
                    //1% x , y+-1
                    int adder= (int) (arr[nx][ny]*0.01);
                    for(int i=-1;i<=1;i+=2){
                        int py=y+i;

                        if(outOfRange(x,py)){
                            answer+=adder;
                        }
                        else{
                            arr[x][py]+=adder;
                        }

                        using+=adder;
                    }
                    //7% nx ,y+-1
                    adder=(int)(arr[nx][ny]*0.07);
                    for(int i=-1;i<=1;i+=2){
                        int py=y+i;

                        if(outOfRange(nx,py)){
                            answer+=adder;
                        }
                        else{
                            arr[nx][py]+=adder;
                        }

                        using+=adder;
                    }
                    //2% nx , y+-2
                    adder=(int)(arr[nx][ny]*0.02);
                    for(int i=-2;i<=2;i+=4){
                        int py=y+i;

                        if(outOfRange(nx,py)){
                            answer+=adder;
                        }
                        else{
                            arr[nx][py]+=adder;
                        }

                        using+=adder;
                    }
                    //10% x+mv[idx][0]*2, y+-1
                    adder=(int)(arr[nx][ny]*0.1);
                    for(int i=-1;i<=1;i+=2){
                        int py=y+i;
                        int px=x+mv[idx][0]*2;
                        if(outOfRange(px,py)){
                            answer+=adder;
                        }
                        else{
                            arr[px][py]+=adder;
                        }

                        using+=adder;
                    }
                    //5%  x+mv[idx][0]*3,y
                    adder=(int)(arr[nx][ny]*0.05);
                    int px=x+mv[idx][0]*3;
                    if(outOfRange(px,y)){
                        answer+=adder;
                    }
                    else{
                        arr[px][y]+=adder;
                    }
                    using+=adder;
                    //a   x+mv[idx][0]*2  ,y
                    if(outOfRange(x+mv[idx][0]*2,y)){
                        answer+=arr[nx][ny]-using;
                    }
                    else{
                        arr[x+mv[idx][0]*2][y]+=arr[nx][ny]-using;
                    }
                }
            }


            cnt++;
            if(cnt==moving_dist){
                change_cnt++;
                cnt=0;

                idx++;
                if(idx==4){
                    idx=0;
                }

                if(moving_dist!=n-1&&change_cnt==2){
                    moving_dist++;
                    change_cnt=0;
                }
            }

            x=nx;
            y=ny;

        }

        System.out.println(answer);
    }
}