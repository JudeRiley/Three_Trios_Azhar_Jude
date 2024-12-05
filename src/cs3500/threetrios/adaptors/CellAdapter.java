package cs3500.threetrios.adaptors;

import java.util.List;

import cs3500.threetrios.code.model.Cell;
import cs3500.threetrios.provider.code.model.IBoardCell;
import cs3500.threetrios.provider.code.model.ICard;
import cs3500.threetrios.provider.code.players.IPlayer;

/**
 * Adapts our original Cell behavior to work for the IBoardCell interface.
 */
public class CellAdapter implements IBoardCell {

  private final Cell ourCell;

  /**
   * Constructs an instance of IBoardCell that makes use of our Cell functionality.
   *
   * @param cell a Cell object from the original three trios model.
   */
  public CellAdapter(Cell cell) {
    this.ourCell = cell;
  }

  /**
   * A method to determine if a given IBoardCell is an instance of the HoleCell class for
   * later implementation.
   *
   * @return true if it is a HoleCell and false if it isn't (ie is a CardCell).
   */
  @Override
  public boolean isAHole() {
    return !ourCell.isCardCell();
  }

  /**
   * A method to determine if the given IBoardCell is empty.
   *
   * @return true if the cell is a HoleCell, true if it's a CardCell.
   */
  @Override
  public boolean isEmpty() {
    return ourCell.isOpenForPlay();
  }

  /**
   * A method to determine if the given IBoardCell is of the CardCell class or the HoleCell
   * class.
   *
   * @return true if it is a CardCell and false if not (ie is a HoleCell).
   */
  @Override
  public boolean isCardCell() {
    return ourCell.isCardCell();
  }

  /**
   * A method to visualize the board cells, with different implementations in each of the
   * concrete classes CardCell and HoleCell.
   *
   * @param getCurCards represents the current player's current cards, with their hand and cards
   *                    of their color on the board.
   * @param curr        represents which player is currently playing in the Three Trios game.
   * @param other       represents which player is not currently playing in the Three Trios game.
   * @return a string of the proper textual view of each board cell.
   */
  @Override
  public String toString(List<ICard> getCurCards, IPlayer curr, IPlayer other) {
    return "";
  }

  @Override
  public IBoardCell copy() {
    // This is not used by the view and is not necessary to adapt.
    return null;
  }

  /**
   * A helper for accessing the card in the CardCell at that time.
   *
   * @return the card in the CardCell.
   * @throws IllegalStateException if the cell is a hole or the cell is empty, ie the card is null.
   */
  @Override
  public ICard getCard() {
    return new CardAdapter(ourCell.getCardCopy());
  }
}
