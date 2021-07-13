package top_interview_question;

/**
 * @author lizihao
 * @date 2021/7/9 23:35
 */
public class Problem_0050_PowXN {
    public static double myPow1(double x, int n) {
        if (n == 0) {
            return 1d;
        }
        if (n == Integer.MIN_VALUE) {
            return (x == 1d || x == -1d) ? 1d : 0;
        }
        int pow = Math.abs(n);
        double ans = 1d;
        double t = x;
        while (pow != 0) {
            if ((pow & 1) != 0) {
                ans *= t;
            }
            pow >>= 1;
            t = t * t;
        }
        return n < 0 ? (1d / ans) : ans;

    }

    public static double myPow2(double x, int n) {
        if (n == 0) {
            return 1D;
        }
        int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        double t = x;
        double ans = 1D;
        while (pow != 0) {
            if ((pow & 1) != 0) {
                ans *= t;
            }
            pow >>= 1;
            t = t * t;
        }
        if (n == Integer.MIN_VALUE) {
            ans *= x;
        }
        return n < 0 ? (1d / ans) : ans;
    }

    public static void main(String[] args) {
        double v = myPow2(2.0, Integer.MIN_VALUE);
        System.out.println(v);
    }
}
