package cs3500.threetrios.adaptors;

import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.view.ThreeTriosView;
import cs3500.threetrios.code.view.ViewListener;
import cs3500.threetrios.provider.code.guiview.ThreeTriosFrame;

public class ViewAdapter implements ThreeTriosView {

  private final ThreeTriosFrame providerView;


  public ViewAdapter(ThreeTriosFrame providerView) {
    this.providerView = providerView;
  }


  @Override
  public void makeVisible() {
    // Unnecessary for adapting
  }

  @Override
  public void refresh() {
    providerView.updateView();
  }

  @Override
  public void highlightSelectedCard(int cardIndex, Player player) {

  }

  @Override
  public void showError(String message) {

  }

  @Override
  public void updateStatus(String status) {

  }

  @Override
  public void setInputEnabled(boolean enabled) {

  }

  @Override
  public void showGameOver(String message) {

  }

  @Override
  public void bringToFront() {

  }

  @Override
  public void addViewListener(ViewListener listener) {

  }

  @Override
  public void removeViewListener(ViewListener listener) {

  }
}
