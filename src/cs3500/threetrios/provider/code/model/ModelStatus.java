package cs3500.threetrios.provider.code.model;

import cs3500.threetrios.controller.Controller;

/**
 * Represents the interface for managing and notifying listeners about changes in the model's
 * status.This interface allows the model to communicate with the controller by notifying it about
 * significant events during the game, such as the game being over, a player's turn changing,
 * or the game starting.
 */
public interface ModelStatus {

  /**
   * Adds a listener (a controller) that will receive notifications about changes
   * in the game's status. This method enables the controller to listen for events such as
   * the game being over or the turn changing.
   *
   * @param controller the controller that will listen for model updates
   */
  void addControllerListener(Controller controller);

  /**
   * Notifies all registered controller listeners that the game is over.
   * This method should be called when the game ends to trigger appropriate actions
   * in the controller, such as displaying the final result and ending the game.
   */
  void notifyControllerListenersGameOver();

  /**
   * Notifies all registered controller listeners that the player's turn has changed.
   * This method should be called whenever a player switches turns, allowing the controller
   * to update the view and take the necessary actions based on the turn change.
   */
  void notifyControllerListenersTurnChange();

  /**
   * Notifies all registered controller listeners that the game has started.
   * This method should be called when the game is initialized, allowing the controller
   * to prepare the view and game logic accordingly.
   */
  void notifyGameStarted();
}
