package cs3500.model;

import java.io.IOException;
import java.util.List;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Cell;
import cs3500.threetrios.model.Player;
import cs3500.threetrios.model.ReadOnlyThreeTrios;

/**
 * Mock implementation of the ReadOnlyThreeTrios interface for testing purposes.
 * It logs method calls and parameters to the provided Appendable.
 */
public class MockReadOnlyThreeTrios implements ReadOnlyThreeTrios {

  private final Appendable log;
  private cs3500.threetrios.model.Cell[][] currentGrid;
  private cs3500.threetrios.model.Player currentPlayer;
  private List<cs3500.threetrios.model.Card> playerHand;
  private cs3500.threetrios.model.Player winner;

  /**
   * Constructs a MockReadOnlyThreeTrios with a given Appendable log.
   *
   * @param log the Appendable where method call logs will be appended
   */
  public MockReadOnlyThreeTrios(Appendable log) {
    this.log = log;
  }

  /**
   * Sets the current grid state.
   *
   * @param grid the grid to set as the current grid
   */
  public void setCurrentGrid(cs3500.threetrios.model.Cell[][] grid) {
    this.currentGrid = grid;
  }

  /**
   * Sets the current player's turn.
   *
   * @param player the player to set as the current player
   */
  public void setCurrentPlayer(cs3500.threetrios.model.Player player) {
    this.currentPlayer = player;
  }

  /**
   * Sets the specified player's hand.
   *
   * @param hand the list of cards to set as the player's hand
   */
  public void setPlayerHand(List<cs3500.threetrios.model.Card> hand) {
    this.playerHand = hand;
  }

  /**
   * Sets the winner of the game.
   *
   * @param winner the player who has won the game
   */
  public void setWinner(cs3500.threetrios.model.Player winner) {
    this.winner = winner;
  }

  @Override
  public Cell[][] getCurrentGrid() {
    try {
      log.append("getCurrentGrid() called\n");
    } catch (IOException e) {
      throw new RuntimeException("Error writing to log in getCurrentGrid()", e);
    }
    return currentGrid;
  }

  @Override
  public cs3500.threetrios.model.Player getTurn() {
    try {
      log.append("getTurn() called\n");
    } catch (IOException e) {
      throw new RuntimeException("Error writing to log in getTurn()", e);
    }
    return currentPlayer;
  }

  @Override
  public List<Card> getHand(cs3500.threetrios.model.Player player) {
    try {
      log.append("getHand(").append(player.toString()).append(") called\n");
    } catch (IOException e) {
      throw new RuntimeException("Error writing to log in getHand()", e);
    }
    return playerHand;
  }

  @Override
  public Player getWinner() {
    try {
      log.append("getWinner() called\n");
    } catch (IOException e) {
      throw new RuntimeException("Error writing to log in getWinner()", e);
    }
    if (winner == null) {
      throw new IllegalStateException("Game is not over yet");
    }
    return winner;
  }
}
