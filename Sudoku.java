import java.util.Scanner;
// This class provide the code for solving a sudoku problem
// with backtracking algorithm
public class Sudoku {
    // class variables
    private int size = 9;
    private int[][] board;

    public Sudoku() {
        this.board = new int[size][size];
    }

    // this method receives the user input and stores the number in the bord
    public void enterBoard() {
        Scanner scan = new Scanner(System.in);
        for (int row = 0; row < size; row++) {
            System.out.println("Please enter numbers for row " + row + ":");
            String s = scan.nextLine();
            int number_set = Integer.parseInt(s);
            int counter = 0;
            while (number_set > 0) {
                int num = number_set%10;
                number_set = number_set/10;
                this.board[row][size-counter-1] = num;
                counter++;
            }
        }
    }

    // this method fills the bord by trying each digit 1-9 for the
    // empty cells and uses backtracking if necessary
    public boolean fillBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (this.board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        this.board[row][col] = num;
                        if (isSafe(row, col)){
                            if(fillBoard()) {
                                return true;
                            }
                        }
                        this.board[row][col] = 0;

                    }
                    return false;
                }
            }
        }
        return true;
    }

    // this method checks if the numbers stored in each cell is
    // a valid choice ( row, column, subregion)
    public boolean isSafe(int row, int col) {
        //checking row
        for (int j = 0; j < size; j++) {
            if(j != col){
                if (this.board[row][j] == this.board[row][col]) {
                    return false;
                }
            }
        }

        // check column
        for (int i = 0; i < size; i++) {
            if (i != row) {
                if (this.board[i][col] == this.board[row][col]) {
                    return false;
                }
            }
        }

        // check subregion
        int subbox_size = 3;
        int low_row_limit = (row/subbox_size) * subbox_size;
        int high_row_limit = ((row/subbox_size) * subbox_size) + subbox_size;
        int low_col_limit = (col/subbox_size) * subbox_size;
        int high_col_limit = ((col/subbox_size) * subbox_size) + subbox_size;

        for (int i = low_row_limit; i < high_row_limit; i++) {
            for (int j = low_col_limit; j < high_col_limit; j++) {
                if (i != row && j != col) {
                    if (this.board[i][j] == this.board[row][col]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // this method prints the board in terminal
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Sudoku sudoku = new Sudoku();
        sudoku.enterBoard();
        if(sudoku.fillBoard()){
            sudoku.printBoard();
        }
        else{
            System.out.println("There is no solution for this board!");
        }

    }

}
