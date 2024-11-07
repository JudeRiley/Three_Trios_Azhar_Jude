package cs3500.model;

/**
 * Represents a rules-keeper for a game of ThreeTrios.
 */
public interface ThreeTrios extends ReadOnlyThreeTrios {

  /**
   * Places the card at the given index in the player's hand whose turn it currently is
   * at the given position on the grid.
   *
   * @param pos     the position in the grid to place the card at.
   * @param cardIdx an index in the current players hand.
   */
  void placeCard(GridPos pos, int cardIdx);

  /**
   * A recursive function that "attacks" the neighbors of the card at the given position.
   * If a neighbor loses, it is reassigned to the player who owns the attacking card.
   * Each losing neighbor proceeds to recursively attack its own neighbors.
   * The return value of each recursive call is ignored.
   * Once all losing neighbors have been iterated through, the initial call to the method
   * returns the player identifier of the player whose turn it next.
   *
   * @param pos the position of the attacking card.
   * @return the Player whose turn is after the current turn.
   */
  Player battleStep(GridPos pos);
}
