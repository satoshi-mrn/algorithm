package practice.src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    // solutionARC031B();
    // solutionARC037B();
    solutionAOJ1160();
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
    dfsForSolutionATC001A(memo, c, h - 1, w);
    dfsForSolutionATC001A(memo, c, h + 1, w);
    dfsForSolutionATC001A(memo, c, h, w - 1);
    dfsForSolutionATC001A(memo, c, h, w + 1);
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

  // Practice: ARC037 B
  // see: https://atcoder.jp/contests/arc037/tasks/arc037_b
  private static void solutionARC037B() {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    List<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      graph.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < M; i++) {
      int u = sc.nextInt() - 1;
      int v = sc.nextInt() - 1;
      graph.get(u).add(v);
      graph.get(v).add(u);
    }
    sc.close();

    boolean[] seen = new boolean[N];
    int ans = 0;
    for (int i = 0; i < seen.length; i++) {
      if (seen[i]) continue;
      boolean hasCycle = dfsForSolutionARC037B(graph, seen, -1, i, false);
      if (!hasCycle) ans++;
    }
    System.out.println(ans);
  }

  private static boolean dfsForSolutionARC037B(
      List<ArrayList<Integer>> graph, boolean[] seen, int parent, int v, boolean hasCycle) {
    seen[v] = true;
    for (Integer nextV : graph.get(v)) {
      if (nextV == parent) continue;
      if (seen[nextV]) return true;
      hasCycle = dfsForSolutionARC037B(graph, seen, v, nextV, hasCycle);
    }
    return hasCycle;
  }

  // Practice: AOJ1160
  // see: https://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=1160&lang=jp
  private static void solutionAOJ1160() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      int w = sc.nextInt();
      int h = sc.nextInt();
      if (w == 0 && h == 0) break;

      int[][] c = new int[h][w];
      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          c[i][j] = sc.nextInt();
        }
      }
      boolean[][] seen = new boolean[h][w];
      int count = 0;
      for (int i = 0; i < seen.length; i++) {
        for (int j = 0; j < seen[0].length; j++) {
          if (c[i][j] == 0) continue;
          if (seen[i][j]) continue;
          dfsForSolutionAOJ1160(c, seen, i, j);
          count++;
        }
      }
      System.out.println(count);
    }
    sc.close();
  }

  private static void dfsForSolutionAOJ1160(int[][] c, boolean[][] seen, int h, int w) {
    if (h < 0 || h > c.length - 1 || w < 0 || w > c[0].length - 1) return;
    if (seen[h][w]) return;
    if (c[h][w] == 0) return;
    seen[h][w] = true;
    dfsForSolutionAOJ1160(c, seen, h - 1, w);
    dfsForSolutionAOJ1160(c, seen, h + 1, w);
    dfsForSolutionAOJ1160(c, seen, h, w - 1);
    dfsForSolutionAOJ1160(c, seen, h, w + 1);
    dfsForSolutionAOJ1160(c, seen, h + 1, w + 1);
    dfsForSolutionAOJ1160(c, seen, h - 1, w - 1);
    dfsForSolutionAOJ1160(c, seen, h + 1, w - 1);
    dfsForSolutionAOJ1160(c, seen, h - 1, w + 1);
  }
}
