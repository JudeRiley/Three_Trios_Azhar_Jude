package cs3500.threetrios.provider.code.model;

import java.util.List;

/**
 * Represents an interface for a card in the game Three Trios. These cards are those that are in
 * the player's hand, placed on the board, and also the ones that battle each other in our game.
 * This interface shows the behaviors of the cards.
 * These ICards are essential to playing the game, and thus belong in the model package.
 */
public interface ICard {

  /**
   * A method to visualize an ICard in the game of Three Trios for the user's view.
   *
   * @return the string representation of an ICard.
   */
  String toString();

  /**
   * A method to compare if an ICard and another object are the same.
   *
   * @param object represents the object we are comparing our ICard to.
   * @return true if the ICard and object are the same and false if not.
   */
  boolean equals(Object object);

  /**
   * A method to that returns a unique numeric identifier for the card.
   *
   * @return the unique identifier of a card.
   */
  int hashCode();

  /**
   * A method to compare two ICards numerical values, specifically in the direction of the
   * recently placed card.
   * For example, if we place an ICard below another ICard, we would compare using the North
   * direction, with the just placed ICard's northern value compared to the other ICard's South
   * value.
   *
   *     @param other     represents the other ICard we are comparing.
   *     @param direction represents in which direction we compare from the card we just placed.
   *     @return true if the recently placed card's specified direction value is greater than the
   *     other card's corresponding direction value and false if not.
   */
  boolean isLargerThan(ICard other, String direction);

  /**
   * A method to return the South (bottom) value of an ICard.
   *
   * @return the CardNumber of the South value of an ICard.
   */
  CardNumber getSouth();

  /**
   * A method to return the North (top) value of an ICard.
   *
   * @return the CardNumber of the North value of an ICard.
   */
  CardNumber getNorth();

  /**
   * A method to return the East (right) value of an ICard.
   *
   * @return the CardNumber of the East value of an ICard.
   */
  CardNumber getEast();

  /**
   * A method to return the West (left) value of an ICard.
   *
   * @return the CardNumber of the West value of an ICard.
   */
  CardNumber getWest();

  /**
   * A method to return true if this ICard is contained in the list.
   *
   * @return true if in the list and false otherwise.
   */
  boolean isInList(List<ICard> iCards);

  /** A method to make a copy of an ICard without pointing to the original.
   *
   * @return the copied ICard.
   */
  ICard copy();
}
