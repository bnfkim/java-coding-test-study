import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class 문자열교환 {

    public static void main(String[] args) throws IOException {


        // ababab a 3개
        // aaa 형태되어야함


        Scanner sc = new Scanner(System.in);
        String str= sc.nextLine();
        int aNum=0;
        int answer=Integer.MAX_VALUE;

        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)=='a')
                aNum++;
        }


        // ababab
        // aba
        for(int i=0;i<str.length();i++)
        {
            int bNum=0;
            for(int j=i;j<i+aNum;j++)

            {
                if(str.charAt(j% str.length())=='b')
                {
                    bNum++;
                }

            }

            answer=Math.min(answer,bNum);

        }
        System.out.println(answer);
    }

}

