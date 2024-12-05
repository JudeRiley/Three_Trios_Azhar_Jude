package cs3500.threetrios.adaptors;

import java.util.List;

import cs3500.threetrios.code.model.Card;
import cs3500.threetrios.code.model.Direction;
import cs3500.threetrios.provider.code.model.CardNumber;
import cs3500.threetrios.provider.code.model.ICard;

/**
 * Adapts our original Card behavior to work for the ICard interface.
 */
public class CardAdapter implements ICard {
  private Card card;

  /**
   * Constructs an instance of ICard that makes use of the functionality of our original
   * Card interface.
   *
   * @param card an original Card object
   */
  public CardAdapter(Card card) {
    this.card = card;
  }

  @Override
  public boolean isLargerThan(ICard other, String direction) {
    // This is not used by the view and is not necessary to adapt.
    return false;
  }

  @Override
  public CardNumber getSouth() {
    return toCardNumber(card.getValueOf(Direction.SOUTH));
  }

  @Override
  public CardNumber getNorth() {
    return toCardNumber(card.getValueOf(Direction.NORTH));
  }

  @Override
  public CardNumber getEast() {
    return toCardNumber(card.getValueOf(Direction.EAST));
  }

  @Override
  public CardNumber getWest() {
    return toCardNumber(card.getValueOf(Direction.WEST));
  }

  private CardNumber toCardNumber(int val) {
    for (CardNumber cardN : CardNumber.values()) {
      if (cardN.getValue() == val) {
        return cardN;
      }
    }
    return null;
  }

  // This is not used by the view and is not necessary to adapt.
  @Override
  public boolean isInList(List<ICard> iCards) {
    return false;
  }

  // This is not used by the view and is not necessary to adapt.
  @Override
  public ICard copy() {
    return null;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ICard) {
      return this.toString().equals(obj.toString());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.card.hashCode();
  }

  @Override
  public String toString() {
    return card.toString();
  }
}
