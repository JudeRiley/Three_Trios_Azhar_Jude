package cs3500.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a Grid in a ThreeTrios game. It acts as a grid of Cells, that may each
 * contain a card. It relies on GridPos to reference its contents.
 */
public class ThreeTriosGrid implements Grid {

  private final Cell[][] grid;

  /**
   * Constructs a grid with a given witch and height that is all
   * playable card-cells.
   *
   * @param width the number of columns in the grid, the x-axis.
   * @param height the number of rows in teh grid, the y-axis.
   */
  public ThreeTriosGrid(int width, int height) {
    if ((width * height) % 2 == 0) {
      throw new IllegalArgumentException("All cells will be card cells, "
              + "so total AREA must be odd.");
    }
    this.grid = new Cell[width][height];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        this.grid[x][y] = new ThreeTriosCell(true);
      }
    }
  }

  /**
   * Constructs a grid given a specific pre-determined layout of
   * playable card-cells and hole cells.
   * @param grid a pre-determined grid layout of hole-cells and card-cells.
   */
  public ThreeTriosGrid(Cell[][] grid) {
    if (grid == null) {
      throw new IllegalArgumentException("Grid cannot be null!");
    }

    int cardCellCount = 0;
    for (Cell[] cells : grid) {
      for (Cell cell : cells) {
        if (cell.isCardCell()) {
          cardCellCount++;
        }
      }
    }
    if (cardCellCount % 2 == 0) {
      throw new IllegalArgumentException("Number of card cells must be odd.");
    }

    this.grid = grid;
  }

  @Override
  public int getNumCardCells() {
    int cardCellCount = 0;
    for (Cell[] cells : this.grid) {
      for (Cell cell : cells) {
        if (cell.isCardCell()) {
          cardCellCount++;
        }
      }
    }
    if (cardCellCount % 2 == 0) {
      throw new IllegalArgumentException("Number of card cells must be odd.");
    }
    return cardCellCount;
  }

  @Override
  public void playCard(GridPos pos, Card card, Player owner) {
    gridCellRef(pos).setCard(card, owner);
  }

  @Override
  public List<GridPos> getLosingNeighbors(GridPos pos) {
    List<GridPos> losingNeighbors = new ArrayList<>();
    Cell startCell = this.gridCellRef(pos);
    GridPos targetPos;
    Cell targetCell;

    for (Direction d : Direction.values()) {
      try {
        targetPos = pos.getAdjacent(d);
      } catch (IllegalArgumentException e) {
        // if out of bounds skip
        continue;
      }

      try {
        targetCell = this.gridCellRef(targetPos);
      } catch (IllegalArgumentException e) {
        // if out of bounds skip
        continue;
      }

      try {
        if (startCell.directionalCompareTo(d, targetCell) == 1) {
          losingNeighbors.add(targetPos);
        }
      } catch (IllegalArgumentException e) {
        // we continue in this case
      }
    }

    return losingNeighbors;
  }

  @Override
  public void flipCardCellTo(GridPos pos, Player owner) {
    this.gridCellRef(pos).setOwner(owner);
  }

  @Override
  public boolean isSaturated() {
    for (Cell[] cells : this.grid) {
      for (Cell cell : cells) {
        if (cell.isOpenForPlay()) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int getScoreOf(Player player) {
    int score = 0;
    for (Cell[] cells : this.grid) {
      for (Cell cell : cells) {
        if (cell.hasCard() && !cell.getOwnerName().equals(player.name())) {
          score++;
        }
      }
    }
    return score;
  }

  @Override
  public Cell[][] getCurrentGrid() {
    int rowLength = this.grid.length;
    int colLength = this.grid[0].length;
    Cell[][] ret = new Cell[rowLength][colLength];
    for (int row = 0; row < rowLength; row++) {
      for (int col = 0; col < colLength; col++) {
        ret[row][col] = new ThreeTriosCell(this.grid[row][col]);
      }
    }
    return ret;
  }

  @Override
  public Cell getCell(GridPos pos) {
    return new ThreeTriosCell(this.grid[pos.getX()][pos.getY()]);
  }

  private Cell gridCellRef(GridPos pos) {
    try {
      return this.grid[pos.getX()][pos.getY()];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Given position is out of bounds!");
    }
  }
}
