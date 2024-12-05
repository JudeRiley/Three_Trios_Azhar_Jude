package cs3500.gameplay;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.code.controller.Controller;
import cs3500.threetrios.code.model.GridPos2d;
import cs3500.threetrios.code.model.MachinePlayer;
import cs3500.threetrios.code.model.Move;
import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.code.model.ThreeTriosMove;
import cs3500.threetrios.code.strategies.ThreeTriosStrategy;

/**
 * Tests that a machine player object performs all the necessary actions for a mocked controller
 * and a mocked strategy.
 */
public class MachinePlayerTest {

  private List<Object> testControllerInputs;
  private List<Object> testStrategyInputs;
  private MachinePlayer blueMachinePlayer;
  private MachinePlayer redMachinePlayer;
  private Move move1;
  private Move move2;
  private Move move3;
  private ReadOnlyThreeTriosModel mockModel;
  private Controller redController;
  private Controller blueController;


  private void init() {
    testControllerInputs = new ArrayList<>();
    testStrategyInputs = new ArrayList<>();
    move1 = new ThreeTriosMove(new GridPos2d(1, 1), 1);
    move2 = new ThreeTriosMove(new GridPos2d(2, 2), 2);
    move3 = new ThreeTriosMove(new GridPos2d(3, 3), 3);
    mockModel = new MockModel();

    redController = new MockController(testControllerInputs, "RedController");
    blueController = new MockController(testControllerInputs, "BlueController");

    ThreeTriosStrategy redStrat =
            new MockStrategy(List.of(move1, move2, move3), testStrategyInputs);
    ThreeTriosStrategy blueStrat =
            new MockStrategy(List.of(move3, move2, move1), testStrategyInputs);

    redMachinePlayer = new MachinePlayer(Player.RED, redStrat);
    blueMachinePlayer = new MachinePlayer(Player.BLUE, blueStrat);
  }

  /**
   * Tests that the human player returns its color correctly.
   */
  @Test
  public void testGetPlayerColor() {
    init();
    Assert.assertEquals(Player.RED, redMachinePlayer.getPlayerColor());
    Assert.assertEquals(Player.BLUE, blueMachinePlayer.getPlayerColor());
  }

  /**
   * Tests that the machine player is given a proper reference to the controller.
   */
  @Test
  public void testSetController() {
    init();
    redMachinePlayer.setController(redController);
    redMachinePlayer.setModel(mockModel);

    redMachinePlayer.performAction();
    Assert.assertTrue(testControllerInputs.contains("RedController"));

    testControllerInputs.clear();

    blueMachinePlayer.setController(blueController);
    blueMachinePlayer.setModel(mockModel);

    blueMachinePlayer.performAction();
    Assert.assertTrue(testControllerInputs.contains("BlueController"));
  }

  /**
   * Tests that the machine player is given a proper reference to the read only model.
   */
  @Test
  public void testSetModel() {
    init();
    redMachinePlayer.setModel(mockModel);
    redMachinePlayer.setController(redController);

    redMachinePlayer.performAction();
    Assert.assertTrue(testStrategyInputs.contains(mockModel));
  }

  /**
   * Tests that the machine player is able to properly pass a Move into the controller.
   */
  @Test
  public void testPerformActionControllerInputs() {
    init();
    redMachinePlayer.setController(redController);
    redMachinePlayer.setModel(mockModel);

    redMachinePlayer.performAction();
    Assert.assertTrue(testControllerInputs.contains(redMachinePlayer));
    Assert.assertTrue(testControllerInputs.contains(move1));
    Assert.assertTrue(testStrategyInputs.contains(Player.RED));
    testControllerInputs.clear();
    redMachinePlayer.performAction();
    Assert.assertTrue(testControllerInputs.contains(redMachinePlayer));
    Assert.assertTrue(testControllerInputs.contains(move2));
    redMachinePlayer.performAction();
    Assert.assertTrue(testControllerInputs.contains(move3));
    testControllerInputs.clear();
    redMachinePlayer.performAction();
    Assert.assertTrue(testControllerInputs.contains(move1));

    testControllerInputs.clear();
    testStrategyInputs.clear();

    blueMachinePlayer.setController(blueController);
    blueMachinePlayer.setModel(mockModel);

    blueMachinePlayer.performAction();
    Assert.assertTrue(testControllerInputs.contains(blueMachinePlayer));
    Assert.assertTrue(testControllerInputs.contains(move3));
    Assert.assertTrue(testStrategyInputs.contains(Player.BLUE));

  }
}
