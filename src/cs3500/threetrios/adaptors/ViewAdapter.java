package cs3500.threetrios.adaptors;

import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.code.view.ThreeTriosView;
import cs3500.threetrios.code.view.ViewListener;
import cs3500.threetrios.provider.code.guiview.IThreeTriosFrame;
import cs3500.threetrios.provider.code.guiview.ThreeTriosFrame;

import javax.swing.JOptionPane;

/**
 * A view adapter class that allows the provided view to work with
 * the local view.
 */
public class ViewAdapter implements ThreeTriosView {

  private final ThreeTriosFrame providerView;

  /**
   * Constructs an instance of our view using an instance of the provider's view
   *
   * @param providerView An instance of the providers view.
   */
  public ViewAdapter(ThreeTriosFrame providerView) {
    this.providerView = providerView;
  }

  @Override
  public void makeVisible() {
    providerView.setVisible(true);
  }

  @Override
  public void refresh() {
    providerView.updateView();
  }

  @Override
  public void highlightSelectedCard(int cardIndex, Player player) {
    // The providers code doesn't support highlighting selected cards
  }

  @Override
  public void showError(String message) {
    JOptionPane.showMessageDialog(providerView, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void updateStatus(String status) {
    providerView.setTitle(status);
  }

  @Override
  public void setInputEnabled(boolean enabled) {
    providerView.setInteractive(enabled, true, true);
  }

  @Override
  public void showGameOver(String message) {
    JOptionPane.showMessageDialog(providerView, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void bringToFront() {
    providerView.toFront();
    providerView.requestFocus();
  }

  @Override
  public void addViewListener(ViewListener listener) {
    // Cannot be adapted without modifying provider code.
    // This is because we cannot reach the necessary components,
    // leftPanel, rightPanel, and boardPanel, as there are no
    // public methods or accessors to retrieve the panels or
    // interact with them.  The internal panels likely handle
    // event processing without exposing these events externally.
  }

  @Override
  public void removeViewListener(ViewListener listener) {
    // Cannot adapt due to the same reason as addViewListener.
  }
}
