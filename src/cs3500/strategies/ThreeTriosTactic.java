package cs3500.strategies;

import java.util.Optional;

import cs3500.model.Move;
import cs3500.model.Player;
import cs3500.model.ReadOnlyThreeTrios;

public interface ThreeTriosTactic {

  /**
   *
   * @param model
   * @param forWhom
   * @return
   */
  Optional<Move> chooseMove(ReadOnlyThreeTrios model, Player forWhom);
}
