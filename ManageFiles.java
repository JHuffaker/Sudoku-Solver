import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class ManageFiles {

  public ArrayList<ArrayList<Integer>> getPuzzle() {
  // Creates a multidimensional ArrayList and loads the sudoku puzzle
  // file into it. Returns the ArrayList.

    // Initialize the multidimensional ArrayList
    ArrayList<ArrayList<Integer>> sudokuPuzzle = new ArrayList<>(9);
      for (int i=0; i < 9; i++) {
        sudokuPuzzle.add(new ArrayList<>(9));
      }

      // Create counter for rows
      int row = 0;

      // Open file
      try {
        File myObj = new File("mygame.txt");
        Scanner myReader = new Scanner(myObj);

        // Get the puzzle line by line and add to ArrayList
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String line = data.trim();
            for (String newLine: line.split(" ")) {
                int number = Integer.parseInt(newLine);
                sudokuPuzzle.get(row).add(number);
            }
            row++;
        }

        // Close the file
        myReader.close();

      // Catch errors
      } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
      }

      // Return the puzzle
      return sudokuPuzzle;
  }


  public void createFile() {
    try {
      File myObj = new File("solution.txt");
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
