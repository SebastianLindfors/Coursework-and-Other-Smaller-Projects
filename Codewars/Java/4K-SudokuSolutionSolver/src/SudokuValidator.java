/**
 * This class is a solution to codewars kata: 4K-Sudoku Solution Validator
 * https://www.codewars.com/kata/529bf0e9bdf7657179000008/
 *
 * Author: Sebastian Lindfors
 */

public class SudokuValidator {

    public static boolean check(int[][] sudoku) {

        int[][] rows =      {{1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},
                            {1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},
                            {1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9}};
        int[][] columns =   {{1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},
                            {1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},
                            {1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9}};
        int[][] blocks =    {{1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},
                            {1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},
                            {1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9},{1, 2, 3, 4, 5, 6, 7, 8, 9}};

        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {

                if (sudoku[i][j] == 0) return false;

                if (rows[i][sudoku[i][j] -1] == sudoku[i][j] ) {
                    rows[i][sudoku[i][j] - 1] = -1;
                }
                else return false;
                if (columns[j][sudoku[i][j] - 1] == sudoku[i][j]) {
                    columns[j][sudoku[i][j] - 1] = -1;
                }
                else return false;
                if (blocks[coordinateToBlock(i,j)][sudoku[i][j] - 1] == sudoku[i][j]) {
                    blocks[coordinateToBlock(i,j)][sudoku[i][j] - 1] = -1;
                }
                else return false;


            }
            System.out.println();
        }



        return true;
    }

    private static int coordinateToBlock(int x, int y) {

        if (x < 3) {
            if (y < 3)      return 0;
            else if (y < 6) return 1;
            else            return 2;
        }
        else if (x < 6) {
            if (y < 3)      return 3;
            else if (y < 6) return 4;
            else            return 5;
        }
        else {
            if (y < 3)      return 6;
            else if (y < 6) return 7;
            else            return 8;
        }
    }

}
