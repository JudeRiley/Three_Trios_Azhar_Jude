package cs3500.model;

public class ThreeTriosMove implements Move{

  private GridPos pos;
  private int cardIdx;

  /**
   *
   * @param pos
   * @param cardIdx
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
  public int getCardIxdInHand() {
    return cardIdx;
  }
}
