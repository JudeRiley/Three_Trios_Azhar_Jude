package cs3500.threetrios.provider.code.players;

import cs3500.threetrios.controller.Controller;
import cs3500.threetrios.model.IThreeTriosGame;

/**
 * Represents the interface for the actions that a player (human or AI) can perform in the Three
 * Trios game. This interface defines methods for notifying the game and controller about the
 * player's actions, such as choosing a move, selecting a card, selecting a position,
 * deselecting a card, and being notified that it is their turn.
 */
public interface PlayerActions {

  /**
   * Adds a listener (a controller) that will receive notifications about the player's actions.
   * This method allows the controller to listen for player actions and react accordingly.
   *
   * @param controller the controller that will listen for player action notifications
   */
  void addControllerListener(Controller controller);

  /**
   * Notifies the game and controller that the player has chosen a move, represented by the given
   * card index and position on the board (row and column). This action allows the game logic to
   * process the player's move and update the game state.
   *
   * @param cardIdx the index of the card the player selected for the move
   * @param row the row where the player wants to make the move
   * @param col the column where the player wants to make the move
   */
  void notifyPlayersChoseAMove(int cardIdx, int row, int col);

  /**
   * Notifies the game and controller that the player has chosen a card. This method is used when
   * the player selects a card from their hand in the card panel to later place and battle on the
   * board.
   *
   * @param cardIdx the index of the card that the player selected
   */
  void notifyPlayersChoseACard(int cardIdx);

  /**
   * Notifies the game and controller that the player has chosen a position on the board, from the
   * board panel.
   * This action involves selecting a row and column to place or move a card.
   *
   * @param selectedRow the row where the player wants to place or move a card
   * @param selectedCol the column where the player wants to place or move a card
   */
  void notifyPlayersChoseAPosn(int selectedRow, int selectedCol);

  /**
   * Notifies the game and controller that the player has deselected a card. This may happen
   * when the player changes their mind about a card they previously selected or want to select
   * another card.
   */
  void notifyPlayerDeselectedCard();

  /**
   * Notifies the player that it is their turn to play in the game. This method is called when
   * the game state changes, and it becomes this player's turn to make a move. It allows the player
   * to perform actions like selecting a card and placing a card.
   *
   * @param model the game model, which provides the current state of the game
   */
  void youTurn(IThreeTriosGame model);
}
