package cs3500.model;

import java.io.IOException;
import java.util.List;

/**
 * Mock implementation of the ReadOnlyThreeTrios interface for testing purposes.
 * It logs method calls and parameters to the provided Appendable.
 */
public class MockReadOnlyThreeTrios implements ReadOnlyThreeTrios {

  private final Appendable log;
  private Cell[][] currentGrid;
  private Player currentPlayer;
  private List<Card> playerHand;
  private Player winner;

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
  public void setCurrentGrid(Cell[][] grid) {
    this.currentGrid = grid;
  }

  /**
   * Sets the current player's turn.
   *
   * @param player the player to set as the current player
   */
  public void setCurrentPlayer(Player player) {
    this.currentPlayer = player;
  }

  /**
   * Sets the specified player's hand.
   *
   * @param hand the list of cards to set as the player's hand
   */
  public void setPlayerHand(List<Card> hand) {
    this.playerHand = hand;
  }

  /**
   * Sets the winner of the game.
   *
   * @param winner the player who has won the game
   */
  public void setWinner(Player winner) {
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
  public Player getTurn() {
    try {
      log.append("getTurn() called\n");
    } catch (IOException e) {
      throw new RuntimeException("Error writing to log in getTurn()", e);
    }
    return currentPlayer;
  }

  @Override
  public List<Card> getHand(Player player) {
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
