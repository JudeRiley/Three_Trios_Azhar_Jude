package cs3500.model.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cs3500.model.ThreeTriosCell;
import cs3500.model.ThreeTriosGrid;
import cs3500.model.Cell;

/**
 * Reads a grid configuration file and constructs a grid object.
 */
public class GridConfigReader {

  private final String filePath;

  /**
   * Constructs a GridConfigReader with the specified file path.
   *
   * @param filePath the path to the grid configuration file
   */
  public GridConfigReader(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Reads the grid configuration file and creates a Grid object.
   *
   * @return a Grid object representing the game grid
   * @throws IOException if an I/O error occurs while reading the file
   */
  public ThreeTriosGrid readGrid() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      // Read the first line to get the number of rows and columns
      String line = reader.readLine();
      if (line == null) {
        throw new IllegalArgumentException("Cannot have empty configuration file");
      }

      String[] tokens = line.trim().split("\\s+");
      if (tokens.length != 2) {
        throw new IllegalArgumentException("Invalid format for rows and cols");
      }

      int rows;
      int cols;
      try {
        rows = Integer.parseInt(tokens[0]);
        cols = Integer.parseInt(tokens[1]);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Rows and cols must be integers");
      }

      if (rows <= 0 || cols <= 0) {
        throw new IllegalArgumentException("rows and cols must be positive integers");
      }

      // Initialize the grid
      Cell[][] grid = new Cell[rows][cols];

      // Read each line and initialize grid
      for (int row = 0; row < rows; row++) {
        line = reader.readLine();
        if (line == null) {
          throw new IllegalArgumentException("Not enough rows in configuration file");
        }
        line = line.trim();

        if (line.length() != cols) {
          throw new IllegalArgumentException("Invalid number of columns at row " + row);
        }

        for (int col = 0; col < cols; col++) {
          char ch = line.charAt(col);
          switch (ch) {
            case 'C':
              grid[row][col] = new ThreeTriosCell(true);
              break;
            case 'X':
              grid[row][col] = new ThreeTriosCell(false);
              break;
            default:
              throw new IllegalArgumentException("Invalid character '" + ch + "' at row " + row
                      + ", column " + col);
          }
        }
      }
      return new ThreeTriosGrid(grid);
    }
  }
}
