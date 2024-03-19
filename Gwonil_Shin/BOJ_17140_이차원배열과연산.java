import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R,C,K;
    static int R_SIZE=3,C_SIZE=3;

    static int[][] arr=new int[3][3];
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        R=Integer.parseInt(st.nextToken())-1;
        C=Integer.parseInt(st.nextToken())-1;
        K=Integer.parseInt(st.nextToken());

        for(int i=0;i<R_SIZE;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<C_SIZE;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int result=0;

        while(true){
            if(R_SIZE>R && C_SIZE>C && arr[R][C]==K){
                System.out.println(result);
                return;
            }

            if(R_SIZE>=C_SIZE){
                r_calc();
            }
            else{
                c_calc();
            }

            if(++result>100){
                break;
            }

        }

        System.out.println(-1);

    }

    static void r_calc(){
        int next_C_SIZE=0;
        Map<Integer,Integer>[]rows=new HashMap[R_SIZE];

        for(int i=0;i<R_SIZE;i++){
            rows[i]=new HashMap<>(C_SIZE);
            int j=0;
            while(j<C_SIZE){
                if(arr[i][j]!=0) {
                    rows[i].put(arr[i][j], rows[i].getOrDefault(arr[i][j], 0) + 1);
                }
                j++;
            }

            next_C_SIZE=Math.max(next_C_SIZE, rows[i].size()*2);
        }
        C_SIZE=next_C_SIZE;

        int n_arr[][]=new int[R_SIZE][C_SIZE];

        for(int i=0;i<R_SIZE;i++){
            Map<Integer,Integer> row=rows[i];
            List<Integer> keys=new ArrayList<>(rows[i].keySet());
            keys.sort((o1,o2)->{
                if(row.get(o1)==row.get(o2)){
                    return o1-o2;
                }
                return row.get(o1)-row.get(o2);
            });

            for(int j=0,size=row.size();j<size;j++){
                n_arr[i][2*j]=keys.get(j);
                n_arr[i][2*j+1]=row.get(keys.get(j));
            }
        }

        arr=n_arr;
    }

    static void c_calc(){
        int next_R_SIZE=0;
        Map<Integer,Integer>[]rows=new HashMap[C_SIZE];

        for(int i=0;i<C_SIZE;i++){
            rows[i]=new HashMap<>(R_SIZE);
            int j=0;
            while(j<R_SIZE){
                if(arr[j][i]!=0) {
                    rows[i].put(arr[j][i], rows[i].getOrDefault(arr[j][i], 0) + 1);
                }
                j++;
            }

            next_R_SIZE=Math.max(next_R_SIZE, rows[i].size()*2);
        }
        R_SIZE=next_R_SIZE;

        int n_arr[][]=new int[R_SIZE][C_SIZE];

        for(int i=0;i<C_SIZE;i++){
            Map<Integer,Integer> row=rows[i];
            List<Integer> keys=new ArrayList<>(rows[i].keySet());
            keys.sort((o1,o2)->{
                if(row.get(o1)==row.get(o2)){
                    return o1-o2;
                }
                return row.get(o1)-row.get(o2);
            });

            for(int j=0,size=row.size();j<size;j++){
                n_arr[2*j][i]=keys.get(j);
                n_arr[2*j+1][i]=row.get(keys.get(j));
            }
        }

        arr=n_arr;
    }
}