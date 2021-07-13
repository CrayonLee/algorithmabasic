package top_interview_question;

import com.sun.org.apache.bcel.internal.generic.SWAP;
import jdk.nashorn.internal.IntDeque;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem_0047_Permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        process(nums,0,ans);
        return ans;
    }

    private void process(int[] nums, int index, List<List<Integer>> ans) {
        if(index==nums.length){
            List<Integer> cur = new ArrayList<>();
            for (int num : nums) {
                cur.add(num);
            }
            ans.add(cur);
        }else{
            Set<Integer> appeared = new HashSet<>();
            for(int i=index;i<nums.length;i++){
                if(appeared.add(nums[i])){
                    swap(nums,index,i);
                    process(nums,index+1,ans);
                    swap(nums,index,i);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    public static void main(String[] args) {
       new Problem_0047_Permutations2().permuteUnique(new int[]{1,1,2}).forEach(System.out::println);
    }
}
