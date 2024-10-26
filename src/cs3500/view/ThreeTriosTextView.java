package cs3500.view;

import java.util.List;

import cs3500.model.Card;
import cs3500.model.Cell;
import cs3500.model.Player;
import cs3500.model.ThreeTrios;

public class ThreeTriosTextView {

  private final ThreeTrios model;

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
