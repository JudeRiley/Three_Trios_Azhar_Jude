package cs3500.threetrios.code.model;

import cs3500.threetrios.code.controller.Controller;
import cs3500.threetrios.code.strategies.ThreeTriosStrategy;

/**
 * The machine player is a type of player that is given
 * a strategy. The machine player will follow the given
 * strategy accordingly.
 */
public class MachinePlayer implements PlayerType {
  private final Player playerColor;
  private final ThreeTriosStrategy strategy;
  private Controller controller;
  private ReadOnlyThreeTrios model;

  /**
   * The constructor for the machine player.
   * @param playerColor color of the player
   * @param strategy strategy used
   */
  public MachinePlayer(Player playerColor, ThreeTriosStrategy strategy) {
    this.playerColor = playerColor;
    this.strategy = strategy;
  }

  /**
   * Sets the controller that the machine uses.
   * @param controller controller of machine
   */
  public void setController(Controller controller) {
    this.controller = controller;
  }

  /**
   * Sets the model that the machine uses.
   * @param model model of the machine
   */
  public void setModel(ReadOnlyThreeTrios model) {
    this.model = model;
  }

  /**
   * Gets the color of the player.
   * @return player color
   */
  @Override
  public Player getPlayerColor() {
    return playerColor;
  }

  /**
   * The machine will perform a move.
   */
  @Override
  public void performAction() {
    Move move = strategy.chooseMove(model, playerColor);

    // Notify the controller
    controller.handleMachinePlayerMove(this, move);
  }
}

