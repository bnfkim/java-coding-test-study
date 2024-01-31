import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<=T;test_case++){
            long N=Long.parseLong(br.readLine());
            int bits=0;
            long answer=N;

            while(true){
                long cur=answer;

                while(cur!=0){
                    long remains=cur%10;
                    bits|=1<<remains;

                    cur/=10;
                }

                //bit check
                if(bits==1023) {
                    break;
                }

                answer+=N;
            }

            System.out.printf("#%d %d\n",test_case,answer);
        }

    }

}

