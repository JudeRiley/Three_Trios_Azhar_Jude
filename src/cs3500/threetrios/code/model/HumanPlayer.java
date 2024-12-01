package cs3500.threetrios.code.model;

/**
 * A player of human type.
 */
public class HumanPlayer implements PlayerType {
  private final Player playerColor;

  /**
   * Constructor for the human player.
   * @param playerColor the color of the player
   */
  public HumanPlayer(Player playerColor) {
    this.playerColor = playerColor;
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

