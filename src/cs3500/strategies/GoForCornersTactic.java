package cs3500.strategies;

import java.util.List;
import java.util.Optional;

import cs3500.model.Card;
import cs3500.model.Cell;
import cs3500.model.Direction;
import cs3500.model.GridPos;
import cs3500.model.GridPos2d;
import cs3500.model.Move;
import cs3500.model.Player;
import cs3500.model.ReadOnlyThreeTrios;
import cs3500.model.ThreeTriosMove;

/**
 * A "tactic" (or partial strategy) that attempts to play in one of the four corners of the grid.
 * It starts with the upper-left corner, and if the corner is not open for play, it tries again
 * with the next corner in the clockwise direction. It chooses the card in the players hand that
 * will be hardest to flip at the first open corner. It takes into account the exposed values and
 * considers that in certain situations (bordered by an enemy card, a hole cell, or a losing ally)
 * the cell will be impossible to flip from that side. There are other situations
 * (bordered by a winning ally) where the cell will always be able to be flipped from this side
 * (assuming that neighboring cell is flipped.)
 */
public class GoForCornersTactic implements ThreeTriosTactic{

  private Cell[][] grid;
  private Player player;
  private List<Card> hand;

  @Override
  public Optional<Move> chooseMove(ReadOnlyThreeTrios model, Player forWhom) {
    this.grid = model.getCurrentGrid();
    this.player = forWhom;
    this.hand = model.getHand(this.player);

    GridPos retPos;
    int retIdx = 0;

    int lastRow = this.grid.length - 1;
    int lastCol = this.grid[0].length - 1;
    GridPos topLeft = new GridPos2d(0, 0);
    GridPos topRight = new GridPos2d(0, lastCol);
    GridPos botLeft = new GridPos2d(lastRow, 0);
    GridPos botRight = new GridPos2d(lastRow, lastCol);

    //top left
    if (topLeft.accessArray(this.grid).isOpenForPlay()) {
      retPos = topLeft;
      retIdx = this.chooseCardIdx(topLeft, Direction.EAST, Direction.SOUTH);
    } else if (topRight.accessArray(this.grid).isOpenForPlay()) {
      retPos = topRight;
      retIdx = this.chooseCardIdx(topRight, Direction.SOUTH, Direction.WEST);
    } else if (botLeft.accessArray(this.grid).isOpenForPlay()) {
      retPos = botLeft;
      retIdx = this.chooseCardIdx(botLeft, Direction.NORTH, Direction.EAST);
    } else if (botRight.accessArray(this.grid).isOpenForPlay()) {
      retPos = botRight;
      retIdx = this.chooseCardIdx(botRight, Direction.NORTH, Direction.WEST);
    } else {
      return Optional.empty();
    }


    return Optional.of(new ThreeTriosMove(retPos, retIdx));
  }

  private int chooseCardIdx(GridPos corner, Direction side1, Direction side2) {
    int bestCardIdx = 0;
    int bestCardValue = SideScore(corner, 0, side1) + SideScore(corner, 0, side2);

    int side1Score;
    int side2Score;

    for (int cardIdx = 1; cardIdx < hand.size(); cardIdx++) {

      side1Score = SideScore(corner, cardIdx, side1);
      side2Score = SideScore(corner, cardIdx, side2);

      if (side1Score + side2Score > bestCardValue) {
        bestCardIdx = cardIdx;
        bestCardValue = side1Score + side2Score;
      }
    }

    return bestCardIdx;
  }


  private int SideScore(GridPos corner, int cardIdx, Direction sideDirection) {
    Cell adjCell = corner.getAdjacent(sideDirection).accessArray(this.grid);

    //by default, this side is as hard to flip as its value designates
    int returnScore = hand.get(cardIdx).getValueOf(sideDirection);

    if (adjCell.isCardCell() && adjCell.hasCard() && adjCell.getOwnerName().equals(player.toString()) && returnScore < adjCell.getCardValueOf(sideDirection.opposite())) {
      //if the adjacent cell is an ally that wins from this side, it is always possible to flip the cell from this side
      returnScore = 0;
    } else {
      //If the adjacent Cell is a Hole, it is impossible to flip the cell from this side.
      //If the adjacent Cell is owned by the other player, it is impossible to flip the cell from this side.
      //If the adjacent cell is an ally that loses from this side, it is impossible to flip the cell from this side.
      returnScore = 10;
    }

    return returnScore;
  }



}


