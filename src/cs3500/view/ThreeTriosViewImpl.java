package cs3500.view;

import cs3500.model.Player;
import cs3500.model.ReadOnlyThreeTrios;

import javax.swing.JFrame;

import java.awt.BorderLayout;

/**
 * ThreeTriosViewImpl is an implementation of the ThreeTriosView.
 * It shows the left side as the Red player's hand and the right side as the
 * Blue player's hand.  The middle is the board where you can place cards.
 */
public class ThreeTriosViewImpl extends JFrame implements ThreeTriosView {
  private final ReadOnlyThreeTrios model;

  /**
   * Constructor that will initialize everything.
   *
   * @param model the read only model
   */
  public ThreeTriosViewImpl(ReadOnlyThreeTrios model) {
    super("ThreeTrios Game");
    this.model = model;

    updateTitle();

    // Set the default close operation and layout
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    // Initialize panels
    BoardPanel boardPanel = new BoardPanel(model);
    HandPanel redHandPanel = new HandPanel(model, Player.RED);
    HandPanel blueHandPanel = new HandPanel(model, Player.BLUE);

    // Add panels to the frame
    this.add(redHandPanel, BorderLayout.WEST);
    this.add(boardPanel, BorderLayout.CENTER);
    this.add(blueHandPanel, BorderLayout.EAST);

    // Pack and set visibility
    this.pack();
    this.setVisible(true);
  }

  /**
   * Makes the view visible.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Refreshes the view when the game is updated.
   */
  @Override
  public void refresh() {
    this.repaint();
    updateTitle();
  }

  // Method to update the title based on the current player
  private void updateTitle() {
    Player currentPlayer = model.getTurn();
    this.setTitle("Current player: " + currentPlayer);
  }
}
