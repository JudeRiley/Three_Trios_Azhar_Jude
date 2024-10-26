package cs3500.model;

public class ThreeTriosCard {

  //INVARIANT: values will always contain four integers between 1 and 10
  private final int[] values;
  private final String name;

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

  public ThreeTriosCard(String name, int North, int South, int East, int West) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null!");
    }
    int[] temp = new int[]{North, South, East, West};
    for (int value : temp) {
      if (value <= 0 || value > 10) {
        throw new IllegalArgumentException("Values must be between 1 and 10!");
      }
    }
    this.name = name;
    this.values = temp;
  }

  public int getValueOf(Direction d) {
    return values[d.ordinal()];
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
