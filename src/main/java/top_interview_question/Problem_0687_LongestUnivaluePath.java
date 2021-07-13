package top_interview_question;

public class Problem_0687_LongestUnivaluePath {

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).max - 1;
    }

    private Info process(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        TreeNode left = x.left;
        Info leftInfo = process(left);
        TreeNode right = x.right;
        Info rightInfo = process(right);
        int len = 1;
        if (left != null && x.val == left.val) {
            len = leftInfo.len + 1;
        }
        if (right != null && x.val == right.val) {
            len = Math.max(len, rightInfo.len + 1);
        }
        int max = Math.max(len, Math.max(leftInfo.max, rightInfo.max));
        if (left != null && right != null && left.val == x.val && right.val == x.val) {
            //注意这里使用len而不能使用max  是包含left和right节点的
            max=Math.max(max,leftInfo.len+rightInfo.len+1);
        }
        return new Info(len,max);
    }


}

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Info {
    /**
     * 路径必须从x出发且只能往下走的情况下，路径的最大距离
     */
    int len;
    /**
     * 路径不要求必须从x出发的情况下，整棵树的合法路径最大距离
     */
    int max;

    public Info(int l, int m) {
        this.len = l;
        this.max = m;
    }
}
