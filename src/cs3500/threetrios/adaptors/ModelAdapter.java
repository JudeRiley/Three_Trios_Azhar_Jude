package cs3500.threetrios.adaptors;

import java.util.Optional;

import cs3500.threetrios.code.model.Cell;
import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.provider.code.model.IBoardCell;
import cs3500.threetrios.provider.code.model.ICard;
import cs3500.threetrios.provider.code.model.ReadOnlyThreeTrios;
import cs3500.threetrios.provider.code.players.IPlayer;

/**
 * Adapts our original ReadOnlyThreeTriosModel behavior
 * to work for the ReadOnlyThreeTrios interface.
 */
public class ModelAdapter implements ReadOnlyThreeTrios {

  private ReadOnlyThreeTriosModel ourModel;

  /**
   * Constructs an instance of the providers read only model that runs off of the functionality
   * of out model.
   *
   * @param ourModel the model we use
   */
  public ModelAdapter(ReadOnlyThreeTriosModel ourModel) {
    this.ourModel = ourModel;
  }

  /**
   * This method works to get the game of Three Trio's grid as it is currently.
   *
   * @return the list of IBoardCells that make up the grid for our game of Three Trios.
   * @throws IllegalStateException if the game has not been started.
   */
  @Override
  public IBoardCell[][] getGrid() {
    Cell[][] ourGrid = ourModel.getCurrentGrid();
    IBoardCell[][] ret = new IBoardCell[ourGrid.length][ourGrid[0].length];

    for (int row = 0; row < ourGrid.length; row++) {
      for (int col = 0; col < ourGrid[row].length; col++) {
        ret[row][col] = new CellAdapter(ourGrid[row][col]);
      }
    }

    return ret;
  }

  /**
   * This method works to get a specific cell in the game of Three Trio's grid as it is currently.
   *
   * @param row the row.
   * @param col the column.
   * @return the IBoardCell at the given row and column.
   * @throws IllegalStateException    if the game has not been started.
   * @throws IllegalArgumentException if the row or column are not valid.
   */
  @Override
  public IBoardCell getCell(int row, int col) {
    return this.getGrid()[row][col];
  }

  @Override
  public int getScore(IPlayer player) {
    // Score is not read by the provider's view, so this is not necessary to adapt.
    return 0;
  }

  @Override
  public int flipCards(ICard cardToPlay, int row, int col) {
    // This is not read by the provider's view, so this is not necessary to adapt.
    return 0;
  }

  @Override
  public boolean isMoveLegal(int cardIdx, int row, int col) {
    // This is not read by the provider's view, so this is not necessary to adapt.
    return false;
  }

  /**
   * This method works to determine what IPlayer is the current player in the game of Three
   * Trios.
   *
   * @return the IPlayer, either playerRed or playerBlue, that is the current player.
   * @throws IllegalStateException if the game is not started.
   */
  @Override
  public IPlayer getCurrentPlayer() {
    return new PlayerAdapter(ourModel.getTurn(), ourModel);
  }

  /**
   * This method works to determine what IPlayer is not the current player in the game of Three
   * Trios.
   *
   * @return the IPlayer, either playerRed or playerBlue, that is not the current player.
   * @throws IllegalStateException if the game is not started.
   */
  @Override
  public IPlayer otherPlayer() {
    return new PlayerAdapter(Player.BLUE, ourModel);
  }

  @Override
  public boolean isGameOver() {
    // This is not read by the provider's view, so this is not necessary to adapt.
    return false;
  }

  @Override
  public Optional<IPlayer> getWinner() {
    // This is not read by the provider's view, so this is not necessary to adapt.
    return Optional.empty();
  }

  @Override
  public boolean isStarted() {
    // This is not read by the provider's view, so this is not necessary to adapt.
    return false;
  }

  @Override
  public boolean isValidRow(int row) {
    // This is not read by the provider's view, so this is not necessary to adapt.
    return false;
  }

  @Override
  public boolean isValidCol(int col) {
    // This is not read by the provider's view, so this is not necessary to adapt.
    return false;
  }
}
