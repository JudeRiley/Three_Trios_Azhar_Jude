package cs3500.threetrios.code.strategies;

import cs3500.threetrios.code.model.Move;
import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.model.ReadOnlyThreeTrios;

/**
 * A complete strategy. One that should always be able to choose a move in a game of ThreeTrios,
 * assuming the model is in a valid state.
 */
public interface ThreeTriosStrategy {

  /**
   * Creates a Move object with the best position and best card in the given players hand to feed
   * to the given model. What is considered the best is defined by the details of the specific
   * Strategy's implementation. This strategy is complete and therefore a move should always
   * be able to be chosen.
   *
   * @param model the ThreeTriosModel for which a Move is chosen.
   * @param forWhom the Player to choose a Move for.
   * @return A "best" Move for this Player in this ThreeTriosModel.
   * @throws IllegalStateException the model is in an invalid state where a move cannot be chosen.
   */
  Move chooseMove(ReadOnlyThreeTrios model, Player forWhom) throws IllegalStateException;
}
