package cs3500.model;

public class ThreeTriosMove implements Move{
  GridPos pos;

  /**
   *
   * @param pos
   * @param cardIdx
   */
  public ThreeTriosMove(GridPos pos, int cardIdx) {

  }

  @Override
  public GridPos getPosition() {
    return pos;
  }

  @Override
  public int getCardIxdInHand() {
    return 0;
  }
}
