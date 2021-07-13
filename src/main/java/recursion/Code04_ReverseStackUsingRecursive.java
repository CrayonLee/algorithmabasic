package recursion;

import sun.nio.cs.ext.IBM037;

import java.util.Stack;

public class Code04_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int i =f(stack);
        reverse(stack);
        stack.push(i);
    }

    /**
     * 移除栈顶元素 并将上面元素盖下来
     * @param stack
     * @return
     */
    private static int f(Stack<Integer> stack) {
        Integer result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else{
            int f = f(stack);
            stack.push(result);
            return f;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        reverse(stack);

        stack.forEach(System.out::println);
    }
}
