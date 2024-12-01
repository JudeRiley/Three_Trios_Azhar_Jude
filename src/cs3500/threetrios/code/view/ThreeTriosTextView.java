package cs3500.threetrios.code.view;

import java.util.List;

import cs3500.threetrios.code.model.Card;
import cs3500.threetrios.code.model.Cell;
import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.model.ThreeTrios;

/**
 * Represents a view of a three trios game as a block of text
 * that displays the turn, the grid, and the current hand.
 */
public class ThreeTriosTextView {

  private final ThreeTrios model;

  /**
   * constructs a text view with the given three trios model.
   *
   * @param model a three trios game.
   */
  public ThreeTriosTextView(ThreeTrios model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!");
    }
    this.model = model;
  }

  @Override
  public String toString() {
    Player turn = model.getTurn();
    List<Card> hand = model.getHand(turn);
    Cell[][] grid = model.getCurrentGrid();

    StringBuilder ret = new StringBuilder("Player: ");
    ret.append(turn.toString()).append("\n");

    for (Cell[] cells : grid) {
      for (Cell cell : cells) {
        if (!cell.isCardCell()) {
          ret.append(" ");
        } else if (!cell.hasCard()) {
          ret.append("_");
        } else {
          ret.append(cell.getOwnerName().charAt(0));
        }
      }
      ret.append("\n");
    }

    ret.append("Hand: ");
    for (Card card : hand) {
      ret.append("\n").append(card.toString());
    }

    return ret.toString();
  }
}
