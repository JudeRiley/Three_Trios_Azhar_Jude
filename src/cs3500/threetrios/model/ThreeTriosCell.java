package cs3500.threetrios.model;

/**
 * Represents a cell in a Three Trios game. The cell can be a hole, meaning that a card cannot
 * be played to it. The cell can also be empty, having not yet had a card played to it.
 * In both of these cases, methods that rely on the card contained by the cell throw exceptions.
 */
public class ThreeTriosCell implements Cell {

  private final boolean isHole;
  private Card card;
  //INVARIANT : this.owner will always be null if this.card is null.
  private Player owner;

  /**
   * Constructs a ThreeTrios cell with only the knowledge if it is a hole cell or not.
   * If it is not a hole, the cell is initially empty.
   *
   * @param playable a boolean representing that is true if the cell is NOT a hole.
   */
  public ThreeTriosCell(boolean playable) {
    this.isHole = !playable;
    this.card = null;
    this.owner = null;
  }

  /**
   * A copy constructor. Creates a new ThreeTriosCell with identical data.
   *
   * @param toCopy the ThreeTriosCell that will be copied.
   */
  public ThreeTriosCell(Cell toCopy) {
    if (toCopy == null) {
      throw new IllegalArgumentException("The Cell to be copied cannot be null!");
    }

    if (toCopy.isCardCell()) {
      this.isHole = false;
      if (toCopy.hasCard()) {
        this.card = toCopy.getCardCopy();
        this.owner = Player.fromString(toCopy.getOwnerName());
      } else {
        this.card = null;
        this.owner = null;
      }
    } else {
      this.isHole = true;
    }
  }

  @Override
  public void setCard(Card card, Player owner) {
    disallowHoleCells();
    argumentException(card);
    argumentException(owner);
    if (this.card != null) {
      throw new IllegalArgumentException("The card cannot be changed once set.");
    }
    this.card = card;
    this.owner = owner;
  }

  @Override
  public void setOwner(Player owner) {
    disallowHoleCells();
    disallowEmptyCells();
    argumentException(owner);
    this.owner = owner;
  }

  @Override
  public String getOwnerName() {
    disallowHoleCells();
    disallowEmptyCells();
    return owner.toString();
  }

  @Override
  public int getCardValueOf(Direction d) {
    disallowHoleCells();
    disallowEmptyCells();
    argumentException(d);
    return this.card.getValueOf(d);
  }

  @Override
  public Card getCardCopy() {
    disallowEmptyCells();
    disallowEmptyCells();
    return new ThreeTriosCard(this.card);
  }

  @Override
  public int directionalCompareTo(Direction d, Cell other) {
    disallowHoleCells();
    disallowEmptyCells();
    argumentException(d);
    argumentException(other);
    if (this.getOwnerName().equals(other.getOwnerName())) {
      return 0;
    }
    return Integer.signum(this.getCardValueOf(d) - other.getCardValueOf(d.opposite()));
  }

  @Override
  public boolean isOpenForPlay() {
    return this.isCardCell() && !this.hasCard();
  }

  @Override
  public boolean hasCard() {
    return this.card != null;
  }

  @Override
  public boolean isCardCell() {
    return !this.isHole;
  }

  private void argumentException(Card card) {
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null!");
    }
  }

  private void argumentException(Player owner) {
    if (owner == null) {
      throw new IllegalArgumentException("Owner cannot be null!");
    }
  }

  private void argumentException(Cell other) {
    if (other == null) {
      throw new IllegalArgumentException("Other cell cannot be null!");
    }
  }

  private void argumentException(Direction d) {
    if (d == null) {
      throw new IllegalArgumentException("Direction cannot be null!");
    }
  }

  private void disallowHoleCells() {
    if (this.isHole) {
      throw new IllegalArgumentException("This Cell is a Hole Cell!");
    }
  }

  private void disallowEmptyCells() {
    if (this.card == null) {
      throw new IllegalArgumentException("This Cell is an Empty Cell!");
    }
  }

  @Override
  public String toString() {
    if (this.isHole) {
      return "Hole";
    }
    if (this.card == null) {
      return "Empty";
    }
    return this.getOwnerName() + " " + this.card.toString();
  }

}
