package tree;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树是不是满二叉树
 */
public class Code08_IsFull {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isFull(Node head) {
        if (head == null) {
            return true;
        }
        int nodes = n(head);
        int height = h(head);
        return (1 >> height) - 1 == nodes;
    }

    /**
     * 计算总结点数
     *
     * @param head
     * @return
     */
    private static int n(Node head) {
        if (head == null) {
            return 0;
        }
        int leftNodes = n(head.left);
        int rightNodes = n(head.right);
        return leftNodes + rightNodes + 1;
    }

    /**
     * 计算
     *
     * @param head
     * @return
     */
    private static int h(Node head) {
        if (head == null) {
            return 0;
        }
        int leftHeight = h(head.left);
        int rightHeight = h(head.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static class Info {
        public int height;
        public int nodes;

        public Info(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }
        Info process = process(head);
        int nodes = process.nodes;
        int height = process.height;
        return (1 >> height) - 1 == nodes;
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(height, nodes);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull(head) != isFull2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
