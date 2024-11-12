package cs3500.strategies;

import cs3500.model.Move;
import cs3500.model.Player;
import cs3500.model.ReadOnlyThreeTrios;

/**
 *
 */
public interface ThreeTriosStrategy {

  /**
   *
   * @param model
   * @param forWhom
   * @return
   */
  Move chooseMove(ReadOnlyThreeTrios model, Player forWhom) throws IllegalStateException;
}
