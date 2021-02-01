
/**
 * EightQueens is a program that can place any number of queens, up to a maximum
 * of 8, on a chessboard such that no queen can attack any other queens.
 * 
 * @author Nafisa Tabassum <a href="mailto:nafisa.tabassum@ucalgary.ca">nafisa.tabassum@ucalgary.ca</a>
 * @version 1.0
 */

import java.util.Arrays;

public class EightQueens implements Cloneable {
    public char[][] chessBoard = new char[8][8];
    private int numPlacedQueens;
    private int totalQueens;

    /**
     * Represents an entire 8x8 empty chessboard denoted by 'o' for each square
     */
    public EightQueens() {
        for (char[] row : this.chessBoard) {
            Arrays.fill(row, 'o');
        }
    }

    /**
     * Creates a deep copy of the chessboard
     */
    public Object clone() throws CloneNotSupportedException {
        EightQueens copy = (EightQueens) super.clone();

        char[][] cloned = new char[copy.chessBoard.length][];
        for (int i = 0; i < copy.chessBoard.length; i++) {
            cloned[i] = copy.chessBoard[i].clone();
        }
        copy.chessBoard = cloned;
        return copy;
    }

    /**
     * Gets current state of the chessboard
     * 
     * @return Current state of chessboard
     */
    public char[][] getBoard() {
        return this.chessBoard;
    }

    /**
     * Ensures the location of the square to be marked as threatened by a potential
     * queen placement is valid when checking diagonal squares
     * 
     * @param row    Row index
     * @param column Column index
     * @return A valid diagonal square location or an arbitrary char if the indices
     *         are out of bounds
     */
    public char get(int row, int column) {
        if (row < 0 || column < 0 || row > 7 || column > 7) {
            return 'o';
        }
        return this.chessBoard[row][column];
    }

    /**
     * Marks a square as a queen denoted by 'Q', updates number of existing queens
     * on board
     * 
     * @param row    Row index
     * @param column Column index
     */
    public void setQueen(int row, int column) {
        this.chessBoard[row][column] = 'Q';
        numPlacedQueens++;
    }

    /**
     * Marks a square as an empty space denoted by 'o', updates number of existing
     * queens on board
     * 
     * @param row    Row index
     * @param column Column index
     */
    public void emptySquare(int row, int column) {
        this.chessBoard[row][column] = 'o';
        numPlacedQueens--;
    }

    /**
     * Recursively attempts to place a specified number of queens in allowed
     * positions on the board
     * 
     * @param queens Number of queens to place on board
     * @return true if successful placement of all specified number of queens and
     *         false if unsuccessful
     */
    public boolean setQueensRecursively(int queens) {
        if (queens == totalQueens) {
            return true;
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (isSafe(i, j)) {
                        setQueen(i, j);
                        queens++;
                        if (setQueensRecursively(queens)) {
                            return true;
                        } else {
                            emptySquare(i, j);
                            queens--;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Wrapper for {@link #setQueensRecursively(int)} setQueensRecursively} method
     * that pre-checks board for illegal user-placed queens and exceeding the
     * maximum number of queens placed on board
     * 
     * @param queensRemaining Number of queens to place on board
     * @return true if successful solution and false if there are errors with the
     *         board or it is unable to place all specified number of queens in a
     *         valid arrangement
     */
    public boolean setQueens(int queensRemaining) {
        if (queensRemaining < 1 || queensRemaining > 8) {
            throw new IllegalArgumentException("Number of queens must be betwen 1 and 8 inclusive");
        }

        if ((getNumPlacedQueens() + queensRemaining) > 8) {
            throw new IllegalArgumentException("Total number of queens present on board exceeds maximum number of 8");
        }

        totalQueens = queensRemaining;

        if (!validBoard()) {
            return false;
        } else {
            if (setQueensRecursively(0)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if user placement of queens are valid i.e. no more than one queen in
     * each row, column, upper diagonal or lower diagonal
     * 
     * @return true if placed queens are in a valid arrangement and false if not
     */
    public boolean validBoard() {
        char[][] board = getBoard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == 'Q') {
                    char val = board[row][col];
                    for (int otherCol = col + 1; otherCol < 8; otherCol++) { // checks if duplicates in same row
                        if (val == board[row][otherCol]) {
                            return false;
                        }
                    }
                    for (int otherRow = row + 1; otherRow < 8; otherRow++) { // checks if duplicates in same col
                        if (val == board[otherRow][col]) {
                            return false;
                        }
                    }
                    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0 && i < 8 && j < 8; i--, j--) {
                        // checks upper diagonal
                        if (val == board[i][j]) {
                            return false;
                        }
                    }
                    for (int k = row + 1, l = col - 1; k < 8 && l >= 0 && k < 8 && l < 8; k++, l--) {
                        // checks lower diagonal
                        if (val == board[k][l]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true; // single queen in row or column;
    }

    /**
     * Marks threatened squares on board
     * 
     * @param x Row index
     * @param y Column index
     * @return true if it is safe to place a queen such that it will not be attacked
     *         by other queens, false if not
     */
    public boolean isSafe(int x, int y) { // finds threatened spots

        // return false if two queens share the same column
        for (int i = 0; i < 8; i++) {
            if (this.chessBoard[x][i] == 'Q') {
                return false;
            }
            if (this.chessBoard[i][y] == 'Q') {
                return false;
            }
        }

        // return false if two queens share the same `` diagonal
        for (int i = 0; i < 8; i++) {
            if ((get(x - i, y - i) == 'Q')) {
                return false;
            }
            if ((get(x - i, y + i) == 'Q')) {
                return false;
            }
            if ((get(x + i, y - i) == 'Q')) {
                return false;
            }
            if ((get(x + i, y + i) == 'Q')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the number of queens already placed on board by user
     * 
     * @return Number of queens placed on board
     */
    public int getNumPlacedQueens() {
        return numPlacedQueens;
    }

    /**
     * Prints out the chessboard in matrix form
     */
    public void display() {
        char[][] board = getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        EightQueens board1 = new EightQueens();
        board1.setQueen(5, 0); // 1
        EightQueens board2 = (EightQueens) board1.clone();
        board2.setQueen(6, 6);
        board1.setQueens(7);
        // board2.setQueen(7, 2);
        // board2.emptySquare(0, 1);
        // System.out.println(board2.setQueens(3));
        // board2.setQueens(3);

        board1.display();
        System.out.println();
        board2.display();

    }

}