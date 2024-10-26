package cs3500.model;

import java.util.ArrayList;
import java.util.List;

public class ThreeTriosGrid implements Grid{

  private final Cell[][] grid;

  public ThreeTriosGrid(int width, int height) {
    this.grid = new Cell[width][height];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        this.grid[x][y] = new ThreeTriosCell(true);
      }
    }
  }

  //TODO : Constructor where you pass in a layout

  public void playCard(GridPos pos, Card card, Player owner) {
    grid(pos).setCard(card, owner);
  }

  public List<GridPos> getLosingNeighbors(GridPos pos) {
    List<GridPos> losingNeighbors = new ArrayList<>();
    Cell startCell = this.grid(pos);
    GridPos targetPos;
    Cell targetCell;

    for (Direction d : Direction.values()) {
      try {
        targetPos = pos.getAdjacent(d);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Direction is not functioning as expected!");
      }

      try {
        targetCell = grid(targetPos);
      } catch (IllegalArgumentException e) {
        break;
      }

      try {
        if (startCell.directionalCompareTo(d, targetCell) == 1) {
          losingNeighbors.add(targetPos);
        }
      } catch (IllegalArgumentException e) {
        break;
      }
    }

    return losingNeighbors;
  }

  public void flipCardCellTo(GridPos pos, Player owner) {
    grid(pos).setOwner(owner);
  }

  public boolean isSaturated() {
    for (Cell[] cells : this.grid) {
      for (Cell cell : cells) {
        if (!cell.isFilled()) {
          return false;
        }
      }
    }
    return true;
  }

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

  private Cell grid(GridPos pos) {
    try {
      return this.grid[pos.getX()][pos.getY()];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Given position is out of bounds!");
    }
  }
}
