package top_interview_question;

import java.util.ArrayList;
import java.util.List;

public class Problem_0022_GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        char[] path = new char[n << 1];
        List<String> ans = new ArrayList<>();
        process(path, 0, 0, n, ans);
        return ans;
    }

    private void process(char[] path, int index, int leftMinusRight, int leftRest, List<String> ans) {
        if (index == path.length) {
            ans.add(String.valueOf(path));
        } else {
          if(leftRest>0){
              path[index]='(';
              process(path,index+1,leftMinusRight+1,leftRest-1,ans);
          }
          if(leftMinusRight>0){
              path[index]=')';
              process(path,index+1,leftMinusRight-1,leftRest,ans);
          }
        }
    }

    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[n << 1];
        force(path, 0, ans);
        return ans;
    }

    private void force(char[] path, int index, List<String> ans) {
        if (index == path.length) {
            if (valid(path)) {
                ans.add(String.valueOf(path));
            }
        } else {
            path[index] = '(';
            force(path, index + 1, ans);
            path[index] = ')';
            force(path, index + 1, ans);
        }
    }

    public boolean valid(char[] path) {
        int count = 0;
        for (char c : path) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}
