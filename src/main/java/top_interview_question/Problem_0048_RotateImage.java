package top_interview_question;

public class Problem_0048_RotateImage {
    public void rotate(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a < c) {
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    private void rotateEdge(int[][] matrix, int a, int b, int c, int d) {
        int times = d - b;
        for (int i = 0; i != times; i++) {
            int tmp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c-i][b];
            matrix[c-i][b]=matrix[c][d-i];
            matrix[c][d-i]=matrix[a+i][d];
            matrix[a+i][d]=tmp;

        }
    }


}
