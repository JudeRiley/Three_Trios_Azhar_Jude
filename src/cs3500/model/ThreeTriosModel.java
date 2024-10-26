package cs3500.model;

import java.util.ArrayList;
import java.util.List;

public class ThreeTriosModel implements ThreeTrios {

  private final Grid grid;
  private Player turn;
  private final List<Card> redHand;
  private final List<Card> blueHand;

  //Temporary Constructor
  public ThreeTriosModel(Grid grid) {
    this.grid = grid;
    this.redHand = new ArrayList<>();
    this.blueHand = new ArrayList<>();
    this.turn = Player.RED;
  }

  //TODO : Constructor that correctly initializes the game OR a startGame() method
  /*
  A startGame() method would require changing fields to be not final UNLESS we create another
  class that only represents a started game, which I think could be an option to explore.
  */

  public void placeCard(GridPos pos, int cardIdx) {
    Card cardToPlay;
    try {
      switch (turn) {
        case RED:
          cardToPlay = redHand.remove(cardIdx);
          break;
        case BLUE:
          cardToPlay = blueHand.remove(cardIdx);
          break;
        default:
          throw new IllegalStateException(turn + "is not a value of Player that can be handled!");
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid card index!");
    }
    this.grid.playCard(pos, cardToPlay, turn);
    this.turn = this.battleStep(pos);
  }

  public Player battleStep(GridPos pos) {
    List<GridPos> neighborsList;

    //gets a list of neighbors that need to be flipped
    neighborsList = new ArrayList<>(this.grid.getLosingNeighbors(pos));

    //flips all of those neighbors
    for (GridPos neighbor : neighborsList) {
      grid.flipCardCellTo(neighbor, turn);
    }

    //recursively calls battleStep on each neighbor
    for (GridPos neighbor : neighborsList) {
      this.battleStep(neighbor);
    }

    //returns the next player. Only the return value of the first call to battleStep is used;
    return turn.nextPlayer();
  }

  public Player getWinner() {
    if (!(this.grid.isSaturated() || (this.redHand.isEmpty() && this.blueHand.isEmpty()))) {
      throw new IllegalStateException("Game is still ongoing!");
    }

    if (this.grid.getScoreOf(Player.RED) > this.grid.getScoreOf(Player.BLUE)) {
      return Player.RED;
    } else {
      return Player.BLUE;
    }
  }






}
