package cs3500.model;

/**
 * Represents a named card in a game of Three Trios
 * with four values corresponding to directions.
 */
public class ThreeTriosCard implements Card {

  //INVARIANT: values will always contain four integers between 1 and 10
  private final int[] values;
  private final String name;

  /**
   * Constructs a ThreeTriosCard with a name by parsing values from an array.
   *
   * @param name the name of the card as a string.
   * @param values an array of values.
   */
  public ThreeTriosCard(String name, int[] values) {
    if (name == null || values == null) {
      throw new IllegalArgumentException("Arguments cannot be null!");
    }
    if (values.length != 4) {
      throw new IllegalArgumentException("A ThreeTriosCard must have 4 values!");
    }
    for (int value : values) {
      if (value <= 0 || value > 10) {
        throw new IllegalArgumentException("Values must be between 1 and 10!");
      }
    }
    this.name = name;
    this.values = values;
  }

  /**
   * Constructs a ThreeTriosCard with 4 individual values corresponding to directions.
   *
   * @param name the name of the card.
   * @param north the north value.
   * @param south the south value.
   * @param east the east value.
   * @param west the west value.
   */
  public ThreeTriosCard(String name, int north, int south, int east, int west) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null!");
    }
    int[] temp = new int[]{north, south, east, west};
    for (int value : temp) {
      if (value <= 0 || value > 10) {
        throw new IllegalArgumentException("Values must be between 1 and 10!");
      }
    }
    this.name = name;
    this.values = temp;
  }

  public ThreeTriosCard(Card toCopy) {
    if (toCopy == null) {
      throw new IllegalArgumentException("The Card to copy cannot be null!");
    }

    this.name = toCopy.getName();
    this.values = new int[]{toCopy.getValueOf(Direction.NORTH), toCopy.getValueOf(Direction.SOUTH),
            toCopy.getValueOf(Direction.EAST), toCopy.getValueOf(Direction.WEST)};
  }

  @Override
  public int getValueOf(Direction d) {
    return values[d.ordinal()];
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder(this.name);
    for (int value : values) {
      if (value == 10) {
        ret.append(" A");
      } else {
        ret.append(" ").append(value);
      }
    }
    return ret.toString();
  }
}
