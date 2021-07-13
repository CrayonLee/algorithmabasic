import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given a 2D matrix of size m x n, consisting of non-negative integers.
 * You are also given an integer k.
 * <p>
 * The value of coordinate (a, b) of the matrix is the XOR of all matrix[i][j] where 0 <= i <= a < m and 0 <= j <= b < n (0-indexed).
 * <p>
 * Find the kth largest value (1-indexed) of all the coordinates of matrix.
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value
 */
public class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        int[][] s = new int[m + 1][n + 1];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                s[i][j] = matrix[i - 1][j] ^ matrix[i][j - 1] ^ matrix[i][j] ^ matrix[i - 1][j - 1];

            }
        }

        return 0;
    }
}
