package cs3500.model;

public class ThreeTriosCard {

  //TODO : Is this an invariant???
  //INVARIANT: values will always contain four integers between 1 and 10
  private final int[] values;
  //TODO : Do we really have to implement name?
  //private final String name;

  public ThreeTriosCard(int[] values) {
    if (values == null) {
      throw new IllegalArgumentException("Values cannot be null!");
    }
    if (values.length != 4) {
      throw new IllegalArgumentException("A ThreeTriosCard must have 4 values!");
    }
    for (int value : values) {
      if (value <= 0 || value > 10) {
        throw new IllegalArgumentException("Values must be between 1 and 10!");
      }
    }
    this.values = values;
  }

  public ThreeTriosCard(int North, int South, int East, int West) {
    int[] temp = new int[]{North, South, East, West};
    for (int value : temp) {
      if (value <= 0 || value > 10) {
        throw new IllegalArgumentException("Values must be between 1 and 10!");
      }
    }
    this.values = temp;
  }

  public int getValueOf(Direction d) {
    return values[d.ordinal()];
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    for (Direction d : Direction.values()) {
      ret.append(d.name()).append("(").append(this.getValueOf(d)).append(")");
    }
    return ret.toString();
  }
}
