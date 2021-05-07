import java.util.ArrayList;

class SudokuSolver {
    public static void main(String[] args) {

        // Create an object of the ManageFiles class
        ManageFiles files = new ManageFiles();
        // Get the puzzle
        ArrayList<ArrayList<Integer>> puzzle = files.getPuzzle();
    }
}