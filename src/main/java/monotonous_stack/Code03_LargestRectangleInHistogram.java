package monotonous_stack;

import java.util.Stack;

// 测试链接：https://leetcode.com/problems/largest-rectangle-in-histogram
public class Code03_LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {

        if (heights == null || heights.length==0) {
            return 0;
        }
        int maxArea =0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty()&&heights[stack.peek()]>=heights[i]){
                int j =stack.pop();
                int k = stack.isEmpty()?-1:stack.peek();
                int curArea = (i-k-1)*heights[j];
                maxArea=Math.max(curArea,maxArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (heights.length - k - 1) * heights[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;

    }

}
