package top_interview_question;

/**
 * leetcode测试链接：https://leetcode.com/problems/regular-expression-matching/
 *
 * @author Lenovo
 */
public class Problem_0010_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        return isValid(str, pattern) && process(str, pattern, 0, 0);
    }

    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        int[][] dp = new int[str.length + 1][pattern.length + 1];
        for (int i = 0; i <= str.length; i++) {
            for (int j = 0; j <= pattern.length; j++) {
                dp[i][j] = -1;
            }
        }
        return isValid(str, pattern) && process2(str, pattern, 0, 0, dp);
    }

    private boolean process(char[] str, char[] pattern, int si, int pi) {
        if (si == str.length) {
            if (pi == pattern.length) {
                return true;
            }

            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                return process(str, pattern, si, pi + 2);
            }
            return false;
        }

        if (pi == pattern.length) {
            return si == str.length;
        }

        if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
            return (str[si] == pattern[pi] || pattern[pi] == '.') && process(str, pattern, si + 1, pi + 1);
        }

        if (pattern[pi] != '.' && str[si] != pattern[pi]) {
            return process(str, pattern, si, pi + 2);
        }
        if (process(str, pattern, si, pi + 2)) {
            return true;
        }

        while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            if (process(str, pattern, si + 1, pi + 2)) {
                return true;
            }
            si++;
        }
        return false;
    }

    private boolean isValid(char[] str, char[] pattern) {
        for (char c : str) {
            if (c == '.' || c == '*') {
                return false;
            }
        }

        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == '*' && (i == 0 || pattern[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    private boolean process2(char[] str, char[] pattern, int si, int pi, int[][] dp) {
        if (dp[si][pi] != -1) {
            return dp[si][pi] == 1;
        }
        // si pi 这个参数组合第一次算

        if (si == str.length) { // si越界了
            if (pi == pattern.length) {
                dp[si][pi] = 1;
                return true;
            }
            // (pi pi+1) pi+2 ....
            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                boolean ans = process2(str, pattern, si, pi + 2, dp);
                dp[si][pi] = ans ? 1 : 0;
                return ans;
            }
            dp[si][pi] = 0;
            return false;
        }
        // si 没越界
        if (pi == pattern.length) {
            boolean ans = si == str.length;
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }
        // si 没越界 pi 没越界
        if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
            boolean ans = ((str[si] == pattern[pi]) || (pattern[pi] == '.'))
                    && process2(str, pattern, si + 1, pi + 1, dp);
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }
        // si 没越界 pi 没越界 pi+1 *
        if (pattern[pi] != '.' && str[si] != pattern[pi]) {
            boolean ans = process2(str, pattern, si, pi + 2, dp);
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }
        if (process2(str, pattern, si, pi + 2, dp)) {
            dp[si][pi] = 1;
            return true;
        }
        while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            if (process2(str, pattern, si + 1, pi + 2, dp)) {
                dp[si][pi] = 1;
                return true;
            }
            si++;
        }
        dp[si][pi] = 0;
        return false;
    }

    public static boolean process3(char[] str, char[] pattern, int si, int pi) {
        if (si == str.length && pi == pattern.length) {
            return true;
        }
        if (si == str.length) {
            return (pi + 1 < pattern.length && pattern[pi + 1] == '*') && process3(str, pattern, si, pi + 2);
        }

        if (pi == pattern.length) {
            return false;
        }

        if (pi + 1 >= pattern.length || pattern[pi] != '*') {
            return (str[si] == pattern[pi] || pattern[pi] == '.') && process3(str, pattern, si + 1, pi + 1);
        }
        //此处为枚举行为优化
        if ((str[si] == pattern[pi] || pattern[pi] == '.') && process3(str, pattern, si + 1, pi)) {
            return true;
        }
        return process3(str, pattern, si, pi + 2);
    }

    public boolean isMatch4(String str, String pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        if (!isValid(s, p)) {
            return false;
        }
        int n = str.length();
        int m = pattern.length();
        Boolean[][] dp = new Boolean[n + 1][m + 1];
        dp[n][m] = true;
        for (int j = m - 1; j >= 0; j--) {
            dp[n][j] = (j + 1 < m && p[j + 1] == '*') && dp[n][j + 2];
        }
        // dp[0..N-2][M-1]都等于false，只有dp[N-1][M-1]需要讨论
        if (n > 0 && m > 0) {
            dp[n-1][m-1]=(s[n-1]==p[m-1]||p[m-1]=='.');
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                if (p[j + 1] != '*') {
                    dp[i][j] = ((s[i] == p[j]) || (p[j] == '.')) && dp[i + 1][j + 1];
                } else {
                    if ((s[i] == p[j] || p[j] == '.') && dp[i + 1][j]) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

}
