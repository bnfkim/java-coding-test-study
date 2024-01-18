package Baekjoon;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 오목 2615
// 24-01-17 21:00 시작
// 24-01-18 21:30 끝

// 고려사항
// 5개를 기준으로 좌우 확인
// 세로를 제외한 나머지 왼쪽부터 1번째를 반환
// 세로는 첫번째를 반환
// 무승부도 고려

// 4가지 경우 모두, 만약 정답인 경우 첫번째 인덱스를 반환하면 됨

// 가로 0,1,2,3,4    // 5, 6 양쪽 끝
// 좌상우하 0,1,2,3,4 //
// 좌하우상 0,1,2,3,4 //
// 세로 0,1,2,3,4    //
// 4가지 종류별 합
public class Solution2615 {
    static int n = 19;

    static int[][] mat = new int[n][n];
    static Point[][] delta = {
            /* 가로 */       {new Point(0, -2), new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, -3), new Point(0, 3)},
            /*좌상우하*/      {new Point(-2, -2), new Point(-1, -1), new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(-3, -3), new Point(3, 3)},
            /*좌하우상*/      {new Point(2, -2), new Point(1, -1), new Point(0, 0), new Point(-1, 1), new Point(-2, 2), new Point(3, -3), new Point(-3, 3)},
            /* 세로 */       {new Point(-2, 0), new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(-3, 0), new Point(3, 0)},
    };

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 데이터 입력
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < n; j++){
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean check_five;
        int type = 0;
        int flag;
        int cx, cy; // Center Pos

        for(int middle_iter = 2; middle_iter < n - 2; middle_iter++){
            for(int origin_iter = 0; origin_iter < n; origin_iter++){

                check_five = true;
                cx = middle_iter; cy = origin_iter;
                flag = mat[cx][cy]; // 가운데 색이 기준

                if(mat[cx][cy] != 0) {
                    // 세로
                    check_five = true;
                    type = 3;
                    flag = mat[cx][cy];
                    for(int i = 0; i < 5; i++){
                        if(mat[cx + delta[type][i].x][cy + delta[type][i].y] != flag){
                            check_five = false;
                            break;
                        }
                    }
                    if(check_five && checkSide(new Point(cx + delta[type][5].x, cy + delta[type][5].y),
                            new Point(cx + delta[type][6].x, cy + delta[type][6].y),
                            flag)){
                        System.out.println(flag + "\n" + (cx + delta[type][0].x + 1) + " " + (cy + delta[type][0].y + 1));
                        return;
                    }
                }


                check_five = true;
                cx = origin_iter; cy = middle_iter;
                flag = mat[cx][cy];

                if(mat[cx][cy] == 0) continue;

                // 가로
                type = 0;
                for (int i = 0; i < 5; i++) {
                    if (mat[cx + delta[type][i].x][cy + delta[type][i].y] != flag) {
                        check_five = false;
                        break;
                    }
                }

                if (check_five && checkSide(new Point(cx + delta[type][5].x, cy + delta[type][5].y),
                        new Point(cx + delta[type][6].x, cy + delta[type][6].y),
                        flag)) {
                    System.out.println(flag + "\n" + (cx + delta[type][0].x + 1) + " " + (cy + delta[type][0].y + 1));
                    return;
                }


                // 대각선
                if(isInnerPoint(cx, cy, 2)){
                    // 좌상우하
                    check_five = true;
                    type = 1;
                    for(int i = 0; i < 5; i++){
                        if(mat[cx + delta[type][i].x][cy + delta[type][i].y] != flag){
                            check_five = false;
                            break;
                        }
                    }

                    // 5개는 일치 and 주변이 다른 색
                    if(check_five && checkSide(new Point(cx + delta[type][5].x, cy + delta[type][5].y),
                            new Point(cx + delta[type][6].x, cy + delta[type][6].y),
                            flag)){
                        System.out.println(flag + "\n" + (cx + delta[type][0].x + 1) + " " + (cy + delta[type][0].y + 1));
                        return;
                    }

                    // 좌하우상
                    check_five = true;
                    type = 2;
                    for(int i = 0; i < 5; i++){
                        if(mat[cx + delta[type][i].x][cy + delta[type][i].y] != flag){
                            check_five = false;
                            break;
                        }
                    }

                    // 5개는 일치 and 주변이 다른 색
                    if(check_five && checkSide(new Point(cx + delta[type][5].x, cy + delta[type][5].y),
                            new Point(cx + delta[type][6].x, cy + delta[type][6].y),
                            flag)){
                        System.out.println(flag + "\n" + (cx + delta[type][0].x + 1) + " " + (cy + delta[type][0].y + 1));
                        return;
                    }




                }


            }
        }
        System.out.println(0);
        return;
    }

    // 두개의 좌표에 flag와 다른 돌이 있는지 체크
    private static boolean checkSide(Point left_end, Point right_end, int flag) {

        // mat 내부에 존재 && 5개의 돌색과 다른 색
        if(isInnerPoint(left_end.x, left_end.y, 0) && mat[left_end.x][left_end.y] == flag) return false;

        if(isInnerPoint(right_end.x, right_end.y, 0) && mat[right_end.x][right_end.y] == flag) return false;

        return true;
    }

    // i, j좌표가 mat 내부에 있는지 체크
    // margin이 2일 경우 "가장자리 2줄"은 밖에 있는 것으로 간주
    private static boolean isInnerPoint(int i, int j, int margin){
        return i >= margin && j >= margin && i < n - margin && j < n - margin;
    }


}