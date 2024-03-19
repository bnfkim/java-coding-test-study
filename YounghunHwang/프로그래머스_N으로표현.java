import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (N == number) {
            return 1;
        }

        Queue<Memo> queue = new ArrayDeque<>();
        queue.add(new Memo(N, 0, -1, N, 1));

        while (!queue.isEmpty()) {
            Memo curr = queue.poll();

            if (curr.count > 8) {
                return -1;
            }

            for (int i = 0; i < 7; i++) {
                if (curr.op == -1 && (i == 3 || i == 5)) {
                    continue;
                }

                Memo next = new Memo(curr.A, curr.B, curr.op, curr.result, curr.count);
                int result = next.operation(N, i);

                if (result < 0) {
                    continue;
                }

                if (result == number) {
                    return next.count;
                }

                queue.add(next);
            }
        }

        return -1;
    }

    static class Memo {
        int A;
        int B;
        int op;
        int result;
        int count;

        public Memo(int A, int B, int op, int result, int count) {
            this.A = A;
            this.B = B;
            this.op = op;
            this.result = result;
            this.count = count;
        }

        public int operation(int N, int op) {
            switch (op) {
                case 0:
                    A = result;
                    B = N;
                    this.op = 0;
                    count++;
                    return result = A + B;
                case 1:
                    A = result;
                    B = N;
                    this.op = 1;
                    count++;
                    return result = A - B;
                case 2:
                    A = result;
                    B = N;
                    this.op = 2;
                    count++;
                    return result = A * B;
                case 3:
                    B *= N;
                    count++;
                    if (this.op == 0) {
                        result = A + B;
                    } else if (this.op == 1) {
                        result = A - B;
                    } else if (this.op == 2) {
                        result = A * B;
                    } else {
                        if (B != 0) {
                            result = A / B;
                        }
                    }
                    return result;
                case 4:
                    A = result;
                    B = N;
                    this.op = 3;
                    count++;
                    return result = A / B;
                case 5:
                    B /= N;
                    count++;
                    if (this.op == 0) {
                        result = A + B;
                    } else if (this.op == 1) {
                        result = A - B;
                    } else if (this.op == 2) {
                        result = A * B;
                    } else {
                        if (B != 0) {
                            result = A / B;
                        }
                    }
                    return result;
                default:
                    if (this.op == -1) {
                        A = A * 10 + N;
                        result = A;
                        count++;
                        return A;
                    }
                    B = B * 10 + N;
                    count++;
                    if (this.op == 0) {
                        result = A + B;
                    } else if (this.op == 1) {
                        result = A - B;
                    } else if (this.op == 2) {
                        result = A * B;
                    } else {
                        result = A / B;
                    }
                    return result;
            }
        }
    }
}