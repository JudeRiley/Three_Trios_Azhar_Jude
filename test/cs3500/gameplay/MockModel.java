package cs3500.gameplay;

import java.util.List;

import cs3500.model.Card;
import cs3500.model.Cell;
import cs3500.model.Player;
import cs3500.model.ReadOnlyThreeTrios;

/**
 * A mock of a model that exists in order to be passed into objects that require a model for tests.
 * Contains default functionality because it is read only.
 */
public class MockModel implements ReadOnlyThreeTrios {

  /**
   * Constructs a new mock of a ReadOnlyThreeTrios model and prints to the console to confirm
   * that it has done so.
   */
  public MockModel() {
    System.out.println("Mock Model Created.");
  }

  @Override
  public Cell[][] getCurrentGrid() {
    return new Cell[0][];
  }

  @Override
  public Player getTurn() {
    return null;
  }

  @Override
  public List<Card> getHand(Player player) {
    return List.of();
  }

  @Override
  public Player getWinner() {
    return null;
  }
}
