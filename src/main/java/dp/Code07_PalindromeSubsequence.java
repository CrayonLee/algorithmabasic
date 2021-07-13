package dp;

/**
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 * <p>
 * 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
 */
public class Code07_PalindromeSubsequence {
    /**
     * 本题目可以转化为当前字符串与其逆序串的最长公共子序列问题
     *
     * @param s
     * @return
     */
    public static int ways1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        StringBuilder builder = new StringBuilder(s);
        String s1 = builder.reverse().toString();
        char[] str1 = s.toCharArray();
        char[] str2 = s1.toCharArray();
        int len1 = str1.length;
        int len2 = str2.length;
        int[][] dp = new int[len1][len2];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < len1; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len1; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? 1 + dp[i - 1][j - 1] : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[len1 - 1][len2 - 1];
    }

    public static int process1(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process1(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process1(str1, str2, i - 1, j);
            }
        } else {
            int p1 = process1(str1, str2, i - 1, j);
            int p2 = process1(str1, str2, i, j - 1);
            int p3 = str1[i] == str2[j] ? 1 + process1(str1, str2, i - 1, j - 1) : 0;
            return Math.max(p1, Math.max(p2, p3));

        }
    }

    public static int ways2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process2(str, 0, str.length - 1);
    }

    private static int process2(char[] str, int i, int j) {
        if (i == j) {
            return 1;
        }
        if (i == j - 1) {
            return str[i] == str[j] ? 2 : 1;
        }
        int p1 = process2(str, i + 1, j - 1);
        int p2 = process2(str, i, j - 1);
        int p3 = process2(str, i + 1, j);
        int p4 = str[i] == str[j] ? (2 + process2(str, i + 1, j - 1)) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p4, p3));

    }

    public static int ways2Dp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i + 1][i] = str[i] == str[i + 1] ? 2 : 1;
        }
        for(int i=N-3;i>=0;i--){
            for(int j=i+2;j<N;j++){
                dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                if(str[i]==str[j]){
                    dp[i][j]=Math.max(dp[i][j],2+dp[i+1][j-1]);
                }
            }
        }
        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        String s = "abab";
        System.out.println(ways1(s));
        System.out.println(ways2(s));
        System.out.println(ways2Dp(s));
    }
}
