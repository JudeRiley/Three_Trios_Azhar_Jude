package cs3500.threetrios.code.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import cs3500.threetrios.code.model.Card;
import cs3500.threetrios.code.model.Cell;
import cs3500.threetrios.code.model.Direction;
import cs3500.threetrios.code.model.Grid;
import cs3500.threetrios.code.model.GridPos;
import cs3500.threetrios.code.model.GridPos2d;
import cs3500.threetrios.code.model.Move;
import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.code.model.ThreeTriosCard;
import cs3500.threetrios.code.model.ThreeTriosGrid;
import cs3500.threetrios.code.model.ThreeTriosMove;

/**
 * A "tactic" (or partial strategy) that chooses a position that will flip the most cards
 * to the current Player given the best possible card. In the event of a tie,
 * it chooses the uppermost-leftmost position. It chooses the first card in the players hand
 * that will successfully flip the cards surrounding this chosen position, starting at index zero.
 * If no card is successful, it will check the hand for cards that succeed at the next most
 * desirable position.
 */
public class FlipMostCardsTactic implements ThreeTriosTactic {

  private Grid testGame;

  @Override
  public Optional<Move> chooseMove(ReadOnlyThreeTriosModel model, Player forWhom) {
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
          if (hand.get(cardIdx).getValueOf(d) > testGame.getLosingSurroundingValues(
                  openPositions.get(bestPosIdx))[d.ordinal()]) {
            count++;
          }
        }
        if (count == 4) {
          return Optional.of(new ThreeTriosMove(openPositions.get(bestPosIdx), cardIdx));
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
      int ret = Integer.compare(getLosingNeighborsStrings(o1).size(),
              getLosingNeighborsStrings(o2).size());
      if (ret == 0) {
        //if there is a tie, then the uppermost-leftmost wins
        ret = Integer.compare(o1.getRow() + o1.getCol(), o2.getRow() + o2.getCol());
      }
      return ret;
    }
  }

}
