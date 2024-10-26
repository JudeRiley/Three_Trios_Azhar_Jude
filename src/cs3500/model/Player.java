package cs3500.model;

public enum Player {
  RED, BLUE;

  public Player nextPlayer() {
    switch (this) {
      case RED:
        return BLUE;
      case BLUE:
        return RED;
      default:
        throw new IllegalArgumentException("Unsupported player!");
    }
  }
}
