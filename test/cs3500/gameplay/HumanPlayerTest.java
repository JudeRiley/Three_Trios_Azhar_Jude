package cs3500.gameplay;

import org.junit.Assert;
import org.junit.Test;

import cs3500.model.HumanPlayer;
import cs3500.model.Player;

/**
 * Tests that a human player object performs all teh necessary actions for a mocked controller.
 */
public class HumanPlayerTest {

  /**
   * Tests that the human player returns its color correctly.
   */
  @Test
  public void testGetPlayerColor() {
    HumanPlayer redPlayer = new HumanPlayer(Player.RED);
    HumanPlayer bluePlayer = new HumanPlayer(Player.BLUE);

    Assert.assertEquals(redPlayer.getPlayerColor(), Player.RED);
    Assert.assertEquals(bluePlayer.getPlayerColor(), Player.BLUE);
  }

  //No Test for performAction is necessary as the action is performed by the user themselves.

}
