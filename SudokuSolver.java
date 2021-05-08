import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList;

class SudokuSolver {
    public static void main(String[] args) {

        // Request the name of the puzzle
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter file name of sudoku puzzle: ");
        String puzzleFileName = myObj.nextLine();

        // Request the name the user would like to give the file
        // of the solution
        Scanner secondObj = new Scanner(System.in);
        System.out.println("What would you like to name the file of the solution? ");
        String solutionFileName = secondObj.nextLine();

        // Create an object of the ManageFiles class
        ManageFiles files = new ManageFiles(puzzleFileName, solutionFileName);

        // Retrieve puzzle
        ArrayList<ArrayList<Integer>> puzzle = files.getPuzzle();

        // Retrieve the solution, and create and write to a file
        ArrayList<ArrayList<Integer>> completedPuzzle = solvePuzzle(puzzle, 0, 0);
        files.createFile();
        files.writeToFile(completedPuzzle);
    }

    public static boolean isApplicable(ArrayList<ArrayList<Integer>> puzzle, int row, int col, int value) {
    // Determines if the value can be added into the cell

        // Iterate through the respective column
        for (int r = 0; r < 9; r++) {
            if (puzzle.get(r).get(col) == value) {
                return false;
            }
        }

        // Iterate through the respective row
        for (int c = 0; c < 9; c++) {
            if (puzzle.get(row).get(c) == value) {
                return false;
            }
        }

        // Find the beginning of the section's
        // row and column
        int rowSection = (row / 3) * 3;
        int colSection = (col / 3) * 3;

        // Iterate through the section
        for (int r = rowSection; r < rowSection + 3; r++) {
            for (int c = colSection; c < colSection + 3; c++) {
                if (puzzle.get(r).get(c) == value) {
                    return false;
                }
            }
        }

        // Return true if the value passes all of the tests
        return true;
    }

    public static ArrayList<ArrayList<Integer>> solvePuzzle(ArrayList<ArrayList<Integer>> puzzle, int row, int col) {
    // This is the bulk of the program that solves the puzzle by incrementing
    // values and iterating through the puzzle via recursion
    
            // Initialize the values that will be used for the 
            // next cell
            int nextCol = 0;
            int nextRow = 0;
    
            // If the value of the cell is 0, we need to change it
            if (puzzle.get(row).get(col) == 0) {
    
                // Iterate through all possible values
                for (int value = 1; value < 10; value++) {
                    // Check to see if value can be placed in cell
                    if (isApplicable(puzzle, row, col, value)) {
                        puzzle.get(row).set(col, value);
                        
                        // Find the next cell
                        if (col < 8) {
                            nextCol = col + 1;
                            nextRow = row;
                        } else {
                            nextCol = 0;
                            nextRow = row + 1;
                            // Return puzzle if its finished
                            if (nextRow > 8) {
                                return puzzle;
                            }
                        }
    
                        // Initialize the next step
                        ArrayList<ArrayList<Integer>> nextStep = solvePuzzle(puzzle, nextRow, nextCol);
    
                        // If it's null, value needs to be reset incremented
                        // and the proces continues
                        if (nextStep == null) {
                            puzzle.get(row).set(col, 0);
                        // if it's not null, we can return the puzzle
                        } else {
                            return nextStep;
                        }
                    }
                }
    
                // Return null if none of the values are applicable
                return null;
    
            // If the value of the cell is not 0, then we just need to
            // go to the next one
            } else {
                // Grab the next row and column
                if (col < 8) {
                    nextCol = col + 1;
                    nextRow = row;
                    return(solvePuzzle(puzzle, nextRow, nextCol));
                } else {
                    nextCol = 0;
                    nextRow = row + 1;
                    if (nextRow > 8) {
                        return puzzle;
                    } else {
                        return(solvePuzzle(puzzle, nextRow, nextCol));
                    }
                }
            }
    }
}