
/**
 * EightQueens is a program that can place any number of queens, up to a maximum
 * of 8, on a chessboard such that no queen can attack any other queens.
 */

import java.util.Arrays;

public class EightQueens implements Cloneable {
    public char[][] chessBoard = new char[8][8];
    private int numPlacedQueens;
    private int totalQueens;
    private int[] location = new int[8];

    public EightQueens() {
        for (char[] row : this.chessBoard) {
            Arrays.fill(row, 'o');
        }
    }

    public Object clone() throws CloneNotSupportedException {
        EightQueens copy = (EightQueens) super.clone();

        char[][] cloned = new char[copy.chessBoard.length][];
        for (int i = 0; i < copy.chessBoard.length; i++) {
            cloned[i] = copy.chessBoard[i].clone();
        }
        copy.chessBoard = cloned;
        return copy;
    }

    public char[][] getBoard() {
        return this.chessBoard;
    }

    public char get(int x, int y) {
        if (x < 0 || y < 0 || x > 7 || y > 7) {
            return 'o';
        }
        return this.chessBoard[x][y];
    }

    public void setQueen(int row, int column) {
        this.chessBoard[row][column] = 'Q';
        numPlacedQueens++;
    }

    public void emptySquare(int row, int column) {
        this.chessBoard[row][column] = 'o';
        numPlacedQueens--;
    }

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
                        // System.out.println(board[i][j]);
                        // System.out.print(val);
                        // checks upper diagonal
                        if (val == board[i][j]) {
                            return false;
                        }
                    }
                    for (int k = row + 1, l = col - 1; k < 8 && l >= 0 && k < 8 && l < 8; k++, l--) {
                        // System.out.println(board[k][l]);
                        // System.out.print(val);
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

    public int getNumPlacedQueens() {
        // char[][] board = getBoard();
        // numPlacedQueens = 0;
        // for (int row = 0; row < board.length; row++) {
        // for (int column = 0; column < board[row].length; column++) {
        // if (this.chessBoard[row][column] == 'Q') {
        // numPlacedQueens++;
        // }
        // }
        // }
        return numPlacedQueens;
    }

    public boolean getQueenLocations() {
        char[][] board = getBoard();
        numPlacedQueens = 0;
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (this.chessBoard[row][column] == 'Q') {
                    System.out.println(isSafe(row, column));
                    if (isSafe(row, column)) {
                        return true;
                        // location[row] = column;
                    }
                }
            }
        }

        return false;
    }

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