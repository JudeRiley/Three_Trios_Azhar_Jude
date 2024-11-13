package cs3500.strategies;

import cs3500.model.*;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.*;

/**
 * Testing the TacticOrFirstOpenSpaceStrategyTest.  You can use any "tactic" inside of this class,
 * and it will just do the "tactic" or if you can't then it will fall back on the first open space
 * strategy.  This is used to test the strategies of the game (we call tactics).
 */
public class TacticOrFirstOpenSpaceStrategyTest {

  /**
   * Tests the GoForCornersTactic (Strategy 2) when all the corners are available.
   * @throws IOException When invalid input
   */
  @Test
  public void testCornersAvailable() throws IOException {
    // Create an Appendable log
    StringBuilder log = new StringBuilder();

    // Create a 3x3 empty grid (corners are available)
    Cell[][] mockGrid = createEmptyGrid(3, 3);
    Player currentPlayer = Player.RED;

    // Create a hand with two cards
    Card weakCard = new ThreeTriosCard("WeakCard", 1, 1, 1, 1);
    Card strongCard = new ThreeTriosCard("StrongCard", 9, 9, 9, 9);
    List<Card> mockHand = Arrays.asList(weakCard, strongCard);

    // Set up the mock model
    MockReadOnlyThreeTrios mockModel = new MockReadOnlyThreeTrios(log);
    mockModel.setCurrentGrid(mockGrid);
    mockModel.setCurrentPlayer(currentPlayer);
    mockModel.setPlayerHand(mockHand);

    // Use GoForCornersTactic as the tactic
    GoForCornersTactic tactic = new GoForCornersTactic();

    // Create an instance of TacticOrFirstOpenSpaceStrategy with the tactic
    TacticOrFirstOpenSpaceStrategy strategy = new TacticOrFirstOpenSpaceStrategy(tactic);

    // Execute the strategy
    Move selectedMove = strategy.chooseMove(mockModel, currentPlayer);

    // The move should be to one of the corners
    GridPos pos = selectedMove.getPosition();
    int row = pos.getRow();
    int col = pos.getCol();

    // Check if the position is one of the corners
    boolean isCorner = (row == 0 && col == 0) || (row == 0 && col == 2) ||
            (row == 2 && col == 0) || (row == 2 && col == 2);

    assertTrue("Expected the move to be to a corner position", isCorner);

    // The selected card should be the strongest card (index 1)
    int expectedCardIndex = 1; // Index of "StrongCard"
    assertEquals("Expected the strongest card to be selected", expectedCardIndex, selectedMove.getCardIdxInHand());

    // Verifying method calls
    String logContent = log.toString();
    assertTrue(logContent.contains("getCurrentGrid() called"));
    assertTrue(logContent.contains("getHand(RED) called"));
  }

  /**
   * Tests the GoForCornersTactic (Strategy 2) when no corners are available.
   * Since there are no corners available, this should be going to the fallback strategy.
   * In turn, this tests the fallback strategy.
   * @throws IOException When invalid input
   */
  @Test
  public void testNoCornersAvailable() throws IOException {
    // Create an Appendable log
    StringBuilder log = new StringBuilder();

    // Create a 3x3 grid with occupied corners, leaving other cells empty
    Cell[][] mockGrid = createGridWithOccupiedCorners(3, 3);
    // Clear one non-corner cell to simulate available space
    mockGrid[1][1] = new ThreeTriosCell(true);
    Player currentPlayer = Player.RED;

    // Create a hand with one card
    Card card = new ThreeTriosCard("Card", 5, 5, 5, 5);
    List<Card> mockHand = Arrays.asList(card);

    // Set up the mock model
    MockReadOnlyThreeTrios mockModel = new MockReadOnlyThreeTrios(log);
    mockModel.setCurrentGrid(mockGrid);
    mockModel.setCurrentPlayer(currentPlayer);
    mockModel.setPlayerHand(mockHand);

    // Use GoForCornersTactic as the tactic
    GoForCornersTactic tactic = new GoForCornersTactic();

    // Create an instance of TacticOrFirstOpenSpaceStrategy with the tactic
    TacticOrFirstOpenSpaceStrategy strategy = new TacticOrFirstOpenSpaceStrategy(tactic);

    // Execute the strategy
    Move selectedMove = strategy.chooseMove(mockModel, currentPlayer);

    // Since no corners are available, it should pick the first open space
    GridPos expectedPosition = new GridPos2d(1, 1); // The cell we cleared
    int expectedCardIndex = 0; // The fallback uses card at index 0

    assertEquals("Expected the move to be to the first open space (1,1)", expectedPosition, selectedMove.getPosition());
    assertEquals("Expected the fallback to use card index 0", expectedCardIndex, selectedMove.getCardIdxInHand());

    // Verifying method calls
    String logContent = log.toString();
    assertTrue(logContent.contains("getCurrentGrid() called"));
    assertTrue(logContent.contains("getHand(RED) called"));
  }

