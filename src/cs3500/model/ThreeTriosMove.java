package cs3500.model;

/**
 * The data that makes up one "move" (a player's action on their turn) in a ThreeTriosModel.
 */
public class ThreeTriosMove implements Move{

  private final GridPos pos;
  private final int cardIdx;

  /**
   * Creates a new ThreeTriosMove that contains the information necessary
   * to play one turn in a ThreeTriosModel.
   *
   * @param pos The grid pos on the board.
   * @param cardIdx The index in the player's hand of the card to be played.
   */
  public ThreeTriosMove(GridPos pos, int cardIdx) {
    this.pos = pos;
    this.cardIdx = cardIdx;
  }

  @Override
  public GridPos getPosition() {
    return new GridPos2d(pos.getRow(), pos.getCol());
  }

  @Override
  public int getCardIdxInHand() {
    return cardIdx;
  }
}
