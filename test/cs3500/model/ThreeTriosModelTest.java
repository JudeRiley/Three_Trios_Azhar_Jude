package cs3500.model;

import cs3500.model.config.GridConfigReader;
import cs3500.model.config.CardConfigReader;
import cs3500.view.ThreeTriosTextView;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class ThreeTriosModelTest {

  private ThreeTriosModel game;
  private ThreeTriosModel randGame;
  private Grid grid;
  private List<Card> deck;

  @Before
  public void setUp() throws IOException {
    // Define the paths to the configuration files
    String gridConfigPath = Paths.get("test", "cs3500", "testingConfigs", "board_connected_holes.txt").toString();
    String cardConfigPath = Paths.get("test", "cs3500", "testingConfigs", "cards_weak_red.txt").toString();

    // Initialize the GridConfigReader and read the grid
    GridConfigReader gridConfigReader = new GridConfigReader(gridConfigPath);
    grid = gridConfigReader.readGrid();

    // Initialize the CardConfigReader and read the deck
    CardConfigReader cardConfigReader = new CardConfigReader(cardConfigPath);
    deck = cardConfigReader.readCards();

    game = new ThreeTriosModel(deck, grid);
  }

  @Test
  public void testBattleStepInternal() {
    // Place a card for Red at a position
    game.placeCard(new GridPos(2, 2), 0);

    // Place a card for Blue adjacent to Red's card to trigger a battle
    game.placeCard(new GridPos(2, 3), 6);

    // Access grid cells to verify the outcome
    Cell cellAt22 = grid.getCell(new GridPos(2, 2));
    Cell cellAt23 = grid.getCell(new GridPos(2, 3));

    assertEquals("Blue should own cell at (2,3)", "BLUE", cellAt23.getOwnerName());
    assertEquals("Blue should have flipped Red's card at (2,2)", "BLUE", cellAt22.getOwnerName());
  }

  @Test
  public void testBattleStepChainBehavior() {
    // Red is the weaker card and we set up the board like
    // RR_RR, then once we place a stronger blue card the row
    // should be BBBBB
    // (The middle R's will be stronger than ending R's)
    game.placeCard(new GridPos(2, 0), 0);
    game.placeCard(new GridPos(0, 0), 0);

    game.placeCard(new GridPos(2, 1), 9);
    game.placeCard(new GridPos(0, 1), 0);

    game.placeCard(new GridPos(2, 3), 9);
    game.placeCard(new GridPos(0, 2), 0);

    game.placeCard(new GridPos(2, 4), 0);
    System.out.println(new ThreeTriosTextView(game));

    game.placeCard(new GridPos(2, 2), 0);
    // should be RRRRR in middle row but is RBBBR
    System.out.println(new ThreeTriosTextView(game));
  }

  @Test
  public void testGetScoreOf() {
    // Place cards and check scores
    game.placeCard(new GridPos(0, 0), 0); // RED
    game.placeCard(new GridPos(0, 3), 0); // BLUE

    int redScore = grid.getScoreOf(Player.RED);
    int blueScore = grid.getScoreOf(Player.BLUE);

    assertEquals(1, redScore);
    assertEquals(1, blueScore);
  }

  @Test
  public void testIsSaturated() {
    assertFalse(grid.isSaturated());

    // Fill up the grid
    int rows = grid.getCurrentGrid().length;
    int cols = grid.getCurrentGrid()[0].length;

    while (!grid.isSaturated()) {
      Player currentPlayer = game.getTurn();
      List<Card> hand = currentPlayer == Player.RED ? game.getHand(Player.RED) : game.getHand(Player.BLUE);

      boolean cardPlaced = false;
      for (int row = 0; row < rows && !cardPlaced; row++) {
        for (int col = 0; col < cols && !cardPlaced; col++) {
          GridPos pos = new GridPos(row, col);
          Cell cell = grid.getCell(pos);
          if (cell.isCardCell() && !cell.hasCard()) {
            if (!hand.isEmpty()) {
              game.placeCard(pos, 0);
              cardPlaced = true;
            }
          }
        }
      }

      if (!cardPlaced) {
        break;
      }
    }
    // Now the grid should be saturated
    assertTrue(grid.isSaturated());
  }

  @Test
  public void testFlipCardCellTo() {
    // Place a card for Red
    GridPos pos = new GridPos(2, 2);
    game.placeCard(pos, 0);

    // Flip the card to Blue
    grid.flipCardCellTo(pos, Player.BLUE);

    // Verify the owner has changed
    Cell cell = grid.getCell(pos);
    assertEquals("BLUE", cell.getOwnerName());
  }

  @Test
  public void testGetLosingNeighbors() {
    GridPos posRed = new GridPos(0, 0);
    game.placeCard(posRed, 0);

    GridPos posBlue = new GridPos(2, 1);
    game.placeCard(posBlue, 0);

    // Place a weaker card for Red
    posRed = new GridPos(2, 2);
    game.placeCard(posRed, 0);

    // Get losing neighbors from Blue's position
    List<GridPos> losingNeighbors = grid.getLosingNeighbors(posBlue);
    // Verify that Blue's card is in the list
    assertTrue(losingNeighbors.get(0).getX() == 2);
    assertTrue(losingNeighbors.get(0).getY() == 2);
  }
}

