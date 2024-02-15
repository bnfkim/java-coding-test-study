import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++) {
            String input=br.readLine();
            if(find(input,0)) {
                sb.append("YES").append('\n');
            }
            else {
                sb.append("NO").append('\n');
            }
        }

        System.out.println(sb);
    }

    static boolean find(String input,int index) {
        if(index==input.length()) { //끝까지 도달하면 성공했다고 간주
            return true;
        }

        if(input.charAt(index)=='1') {
            int zero_cnt=0; //100+1+에서 1뒤에 0을 확인하는 로직
            for(int i=index+1;i<input.length();i++) {
                if(input.charAt(i)!='0') break;
                zero_cnt++;
            }

            if(zero_cnt<2||index+zero_cnt==input.length()) return false; //0이 2회 미만으로 나오거나 0뒤에 1이 존재하지 않는경우 성립이 안됨

            int one_cnt=0;
            for(int i=index+zero_cnt+1;i<input.length();i++) { // 100+ 이후 1+를 확인하는 과정
                if(input.charAt(i)!='1') break;
                one_cnt++;
            }

            if(one_cnt==0) return false; //1이 없다면 성립이 안됨.

            if(one_cnt==1) return find(input,index+zero_cnt+one_cnt+1); //1이 하나라면 다음과정을 진행하면 된다.


            return find(input,index+zero_cnt+one_cnt)||find(input,index+zero_cnt+one_cnt+1); //1이 두개 이상이라면 케이스가 두개로 나뉘어 진다.
        }
        else { //현재 위치가 0인 경우 (01이 나와줘야함)
            if(index==input.length()-1||input.charAt(index+1)!='1') {
                return false;
            }

            return find(input,index+2);
        }
    }
}
