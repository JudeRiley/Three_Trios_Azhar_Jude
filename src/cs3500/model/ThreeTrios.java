package cs3500.model;

public interface ThreeTrios {

  public void placeCard(GridPos pos, int cardIdx);

  public Player battleStep(GridPos pos);

  public Player getWinner();
}
