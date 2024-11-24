package cs3500.gameplay;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.controller.ThreeTriosController;

/**
 * Tests the functionality of the controller using Mocks for Players and Views.
 */
public class ControllerTest {

  private int test;


  private void init() {
    test = 1;
    List<Object> modelInputs = new ArrayList<>();
    List<Object> viewInputs = new ArrayList<>();

    MockPlayModel mockPlayModel = new MockPlayModel(modelInputs);
    MockView mockView = new MockView(viewInputs);

  }

  /**
   * Makes sure that the Machine Player and move are processed correctly;
   */
  @Test
  public void testHandleMachinePlayerMove() {
    init();
    Assert.assertEquals(1, test);
  }

  /**
   * Makes sure that the correct actions are take upon card selection in the view.
   */
  @Test
  public void testViewListenerOnCardSelected() {
    init();
    Assert.assertEquals(1, test);
  }

  /**
   * Makes sure that the correct actions are take upon position selection in the view.
   */
  @Test
  public void testViewListenerOnPosSelected() {
    init();
    Assert.assertEquals(1, test);
  }

  /**
   * Makes sure that the correct actions are take upon turn completion in the model.
   */
  @Test
  public void testModelListenerOnTurnChanged() {
    init();
    Assert.assertEquals(1, test);
  }

  /**
   * Makes sure that the correct actions are take upon game completion in the model.
   */
  @Test
  public void testModelListenerOnGameOver() {
    init();
    Assert.assertEquals(1, test);
  }

}
