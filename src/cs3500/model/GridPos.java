package cs3500.model;

public class GridPos {
  private final int x;
  private final int y;

  public GridPos(int x, int y) {
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
        return new GridPos(this.x, this.y - 1);
      case SOUTH:
        return new GridPos(this.x, this.y + 1);
      case EAST:
        return new GridPos(this.x + 1, this.y);
      case WEST:
        return new GridPos(this.x - 1, this.y);
      default:
        throw new IllegalArgumentException("Direction must be NORTH, SOUTH, EAST, or WEST!");
    }
  }

}
