package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 빗물 14719 G5
시작 시간 : 24-01-27 20:30
종료 시간 : 24-01-27 22:00
실행시간 : 80ms

고려사항
벽의 최대 높이가 1개인 경우와 2개 이상인 경우로 나누어서 계산
최대로 높은 벽이 1개인 경우
좌, 우로 나누어 다시 재귀적으로 탐색
최대로 높은 벽이 2개 이상인 경우
0~가장 왼쪽 max 높이의 벽 까지 왼쪽 탐색
가장 오른쪽 max 높이의 벽 ~ end까지 오른쪽 탐색
*/

public class Solution14719 {

    static int H, W;
    static int[] data;
    static StringTokenizer st;
    static int ans;
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        data = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        split(data);

        System.out.println(ans);
    }

    private static void split(int[] arr) {

        if(arr.length == 0) return;

        int max = Integer.MIN_VALUE;

        // 최대값 계산
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max) max = arr[i];
        }

        // 최대값들의 index 저장
        ArrayList<Integer> maxIndices = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == max) maxIndices.add(i);
        }

        // 전체가 같은 높이일 경우 종료
        if(maxIndices.size() == arr.length) return;

        // max값이 1개인 경우 좌우로 나누어 탐색
        if(maxIndices.size() == 1){
            searchLeft(Arrays.copyOfRange(arr, 0, maxIndices.get(0)), max);
            searchRight(Arrays.copyOfRange(arr, maxIndices.get(0)+1, arr.length), max);
        }else{
            // max값들 사이는 빗물이 찰수 있는 최대 높이가 max인 것으로 계산
            for (int i = maxIndices.get(0); i < maxIndices.get(maxIndices.size()-1); i++) {
                ans += max - arr[i];
            }

            // 왼쪽 오른쪽 끝으로 나누어 탐색
            searchLeft(Arrays.copyOfRange(arr, 0, maxIndices.get(0)), max);
            searchRight(Arrays.copyOfRange(arr, maxIndices.get(maxIndices.size()-1)+1, arr.length), max);
        }

    }

    // 왼쪽 끝 부분을 탐색
    private static void searchLeft(int[] arr, int maxVal){
        if(arr.length <= 1) return;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max) max = arr[i];
        }
        ArrayList<Integer> maxIndices = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == max) maxIndices.add(i);
        }

        if(maxIndices.size() == arr.length) return;

        for (int i = maxIndices.get(0) + 1; i < arr.length; i++) {
            ans += max - arr[i];
        }

        searchLeft(Arrays.copyOfRange(arr, 0, maxIndices.get(0)), max);
    }

    // 오른쪽 끝 부분을 탐색
    private static void searchRight(int[] arr, int maxVal){
        if(arr.length <= 1) return;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max) max = arr[i];
        }
        ArrayList<Integer> maxIndices = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == max) maxIndices.add(i);
        }

        if(maxIndices.size() == arr.length) return;

        for (int i = maxIndices.get(maxIndices.size()-1) - 1; i >= 0; i--) {
            ans += max - arr[i];
        }

        searchRight(Arrays.copyOfRange(arr, maxIndices.get(maxIndices.size()-1)+1, arr.length), max);
    }
}
