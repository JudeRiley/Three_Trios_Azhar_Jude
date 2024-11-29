package cs3500.threetrios.view;

import cs3500.threetrios.model.Player;

/**
 * The interface for the ThreeTriosView.
 */
public interface ThreeTriosView extends ViewFeatures {
  /**
   * Makes the view visible.
   */
  void makeVisible();

  /**
   * Refreshes the view to reflect any changes in the model.
   */
  void refresh();

  /**
   * Highlights the selected card in the view.
   *
   * @param cardIndex index of the card selected
   * @param player    player color
   */
  void highlightSelectedCard(int cardIndex, Player player);

  void showError(String message);

  void updateStatus(String status);

  void setInputEnabled(boolean enabled);

  /**
   * Displays a dialog box to inform the player that the game is over, showing the winner and the
   * score.
   *
   * @param message The message to display to the user.
   */
  void showGameOver(String message);

  /**
   * Used the bring the current players window to the front.
   */
  void bringToFront();
}

