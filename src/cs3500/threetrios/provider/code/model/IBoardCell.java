package cs3500.threetrios.provider.code.model;

import java.util.List;

import cs3500.threetrios.provider.code.players.IPlayer;

/**
 * Represents an interface for board cell in the Three Trios game, being extended to the abstract
 * board cell class for later implementation of holes and card cells. Used an interface to
 * describe the behaviors of the holes and/or cells. This belongs to the model as it is an
 * essential part of the Three Trios game and is necessary for game play. Because we need
 * board cells in our game, this interface should be part of the model.
 * These Board Cells are essential to having a board to play the game and, and thus belong
 * in the model package.
 */
public interface IBoardCell {

  /**
   * A method to determine if a given IBoardCell is an instance of the HoleCell class for
   * later implementation.
   *
   * @return true if it is a HoleCell and false if it isn't (ie is a CardCell).
   */
  boolean isAHole();

  /**
   * A method to determine if the given IBoardCell is empty.
   *
   * @return true if the cell is a HoleCell, true if it's a CardCell
   */
  boolean isEmpty();

  /**
   * A method to determine if the given IBoardCell is of the CardCell class or the HoleCell
   * class.
   *
   * @return true if it is a CardCell and false if not (ie is a HoleCell).
   */
  boolean isCardCell();


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
  String toString(List<ICard> getCurCards, IPlayer curr, IPlayer other);

  /** Makes a copy of this cell and it's contents.
   *
   * @return the copied cell
   */
  IBoardCell copy();
}
