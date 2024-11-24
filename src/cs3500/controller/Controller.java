package cs3500.controller;

import cs3500.model.MachinePlayer;
import cs3500.model.ModelListener;
import cs3500.model.Move;
import cs3500.view.ViewListener;

/**
 * A controller has both a model and view listener in order to properly process input and
 * display input. The controller is able to handle input from a MachinePlayer.
 */
public interface Controller extends ViewListener, ModelListener {

  /**
   * This method will handle the machine players move and update the
   * view and the model.  If the machine chooses an invalid coordinate,
   * the view will show an error.
   * @param machinePlayer current machine player
   * @param move move used
   */
  void handleMachinePlayerMove(MachinePlayer machinePlayer, Move move);
}
