package cs3500.threetrios.provider.code.players;

import java.util.List;
import java.util.Optional;

import cs3500.threetrios.provider.code.model.ICard;
import cs3500.threetrios.provider.code.model.IThreeTriosGame;
import cs3500.threetrios.provider.code.model.PlayerColor;
import cs3500.threetrios.provider.code.strategies.IMove;

/**
 * Represents an interface for a player in the game Three Trios. This interface shows the
 * behaviors of the players. This interface is implemented in the concrete class Player, that
 * represents a human player. For the future, we can use this IPlayer interface and implement it to
 * work for an AI player, as both the human and AI players will behave the same, and thus can
 * implement the same behaviors and interface. Because we need players to play the game, this
 * interface should be part of the model.
 */
public interface IPlayer extends PlayerActions {

  /**
   * This method works to get the ICards in the hand of an IPlayer in the game Three Trios.
   *
   * @return a list of ICards that makes up the hand of an IPlayer.
   */
  List<ICard> getHand();

  /**
   * This method works to get the IPlayer's PlayerColor, being either RED or BLUE in the game
   * Three Trios.
   *
   * @return the PlayerColor of the IPlayer.
   */
  PlayerColor getPlayerColor();

  /**
   * This method works to get the ICards that make up the hand of an IPlayer as well as any cards
   * in the grid that have the same color as this IPlayer's PlayerColor, which changes through
   * battling and flipping of the cards during the game of Three Trios.
   *
   *     @return the list of ICards that make up the IPlayer's hand and any ICard on the grid that
   *     has the same color as the IPlayer's PlayerColor.
   */
  List<ICard> getAllPlayerCards();

  /**
   * This method works to create a visual representation of strings of an IPlayer in the game of
   * Three Trios.
   *
   * @return either the string "BLUE" or "RED" depending on the IPlayer's PlayerColor.
   */
  String toString();

  /**
   * This method works to set the hand of an IPlayer while dealing the cards. This can only
   * happen once, as we only deal the cards to the IPlayers once. This method sets a field in the
   * Player class, and thus does not return anything / returns void.
   *
   * @param hand represents the list of ICards that we want to set the IPlayer's hand to be.
   */
  void setHand(List<ICard> hand);

  /**
   * This method works to remove an ICard from an IPlayer's hand, for example after an IPlayer
   * plays a card to the board, this card must be removed from said IPlayer's hand.
   *
   * @param cardIdx represents the index of the ICard in the list of ICards (the IPlayer's hand)
   *                that we want to remove.
   * @return the ICard at the cardIdx of the player's hand (list of ICards) that we removed.
   */
  ICard removeCardFromHand(int cardIdx);

  /**
   * This method works to add an ICard to an IPlayer's all cards list of ICards, for example, after
   * an IPlayer's card battles and wins, the other losing card should be added to this IPlayer's
   * list of all cards, representing the flipping of that losing card.
   *
   * @param card represents the ICard that we want to add to the list of ICards in the game
   *             Three Trios.
   */
  void addToAllCards(ICard card);

  /**
   * This method works to remove an ICard to an IPlayer's all cards list of ICards, for example,
   * after an IPlayer's card battles and loses, the losing card should be removed from this
   * IPlayer's list of all cards, representing the flipping of that losing card.
   *
   * @param otherCard represents the ICard that we want to remove from the list of ICards in the
   *                  game Three Trios.
   */
  void removeCardFromAllPlayerCards(ICard otherCard);

  /**
   * This method is used by the player to determine their move in the game.
   * It is optional because for the human player, the return will be empty. For the AI Player,
   * the return will be the best based on their strategy. In the case of a tie, it will give
   * the move with uppermost-leftmost location and the best card for that position with an index
   * closest to 0 in the hand. In the case there is no valid move, the move is the upper-most,
   * left-most open position and the card at index 0.
   *
   * @param model represents the game's grid.
   * @return an optional best or valid move.
   */
  Optional<IMove> getMove(IThreeTriosGame model);

  IPlayer copy();
}


