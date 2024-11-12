package cs3500.strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import cs3500.model.Card;
import cs3500.model.Cell;
import cs3500.model.Grid;
import cs3500.model.GridPos;
import cs3500.model.GridPos2d;
import cs3500.model.Move;
import cs3500.model.Player;
import cs3500.model.ReadOnlyThreeTrios;
import cs3500.model.ThreeTriosGrid;

public class FlipMostCardsTactic implements ThreeTriosTactic {

  private Cell[][] grid;
  private Grid gameGrid;
  private Player player;
  private List<Card> hand;

  @Override
  public Optional<Move> chooseMove(ReadOnlyThreeTrios model, Player forWhom) {
    this.grid = model.getCurrentGrid();
    this.gameGrid = new ThreeTriosGrid(this.grid);
    this.player = forWhom;
    this.hand = model.getHand(this.player);


    Map<GridPos, List<GridPos>> allLosingNeighborsLists = new HashMap<>();

    for (int row = 0; row < this.grid.length; row++) {
      for (int col = 0; col < this.grid[row].length; col++) {
        GridPos currCell = new GridPos2d(row, col);
        allLosingNeighborsLists.put(currCell, this.gameGrid.getLosingNeighbors(currCell));
      }
    }

    //Collections.max()

    return Optional.empty();
  }




}
