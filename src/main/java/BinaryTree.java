import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryTree<T> {
    private final List<T> heap;
    private final Map<T, Integer> indexMap;
    private int heapSize;
    private final Comparator<? super T> comparator;


    public BinaryTree(Comparator<? super T> comparator) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = heapSize;
        this.comparator = comparator;
    }

    public BinaryTree(List<T> heap, Map<T, Integer> indexMap, int heapSize, Comparator<? super T> comparator) {
        this.heap = heap;
        this.indexMap = indexMap;
        this.heapSize = heapSize;
        this.comparator = comparator;
    }

    public static void main(String[] args) {

        final int[] values = {1, 3, 4, 5, 2, 8, 6, 7, 9, 0};
        // TODO:

        List<Integer> heap = new ArrayList<>();
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            heap.add(values[i]);
            indexMap.put(values[i],i);
        }
        BinaryTree<Integer> tree = new BinaryTree<Integer>(heap,indexMap,values.length,(a, b) -> a - b);
        Node head = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(2);
        Node node6 = new Node(8);
        Node node7 = new Node(6);
        Node node8 = new Node(7);
        Node node9 = new Node(9);
        Node node10 = new Node(0);
        head.setLeft(node2);
        head.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        node4.setLeft(node8);
        node4.setRight(node9);
        node5.setLeft(node10);
        tree.createBinaryTree(head,1);
    }

    public  Node createBinaryTree(Node node, int value) {
        // TODO:
        int index = 0;
        node.setValue(value);
        Node leftChild = new Node();
        leftChild = createBinaryTree(leftChild, ++index);
        Node rightChild = new Node();
        rightChild = createBinaryTree(rightChild, ++index);
        return node;
    }

    public static void inOrderTransval(Node node) {
        // TODO:
        if (node == null) {
            return;
        }
        inOrderTransval(node.getLeft());
        System.out.println(node.getValue());
        inOrderTransval(node.getRight());
    }

    public int size() {
        return heapSize;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean contains(T t) {
        return indexMap.containsKey(t);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }


    public List<T> getAll() {
        List<T> resList = new ArrayList<>();
        for (T t : heap) {
            resList.add(t);
        }
        return resList;
    }

    public void heapInsert(int index) {
        //比较当前节点与父节点  较小值放在上面
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) > 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void swap(int i, int j) {
        T t1 = heap.get(i);
        T t2 = heap.get(j);
        heap.set(i, t2);
        heap.set(j, t1);
        indexMap.put(t2, i);
        indexMap.put(t1, j);

    }

    public void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int best = (left + 1) < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) > 0 ? (left + 1) : left;
            best = comparator.compare(heap.get(best), heap.get(index)) > 0 ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            best = best * 2 + 1;
        }
    }

    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }


}

class Node {

    // 节点值
    private int value;

    // 左节点
    private Node left;

    // 右节点
    private Node right;

    // TODO:


    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}