import java.io.*;
import java.util.*;

public class Main {
  static char[][] map = new char[3][3];
  static boolean valid, isBlank, winO, winX;
  static int cntX, cntO;

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    String input;
    while(!(input = br.readLine()).equals("end")) {
      valid = false;
      isBlank = false;
      winO = false;
      winX = false;
      cntX = 0;
      cntO = 0;

      for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
          map[i][j] = input.charAt(3 * i + j);

          if(map[i][j] == '.') isBlank = true;
          if(map[i][j] == 'X') cntX++;
          if(map[i][j] == 'O') cntO++;
        }
      }
      checkValid();
      sb.append((valid) ? "valid" : "invalid").append("\n");
    }
    System.out.println(sb.toString().trim());
  }

  public static void checkValid() {
    checkWinX();
    checkWinO();

    // X와 O가 동시에 이긴 경우는 유효하지 않음
    if(winO && winX) valid = false;
    // O가 이긴 경우, O의 개수가 X의 개수와 같아야 함
    else if(winO && cntO == cntX) valid = true;
    //X가 이긴 경우, X의 개수가 O의 개수보다 하나 많아야 함
    else if(winX && cntX == cntO + 1) valid = true;
    //아무도 이긴사람이 없을 경우, 빈간이면 안 됨, X가 하나 더 많아야함
    else if(!winO && !winX && !isBlank && cntX == cntO + 1) valid = true;
  }

  public static void checkWinX() {
    for(int i = 0; i < 3; i++) {
      int cntCol = 0;
      int cntRow = 0;
      for(int j = 0; j < 3; j++) {
        if(map[i][j] == 'X') cntRow++;
        if(map[j][i] == 'X') cntCol++;
      }
      if(cntRow == 3 || cntCol == 3) winX = true; // 각 행 또는 열 검사 후 승리 조건 충족 확인
    }

    int cntDiaDown = 0;
    int cntDiaUp = 0;
    for(int i = 0; i < 3; i++) {
      if(map[i][i] == 'X') cntDiaDown++;
      if(map[2 - i][i] == 'X') cntDiaUp++;
    }
    if(cntDiaUp == 3 || cntDiaDown == 3) winX = true;
  }

  public static void checkWinO() {
    for(int i = 0; i < 3; i++) {
      int cntCol = 0;
      int cntRow = 0;
      for(int j = 0; j < 3; j++) {
        if(map[i][j] == 'O') cntRow++;
        if(map[j][i] == 'O') cntCol++;
      }
      if(cntRow == 3 || cntCol == 3) winO = true; // 각 행 또는 열 검사 후 승리 조건 충족 확인
    }

    int cntDiaDown = 0;
    int cntDiaUp = 0;
    for(int i = 0; i < 3; i++) {
      if(map[i][i] == 'O') cntDiaDown++;
      if(map[2 - i][i] == 'O') cntDiaUp++;
    }
    if(cntDiaUp == 3 || cntDiaDown == 3) winO = true;
  }
}
