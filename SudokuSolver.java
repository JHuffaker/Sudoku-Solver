import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // Import ArrayList class

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
        FileManager fileManager = new FileManager(puzzleFileName, solutionFileName);

        // Retrieve puzzle
        ArrayList<ArrayList<Integer>> puzzle = fileManager.getPuzzle();

        // Retrieve the solution, and create and write to a file
        Puzzle puzzleSolver = new Puzzle();
        ArrayList<ArrayList<Integer>> completedPuzzle = puzzleSolver.solvePuzzle(puzzle, 0, 0);
        fileManager.createFile();
        fileManager.writeToFile(completedPuzzle);
    }
}