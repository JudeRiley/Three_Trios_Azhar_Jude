package cs3500.model;

import cs3500.strategies.ThreeTriosStrategy;

public class MachinePlayer implements PlayerType {
  private final Player playerColor;
  private final ThreeTriosStrategy strategy;
  private ThreeTriosController controller;
  private ReadOnlyThreeTrios model;

  public MachinePlayer(Player playerColor, ThreeTriosStrategy strategy) {
    this.playerColor = playerColor;
    this.strategy = strategy;
  }

  public void setController(ThreeTriosController controller) {
    this.controller = controller;
  }

  public void setModel(ReadOnlyThreeTrios model) {
    this.model = model;
  }

  @Override
  public Player getPlayerColor() {
    return playerColor;
  }

  @Override
  public void performAction() {
    Move move = strategy.chooseMove(model, playerColor);

    // Notify the controller
    controller.handleMachinePlayerMove(this, move);
  }
}