  /**
   * Tests the GoForCornersTactic (Strategy 2) when some corners are available.
   * @throws IOException When invalid input
   */
  @Test
  public void testSomeCornersAvailable() throws IOException {
    // Create an Appendable log
    StringBuilder log = new StringBuilder();

    // Create a 3x3 grid with some corners occupied
    Cell[][] mockGrid = createGridWithSomeOccupiedCorners(3, 3);
    Player currentPlayer = Player.RED;

    // Create a hand with two cards
    Card weakCard = new ThreeTriosCard("WeakCard", 1, 1, 1, 1);
    Card strongCard = new ThreeTriosCard("StrongCard", 9, 9, 9, 9);
    List<Card> mockHand = Arrays.asList(weakCard, strongCard);

    // Set up the mock model
    MockReadOnlyThreeTrios mockModel = new MockReadOnlyThreeTrios(log);
    mockModel.setCurrentGrid(mockGrid);
    mockModel.setCurrentPlayer(currentPlayer);
    mockModel.setPlayerHand(mockHand);

    // Use GoForCornersTactic as the tactic
    GoForCornersTactic tactic = new GoForCornersTactic();

    // Create an instance of TacticOrFirstOpenSpaceStrategy with the tactic
    TacticOrFirstOpenSpaceStrategy strategy = new TacticOrFirstOpenSpaceStrategy(tactic);

    // Execute the strategy
    Move selectedMove = strategy.chooseMove(mockModel, currentPlayer);

    // The only available corner is top-left (0,0)
    GridPos expectedPosition = new GridPos2d(0, 0);
    int expectedCardIndex = 1; // The strongest card

    assertEquals("Expected the move to be to the available corner (0,0)", expectedPosition, selectedMove.getPosition());
    assertEquals("Expected the strongest card to be selected", expectedCardIndex, selectedMove.getCardIdxInHand());

    // Verifying method calls
    String logContent = log.toString();
    assertTrue(logContent.contains("getCurrentGrid() called"));
    assertTrue(logContent.contains("getHand(RED) called"));
  }

  /**
   * Makes sure that it throws an ISE when there are no moves available and the player is trying
   * to make a move.
   * @throws IOException when invalid input
   */
  @Test(expected = IllegalStateException.class)
  public void testNoMovesAvailable() throws IOException {
    // Create an Appendable log
    StringBuilder log = new StringBuilder();

    // Create a 3x3 full grid (no open spaces)
    Cell[][] mockGrid = createFullMockGrid(3, 3);
    Player currentPlayer = Player.RED;
    // No cards in hand to place
    List<Card> mockHand = new ArrayList<>();

    // Set up the mock model
    MockReadOnlyThreeTrios mockModel = new MockReadOnlyThreeTrios(log);
    mockModel.setCurrentGrid(mockGrid);
    mockModel.setCurrentPlayer(currentPlayer);
    mockModel.setPlayerHand(mockHand);

    // Use GoForCornersTactic as the tactic
    GoForCornersTactic tactic = new GoForCornersTactic();

    // Create an instance of TacticOrFirstOpenSpaceStrategy with the tactic
    TacticOrFirstOpenSpaceStrategy strategy = new TacticOrFirstOpenSpaceStrategy(tactic);

    // Execute the strategy
    // Expected to throw an IllegalStateException because there are no moves
    strategy.chooseMove(mockModel, currentPlayer);
  }

