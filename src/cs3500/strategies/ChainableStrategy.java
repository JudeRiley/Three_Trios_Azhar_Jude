package cs3500.strategies;

import java.util.Optional;

import cs3500.model.Move;
import cs3500.model.Player;
import cs3500.model.ReadOnlyThreeTrios;

/**
 * A complete strategy that first attempts to choose a move with the given tactic. If that fails
 * then it defaults to the fallback strategy, which can be another chained strategy, and is
 * always a complete strategy.
 */
public class ChainableStrategy implements ThreeTriosStrategy {
  private final ThreeTriosStrategy fallbackStrategy;
  private final ThreeTriosTactic tacticToTry;

  /**
   * Constructs a new Chainable Strategy. The given tactic is added to the top of the
   * existing fallback strategy. This tactic will be attempted first. The fallback strategy
   * can be another chained strategy so that multiple different tactics can be linked together
   * in an order.
   *
   * @param fallbackStrategy a complete strategy to fall back on if the added tactic fails.
   * @param addTactic        the tactic to be added to the chain of strategies.
   */
  public ChainableStrategy(ThreeTriosStrategy fallbackStrategy, ThreeTriosTactic addTactic) {
    this.fallbackStrategy = fallbackStrategy;
    this.tacticToTry = addTactic;
  }

  @Override
  public Move chooseMove(ReadOnlyThreeTrios model, Player forWhom) throws IllegalStateException {
    Optional<Move> tacticMove = this.tacticToTry.chooseMove(model, forWhom);
    if (tacticMove.isPresent()) {
      return tacticMove.get();
    } else {
      return this.fallbackStrategy.chooseMove(model, forWhom);
    }
  }
}