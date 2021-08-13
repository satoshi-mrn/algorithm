package practice.src;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class FullSearch {
  public static void main(String[] args) {
    // solutionARC061C();
    // solutionABC079C();
    // solutionABC104C();
    // solutionARC029C();
    // solutionABC002D();
    // solutionATC001A();
    solutionARC031B();
  }

  // Practice: ARC061 C
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

  // Practice: ABC079 C
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

  // Practice: ABC104 C
  // see: https://atcoder.jp/contests/abc104/tasks/abc104_c
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

  // Practice: ARC029 C
  // see: https://atcoder.jp/contests/arc029/tasks/arc029_1
  private static void solutionARC029C() {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] t = new int[N];
    for (int i = 0; i < t.length; i++) {
      t[i] = sc.nextInt();
    }
    sc.close();

    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < (1 << t.length); i++) {
      int a = 0;
      int b = 0;
      for (int j = 0; j < t.length; j++) {
        if (((i >> j) & 1) != 0) {
          a += t[j];
        } else {
          b += t[j];
        }
      }
      int max = Math.max(a, b);
      ans = Math.min(ans, max);
    }
    System.out.println(ans);
  }

  // Practice: ABC002 D
  // see: https://atcoder.jp/contests/abc002/tasks/abc002_4
  private static void solutionABC002D() {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    long[] xy = new long[N];
    for (int i = 0; i < N; i++) {
      xy[i] |= (1 << i);
    }
    for (int i = 0; i < M; i++) {
      int x = sc.nextInt() - 1;
      int y = sc.nextInt() - 1;
      xy[x] |= (1 << y);
      xy[y] |= (1 << x);
    }
    sc.close();

    int ans = 0;
    for (int i = 0; i < (1 << N); i++) {
      Set<Integer> set = new HashSet<>();
      for (int j = 0; j < N; j++) {
        if (((i >> j) & 1) != 0) {
          set.add(j);
        }
      }

      boolean ok = true;
      for (int j = 0; j < N && set.size() > 0; j++) {
        if (!set.contains(j)) {
          continue;
        }
        if ((i & xy[j]) != i) {
          ok = false;
          break;
        }
      }
      if (ok) {
        ans = Math.max(ans, set.size());
      }
    }
    System.out.println(ans);
  }

  // Practice: ATC001 A
  // see: https://atcoder.jp/contests/atc001/tasks/dfs_a
  private static void solutionATC001A() {
    Scanner sc = new Scanner(System.in);
    int H = sc.nextInt();
    int W = sc.nextInt();
    char[][] c = new char[H][W];
    int sh = -1;
    int sw = -1;
    int gh = -1;
    int gw = -1;
    for (int i = 0; i < H; i++) {
      c[i] = sc.next().toCharArray();
    }
    sc.close();
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        if (c[i][j] == 's') {
          sh = i;
          sw = j;
        }
        if (c[i][j] == 'g') {
          gh = i;
          gw = j;
        }
      }
    }
    boolean[][] memo = new boolean[H][W];
    dfsForSolutionATC001A(memo, c, sh, sw);
    System.out.println(memo[gh][gw] ? "Yes" : "No");
  }

  private static void dfsForSolutionATC001A(boolean[][] memo, char[][] c, int h, int w) {
    if (h < 0 || h > c.length - 1 || w < 0 || w > c[0].length - 1 || memo[h][w] || c[h][w] == '#') {
      return;
    }
    memo[h][w] = true;
    dfsForsolutionATC001A(memo, c, h - 1, w);
    dfsForsolutionATC001A(memo, c, h + 1, w);
    dfsForsolutionATC001A(memo, c, h, w - 1);
    dfsForsolutionATC001A(memo, c, h, w + 1);
  }

  // Practice: ARC031 B
  // see: https://atcoder.jp/contests/arc031/tasks/arc031_2
  private static void solutionARC031B() {
    Scanner sc = new Scanner(System.in);
    char[][] A = new char[10][10];
    for (int i = 0; i < 10; i++) {
      A[i] = sc.next().toCharArray();
    }
    sc.close();

    int expected = 1;
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A.length; j++) {
        if (A[i][j] == 'o') {
          expected++;
        }
      }
    }

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (A[i][j] == 'o') {
          continue;
        }

        boolean[][] memo = new boolean[10][10];
        int sum = dfsForSolutionARC031B(memo, A, i, j, true);
        if (sum == expected) {
          System.out.println("YES");
          return;
        }
      }
    }
    System.out.println("NO");
  }

  private static int dfsForSolutionARC031B(boolean[][] memo, char[][] c, int h, int w, boolean s) {
    if (h < 0 || h > c.length - 1 || w < 0 || w > c[0].length - 1 || memo[h][w]) {
      return 0;
    }
    if ((!s) && c[h][w] == 'x') {
      return 0;
    }

    int count = 1;
    memo[h][w] = true;
    count += dfsForSolutionARC031B(memo, c, h - 1, w, false);
    count += dfsForSolutionARC031B(memo, c, h + 1, w, false);
    count += dfsForSolutionARC031B(memo, c, h, w - 1, false);
    count += dfsForSolutionARC031B(memo, c, h, w + 1, false);
    return count;
  }
}
