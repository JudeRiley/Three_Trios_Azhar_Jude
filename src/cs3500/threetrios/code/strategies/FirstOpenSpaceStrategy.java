package cs3500.threetrios.code.strategies;

import cs3500.threetrios.code.model.Cell;
import cs3500.threetrios.code.model.GridPos2d;
import cs3500.threetrios.code.model.Move;
import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.code.model.ThreeTriosMove;

/**
 * An infallible strategy that plays the first card in a players hand to the first open space.
 */
public class FirstOpenSpaceStrategy implements ThreeTriosStrategy {

  @Override
  public Move chooseMove(ReadOnlyThreeTriosModel model, Player fr) throws IllegalStateException {
    Cell[][] grid = model.getCurrentGrid();

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
        if (grid[row][col].isOpenForPlay()) {
          return new ThreeTriosMove(new GridPos2d(row, col), 0);
        }
      }
    }
    //Something has gone wrong, likely the game is over or the model is in an invalid state.
    throw new IllegalStateException("There are no possible moves chosen by this strategy!");
  }

}
