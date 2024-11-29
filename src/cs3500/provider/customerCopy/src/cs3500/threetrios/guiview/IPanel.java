package cs3500.provider.customerCopy.src.cs3500.threetrios.guiview;


/**
 * Represents the interface for the behaviors of both the card panel and the board panel
 * in the Three Trios game. This interface defines the actions that both panels should be capable
 * of performing, including setting a command callback to interact with the game controller,
 * updating the view to reflect game state changes, resetting the panel, and controlling
 * interactivity.
 */
public interface IPanel {

  /**
   * Updates the view of the card panel, refreshing the display to reflect the latest state of
   * the game. This method should be called whenever the game state changes and the card panels
   * needs to be redrawn.
   */
  void updateView();

  /**
   * Resets the card panel to its initial state, clearing any previous game data
   * and preparing the view for a new game session (resetting the selected card).
   */
  void reset();

  /**
   * Sets the interactivity of the card panel.
   * When interactive is true, the card panel should respond to user inputs, such as clicks.
   * When interactive is false, the card panel should disable user interaction, such as clicks fron
   * the user.
   *
   * @param interactive a boolean indicating whether the board should be interactive or not
   */
  void setInteractive(boolean interactive);
}
