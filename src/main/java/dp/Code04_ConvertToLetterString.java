package dp;

/**
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class Code04_ConvertToLetterString {
    // str只含有数字字符0~9
    // 返回多少种转化方案
    public static int nums(String str) {
        if (str == null || str.length() == 0) {
          return 0;
        }
        return process(str.toCharArray(),0);
    }

    private static int process(char[] str, int i) {
        if(i==str.length){
            return 1;
        }
        if(str[i]=='0'){
            return 0;
        }
        int ways = process(str,i+1);
        if(i+1<str.length&&(str[i]-'0')*10+(str[i+1]-'0')<27){
            ways+=process(str,i+2);
        }
        return ways;
    }

    public static int dp(String str){
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int N = chars.length;
        int[] dp = new int[N+1];
        dp[N]=1;
        for (int i = N-1; i >=0; i--) {
//                if(chars[i]=='0'){
//                    dp[i]=0;
//                }
            if(chars[i]!='0'){
                int ways = process(chars,i+1);
                if(i+1<chars.length&&(chars[i]-'0')*10+(chars[i+1]-'0')<27){
                    ways+=process(chars,i+2);
                }
                dp[i]=ways;
            }

        }

        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(nums("7210231231232031203123"));
        System.out.println(dp("7210231231232031203123"));
    }

}
