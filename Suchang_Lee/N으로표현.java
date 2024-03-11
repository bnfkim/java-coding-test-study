import java.util.*;
class Solution {

    public int solution(int N, int number) {
        Set <Integer> [] arr = new HashSet[9];
        int c = N;
        for (int i = 1; i < 9; i++) {
            arr[i] = new HashSet<>();
            arr[i].add(c);
            c = c * 10 + N;
        }

        for (int i = 2; i < 9; i++) {
            for (int j = 1; j < i; j++) {
                for (int s : arr[i-j]) {
                    for (int t: arr[j]) {
                        arr[i].add(s+t);

                        if(s>t)
                            arr[i].add(s-t);
                        arr[i].add(s*t);
                        if(t!=0) arr[i].add(s/t);
                    }

                }
            }
        }

        for (int i = 1; i < 9; i++) {
            if(arr[i].contains(number)) return i;
        }
        return -1;
    }
}