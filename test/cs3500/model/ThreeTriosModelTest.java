package cs3500.model;

import cs3500.model.config.GridConfigReader;
import cs3500.model.config.CardConfigReader;

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
  private Random rand;

  @Before
  public void setUp() throws IOException {
    // Define the paths to the configuration files
    String gridConfigPath = Paths.get("test", "cs3500", "testingConfigs", "board_connected_holes.txt").toString();
    String cardConfigPath = Paths.get("test", "cs3500", "testingConfigs", "cards_large.txt").toString();

    // Initialize the GridConfigReader and read the grid
    GridConfigReader gridConfigReader = new GridConfigReader(gridConfigPath);
    grid = gridConfigReader.readGrid();

    // Initialize the CardConfigReader and read the deck
    CardConfigReader cardConfigReader = new CardConfigReader(cardConfigPath);
    deck = cardConfigReader.readCards();

    // Initialize the game model with a fixed random seed and normal game
    rand = new Random(42);
    randGame = new ThreeTriosModel(deck, grid, rand);
    game = new ThreeTriosModel(deck, grid);
  }

  @Test
  public void testConstructorWithRandom() {
    // Access package-private fields redHand and blueHand
    List<Card> redHand = randGame.getHand(Player.RED);
    List<Card> blueHand = randGame.getHand(Player.BLUE);

    assertEquals(deck.size() / 2, redHand.size());
    assertEquals(deck.size() / 2, blueHand.size());
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
    // red places cards like CC (EMPTY) CC so that blue can place the middle card
    // to trigger the chain effect
    game.placeCard(new GridPos(2, 0), 0);
    game.placeCard(new GridPos(0, 0), 0);

    game.placeCard(new GridPos(2, 1), 0);
    game.placeCard(new GridPos(0, 1), 0);

    game.placeCard(new GridPos(2, 3), 0);
    game.placeCard(new GridPos(0, 2), 0);

    game.placeCard(new GridPos(2, 4), 1);
    game.placeCard(new GridPos(0, 3), 0);
    game.placeCard(new GridPos(4, 0), 0);

    int eastVal = grid.getCell(new GridPos(2, 3)).getCardValueOf(Direction.WEST);
    int currVal;

    // make sure the adjacent value is Red before placing
    assertTrue(grid.getCell(new GridPos(2, 3)).getOwnerName().equals("RED"));

    // Place blue card in the middle empty slot to trigger battles
    game.placeCard(new GridPos(2, 2), 3);
    currVal = grid.getCell(new GridPos(2, 2)).getCardValueOf(Direction.EAST);

    // make sure the current east if bigger than the right west val
    assertTrue(currVal > eastVal);

    // make sure the adjacent card gets flipped
    assertTrue(grid.getCell(new GridPos(2, 3)).getOwnerName().equals("BLUE"));

    // check to see that the flipped card has a value greater than the neighbor of the flipped card
    int flippedEastVal = grid.getCell(new GridPos(2, 3)).getCardValueOf(Direction.EAST);
    int flippedNeighborWestVal = grid.getCell(new GridPos(2, 4)).getCardValueOf(Direction.WEST);
    assertTrue(flippedEastVal > flippedNeighborWestVal);

    // Access grid cells to verify the outcome
    Cell cellAt23 = grid.getCell(new GridPos(2, 3));
    Cell cellAt24 = grid.getCell(new GridPos(2, 4));
    assertEquals("BLUE", cellAt23.getOwnerName());
    assertEquals("BLUE", cellAt24.getOwnerName());
  }

  @Test
  public void testGetScoreOf() {
    // Place cards and check scores
    game.placeCard(new GridPos(0, 0), 0); // RED
    game.placeCard(new GridPos(0, 1), 0); // BLUE

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
    // Place a card for Red
    GridPos posRed = new GridPos(2, 2);
    game.placeCard(posRed, 8);

    GridPos posBlue = new GridPos(2, 1);
    // Place a weaker card for Blue adjacent to Red's card
    game.placeCard(posBlue, 0);

    // Get losing neighbors from Red's position
    List<GridPos> losingNeighbors = grid.getLosingNeighbors(posRed);
    // Verify that Blue's card is in the list
    assertTrue(losingNeighbors.get(0).getX() == 2);
    assertTrue(losingNeighbors.get(0).getY() == 1);
  }
}

