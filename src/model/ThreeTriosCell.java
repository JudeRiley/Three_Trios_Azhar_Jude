package model;

public class ThreeTriosCell implements Cell {

  private final boolean isHole;
  private Card card;
  private Player owner;

  public ThreeTriosCell(boolean isHole) {
    this.isHole = true;
  }

  public void setCard(Card card, Player owner) {
    disallowHoleCells();
    if (this.card != null) {
      throw new IllegalArgumentException("The card cannot be changed once set.");
    }
    this.card = card;
    this.owner = owner;
  }

  public void setOwner(Player owner) {
    disallowHoleCells();
    disallowEmptyCells();
    this.owner = owner;
  }

  public String getOwnerName() {
    disallowHoleCells();
    disallowEmptyCells();
    return owner.name();
  }

  public int getCardValueOf(Direction d) {
    disallowHoleCells();
    disallowEmptyCells();
    return this.card.getValueOf(d);
  }

  //Takes color into account
  //Returns 0 if owners are the same ELSE:
  //returns 0 if number is the same, -1 if it is less and 1 if it is more
  public int directionalCompareTo(Direction d, Cell other) {
    disallowHoleCells();
    disallowEmptyCells();
    if (this.getOwnerName().equals(other.getOwnerName())) {
      return 0;
    }
    return Integer.signum(this.getCardValueOf(d) - other.getCardValueOf(d.opposite()));
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
