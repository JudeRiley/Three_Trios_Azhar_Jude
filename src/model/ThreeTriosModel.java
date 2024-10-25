package model;

import java.util.ArrayList;
import java.util.List;

public class ThreeTriosModel implements ThreeTrios{

  private final Grid grid;
  private Player turn;
  private final List<Card> oneHand;
  private final List<Card> twoHand;

  public ThreeTriosModel(Grid grid, int handSize) {
    this.grid = grid;
    this.oneHand = new ArrayList<>();
    this.twoHand = new ArrayList<>();
  }

  public void play(int x, int y, int cardIdx) {

  }


}
