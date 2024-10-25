package model;

import java.util.List;

public interface Grid {

  public void playCard(GridPos pos, Card card, Player owner);

  public List<GridPos> returnLosingNeighbors(GridPos pos);

}
