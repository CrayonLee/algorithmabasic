package top_interview_question;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class JumpGameIIFollowUpOnClass {
    public static int jump1(int N, int start, int end, int[] arr) {
        boolean[] walk = new boolean[N];
        return f(arr, N, end, start, walk);
    }

    /**
     * 从i位置到end，至少要几步
     * walk[i-1]==false 代表i之前没去过
     * walk[i-1]==true 代表i之前到过
     *
     * @param arr
     * @param n
     * @param end
     * @param i
     * @param walk
     * @return
     */
    private static int f(int[] arr, int n, int end, int i, boolean[] walk) {
        if (i < 1 || i > n) {
            return -1;
        }
        if (walk[i - 1]) {
            return -1;
        }
        if (i == end) {
            return 0;
        }
        walk[i - 1] = true;
        int left = i - arr[i - 1];
        int right = i + arr[i - 1];
        int next = -1;
        int ans1 = f(arr, n, end, left, walk);
        int ans2 = f(arr, n, end, right, walk);
        if (ans1 != -1 && ans2 != -1) {
            next = Math.min(ans1, ans2);
        } else if (ans1 != -1) {
            next = ans1;
        } else if (ans2 != -1) {
            next = ans2;
        }
        walk[i - 1] = false;
        if (next == -1) {
            return -1;
        }
        return next + 1;
    }

    public static int jump2(int N, int start, int end, int[] arr) {
        return g(arr, N, end, start, 0);
    }

    private static int g(int[] arr, int n, int end, int i, int k) {
        if (i < 1 || i > n || k > n - 1) {
            return -1;
        }
        if (i == end) {
            return k;
        }
        int left = i - arr[i - 1];
        int right = i + arr[i - 1];
        int ans = -1;
        int ans1 = g(arr, n, end, left, k + 1);
        int ans2 = g(arr, n, end, right, k + 1);
        if (ans1 != -1 && ans2 != -1) {
            ans = Math.min(ans1, ans2);
        } else if (ans1 != -1) {
            ans = ans1;
        } else if (ans2 != -1) {
            ans = ans2;
        }
        return ans;
    }

    public static int jumpMinTimes1(int N, int start, int end, int[] arr) {
        boolean[] map = new boolean[N + 1];

        return f1(N, start, end, 0, arr, map);
    }

    /**
     * @param n    一共有n位置 每个位置如何跳 记录在arr中
     * @param cur  当前位置
     * @param aim  最终想去位置
     * @param step 之前做过的决定所走的步数
     * @param arr
     * @param map  如果map[i] == true 表示i位置，之前来过
     *             如果map[i] == false 表示i位置，之前没来过
     * @return
     */
    private static int f1(int n, int cur, int aim, int step, int[] arr, boolean[] map) {
        if (cur < 1 || cur > n) {
            return -1;
        }
        if (map[cur]) {
            return -1;
        }
        //有效地位置  没有来过
        if (cur == aim) {
            return step;
        }
        map[cur] = true;
        int ans1 = f1(n, cur - arr[cur], aim, step + 1, arr, map);
        int ans2 = f1(n, cur + arr[cur], aim, step + 1, arr, map);
        map[cur] = false;
        if (ans1 != -1 && ans2 != -1) {
            return Math.min(ans1, ans2);
        } else if (ans1 != -1) {
            return ans1;
        } else {
            return ans2;
        }
    }

    //bfs
    public static int jumpMinTimes3(int n, int start, int end, int[] arr) {
        if (start < 1 || start > n || end < 1 || end > n) {
            return -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> levelMap = new HashMap<>();
        queue.add(start);
        levelMap.put(start, 0);

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            int level = levelMap.get(cur);

            if (cur == end) {
                return level;
            } else {
                int left = cur - arr[cur - 1];
                int right = cur + arr[cur - 1];
                if (left > 0 && !levelMap.containsKey(left)) {
                    queue.add(left);
                    levelMap.put(left, level + 1);
                }
                if(right<=n && !levelMap.containsKey(right)){
                    queue.add(right);
                    levelMap.put(right, level + 1);
                }
            }
        }
        return -1;
    }


    public static int jumpMinTimes2(int N, int start, int end, int[] arr) {
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -2;
            }
        }
        // dp[i][k] == -2表示这个过程没算过
        // dp[i][k] != -2表示这个过程算过了
        return f2(N, end, arr, start, 0, dp);
    }

    // 一共有N个位置，跳的过程中，如果你又跳回到某个位置，其实这已经说明不是最优步数了
    // 也就是说，如果存在最优的跳法，那么这个最优跳法一定不会大于N-1步
    // 所以，增加了一个参数k，表示已经跳了多少步
    // 整个函数的含义：
    // 一共有1~N个位置，目标是aim位置
    // 所有位置能跳的距离都记录在arr中，并且对任意的arr[i] > 0
    // 当前来到的位置是i, 之前已经跳过了k步，
    // 返回最后到达aim位置，跳的最少的步数
    // 如果返回-1表示怎么也无法到达
    public static int f2(int N, int aim, int[] arr, int i, int k, int[][] dp) {
        if (i < 1 || i > N || k > N - 1) {
            return -1;
        }
        if (dp[i][k] != -2) {
            return dp[i][k];
        }
        if (i == aim) {
            dp[i][k] = k;
            return k;
        }
        // 请注意，arr的下标是从0开始的，但是题目规定的下标从1开始
        // 所以，拿出i位置能跳的距离，需要拿arr[i-1]位置的值
        int ans1 = f2(N, aim, arr, i - arr[i - 1], k + 1, dp);
        int ans2 = f2(N, aim, arr, i + arr[i - 1], k + 1, dp);
        if (ans1 != -1 && ans2 != -1) {
            dp[i][k] = Math.min(ans1, ans2);
        } else if (ans1 != -1) {
            dp[i][k] = ans1;
        } else {
            dp[i][k] = ans2;
        }
        return dp[i][k];
    }

    // for test
    public static int[] gerRandomArray(int N, int R) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * R);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxN = 20;
        int maxV = 10;
        int testTimes = 200;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerRandomArray(maxN, maxV);
            int N = arr.length;
            int start = (int) (Math.random() * N) + 1;
            int end = (int) (Math.random() * N) + 1;
            if (start > end) {
                continue;
            }
            int ans1 = jump1(N, start, end, arr);
//            int ans2 = jump2(N, start, end, arr);
            int ans2 = jumpMinTimes2(N, start, end, arr);
            int ans3 = jumpMinTimes3(N, start, end, arr);
            if (ans1 != ans2 || ans2 != ans3) {
                printArray(arr);
                System.out.println("start : " + start);
                System.out.println("end : " + end);
                System.out.println("ans1 : " + ans1);
                System.out.println("ans2 : " + ans2);
                System.out.println("ans3 : " + ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test end");
    }
}
