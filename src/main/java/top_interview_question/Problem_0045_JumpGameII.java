package top_interview_question;

public class Problem_0045_JumpGameII {
    public static int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int cur = 0;
        int step = 0;
        int next = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(cur<i){
                step++;
                cur=next;
            }
            next=Math.max(next,i+arr[i]);
        }
        return step;
    }
}
