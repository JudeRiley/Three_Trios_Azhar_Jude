package cs3500.gameplay;

import java.util.ArrayList;
import java.util.List;

import cs3500.model.Move;
import cs3500.model.Player;
import cs3500.model.ReadOnlyThreeTrios;
import cs3500.strategies.ThreeTriosStrategy;

/**
 * A mock of a complete Three Trios Strategy. It will return the Moves that are fed to it by
 * the tester. It is a complete and therefore infallible strategy so it always returns a Move.
 */
public class MockStrategy implements ThreeTriosStrategy {

  private final List<Object> testInputs;

  private List<Move> moves;
  private final List<Move> reference;

  /**
   * Creates a Mock Strategy that returns the moves in the given list in order on
   * each subsequent call to chooseMove. When it reaches the end of the list, it restarts.
   *
   * @param testMoves the Move this Strategy will always choose.
   */
  public MockStrategy(List<Move> testMoves, List<Object> testInputs) {
    this.testInputs = testInputs;
    this.reference = new ArrayList<>(testMoves);
    this.moves = new ArrayList<>(reference);
  }


  @Override
  public Move chooseMove(ReadOnlyThreeTrios model, Player forWhom) throws IllegalStateException {
    System.out.println("The chooseMove method of this MockStrategy has been called.");
    testInputs.add(model);
    testInputs.add(forWhom);

    if (moves.isEmpty()) {
      moves = new ArrayList<>(reference);
    }
    return moves.remove(0);
  }
}
