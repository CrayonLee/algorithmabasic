package top_interview_question;

import java.util.Stack;

public class Problem_0020_ValidParentheses {
    public static boolean isValid(String s) {
        if(s==null||s.length()==0){
            return true;
        }
        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            if(c=='('||c=='['||c=='{'){
                stack.add(c=='('?')':(c=='['?']':'}'));
            }else{
                if(stack.isEmpty()){
                    return false;
                }else{
                    Character pop = stack.pop();
                    if(c!=pop){
                        return false;
                    }

                }
            }
        }
        return stack.isEmpty();
    }
}
