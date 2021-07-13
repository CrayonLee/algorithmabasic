package top_interview_question;

public class Problem_0042_TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int n = height.length;
        int l = 1;
        int r = n - 2;
        int water = 0;
        int leftMax = height[0];
        int rightMax = height[n - 1];
        while (l <= r) {
            if (leftMax <= rightMax) {
                water += Math.max(0, leftMax - height[l]);
                leftMax = Math.max(leftMax, height[l++]);
            } else {
                water += Math.max(0, rightMax - height[r]);
                rightMax = Math.max(rightMax, height[r--]);
            }
        }
        return water;
    }
}
