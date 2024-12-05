package cs3500.gameplay;

import java.util.List;

import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.view.ThreeTriosView;
import cs3500.threetrios.code.view.ViewListener;

/**
 * A mock of a ThreeTriosView to be used in testing the controller.
 * Adds proof of functionality to a log.
 */
public class MockView implements ThreeTriosView {

  private final List<Object> log;

  /**
   * Constructs a mock of a view that adds proof of functionality to a log.
   *
   * @param log a list of objects that will be added to in order to prove functionality.
   */
  public MockView(List<Object> log) {
    this.log = log;
  }

  @Override
  public void makeVisible() {
    log.add("makeVisible");
  }

  @Override
  public void refresh() {
    log.add("refresh");
  }

  @Override
  public void highlightSelectedCard(int cardIndex, Player player) {
    // na
  }

  @Override
  public void showError(String message) {
    // na
  }

  @Override
  public void updateStatus(String status) {
    // na
  }

  @Override
  public void setInputEnabled(boolean enabled) {
    // na
  }

  @Override
  public void showGameOver(String message) {
    // na
  }

  @Override
  public void bringToFront() {
    // na
  }

  @Override
  public void addViewListener(ViewListener listener) {
    log.add(listener);
  }

  @Override
  public void removeViewListener(ViewListener listener) {
    log.add("removed listener" + listener);
  }
}
