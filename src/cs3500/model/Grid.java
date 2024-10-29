package cs3500.model;

import java.util.List;

public interface Grid {

  public void playCard(GridPos pos, Card card, Player owner);

  public List<GridPos> getLosingNeighbors(GridPos pos);

  public void flipCardCellTo(GridPos pos, Player owner);

  public boolean isSaturated();

  public int getScoreOf(Player player);

  public int getNumCardCells();

  public Cell[][] getCurrentGrid();

  /**
   * Returns the cell at the specified GridPos.
   *
   * @param pos the position of the cell to retrieve
   * @return the Cell at the given position
   * @throws IllegalArgumentException if the position is out of bounds
   */
  Cell getCell(GridPos pos);
}
