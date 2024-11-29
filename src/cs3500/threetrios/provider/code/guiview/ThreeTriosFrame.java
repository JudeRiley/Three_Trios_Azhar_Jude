package cs3500.threetrios.provider.code.guiview;

import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cs3500.threetrios.model.ReadOnlyThreeTrios;
import cs3500.threetrios.players.IPlayer;

/**
 * Describes what the frame of the view for the game Three Trios ought to be able to do.
 */
public class ThreeTriosFrame extends JFrame implements IThreeTriosFrame {

  // the panels are concrete types because they need to behave both like a JPanel
  // and like an IBoard/ICard Panel
  private final CardPanel leftPanel;
  private final CardPanel rightPanel;
  private final BoardPanel boardPanel;
  private final ReadOnlyThreeTrios model;


  /**
   * the constructor for the frame.
   *
   * @param model represents the model of the Three Trios game.
   */
  public ThreeTriosFrame(ReadOnlyThreeTrios model) {
    super();
    Objects.requireNonNull(model);
    this.model = model;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

    IPlayer player1 = model.getCurrentPlayer();
    IPlayer player2 = model.otherPlayer();

    this.leftPanel = new CardPanel(model, player1);
    this.rightPanel = new CardPanel(model, player2);
    this.boardPanel = new BoardPanel(model);

    container.add(this.leftPanel);
    container.add(this.boardPanel);
    container.add(this.rightPanel);

    this.add(container);
    this.pack();
    this.setSize(800, 800);
    this.setResizable(true);
    this.setVisible(true);
  }

  @Override
  public void setPlayerTitle(IPlayer player) {
    if (player.getPlayerColor().equals(model.getCurrentPlayer().getPlayerColor())) {
      this.setTitle(player.toString() + "'s turn!");
    } else {
      this.setTitle(player.toString() + " is waiting for their turn!");
    }
  }

  @Override
  public void updateView() {
    this.leftPanel.updateView();
    this.rightPanel.updateView();
    this.boardPanel.updateView();
  }

  @Override
  public void reset() {
    this.leftPanel.reset();
    this.rightPanel.reset();
    this.boardPanel.reset();
  }


  @Override
  public void setInteractive(boolean interactive, boolean boardPanel, boolean cardPanels) {
    if (boardPanel) {
      this.boardPanel.setInteractive(interactive);
    }
    if (cardPanels) {
      this.leftPanel.setInteractive(interactive);

      this.rightPanel.setInteractive(interactive);
    }
  }
}
