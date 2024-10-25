package model;

import java.util.ArrayList;
import java.util.List;

public class ThreeTriosGrid {

  private final Cell[][] grid;

  public ThreeTriosGrid(int width, int height) {
    this.grid = new Cell[width][height];
  }

  public void playCard(GridPos pos, Card card, Player owner) {
    grid(pos).setCard(card, owner);
  }

  public List<GridPos> returnLosingNeighbors(GridPos pos) {
    List<GridPos> losingNeighbors = new ArrayList<>();
    Cell startCell = grid(pos);
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

  private Cell grid(GridPos pos) {
    try {
      return grid[pos.getX()][pos.getY()];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Given position is out of bounds!");
    }
  }


}
