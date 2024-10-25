package model;

/**
 * A generic card with values that correspond to directions
 */
public interface Card {

  /**
   * Returns the integer value that the card has at the specified direction.
   *
   * @param d the direction to check
   * @return the value in the given direction.
   */
  public int getValueOf(Direction d);

}