  /**
   * Makes sure that FlipMostCards (Strategy 1) works.
   * @throws IOException when input invalid
   */
  @Test
  public void testFlipMostCardsTacticProvidesMove() throws IOException {
    // Create an Appendable log
    StringBuilder log = new StringBuilder();

    // Mock data
    Cell[][] mockGrid = createGridWithFlippableCards();
    Player currentPlayer = Player.RED;

    // Create a hand with cards
    Card card1 = new ThreeTriosCard("Card1", 9, 9, 9, 9);
    List<Card> mockHand = Arrays.asList(card1);

    // Set up the mock model
    MockReadOnlyThreeTrios mockModel = new MockReadOnlyThreeTrios(log);
    mockModel.setCurrentGrid(mockGrid);
    mockModel.setCurrentPlayer(currentPlayer);
    mockModel.setPlayerHand(mockHand);

    // Use FlipMostCardsTactic as the tactic
    FlipMostCardsTactic tactic = new FlipMostCardsTactic();

    // Create an instance of TacticOrFirstOpenSpaceStrategy with the tactic
    TacticOrFirstOpenSpaceStrategy strategy = new TacticOrFirstOpenSpaceStrategy(tactic);

    // Execute the strategy
    Move selectedMove = strategy.chooseMove(mockModel, currentPlayer);

    // Verify that the move returned is the one from the tactic
    Move expectedMove = new ThreeTriosMove(new GridPos2d(1, 1), 0);
    assertEquals("Expected the move from the tactic to be used", expectedMove, selectedMove);

    // Verifying method logs
    String logContent = log.toString();
    assertTrue(logContent.contains("getCurrentGrid() called"));
    assertTrue(logContent.contains("getHand(RED) called"));
  }

  /**
   * This test makes sure that flip most cards selects the best move available out of
   * many different move options.
   * @throws IOException when input invalid
   */
  @Test
  public void testFlipMostCardsTacticSelectsBestMove() throws IOException {
    // Create an Appendable log
    StringBuilder log = new StringBuilder();

    // Mock data
    Cell[][] mockGrid = createGridWithMultipleFlippableOptions();
    Player currentPlayer = Player.RED;

    // Create a hand with multiple cards
    Card card1 = new ThreeTriosCard("Card1", 8, 8, 8, 8); // High values
    Card card2 = new ThreeTriosCard("Card2", 3, 3, 3, 3); // Low values
    List<Card> mockHand = Arrays.asList(card1, card2);

    // Set up the mock model
    MockReadOnlyThreeTrios mockModel = new MockReadOnlyThreeTrios(log);
    mockModel.setCurrentGrid(mockGrid);
    mockModel.setCurrentPlayer(currentPlayer);
    mockModel.setPlayerHand(mockHand);

    // Use FlipMostCardsTactic as the tactic
    FlipMostCardsTactic tactic = new FlipMostCardsTactic();

    // Create an instance of TacticOrFirstOpenSpaceStrategy with the tactic
    TacticOrFirstOpenSpaceStrategy strategy = new TacticOrFirstOpenSpaceStrategy(tactic);

    // Execute the strategy
    Move selectedMove = strategy.chooseMove(mockModel, currentPlayer);

    // Verify that the move is as expected
    Move expectedMove = new ThreeTriosMove(new GridPos2d(1, 1), 0);
    assertEquals("Expected the tactic to choose the move that flips the most cards", expectedMove, selectedMove);

    // Verifying method calls
    String logContent = log.toString();
    assertTrue(logContent.contains("getCurrentGrid() called"));
    assertTrue(logContent.contains("getHand(RED) called"));
  }

