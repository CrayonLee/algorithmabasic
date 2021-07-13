package monotonous_stack;

import java.util.Stack;
import java.util.concurrent.ForkJoinPool;

public class Code04_MaximalRectangle {
    public static int maximalRectangle(char[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == '0' ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    private static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while(!stack.isEmpty()&&height[stack.peek()]>=height[i]){
                int j =stack.pop();
                int k =stack.isEmpty()?-1:stack.peek();
                int curArea = (i-k-1)*height[j];
                maxArea=Math.max(curArea,maxArea);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            int j =stack.pop();
            int k =stack.isEmpty()?-1:stack.peek();
            int curArea = (height.length-k-1)*height[j];
            maxArea=Math.max(curArea,maxArea);
        }
        return maxArea;
    }
}
