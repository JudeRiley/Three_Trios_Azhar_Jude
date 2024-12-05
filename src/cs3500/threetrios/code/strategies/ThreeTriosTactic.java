package cs3500.threetrios.code.strategies;

import java.util.Optional;

import cs3500.threetrios.code.model.Move;
import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.model.ReadOnlyThreeTriosModel;

/**
 * A tactic is a part of a larger strategy. It is incomplete. It attempts to choose a move
 * according to its specific implementation, allowing for the possibility that no appropriate
 * move could be chosen.
 */
public interface ThreeTriosTactic {

  /**
   * Attempts to create a Move object with the best position and best card
   * in the given players hand to feed to the given model. What is considered the best is defined
   * by the details of the specific Tactic's implementation. If there is no valid best move
   * according to the implementation, it returns an empty Optional.
   *
   * @param model the ThreeTriosModel for which a Move is chosen.
   * @param forWhom the Player to choose a Move for.
   * @return A "best" Move for this Player in this ThreeTriosModel or an empty Optional.
   */
  Optional<Move> chooseMove(ReadOnlyThreeTriosModel model, Player forWhom);
}
