package top_interview_question;

import java.util.*;

/**
 * @author lizihao
 * @date 2021/7/9 23:26
 */
public class Problem_0049_GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
       Map<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String key = String.valueOf(chs);
            if(!map.containsKey(key)){

                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] record = new int[26];
            for(char ch:str.toCharArray()){
                record[ch-'a']++;
            }
            StringBuilder builder = new StringBuilder();
            for (int i : record) {
                builder.append(String.valueOf(i)).append("_");
            }
            String key =builder.toString();
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return  new ArrayList<>(map.values());
    }
}
