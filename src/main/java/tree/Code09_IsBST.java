package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵树的头结点，返回这棵树是不是二叉搜索树
 * <p>
 * 二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，
 * 或者是具有下列性质的二叉树： 若它的左子树不空，则左子树上所有  结点的值均小于它的根结点的值；
 * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉
 * 排序树。二叉搜索树作为一种经典的数据结构，它既有链表的快速插入与删除操作的特点，又有数组
 * 快速查找的优势；所以应用十分广泛，例如在文件系统和数据库系统一般会采用这种数据结构进行
 * 高效率的排序与检索操作。
 */
public class Code09_IsBST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //暴力解法   二叉树中序遍历  前一个数必须前一个数必须小于后一个数
    public static boolean rightWay(Node head) {
        if (head == null) {
            return true;
        }
        List<Node> nodes = new ArrayList<>();
        in(head, nodes);
        for (int i = 1; i < nodes.size(); i++) {
            if (nodes.get(i).value < nodes.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, List<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);

    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean i, int ma, int mi) {
            isBST = i;
            max = ma;
            min = mi;
        }

    }

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;

    }

    public static Info process(Node head) {
        /*if (head == null) {
            return new Info(true,0,0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        boolean flag=true;
        int max =head.value;
        int min = head.value;
        max=Math.max(max,leftInfo.max);
        max=Math.max(max,rightInfo.max);
        min=Math.min(min,leftInfo.min);
        min=Math.min(min,rightInfo.min);
        boolean isBST=true;
        if(head.value<=leftInfo.max){
            isBST=false;
        }
        if(head.value>=rightInfo.min){
            isBST=false;
        }
        if(!leftInfo.isBST||!rightInfo.isBST){
            isBST=false;
        }
        return new Info(isBST,max,min);*/
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int max =head.value;
        if (leftInfo != null) {
            max=Math.max(max,leftInfo.max);
        }
        if (rightInfo != null) {
            max=Math.max(max,rightInfo.max);
        }
        int min =head.value;
        if (leftInfo != null) {
            min=Math.min(min,leftInfo.min);
        }
        if (rightInfo != null) {
            min=Math.min(min,rightInfo.min);
        }
        boolean isBST=true;
        if (leftInfo != null&&!leftInfo.isBST) {
            isBST=false;
        }
        if (rightInfo != null&&!rightInfo.isBST) {
            isBST=false;
        }
        if (leftInfo != null && leftInfo.max >= head.value) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= head.value) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }

    public static Node generateRandomBST(int maxLevel,int maxVal){
        return generate(1,maxLevel,maxVal);
    }

    public static Node generate(int level, int maxLevel, int maxVal) {
        if(level>maxLevel|| Math.random()<0.5){
            return null;
        }
        Node head = new Node((int)(Math.random()*maxVal));
        head.left=generate(level+1,maxLevel,maxVal);
        head.right=generate(level+1,maxLevel,maxVal);
        return head;
    }
    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 10;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (rightWay(head) != isBST(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
