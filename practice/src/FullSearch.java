package practice.src;

import java.util.Scanner;

class FullSearch {
  public static void main(String[] args) {
    // solutionARC061C();
    solutionABC079C();
  }

  // Practice: ARC 061 C
  // see: https://atcoder.jp/contests/arc061/tasks/arc061_a
  private static void solutionARC061C() {
    Scanner sc = new Scanner(System.in);
    char[] S = sc.next().toCharArray();
    long ans = 0;
    for (int i = 0; i < (1 << (S.length - 1)); i++) {
      long sum = 0;
      long tmp = 0;
      for (int j = 0; j < S.length; j++) {
        int num = Character.getNumericValue(S[j]);
        if (((i >> j) & 1) > 0) {
          sum += num + tmp * 10;
          tmp = 0;
        } else {
          tmp *= 10;
          tmp += num;
        }
      }
      ans += sum + tmp;
      tmp = 0;
    }
    System.out.println(ans);
    sc.close();
  }

  // Practice: ABC 079 C
  // see: https://atcoder.jp/contests/abc079/tasks/abc079_c
  private static void solutionABC079C() {
    Scanner sc = new Scanner(System.in);
    char[] ABCD = sc.next().toCharArray();
    sc.close();

    final int expected = 7;
    for (int i = 0; i < (1 << (ABCD.length - 1)); i++) {
      int sum = Character.getNumericValue(ABCD[0]);
      StringBuilder sb = new StringBuilder();
      sb.append(Character.getNumericValue(ABCD[0]));
      for (int j = 0; j < ABCD.length - 1; j++) {
        int num = Character.getNumericValue(ABCD[j + 1]);
        if (((i >> j) & 1) > 0) {
          sum += num;
          sb.append("+").append(num);
        } else {
          sum -= num;
          sb.append("-").append(num);
        }
      }
      if (sum == expected) {
        sb.append("=7");
        System.out.println(sb.toString());
        return;
      }
    }
  }
}
