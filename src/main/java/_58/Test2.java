package _58;

import javafx.beans.binding.LongExpression;

public class Test2 {
    /**
     * 最长回文串
     * @param string string字符串
     * @return string字符串
     */
    public static String longestPalindrom (String string) {
        // write code here
//        StringBuilder builder = new StringBuilder();
//        int i=0,j=string.length()-1;
//        while(i<string.length()-1&&j>=i){
//            while (string.charAt(i)==string.charAt(j)){
//                builder.append(string.charAt(i));
//                i++;
//                j--;
//
//            }
//            i++;j--;
//        }
//        return builder.toString();
        StringBuilder builder= new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            for (int j=string.length()-1;j>0;j--){
                char c1 = string.charAt(j);
                if(c==c1){
                    builder.append(c);
                    i++;
                    j--;
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String str = longestPalindrom("abcddceffecg");
        System.out.println(str);
    }
}
