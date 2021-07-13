package top_interview_question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode测试链接：https://leetcode.com/problems/3sum/
 *
 * @author Lenovo
 */
public class Problem_0015_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if(i==0||nums[i-1]!=nums[i]){
                List<List<Integer>> nexts = twoSum(nums,i+1,-nums[i]);
                for (List<Integer> cur : nexts) {
                    cur.add(0,nums[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    /**
     * 找出[begin,...]范围上所有累加和为target的二元组
     *
     * @param nums
     * @param begin
     * @param target
     * @return
     */
    public List<List<Integer>> twoSum(int[] nums, int begin, int target) {
        int l =begin;
        int r=nums.length-1;
        List<List<Integer>> ans = new ArrayList<>();
        while(l<r){
            if(nums[l]+nums[r]>target){
                r--;
            }else if(nums[l]+nums[r]<target){
                l++;
            }else{
                if(l==begin||nums[l-1]!=nums[l]){
                   List<Integer> cur = new ArrayList<>();
                   cur.add(nums[l]);
                   cur.add(nums[r]);
                   ans.add(cur);
                }
                l++;
            }
        }
        return ans;
    }
}
