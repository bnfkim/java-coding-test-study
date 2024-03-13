import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String input=br.readLine();
        Deque<Character> stack=new ArrayDeque<>();
        Deque<int[]> adder=new ArrayDeque<>();
        int result=0;
        for(int i=0,size=input.length();i<size;i++) {
            char cur=input.charAt(i);
            if(cur=='('|| cur=='[') {
                stack.add(cur);
                continue;
            }

            if(stack.isEmpty()) {
                System.out.println(0);
                return;
            }

            char last=stack.peekLast();
            if((last=='('&&cur==']') ||(last=='[' && cur==')')) {
                System.out.println(0);
                return;
            }

            //짝이 맞는 경우
            int value=(cur==')')? 2:3;
            int level=stack.size();
            int[] add=new int[] {value,level};
            while(!adder.isEmpty()&&add[1]<=adder.peekLast()[1]) {
                int[] top=adder.pollLast();
                if(add[1]<top[1]) {
                    add[0]*=top[0];
                }
                else if(add[1]==top[1]) {
                    add[0]+=top[0];
                }
            }

            adder.add(add);
            stack.pollLast();
        }

        if(!stack.isEmpty()) {
            result=0;
        }
        else {
            for(int[] cur:adder) {
                result+=cur[0];
            }
        }

        System.out.println(result);
    }


}
