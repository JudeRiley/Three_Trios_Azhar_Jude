package cs3500.model;

/**
 * Represents a position on a grid of rows and columns.
 */
public interface GridPos {

  /**
   * Returns the vertical value of this grid position.
   *
   * @return an integer representing the row index.
   */
  int getRow();

  /**
   * Returns the horizontal value of this grid position.
   *
   * @return an integer representing the index within a row.
   */
  int getCol();

  /**
   * Returns the grid position that is one unit in the given direction
   * away from this grid position.
   *
   * @param d The direction of the desired adjacent position.
   * @return a new GridPos corresponding to the adjacent position.
   */
  GridPos getAdjacent(Direction d);

  boolean containsNegatives();

  boolean isInBoundsFor(int numRows, int rowLength);

  <T> T accessArray(T[][] arr2d);

}
