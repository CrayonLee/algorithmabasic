package top_interview_question;

public class Problem_0044_WildcardMatching {
    public static boolean isMatch(String str, String pattern) {
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        return process(s, p, 0, 0);
    }

    private static boolean process(char[] s, char[] p, int si, int pi) {
        if (si == s.length) {
            if (pi == p.length) {
                return true;
            } else {
                return p[pi] == '*' && process(s, p, si, pi + 1);
            }
        }

        if (pi == p.length) {
            return si == s.length;
        }
        if (p[pi] != '?' && p[pi] != '*') {
            return s[si] == p[pi] && process(s, p, si + 1, pi + 1);
        }

        //处理特殊情况
        if (p[pi] == '?') {
            return process(s, p, si + 1, pi + 1);
        }
        //p[pi]='*'
        for (int len = 0; len <= s.length - si; len++) {
            if (process(s, p, si + len, pi + 1)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isMatch2(String str, String pattern) {
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        int m = s.length;
        int n = p.length;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[m][n] = true;
        for (int pi = n - 1; pi >= 0; pi--) {
            dp[m][pi] = p[pi] == '*' && dp[m][pi + 1];
        }

        for (int si = m - 1; si >= 0; si--) {
            for (int pi = n - 1; pi >= 0; pi--) {
                if (p[pi] != '?' && p[pi] != '*') {
                    dp[si][pi] = s[si] == p[pi] && dp[si + 1][pi + 1];
                    continue;
                }

                if (p[pi] == '?') {
                    dp[si][pi] = dp[si + 1][pi + 1];
                    continue;
                }

                dp[si][pi] = dp[si + 1][pi] || dp[si][pi + 1];
            }
        }
        return dp[0][0];
    }
}
