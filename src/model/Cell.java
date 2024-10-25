package model;

public interface Cell {

  public void setCard(Card card, Player owner);

  public void setOwner(Player owner);

  public String getOwnerName();

  public int getCardValueOf(Direction d);

  public int directionalCompareTo(Direction d, Cell other);

}
