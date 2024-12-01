package cs3500.gameplay;

import java.util.List;

import cs3500.threetrios.code.controller.Controller;
import cs3500.threetrios.code.model.Move;
import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.model.PlayerType;

/**
 * A mock of a Controller for a ThreeTrios game used for testing Human and Machine Players.
 */
public class MockController implements Controller {

  private final List<Object> testInputs;
  private final String name;

  /**
   * Constructs a mock of a Controller that only implements the methods necessary to test Human
   * and Machine Players.
   *
   * @param testInputs reference to a list that will contain objects passed into the controller.
   * @param name       a name for identification of this mock controller.
   */
  public MockController(List<Object> testInputs, String name) {
    this.name = name;
    this.testInputs = testInputs;
  }

  @Override
  public void handleMachinePlayerMove(PlayerType machinePlayer, Move move) {
    testInputs.add(name);
    testInputs.add(machinePlayer);
    testInputs.add(move);
  }

  @Override
  public void onTurnChanged(Player currentPlayer) {
    // not called by players
  }

  @Override
  public void onGameOver(Player winner, int winningScore) {
    // not called by players
  }

  @Override
  public void onCardSelected(int cardIndex) {
    // not called by players
  }

  @Override
  public void onPosSelected(int row, int col) {
    // not called by players
  }
}
