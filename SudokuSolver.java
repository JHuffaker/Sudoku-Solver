import java.util.ArrayList;

class SudokuSolver {
    public static void main(String[] args) {

        // Create an object of the ManageFiles class
        ManageFiles files = new ManageFiles();
        // Get the puzzle
        ArrayList<ArrayList<Integer>> puzzle = files.getPuzzle();
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
}