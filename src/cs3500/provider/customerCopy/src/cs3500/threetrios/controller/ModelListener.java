package cs3500.provider.customerCopy.src.cs3500.threetrios.controller;

/**
 * Represents the class that allows listening abilities from the model itself.
 * This interface provides methods for listening to changes in the game state such as when
 * the game starts, a player switches, or when the game is over.
 */
public interface ModelListener {

  /**
   * This method is called when the game is over.
   * Implementations should define the behavior that occurs when the game ends.
   */
  void gameIsOver();

  /**
   * This method is called when a player switches.
   * Implementations should define the behavior that occurs when the active player changes.
   */
  void playerHasSwitched();

  /**
   * This method is called when the game has started.
   * Implementations should define the behavior that occurs when the game begins.
   */
  void gameHasStarted();
}
