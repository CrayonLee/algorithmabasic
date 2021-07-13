package recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 打印所有子序列
 */
public class Code02_PrintAllSubsquences {
    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        List<String> ans = new ArrayList<>();
        String path="";
        process1(str,0,ans,path);
        return ans;
    }

    /**
     *
     * @param str 固定参数
     * @param index 来到了str[index]字符，index是位置
     * @param ans  答案
     * @param path str[0...index-1]已经走过了！之前的决定，都在path上；之前的决定已经不能改变了，就是path
     *             str[index...]还能决定，之前已经确定，而后米娜还能自由选择的话，
     *             把所有生成的子序列，放入ans中
     */
    private static void process1(char[] str, int index, List<String> ans, String path) {
        if(index==str.length){
            ans.add(path);
            return;
        }
        process1(str,index,ans,path);
        process1(str,index,ans,path+String.valueOf(str[index]));
    }

    public static void process2(char[] str, int index, HashSet<String> ans, String path){
        if(index==str.length){
            ans.add(path);
            return;
        }
        process2(str,index+1,ans,path);
        process2(str,index+1,ans,path+String.valueOf(str[index]));
    }

    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path="";
        HashSet<String> set = new HashSet<>();
        process2(str,0,set,path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    public static void main(String[] args) {
        String test = "accc";
        List<String> ans1 = subs(test);
        for (String s : ans1) {
            System.out.println(s);
        }
        System.out.println("-----");

        List<String> ans2 = subsNoRepeat(test);
        for (String s : ans2) {
            System.out.println(s);
        }
    }
}
