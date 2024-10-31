package cs3500.model;

/**
 * Represents a position on a 2d coordinate grid.
 */
public class GridPos2d implements GridPos {
  private final int x;
  private final int y;

  /**
   * Constructs a coordinate pair on a 2d grid with an x and y value.
   *
   * @param x the x value of the position.
   * @param y the y value of the position.
   */
  public GridPos2d(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public GridPos getAdjacent(Direction d) {
    switch (d) {
      case NORTH:
        return new GridPos2d(this.x, this.y - 1);
      case SOUTH:
        return new GridPos2d(this.x, this.y + 1);
      case EAST:
        return new GridPos2d(this.x + 1, this.y);
      case WEST:
        return new GridPos2d(this.x - 1, this.y);
      default:
        throw new IllegalArgumentException("Direction must be NORTH, SOUTH, EAST, or WEST!");
    }
  }

}
