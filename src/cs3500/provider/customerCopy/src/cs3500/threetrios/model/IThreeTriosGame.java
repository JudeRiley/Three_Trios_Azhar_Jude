package cs3500.provider.customerCopy.src.cs3500.threetrios.model;

import java.util.List;

/**
 * Represents an interface for the game Three Trios. This interface shows the behaviors of the
 * game that mutate the game. This interface is implemented in the concrete class Game, that
 * represents this game model.For the future, we can use this IGame interface and implement it to
 * work for any new variants of the game Three Trios. Because we need a game to play, this interface
 * should be part of the model.
 */
public interface IThreeTriosGame extends ReadOnlyThreeTrios, ModelStatus {

  /** A method to make a copy of the game without pointing to the original.
   *
   * @return the copied IThreeTrios game with all of the fields copied as well.
   */
  IThreeTriosGame copy();

  /**
   * This method works to play a card from an IPlayer's hand to the grid in the game Three Trios.
   * The indexing for playing a card from the hand is 0-based. This method removes the current
   * ICard at the specified card index from the current IPlayer's hand.
   *
   * @param cardIdx represents the index of the hand (list of ICards) that the IPlayer wants to
   *                play to the grid in the game Three Trios.
   * @param row     represents the row of the grid that the IPlayer wants to play the ICard to.
   *                These rows are 0-based.
   * @param col     represents the column of the grid that the IPlayer wants to play the ICard to.
   *                These columns are 0-based.
   * @throws IllegalStateException    if the game is not started or game is over.
   * @throws IllegalArgumentException if:
   *                                  - the IPlayer tries to play to an IBoardCell that is a
   *                                    HoleCell
   *                                  - the cardIdx is less than 0 or larger than the current
   *                                    IPlayer's hand size.
   *                                  - if the row or column integer is less than 0 or greater than
   *                                    the grid's length
   */
  void playCard(int cardIdx, int row, int col);

  /**
   * This method works to have two cards battle by comparing the Card's attack values in their
   * respective direction (i.e. the Northern value for the bottom card battles the Southern
   * value for the top card on the grid). Battling is done by comparing these attack values and the
   * winner is the ICard with the greater attack value, compared by comparing the CardNumber's
   * integer value (i.e. with THREE going against FIVE, FIVE would win because THREE's integer
   * value 3 < FIVE's integer value of 5).
   *
   * @param row represents the row of the card that was just placed that battles the neighboring
   *            ICards.
   * @param col represents the column of the card that was just placed that battles the neighboring
   *            ICards.
   * @throws IllegalStateException if the game is not started or game is over.
   */
  void battle(int row, int col);


  /**
   * This method works to switch the current player between playedRed and playerBlue in the game
   * Three Trios after the current player's turn is complete (i.e. the current player plays a card,
   * and that card completes it's battling phase). This returns void as the field of the Game is
   * changed, and nothing/void is returned.
   *
   * @throws IllegalStateException if the game is not started or game is over.
   */
  void switchPlayer();

  /**
   * This method works to initialize the grid and cards for later dealing to the IPlayers in the
   * game Three Trios. If the given configuration files for the grid is at all faulty, we
   * initialize the grid for our game to a set default grid. The same goes for any faulty cards in
   * the card configuration file, and we will set the list of ICards to our default in this faulty
   * case. We also make sure to change the observation of if the game has been started to true.
   *
   * @param grid represents the grid provided by the user.
   * @param cards represents the ICards provided by the user.
   * @throws IllegalStateException if the game is started or game is over.
   */
  void startGame(IBoardCell[][] grid, List<ICard> cards);

}


