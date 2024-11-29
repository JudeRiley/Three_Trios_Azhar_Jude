package cs3500.threetrios.provider.code.model;

import java.util.Objects;

/**
 * Represents the possible options for the numbers of a Card, being ONE-TEN, with TEN being
 * represented to the user as 'A' for the view later.
 * These CardNumbers are essential for the Cards and for playing the game, and thus belong in
 * the model package.
 */
public enum CardNumber {
  ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
  SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);

  // represents the numerical value of the CardNumber, so ONE having the value of 10 as an integer.
  private final int value;

  CardNumber(int val) {
    Objects.requireNonNull(val);
    if (val > 10 || val < 0) {
      throw new IllegalArgumentException("invalid card number");
    }
    this.value = val;
  }

  /**
   * Helper to access the value of the CardNumber enumeration.
   *
   * @return the value of the enum card of CardNumber.
   */
  public int getValue() {
    return this.value;
  }

  // We change the toString representation for the CardNumber TEN to 'A' here, for the user's view.
  @Override
  public String toString() {
    if (this.value == 10) {
      return "A";
    }
    return String.valueOf(this.value);
  }
}
