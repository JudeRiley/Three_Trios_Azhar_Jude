package cs3500.model;

public class ThreeTriosCell implements Cell {

  private final boolean isHole;
  private Card card;
  private Player owner;

  public ThreeTriosCell(boolean playable) {
    this.isHole = !playable;
  }

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

  public void setOwner(Player owner) {
    disallowHoleCells();
    disallowEmptyCells();
    argumentException(owner);
    this.owner = owner;
  }

  public String getOwnerName() {
    disallowHoleCells();
    disallowEmptyCells();
    return owner.toString();
  }

  public int getCardValueOf(Direction d) {
    disallowHoleCells();
    disallowEmptyCells();
    argumentException(d);
    return this.card.getValueOf(d);
  }

  //TODO : return zero instead of excpetions?
  //Takes color into account
  //Returns 0 if owners are the same ELSE:
  //returns 0 if number is the same, -1 if it is less and 1 if it is more
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


  //TODO : Consolidate into one function with some sort of switch case?
  public boolean isFilled() {
    return this.isHole || this.card != null;
  }

  public boolean hasCard() {
    return this.card != null;
  }

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
    return this.getOwnerName() + " " + this.card.toString();
  }

}
