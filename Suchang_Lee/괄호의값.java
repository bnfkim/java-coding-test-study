import java.util.Scanner;
import java.util.Stack;
// 0916
// 0938
public class 괄호의값 {
    public static void main(String [] args) {
        Stack<String> s = new Stack<>();
        Scanner sc = new Scanner(System.in);
        String [] arr = sc.nextLine().split("");
        int remain = 1;
        int answer = 0;
        int flag = 0;
//        (()())[]
        for (int i = 0; i < arr.length; i++) {
            String t = arr[i];
            if (t.equals("(")) {
                s.push(t);
                remain *= 2;
                flag = 1;
            }
            else if (t.equals("[")) {
                s.push(t);
                remain *= 3;
                flag = 1;
            }
            else {
                if (t.equals(")")) {
                    if (!s.isEmpty()&&s.peek().equals("(")) {
                        s.pop();
                        if (flag == 0) {
                            remain /= 2;
                        }
                        else{
                            answer += remain ;
                            remain /= 2;
                            flag = 0;
                        }
                    }
                    else{
                        System.out.println(0);
                        return;
                    }

                }
                else {
                    if (!s.isEmpty()&&s.peek().equals("[")) {
                        s.pop();
                        if (flag == 0) {
                            remain /= 3;
                        }
                        else{
                            answer += remain ;
                            remain /= 3;
                            flag = 0;
                        }
                    }
                    else{
                        System.out.println(0);
                        return;
                    }

                }
            }
//            System.out.println(i+"remain"+remain+"answer"+answer);
        }

        if(s.isEmpty()) {
            System.out.println(answer);
        }
        else System.out.println(0);
    }
}
