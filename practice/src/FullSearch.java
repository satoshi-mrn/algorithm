package practice.src;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class FullSearch {
  public static void main(String[] args) {
    // solutionARC061C();
    // solutionABC079C();
    solutionABC104C();
  }

  // Practice: ARC 061 C
  // see: https://atcoder.jp/contests/arc061/tasks/arc061_a
  private static void solutionARC061C() {
    Scanner sc = new Scanner(System.in);
    char[] S = sc.next().toCharArray();
    long ans = 0;
    for (int i = 0; i < (1 << (S.length - 1)); i++) {
      long tmp = Character.getNumericValue(S[0]);
      for (int j = 0; j < S.length - 1; j++) {
        if (((i >> j) & 1) > 0) {
          ans += tmp;
          tmp = Character.getNumericValue(S[j + 1]);
        } else {
          tmp *= 10;
          tmp += Character.getNumericValue(S[j + 1]);
        }
      }
      ans += tmp;
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

  // Practice: ABC 104 C
  // see: https://atcoder.jp/contests/abc079/tasks/abc079_c
  private static void solutionABC104C() {
    Scanner sc = new Scanner(System.in);
    int D = sc.nextInt();
    int G = sc.nextInt();
    int[] p = new int[D];
    int[] c = new int[D];
    for (int i = 0; i < D; i++) {
      p[i] = sc.nextInt();
      c[i] = sc.nextInt();
    }
    sc.close();

    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < (1 << D); i++) {
      Set<Integer> set = new HashSet<>();
      int count = 0;
      int sum = 0;
      for (int j = 0; j < D; j++) {
        if (((i >> j) & 1) != 0) {
          set.add(j);
          sum += c[j] + (p[j] * (100 * (j + 1)));
          count += p[j];
        }
      }
      for (int j = c.length - 1; j >= 0 && sum < G; j--) {
        if (set.contains(j)) {
          continue;
        }

        int score = 100 * (j + 1);
        int remain = G - sum;
        int nc = remain / score;
        if (remain % score != 0) {
          nc += 1;
        }

        if (nc > p[j]) {
          count += p[j];
          sum += (score * p[j]) + c[j];
        } else {
          count += nc;
          sum += (score * nc);
        }
      }
      ans = Math.min(count, ans);
    }
    System.out.println(ans);
  }
}
