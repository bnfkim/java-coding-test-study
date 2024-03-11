import java.io.*;
import java.util.*;

// 124ms
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		
		// -4 -3 -2 -1
		//  (  )  [  ]
		Deque<Integer> stack = new ArrayDeque<>();		
		
		for (int i = 0; i < line.length(); ++i) {
			char cur = line.charAt(i);
			
			int num = 1;
			
			// 여는 괄호는 스택에 저장
			if (cur == '(')
				stack.push(-4);				
			else if (cur == '[')
				stack.push(-2);
			
			// 닫는괄호 )
			else if (cur == ')') {
				while (true) {
					
					// 짝이 맞지 않으면 0 출력
					if (stack.isEmpty() || -3 <= stack.peek() && stack.peek() <= -1) {
						System.out.println(0);
						return;
					}
					
					// 맞는 짝이 나온 경우
					if (stack.peek() == -4) {
						stack.pop();
						
						// 더해줄 숫자가 이미 존재하면 더하기
						if (!stack.isEmpty() && stack.peek() > 0)
							stack.push(num * 2 + stack.pop());
						else
							stack.push(num * 2);
						
						break;
					}
					
					// ( x ) 형태인 경우
					else
						num *= stack.pop();
				}
			}
			
			// 닫는 괄호 ]
			else {
				while (true) {
					
					// 짝이 맞지 않으면 0 출력
					if (stack.isEmpty() || stack.peek() < 0 && stack.peek() != -2) {
						System.out.println(0);
						return;
					}
					
					// 맞는 짝이 나온 경우
					if (stack.peek() == -2) {
						stack.pop();
						
						// 더해줄 숫자가 이미 존재하면 더하기
						if (!stack.isEmpty() && stack.peek() > 0)
							stack.push(num * 3 + stack.pop());
						else
							stack.push(num * 3);
						
						break;
					}
					
					// ( x ) 형태인 경우
					else
						num *= stack.pop();
				}
			}
		}
		
		if (stack.size() >= 2 || !stack.isEmpty() && stack.peek() < 0)
			System.out.println(0);
		else
			System.out.println(stack.peek());
	}
}
