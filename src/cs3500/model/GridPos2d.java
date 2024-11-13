package cs3500.model;

/**
 * Represents a position on a 2d coordinate grid of rows and columns.
 */
public class GridPos2d implements GridPos {
  private final int row;
  private final int col;

  /**
   * Constructs a pair of indexes for a 2d grid of rows and columns.
   *
   * @param row the x value of the position.
   * @param col the y value of the position.
   */
  public GridPos2d(int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getCol() {
    return col;
  }

  @Override
  public GridPos getAdjacent(Direction d) {
    switch (d) {
      case NORTH:
        return new GridPos2d(this.row - 1, this.col);
      case SOUTH:
        return new GridPos2d(this.row + 1, this.col);
      case EAST:
        return new GridPos2d(this.row, this.col + 1);
      case WEST:
        return new GridPos2d(this.row, this.col - 1);
      default:
        throw new IllegalArgumentException("Direction must be NORTH, SOUTH, EAST, or WEST!");
    }
  }

  @Override
  public boolean isInBoundsFor(int numRows, int rowLength) {
    return !this.containsNegatives() && this.row < numRows && this.col < rowLength;
  }

  private boolean containsNegatives(){
    return this.row < 0 || this.col < 0;
  }

  @Override
  public String toString() {
    return "(" + row + ", " + col + ")";
  }

  @Override
  public <T> T accessArray(T[][] arr2d) {
    if (!this.isInBoundsFor(arr2d.length, arr2d[0].length)) {
      throw new IllegalArgumentException("The given array does not contain a reference for this position.");
    }
    return arr2d[this.row][this.col];
  }
}
