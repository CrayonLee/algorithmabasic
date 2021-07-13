public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        // 如果回字符串长度小于2直接返回
        if (s.length() < 2) {
            return s;
        }
        // 记录最长的回文串
        String palindromic = "";
        int j, k;
        for (int i = 1; i < s.length(); i++) {
            if (s.length() - i <= palindromic.length() / 2) {
                break;
            }
            int sum = 1;
            for (j = i - 1, k = i + 1; j >= 0 && k < s.length(); j--, k++) {
                if(s.charAt(j)!=s.charAt(k)){
                    break;
                }
                sum+=2;
            }
            if(sum>palindromic.length()){
                palindromic=s.substring(j+1,k);
            }
            sum=0;
            //
            for(j=i-1,k=i;j>=0&&k<s.length();j--,k++){
                if(s.charAt(j)!=s.charAt(k)){
                    break;
                }
                sum+=2;
            }
            if(sum>palindromic.length()){
                palindromic=s.substring(j+1,k);
            }
        }
        return palindromic;
    }

    public static void main(String[] args) {
        String str = longestPalindrome("abcddceffecg");
        System.out.println(str);
    }
}
