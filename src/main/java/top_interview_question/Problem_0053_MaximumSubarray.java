package top_interview_question;

/**
 * @author lizihao
 * @date 2021/7/10 21:48
 */
public class Problem_0053_MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            max = Math.max(cur, max);
            cur = Math.max(cur, 0);
        }
        return max;
    }
}
