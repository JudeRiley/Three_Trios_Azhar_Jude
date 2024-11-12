package cs3500.model;

/**
 * An enum representing the player identifiers,
 * which in a Three trios game, are colors.
 */
public enum Player {
  RED, BLUE;

  /**
   * Returns the next player identifier in the enum, i
   * f it is at the end, it returns the first player identifier.
   *
   * @return the next player's identifier.
   */
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

  public static Player fromString(String str) {
    for (Player p : Player.values()) {
      if (p.toString().equals(str)) {
        return p;
      }
    }
    throw new IllegalArgumentException("Unsupported player!");
  }
}
