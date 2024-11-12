package cs3500.strategies;

import java.util.Optional;

import cs3500.model.Move;
import cs3500.model.Player;
import cs3500.model.ReadOnlyThreeTrios;

public class TacticOrFirstOpenSpaceStrategy implements ThreeTriosStrategy {
  private final ThreeTriosTactic tacticToTry;

  public TacticOrFirstOpenSpaceStrategy(ThreeTriosTactic tacticToTry) {
    if (tacticToTry == null) {
      throw new IllegalArgumentException("Tactic cannot be null!");
    }
    this.tacticToTry = tacticToTry;
  }

  @Override
  public Move chooseMove(ReadOnlyThreeTrios model, Player forWhom) throws IllegalStateException {
    Optional<Move> tacticMove = tacticToTry.chooseMove(model, forWhom);
    if (tacticMove.isPresent()) {
      return tacticMove.get();
    }
    throw new IllegalStateException("There are no possible moves chosen by this strategy!");
  }

}
