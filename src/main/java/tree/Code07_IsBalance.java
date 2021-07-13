package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一棵树的头结点 返回这棵树是不是平衡二叉树
 * 平衡二叉树：
 * 对于任意一颗子树，左子树与右字数的高度差不超过1
 */
public class Code07_IsBalance {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //返回信息类
    public static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }
        return new Info(isBalanced, height);
    }

    public static boolean isBalanced1(Node head){
        return process(head).isBalanced;
    }

    //暴力解法
   public static boolean isBalanced2(Node head){
        Boolean[] ans = new Boolean[1];
        ans[0]=true;
        process2(head,ans);
        return ans[0];
   }

    public  static int process2(Node head, Boolean[] ans) {
        if(!ans[0]||head==null){
            return -1;
        }
        int leftHeight = process2(head.left,ans);
        int rightHeight = process2(head.right,ans);
        if(Math.abs(leftHeight-rightHeight)>1){
            ans[0]=false;
        }
        int height = Math.max(leftHeight,rightHeight)+1;
        return height;
    }

    public static Node generateRandomBST(int maxLevel,int maxVal){
        return generate(1,maxLevel,maxVal);
    }

    public static Node generate(int level, int maxLevel, int maxVal) {
        if(level>maxLevel||Math.random()<0.5){
            return null;
        }
        Node head = new Node((int)(Math.random()*maxVal));
        head.left=generate(level+1,maxLevel,maxVal);
        head.right=generate(level+1,maxLevel,maxVal);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
