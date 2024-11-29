package cs3500.threetrios.provider.code.model;

import java.util.Optional;

import cs3500.threetrios.players.IPlayer;

/**
 * Represents an interface for the game Three Trios. This interface shows the observations of the
 * game. This interface is extended by the IGame interface which also contains behaviors that mutate
 * the model. Because we need a game to play, this interface should be part of the model.
 */
public interface ReadOnlyThreeTrios {
  /**
   * This method works to get the game of Three Trio's grid as it is currently.
   *
   *     @return the list of IBoardCells that make up the grid for our game of Three Trios.
   *     @throws IllegalStateException if the game has not been started.
   */
  IBoardCell[][] getGrid();

  /**
   * This method works to get a specific cell in the game of Three Trio's grid as it is currently.
   *
   *     @return the IBoardCell at the given row and column.
   *     @throws IllegalStateException if the game has not been started.
   *     @throws IllegalArgumentException if the row or column are not valid.
   */
  IBoardCell getCell(int row, int col);

  /**
   * This method works to get the score based on the given player.
   *
   *     @param player the player whose score you are looking for.
   *     @return the number of cards the given player owns.
   *     @throws IllegalStateException if the game has not been started.
   */
  int getScore(IPlayer player);

  /**
   * This method works to figure out the number of cards a player
   * the current player can flip given a card and a coordinate (0 based).
   *
   *     @param cardToPlay the card the current player's hand they want to play.
   *     @param row the given row the card should be placed.
   *     @param col the given col the card should be placed.
   *     @return the number of cards that can be flipped by playing to the given spot
   *     with the given card.
   *     @throws IllegalStateException if the game has not been started or the game is over.
   *     @throws IllegalArgumentException if the row or col are not valid
   *     or occupied or the card is null or not in the current player's hand.
   */
  int flipCards(ICard cardToPlay, int row, int col);


  /**
   * This method works to figure out the number of cards a player
   * the current player can flip given a card and a coordinate (0 based).
   *
   * @param cardIdx the card index the current player's hand they want to play.
   * @param row the given row the card should be placed.
   * @param col the given col the card should be placed.
   * @return true if the move is legal and false otherwise
   * @throws IllegalStateException if the game is not started or already over.
   */
  boolean isMoveLegal(int cardIdx, int row, int col);

  /**
   * This method works to determine what IPlayer is the current player in the game of Three
   * Trios.
   *
   * @return the IPlayer, either playerRed or playerBlue, that is the current player.
   * @throws IllegalStateException if the game is not started.
   */
  IPlayer getCurrentPlayer();

  /**
   * This method works to determine what IPlayer is not the current player in the game of Three
   * Trios.
   *
   * @return the IPlayer, either playerRed or playerBlue, that is not the current player.
   * @throws IllegalStateException if the game is not started.
   */
  IPlayer otherPlayer();

  /**
   * This method works to determine if the game is over, meaning if all the card cells have been
   * filled by ICards.
   *
   *     @return true if all the CardCells of a grid in Three Trios are filled with ICards and
   *     false otherwise.
   *     @throws IllegalStateException if the game has not been started.
   */
  boolean isGameOver();

  /**
   * This method works to determine who the winner of the game Three Trios is, meaning which
   * IPlayer has the most ICards that correspond to the PlayerColor of the IPlayer ("owned" ICards).
   * If the IPlayers have the same amount of owned ICards, then the game results in a tie and this
   * method returns null.
   *
   *     @return the IPlayer that has the most amount of "owned" ICards, and if no such player
   *     exists, returns null.
   *     @throws IllegalStateException if the game is not started or game is not over.
   */
  Optional<IPlayer> getWinner();

  /**
   * This method works to visualize the game of Three Trios in a textual (String) representation.
   * This method appends the current IPlayer's PlayerColor, the grid at that point (with empty
   * CardCells, filled CardCells, and HoleCells each represented differently and discussed in those
   * respective classes), and the current IPlayer's hand, which is a list of ICards they are able to
   * place / play to the grid.
   *
   *     @return the String representation of the game Three Trios, with the current IPlayer's
   *     PlayerColor, the grid at that moment, and the current IPlayer's hand.
   */
  String toString();

  /**
   * This method works to determine if the game is started.
   *
   *     @return true if the game has been started.
   *     @throws IllegalStateException if the game is already over.
   */
  boolean isStarted();

  /**
   * This method checks to see if the given row is valid based on the game.
   *
   * @param row represents a row in the grid of the game.
   * @throws IllegalStateException if the game is not started.
   */
  boolean isValidRow(int row);

  /**
   * This method checks to see if the given column is valid based on the game.
   *
   * @param col represents a col in the grid of the game.
   * @throws IllegalStateException if the game is not started.
   */
  boolean isValidCol(int col);
}
