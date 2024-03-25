import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] gears=new int[4][8];
    static int K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i=0;i<4;i++){
            char[] input=br.readLine().toCharArray();

            for(int j=0;j<8;j++){
                gears[i][j]=input[j]-'0';
            }
        }

        K=Integer.parseInt(br.readLine());

        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine());
            int gearNum=Integer.parseInt(st.nextToken())-1;
            int pureRotate=Integer.parseInt(st.nextToken());

            int []rotate=new int[4];

            rotate[gearNum]=pureRotate;

            //이후것의 회전 여부 파악
            for(int j=gearNum;j<3;j++){
                if(gears[j][2]+gears[j+1][6]==1){
                    if(rotate[j]==1){
                        rotate[j+1]=-1;
                    }
                    else{
                        rotate[j+1]=1;
                    }
                }
                else{
                    break;
                }
            }

            //이전것의 회전 여부 파악
            for(int j=gearNum;j>0;j--){
                if(gears[j][6]+gears[j-1][2]==1){
                    if(rotate[j]==1){
                        rotate[j-1]=-1;
                    }
                    else{
                        rotate[j-1]=1;
                    }
                }
                else{
                    break;
                }
            }

            for(int j=0;j<4;j++){
                if(rotate[j]==0) continue;

                if(rotate[j]==1){ //정방향
                    int []nGear=new int[8];

                    nGear[0]=gears[j][7];
                    for(int k=1;k<8;k++){
                        nGear[k]=gears[j][k-1];
                    }

                    gears[j]=nGear;

                }
                else{ //역방향
                    int []nGear=new int[8];

                    nGear[7]=gears[j][0];
                    for(int k=0;k<7;k++){
                        nGear[k]=gears[j][k+1];
                    }

                    gears[j]=nGear;
                }
            }
        }

        int result=0;
        for(int i=0;i<4;i++){
            result+=(gears[i][0]<<i);
        }

        System.out.println(result);

    }
}