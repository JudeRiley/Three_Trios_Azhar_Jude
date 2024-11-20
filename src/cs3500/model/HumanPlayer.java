package cs3500.model;

import cs3500.view.ThreeTriosView;

/**
 * A player of human type.
 */
public class HumanPlayer implements PlayerType {
  private final Player playerColor;
  private final ThreeTriosView view;

  /**
   * Constructor for the human player.
   * @param playerColor the color of the player
   * @param view the view that the player uses
   */
  public HumanPlayer(Player playerColor, ThreeTriosView view) {
    this.playerColor = playerColor;
    this.view = view;
  }

  @Override
  public Player getPlayerColor() {
    return playerColor;
  }

  @Override
  public void performAction() {
    // UI performs player actions
  }
}

