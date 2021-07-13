package _58;

import java.awt.*;
import java.util.Arrays;

public class Test1 {
    public static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node(int val){
            this.val=val;
        }
    }
    /**
     * 返回根据arr中元素顺序构成二叉搜索树的树层级
     * @param arr int整型一维数组 无序且无数值重复的数组
     * @return int整型
     */
    public int tdepth (int[] arr) {
        // write code here
        int[] arrCopy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrCopy[i]=arr[i];
        }
        Arrays.sort(arrCopy);
        Node head= new Node(arr[0]);
        for (int i = 0; i < arrCopy.length; i++) {
            if(arrCopy[i]==arr[0]){
                break;
            }
            head.left=new Node(arr[i]);

        }
        return 0;
    }
}
