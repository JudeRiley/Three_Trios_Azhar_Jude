package cs3500.test;

import cs3500.model.Card;
import cs3500.model.Cell;
import cs3500.model.Grid;
import cs3500.model.GridPos;
import cs3500.model.Player;
import cs3500.model.ThreeTrios;
import cs3500.model.ThreeTriosModel;
import cs3500.model.config.GridConfigReader;
import cs3500.model.config.CardConfigReader;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ThreeTriosInterfaceTest {

  private ThreeTrios game;
  private Grid grid;
  private List<Card> deck;

  @Before
  public void setUp() throws IOException {
    // Define the paths to the configuration files
    String gridConfigPath = Paths.get("test", "cs3500", "testingConfigs", "board_no_holes.txt").toString();
    String cardConfigPath = Paths.get("test", "cs3500", "testingConfigs", "cards_small.txt").toString();

    // Initialize the GridConfigReader and read the grid
    GridConfigReader gridConfigReader = new GridConfigReader(gridConfigPath);
    grid = gridConfigReader.readGrid();

    // Initialize the CardConfigReader and read the deck
    CardConfigReader cardConfigReader = new CardConfigReader(cardConfigPath);
    deck = cardConfigReader.readCards();

    // Initialize the game model
    game = new ThreeTriosModel(deck, grid);
  }

  @Test
  public void testPlaceCard() {
    GridPos pos = new GridPos(0, 0);
    int cardIdx = 0;

    game.placeCard(pos, cardIdx);

    // Verify the card was placed correctly
    Cell cell = game.getCurrentGrid()[0][0];
    assertTrue(cell.hasCard());
    assertEquals("RED", cell.getOwnerName());

    // Check that the turn has switched to BLUE
    assertEquals(Player.BLUE, game.getTurn());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlaceCardInvalidIndex() {
    GridPos pos = new GridPos(0, 0);
    int invalidCardIdx = 10;
    game.placeCard(pos, invalidCardIdx);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlaceCardOnOccupiedCell() {
    GridPos pos = new GridPos(0, 0);
    game.placeCard(pos, 0);

    // Repeated placement
    game.placeCard(pos, 0);
  }

  @Test
  public void testBattleStep() {
    GridPos posRed = new GridPos(0, 0);
    GridPos posBlue = new GridPos(0, 1);

    // Red places a card
    game.placeCard(posRed, 0);

    // Blue places a card next to Red's card
    game.placeCard(posBlue, 0);

    Player nextPlayer = game.getTurn();
    assertEquals(Player.RED, nextPlayer);
  }

  @Test
  public void testGetWinnerBeforeGameOver() {
    try {
      game.getWinner();
    } catch (IllegalStateException e) {
      assertEquals("Game is still ongoing!", e.getMessage());
    }
  }

  @Test
  public void testGetWinnerAfterGameOver() {
    // Simulate a full game
    // Fill the grid with cards from both players
    int rows = grid.getCurrentGrid().length;
    int cols = grid.getCurrentGrid()[0].length;
    int totalCells = grid.getNumCardCells();

    for (int i = 0; i < totalCells; i++) {
      boolean cardPlaced = false;
      for (int row = 0; row < rows && !cardPlaced; row++) {
        for (int col = 0; col < cols && !cardPlaced; col++) {
          GridPos pos = new GridPos(row, col);
          Cell cell = game.getCurrentGrid()[row][col];
          if (cell.isCardCell() && !cell.hasCard()) {
            game.placeCard(pos, 0);
            cardPlaced = true;
          }
        }
      }
    }

    Player winner = game.getWinner();
    // Check that the winner is either RED, BLUE, or null
    assertTrue(winner == Player.RED || winner == Player.BLUE || winner == null);
  }

  @Test
  public void testGetTurn() {
    assertEquals(Player.RED, game.getTurn());
    game.placeCard(new GridPos(0, 0), 0);
    assertEquals(Player.BLUE, game.getTurn());
  }

  @Test
  public void testGetCurrentGrid() {
    Cell[][] currentGrid = game.getCurrentGrid();
    assertNotNull(currentGrid);
    assertEquals(grid.getCurrentGrid().length, currentGrid.length);
    assertEquals(grid.getCurrentGrid()[0].length, currentGrid[0].length);
  }

  @Test
  public void testGetHand() {
    List<Card> redHand = game.getHand(Player.RED);
    List<Card> blueHand = game.getHand(Player.BLUE);

    int deckSize = deck.size();
    assertEquals(deckSize / 2, redHand.size());
    assertEquals(deckSize / 2, blueHand.size());

    // After RED places a card
    game.placeCard(new GridPos(0, 0), 0);
    redHand = game.getHand(Player.RED);
    assertEquals(deckSize / 2 - 1, redHand.size());
  }
}

