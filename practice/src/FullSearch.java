package practice.src;

import java.util.Scanner;

class FullSearch {
  public static void main(String[] args) {
    solutionARC061C();
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
}
