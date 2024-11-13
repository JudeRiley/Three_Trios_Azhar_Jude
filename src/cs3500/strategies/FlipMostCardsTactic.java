package cs3500.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import cs3500.model.Card;
import cs3500.model.Cell;
import cs3500.model.Direction;
import cs3500.model.Grid;
import cs3500.model.GridPos;
import cs3500.model.GridPos2d;
import cs3500.model.Move;
import cs3500.model.Player;
import cs3500.model.ReadOnlyThreeTrios;
import cs3500.model.ThreeTriosCard;
import cs3500.model.ThreeTriosGrid;
import cs3500.model.ThreeTriosMove;

public class FlipMostCardsTactic implements ThreeTriosTactic {

  private Grid testGame;

  @Override
  public Optional<Move> chooseMove(ReadOnlyThreeTrios model, Player forWhom) {
    Cell[][] grid = model.getCurrentGrid();
    this.testGame = new ThreeTriosGrid(grid);
    List<Card> hand = model.getHand(forWhom);

    Card bestCard = new ThreeTriosCard("BEST", 10, 10, 10, 10);
    List<GridPos> openPositions = new ArrayList<>();

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
        GridPos currCell = new GridPos2d(row, col);

        if (testGame.getCell(currCell).isOpenForPlay()) {
          testGame.playCard(currCell, bestCard, forWhom);
          openPositions.add(currCell);
        }
      }
    }

    openPositions.sort(new SortByFlipCount());

    for (int bestPosIdx = 0; bestPosIdx < openPositions.size(); bestPosIdx++) {
      for (int cardIdx = 0; cardIdx < hand.size(); cardIdx++) {
        int count = 0;
        for (Direction d : Direction.values()) {
          if (hand.get(cardIdx).getValueOf(d) > testGame.getLosingSurroundingValues(openPositions.get(bestPosIdx))[d.ordinal()]) {
            count++;
          }
        }
        if (count == 4) {
          return Optional.of(new ThreeTriosMove(openPositions.get(bestPosIdx),cardIdx));
        }
      }
    }

    return Optional.empty();
  }

  private HashSet<String> getLosingNeighborsStrings(GridPos pos) {
    HashSet<String> retList = new HashSet<>();
    List<GridPos> directNeighbors = testGame.getLosingNeighbors(pos);
    for (GridPos neighbor : directNeighbors) {
      retList.add(neighbor.toString());
      retList.addAll(getLosingNeighborsStrings(neighbor));
    }
    return retList;
  }


  class SortByFlipCount implements Comparator<GridPos> {
    @Override
    public int compare(GridPos o1, GridPos o2) {
      int ret = Integer.compare(getLosingNeighborsStrings(o1).size(), getLosingNeighborsStrings(o2).size());
      if (ret == 0) {
        //if there is a tie, then the uppermost-leftmost wins
        ret = Integer.compare(o1.getRow() + o1.getCol(), o2.getRow() + o2.getCol());
      }
      return ret;
    }
  }

}
