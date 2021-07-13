package top_interview_question;

public class Problem_0014_LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        char[] first = strs[0].toCharArray();
        int min =Integer.MAX_VALUE;
        for (String str : strs) {
            char[] cur = str.toCharArray();
            int index = 0;
            while (index<first.length && index<cur.length){
                if(cur[index]!=first[index]){
                    break;
                }
                index++;
            }
            min=Math.min(min,index);
        }
        return strs[0].substring(0,min);
    }
}
