package cs3500.threetrios.code.strategies;

import java.util.Optional;

import cs3500.threetrios.code.model.Move;
import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.model.ReadOnlyThreeTriosModel;

/**
 * A complete strategy that attempts to choose a move with a given strategy, and if no move
 * is chosen, it chooses a move using the first open space strategy.
 */
public class TacticOrFirstOpenSpaceStrategy implements ThreeTriosStrategy {
  private final ThreeTriosTactic tacticToTry;
  private final FirstOpenSpaceStrategy fallback;

  /**
   * Creates a new strategy that first attempts the given tactic, and if that tactic fails,
   * then it will play to the first open space. This essentially turns the given tactic into
   * a simple complete strategy.
   *
   * @param tacticToTry the tactic to attempt before falling back on first open space.
   */
  public TacticOrFirstOpenSpaceStrategy(ThreeTriosTactic tacticToTry) {
    if (tacticToTry == null) {
      throw new IllegalArgumentException("Tactic cannot be null!");
    }
    this.tacticToTry = tacticToTry;
    this.fallback = new FirstOpenSpaceStrategy();
  }

  @Override
  public Move chooseMove(ReadOnlyThreeTriosModel model, Player fr) throws IllegalStateException {
    Optional<Move> tacticMove = tacticToTry.chooseMove(model, fr);
    if (tacticMove.isPresent()) {
      return tacticMove.get();
    } else {
      return fallback.chooseMove(model, fr);
    }
  }

}