  // Helper Methods

  private Cell[][] createEmptyGrid(int rows, int cols) {
    Cell[][] grid = new Cell[rows][cols];
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        grid[row][col] = new ThreeTriosCell(true);
      }
    }
    return grid;
  }

  private Cell[][] createGridWithOccupiedCorners(int rows, int cols) {
    Cell[][] grid = createEmptyGrid(rows, cols);

    // Create a card to occupy corners
    Card cornerCard = new ThreeTriosCard("CornerCard", 5, 5, 5, 5);

    // Occupy corners
    grid[0][0] = new ThreeTriosCell(true); // Top-left corner
    grid[0][cols - 1] = new ThreeTriosCell(true); // Top-right corner
    grid[rows - 1][0] = new ThreeTriosCell(true); // Bottom-left corner
    grid[rows - 1][cols - 1] = new ThreeTriosCell(true); // Bottom-right corner

    grid[0][0].setCard(cornerCard, Player.BLUE);
    grid[0][cols - 1].setCard(cornerCard, Player.BLUE);
    grid[rows - 1][0].setCard(cornerCard, Player.BLUE);
    grid[rows - 1][cols - 1].setCard(cornerCard, Player.BLUE);

    return grid;
  }

  private Cell[][] createGridWithSomeOccupiedCorners(int rows, int cols) {
    Cell[][] grid = createEmptyGrid(rows, cols);

    // Create a card to occupy some corners
    Card cornerCard = new ThreeTriosCard("CornerCard", 5, 5, 5, 5);

    // Occupy all corners except top-left
    grid[0][cols - 1] = new ThreeTriosCell(true); // Top-right corner
    grid[rows - 1][0] = new ThreeTriosCell(true); // Bottom-left corner
    grid[rows - 1][cols - 1] = new ThreeTriosCell(true); // Bottom-right corner

    grid[0][cols - 1].setCard(cornerCard, Player.BLUE);
    grid[rows - 1][0].setCard(cornerCard, Player.BLUE);
    grid[rows - 1][cols - 1].setCard(cornerCard, Player.BLUE);

    return grid;
  }

  private Cell[][] createFullMockGrid(int rows, int cols) {
    Cell[][] grid = new Cell[rows][cols];
    Card occupiedCard = new ThreeTriosCard("OccupiedCard", 5, 5, 5, 5);
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        grid[row][col] = new ThreeTriosCell(true); // Occupied by BLUE player
        grid[row][col].setCard(occupiedCard, Player.BLUE);
      }
    }
    return grid;
  }

  private Cell[][] createGridWithFlippableCards() {
    int rows = 3;
    int cols = 3;
    Cell[][] grid = createEmptyGrid(rows, cols);

    // Place opponent's cards adjacent to empty positions
    Card opponentCard = new ThreeTriosCard("OpponentCard", 2, 2, 2, 2);
    grid[1][0] = new ThreeTriosCell(true);
    grid[0][1] = new ThreeTriosCell(true);

    grid[1][0].setCard(opponentCard, Player.BLUE);
    grid[0][1].setCard(opponentCard, Player.BLUE);

    return grid;
  }

  private Cell[][] createGridWithMultipleFlippableOptions() {
    int rows = 3;
    int cols = 3;
    Cell[][] grid = createEmptyGrid(rows, cols);

    // Place opponent's cards around multiple empty positions
    Card opponentCard = new ThreeTriosCard("OpponentCard", 2, 2, 2, 2);
    grid[1][0] = new ThreeTriosCell(true);
    grid[0][1] = new ThreeTriosCell(true);
    grid[1][2] = new ThreeTriosCell(true);
    grid[2][1] = new ThreeTriosCell(true);

    grid[1][0].setCard(opponentCard, Player.BLUE);
    grid[0][1].setCard(opponentCard, Player.BLUE);
    grid[1][2].setCard(opponentCard, Player.BLUE);
    grid[2][1].setCard(opponentCard, Player.BLUE);

    return grid;
  }

}

