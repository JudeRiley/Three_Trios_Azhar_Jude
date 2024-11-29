package cs3500.provider.customerCopy.src.cs3500.threetrios.controller;

/**
 * Represents a listener interface that listens for player actions and interactions
 * during the game. The methods in this interface are invoked in response to player
 * moves or changes in the player's interaction state.
 */
public interface PlayerListener {

  /**
   * This method is called when a player chooses a move.
   * Implementations should define the behavior that occurs when a player selects a card
   * and the associated row and column for the move.
   *
   * @param cardIdx the index of the card the player has chosen
   * @param row the row index where the player wants to place the card
   * @param col the column index where the player wants to place the card
   */
  void playerChoseAMove(int cardIdx, int row, int col);

  /**
   * This method is called to update the interactive state of the game, including the
   * board and card panels of the frame. Implementations should define the behavior that occurs
   * when the interaction state changes, such as enabling or disabling certain JPanels to be able
   * to be clicked on.
   *
   * @param interactive a boolean indicating whether the game is interactive or not
   * @param boardPanel a boolean indicating whether the board panel should be changed to the passed
   *                   interactive
   * @param cardPanels a boolean indicating whether the card panels should be changed to the passed
   *                   interactive
   */
  void changeInteractive(boolean interactive, boolean boardPanel, boolean cardPanels);
}
