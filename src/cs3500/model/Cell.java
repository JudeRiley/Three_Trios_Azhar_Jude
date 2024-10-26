package cs3500.model;

/**
 * A generic game cell with teh ability to contain a card and to be owned by a player.
 */
public interface Cell {

  /**
   * Sets the card of the cell permanently.
   *
   * @param card The card to be played.
   * @param owner The player that owns the card being played.
   * @exception IllegalArgumentException If this cell is a hole cell.
   * @exception IllegalArgumentException If this cell already has a card.
   */
  public void setCard(Card card, Player owner);

  /**
   *
   * @param owner
   */
  public void setOwner(Player owner);

  /**
   *
   * @return
   */
  public String getOwnerName();

  /**
   *
   * @param d
   * @return
   */
  public int getCardValueOf(Direction d);

  /**
   *
   * @param d
   * @param other
   * @return
   */
  public int directionalCompareTo(Direction d, Cell other);

  public boolean isFilled();

  public boolean hasCard();

}
