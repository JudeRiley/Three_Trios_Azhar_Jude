package cs3500.threetrios.model.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cs3500.threetrios.model.ThreeTriosCell;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.Cell;

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
      // Read and parse the dimensions from the first line
      int[] dimensions = readDimensions(reader);
      int rows = dimensions[0];
      int cols = dimensions[1];

      // Validate the dimensions
      validateDimensions(rows, cols);

      // Initialize the grid
      Cell[][] grid = new Cell[rows][cols];

      // Read the grid lines and populate the grid
      readGridLines(reader, grid);

      return new ThreeTriosGrid(grid);
    }
  }

  /**
   * Reads the dimensions (rows and columns) from the first line of the file.
   *
   * @param reader the BufferedReader to read from
   * @return an array containing the number of rows and columns
   * @throws IOException if an I/O error occurs
   */
  private int[] readDimensions(BufferedReader reader) throws IOException {
    String line = reader.readLine();
    if (line == null) {
      throw new IllegalArgumentException("Cannot have an empty configuration file");
    }

    String[] tokens = line.trim().split("\\s+");
    if (tokens.length != 2) {
      throw new IllegalArgumentException("Invalid format for rows and columns");
    }

    try {
      int rows = Integer.parseInt(tokens[0]);
      int cols = Integer.parseInt(tokens[1]);
      return new int[]{rows, cols};
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Rows and columns must be integers");
    }
  }

  /**
   * Validates that the number of rows and columns are positive integers.
   *
   * @param rows the number of rows
   * @param cols the number of columns
   */
  private void validateDimensions(int rows, int cols) {
    if (rows <= 0 || cols <= 0) {
      throw new IllegalArgumentException("Rows and columns must be positive integers");
    }
  }

  /**
   * Reads the grid lines from the file and populates the grid.
   *
   * @param reader the BufferedReader to read from
   * @param grid the grid to populate
   * @throws IOException if an I/O error occurs
   */
  private void readGridLines(BufferedReader reader, Cell[][] grid) throws IOException {
    int rows = grid.length;
    int cols = grid[0].length;

    for (int row = 0; row < rows; row++) {
      String line = reader.readLine();
      if (line == null) {
        throw new IllegalArgumentException("Not enough rows in configuration file");
      }

      line = line.trim();
      if (line.length() != cols) {
        throw new IllegalArgumentException("Invalid number of columns at row " + row);
      }

      parseLine(line, row, grid);
    }
  }

  /**
   * Parses a single line of the grid configuration and populates the corresponding row in the grid.
   *
   * @param line the line to parse
   * @param rowIndex the index of the row in the grid
   * @param grid the grid to populate
   */
  private void parseLine(String line, int rowIndex, Cell[][] grid) {
    int cols = grid[0].length;

    for (int col = 0; col < cols; col++) {
      char ch = line.charAt(col);
      grid[rowIndex][col] = parseCell(ch, rowIndex, col);
    }
  }

  /**
   * Parses a single character and creates the corresponding Cell object.
   *
   * @param ch  the character to parse
   * @param row the row index of the cell
   * @param col the column index of the cell
   * @return the created Cell object
   */
  private Cell parseCell(char ch, int row, int col) {
    switch (ch) {
      case 'C':
        return new ThreeTriosCell(true); // Cell is available for play
      case 'X':
        return new ThreeTriosCell(false); // Cell is a hole or unavailable
      default:
        throw new IllegalArgumentException("Invalid character '" + ch + "' at row " + row
                + ", column " + col);
    }
  }

}
