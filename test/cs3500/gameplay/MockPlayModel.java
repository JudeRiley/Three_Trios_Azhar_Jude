package cs3500.gameplay;

import java.util.List;

import cs3500.model.Card;
import cs3500.model.Cell;
import cs3500.model.GridPos;
import cs3500.model.ModelFeatures;
import cs3500.model.ModelListener;
import cs3500.model.Player;
import cs3500.model.ThreeTrios;

/**
 * A mock of a model that exists in order to be passed into objects that require a model for tests.
 * Used for testing the controller, and therefore broadcasts its functionality appropriately.
 */
public class MockPlayModel implements ThreeTrios, ModelFeatures {

  private final List<Object> log;

  /**
   * Constructs a mock of a playable three trios model that adds proof of functionality to a log.
   *
   * @param log a list of objects that will be added to in order to prove functionality.
   */
  public MockPlayModel(List<Object> log) {
    this.log = log;
  }


  @Override
  public void addModelListener(ModelListener listener) {
    log.add(listener);
  }

  @Override
  public void removeModelListener(ModelListener listener) {
    log.add("removed listener" + listener);
  }

  @Override
  public void placeCard(GridPos pos, int cardIdx) {
    log.add(pos.toString());
    log.add(cardIdx);
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
