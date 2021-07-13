package slidingwindow;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 */
public class Code02_AllLessNumSubArray {
    // 暴力的对数器方法
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int n=arr.length;
        int count=0;
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                int max=arr[l];
                int min=arr[l];
                for(int i=l+1;i<=r;i++){
                    max=Math.max(max,arr[i]);
                    min=Math.min(min,arr[i]);
                }

                if(max-min<=sum){
                    count++;
                }
            }
        }


        return count;
    }

    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int n=arr.length;
        int count=0;
        int r=0;
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        for (int l = 0; l < n; l++) {
            while (r<n){
                while(!maxWindow.isEmpty()&&arr[maxWindow.peekLast()]<=arr[r]){
                    maxWindow.pollLast();
                }
                maxWindow.addLast(r);

                while(!minWindow.isEmpty()&&arr[minWindow.peekLast()]>=arr[r]){
                    minWindow.pollLast();
                }
                minWindow.addLast(r);

                if(arr[maxWindow.peek()]-arr[minWindow.peek()]>sum){
                    break;
                }else{
                    r++;
                }
            }
            count+=r-l;
            if(maxWindow.peek()==l){
                maxWindow.poll();
            }
            if (minWindow.peek()==l){
                minWindow.poll();
            }
        }
         return count;
    }


    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
