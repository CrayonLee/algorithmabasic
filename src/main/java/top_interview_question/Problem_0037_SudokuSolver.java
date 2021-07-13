package top_interview_question;

public class Problem_0037_SudokuSolver {
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];
        init(board, row, col, bucket);
        process(board, 0, 0, row, col, bucket);
    }

    private boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        // all filled
        if (i == 9) {
            return true;
        }
        int nexti = j == 8 ? i + 1 : i;
        int nextj = j == 8 ? 0 : j + 1;
        if (board[i][j] != '.') {
            return process(board, nexti, nextj, row, col, bucket);
        } else {
            int bid = 3 * (i / 3) + (j / 3);
            for (int num = 1; num <= 9; num++) {
                if ((!row[i][num]) && (!col[j][num]) && (!bucket[bid][num])) {
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                    board[i][j] = (char) (num + '0');
                    if (process(board, nexti, nextj, row, col, bucket)) {
                        return true;
                    }
                    row[i][num] = false;
                    col[j][num] = false;
                    bucket[bid][num] = false;
                    board[i][j] = '.';
                }
            }
            return false;
        }
    }

    private void init(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int bid = 3 * (i / 3) + (j / 3);
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                }
            }
        }
    }

//    public void solveSudoku(char[][] board) {
//        if (board == null || board.length == 0) {
//            return;
//        }
//        solve(board);
//    }
//
//    private boolean solve(char[][] board) {
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                if(board[i][j]=='.'){
//                    for(char c='1';c<='9';c++){
//                        if(isValid(board,i,j,c)){
//                            //put c into this cell
//                            board[i][j]=c;
//
//                            if(solve(board)){
//                                //it is the solution
//                                return true;
//                            }else{
//                                //go back
//                                board[i][j]='.';
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    private boolean isValid(char[][] board, int row, int col, char c) {
//        for (int i = 0; i < 9; i++) {
//            //check row
//            if (board[i][col] != '.' && board[i][col] == c) {
//                return false;
//            }
//            //check column
//            if (board[row][i] != '.' && board[row][i] == c) {
//                return false;
//            }
//            //check block
//            if (board[3 * (row / 3) + (i / 3)][3 * (col / 3) + (i % 3)] != '.'
//                    && board[3 * (row / 3) + (i / 3)][3 * (col / 3) + (i % 3)] == c){
//                return false;
//            }
//
//        }
//        return true;
//    }
}
