package cs3500.threetrios.provider.code.guiview;


import cs3500.threetrios.players.IPlayer;


/**
 * Represents the interface for the behaviors of the frame in the Three Trios game view.
 * This interface defines the actions the frame should be able to perform, such as
 * updating the view, resetting the game, and setting
 * player titles, as well as managing interactivity in the game.
 */
public interface IThreeTriosFrame {


  /**
   * Sets the title for the player in the game frame, displaying the player's name and game
   * status (if they are currently playing or waiting). This is used to update the frame title to
   * reflect which player is currently actively playing or waiting for their turn.
   *
   * @param player the player whose title should be displayed in the game frame
   */
  void setPlayerTitle(IPlayer player);

  /**
   * Updates the view of the game frame, refreshing the display to reflect the latest state of
   * the game. This method should be called whenever the game state changes and the frame
   * needs to be redrawn.
   */
  void updateView();

  /**
   * Resets the game frame to its initial state, clearing any previous game data
   * and preparing the view for a new game session (resetting the selected card and coordinates).
   */
  void reset();

  /**
   * Sets the interactivity of the game frame, including the board panel and card panels.
   * When interactive is true, the panels should respond to user inputs. When interactive is false,
   * the panels should disable user interaction.
   *
   *     @param interactive a boolean indicating whether something should be interactive or not
   *     @param boardPanel  a boolean indicating whether the board panel should be changed to
   *                        whatever the interactive boolean is.
   *     @param cardPanels  a boolean indicating whether the card panels should be changed to
   *                        whatever the interactive boolean is.
   */
  void setInteractive(boolean interactive, boolean boardPanel, boolean cardPanels);

}
