package cs3500.model;

import java.util.List;

public interface ThreeTrios {

  public void placeCard(GridPos pos, int cardIdx);

  public Player battleStep(GridPos pos);

  public Player getWinner();

  public Player getTurn();

  public Cell[][] getCurrentGrid();

  public List<Card> getHand(Player player);
}
