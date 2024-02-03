package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
백준 상어초등학교 21608 G5
시작 시간 : 24-01-30 00:00
종료 시간 : 24-01-30 01:20
실행시간 : 372ms

고려사항
// 입력
ArrayList를 이용하여 자리를 배치할 순서를 저장하였습니다.
Map<Integer, int[]>를 통해 key 학생 번호 - value 좋아하는 친구 목록을 저장하였습니다.
map구조를 선택한 이유는 문제에서 주어진 메모리 제한이 1024MB로 컸기때문입니다.
메모리 사용을 통해 시간복잡도를 낮추려했습니다.

// 자리배치
1. 모든 자리를 순회하며 배치될 수 있는 자리를 ArrayList에 저장
2. 이미 배치된 학생이 있는 자리는 스킵
3. 문제에 제시된 조건에 맞게 sorting
4. 가장 우선순위인 0번째 위치 선택
5. 해당 자리에 학생 배치
6. 모든 학생을 배치할 때까지 반복

// 호감도 계산
모든 자리를 순회하며 주변에 친한 친구가 있는지 확인
호감도 계산
 */

public class Solution21608 {

    static class Node{
        int i, j;
        int friends, empty;

        public Node(int i, int j, int friends, int empty) {
            this.i = i;
            this.j = j;
            this.friends = friends;
            this.empty = empty;
        }
    }

    static int N;
    static StringTokenizer st;
    static Map<Integer, int[]> friends;
    static int[] order;
    static int[][] place;
    static int[] happy = {0,1,10,100,1000};
    static int[][] delta = {{0,1}, {0,-1},{1,0},{-1,0}};
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        friends = new HashMap<>(N*N);
        order = new int[N*N];
        place = new int[N][N];

        // 입력
        for (int i = 0; i < N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int origin = Integer.parseInt(st.nextToken());
            order[i] = origin;
            friends.put(origin, new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())
            });
        }

        // 자리배치
        int idx = -1;
        while(++idx < N*N){
            int curStuNum = order[idx];
            int[] curFriend = friends.get(curStuNum);

            ArrayList<Node> available = new ArrayList<>(N*N-idx);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(place[i][j] != 0) continue;
                    int adjFriendNum = calcFriend(i, j, curFriend);
                    int emptyNum = calcEmpty(i, j);
                    available.add(new Node(i, j, adjFriendNum, emptyNum));
                }
            }

            // 문제에서 제시된 자리배치의 우선순위
            available.sort(((o1, o2) -> {
                if (o1.friends != o2.friends) return o2.friends - o1.friends;
                if (o1.empty != o2.empty) return o2.empty - o1.empty;
                if (o1.i != o2.i) return o1.i - o2.i;
                return o1.j - o2.j;
            }));

            Node fixed = available.get(0);
            place[fixed.i][fixed.j] = curStuNum;

        }
        System.out.println(calcHappy());
    }

    private static int calcHappy() {
        int ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += happy[calcHappyOne(i, j)];
            }
        }

        return ans;
    }

    private static int calcHappyOne(int idxI, int idxJ) {
        int cnt = 0;
        int me = place[idxI][idxJ];

        for (int i = 0; i < 4; i++) {
            int nextI = idxI + delta[i][0];
            int nextJ = idxJ + delta[i][1];
            if(nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) continue;

            for (int j = 0; j < 4; j++) {
                if(friends.get(me)[j] == place[nextI][nextJ]) cnt++;
            }
        }
        return cnt;
    }


    private static int calcEmpty(int idxI, int idxJ) {
        int cnt = 0;

        for (int dir = 0; dir < 4; dir++) {
            int nextI = idxI + delta[dir][0];
            int nextJ = idxJ + delta[dir][1];
            if(nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) continue;

            if(place[nextI][nextJ] == 0) cnt++;
        }
        return cnt;
    }

    private static int calcFriend(int idxI, int idxJ, int[] curFriend) {
        int res = 0;
        for (int dir = 0; dir < 4; dir++) {
            int nextI = idxI + delta[dir][0];
            int nextJ = idxJ + delta[dir][1];
            if(nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) continue;

            for (int i = 0; i < 4; i++) {
                if(place[nextI][nextJ] == curFriend[i]) res++;
            }
        }
        return res;
    }
}