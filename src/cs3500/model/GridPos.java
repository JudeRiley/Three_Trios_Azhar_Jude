package cs3500.model;

/**
 * Represents a position on a grid.
 */
public interface GridPos {

  /**
   * Returns the x-value of this grid position.
   *
   * @return an integer equalling the x-value.
   */
  int getX();

  /**
   * Returns the y-value of this grid position.
   *
   * @return an integer equalling the y-value.
   */
  int getY();

  /**
   * Returns the grid position that is one unit in the given direction
   * away from this grid position.
   *
   * @param d The direction of the desired adjacent position.
   * @return a new GridPos corresponding to the adjacent position.
   */
  GridPos getAdjacent(Direction d);

  <T> T accessArray(T[][] arr2d);

}
