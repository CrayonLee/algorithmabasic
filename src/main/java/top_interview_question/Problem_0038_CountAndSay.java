package top_interview_question;

public class Problem_0038_CountAndSay {
    public static String countAndSay(int n) {
        if (n < 1) {
            return "";
        }
        if (n == 1) {
            return "1";
        }
        StringBuilder ans = new StringBuilder();
        char[] last = countAndSay(n - 1).toCharArray();
        int times = 1;
        for (int i = 1; i < last.length; i++) {
            if (last[i - 1] == last[i]) {
                times++;
            } else {
                ans.append(times);
                ans.append(last[i - 1]);
                times = 1;
            }
        }
        ans.append(times);
        ans.append(last[last.length - 1]);
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }
}
