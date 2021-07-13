package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出一个字符串的全排列问题
 */
public class Code03_PrintAllPermutations {
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        List<Character> rest = new ArrayList<>();
        for (char c : str) {
            rest.add(c);
        }
        String path = "";
        process1(rest, path, ans);
        return ans;
    }

    //相对粗糙的
    private static void process1(List<Character> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            for (int i = 0; i < rest.size(); i++) {
                Character cur = rest.get(i);
                rest.remove(i);
                process1(rest, path + cur, ans);
                //恢复现场
                rest.add(i, cur);
            }
        }

    }

    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g(str, 0, ans);
        return ans;
    }

    private static void g(char[] str, int index, List<String> ans) {
        if(index==str.length){
            ans.add(String.valueOf(str));
        }else{
            for (int i = index; i < str.length; i++) {
                swap(str,i,index);
                g(str,index+1,ans);
                swap(str,i,index);
            }
        }
    }

    private static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g1(str, 0, ans);
        return ans;
    }

    /**
     * 将全排列中的结果去重
     *
     * @param str
     * @param index
     * @param ans
     */
    private static void g1(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, index, i);
                    g1(str, index + 1, ans);
                    swap(str, index, i);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> list = permutation1("abc");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("===");
        List<String> list1 = permutation2("acc");
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
        System.out.println("===");
        List<String> list3 = permutation3("acc");
        list3.forEach(System.out::println);
    }
}
