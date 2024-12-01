package cs3500.threetrios.provider.code.guiview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import cs3500.threetrios.provider.code.model.CardCell;
import cs3500.threetrios.provider.code.model.ICard;

import cs3500.threetrios.provider.code.model.ReadOnlyThreeTrios;
import cs3500.threetrios.provider.code.players.IPlayer;

/**
 * Represents the panel that holds the Three Trios board/grid and helps the user to view this
 * panel of IBoardCells, with CardCells or HoleCells filling the grid.
 */
public class BoardPanel extends JPanel implements IPanel {

  protected final ReadOnlyThreeTrios model;
  private int cellHeight;
  private int cellWidth;
  private final int rows;
  private final int cols;
  protected int selectedRow;
  protected int selectedCol;
  private boolean interactive;

  /**
   * The constructor for the board panel.
   *
   * @param game represents the game of Three Trios.
   */
  public BoardPanel(ReadOnlyThreeTrios game) {
    setPreferredSize(new Dimension(600, 600));
    this.model = game;
    addMouseListener(new BoardListener());
    this.rows = game.getGrid().length;
    this.cols = game.getGrid()[0].length;
    this.selectedRow = -1;
    this.selectedCol = -1;
    this.interactive = false;
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D g2d = (Graphics2D) graphics;

    int panelWidth = getWidth();
    int panelHeight = getHeight();
    this.cellWidth = panelWidth / cols;
    this.cellHeight = panelHeight / rows;

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        int x = col * cellWidth;
        int y = row * cellHeight;

        if (model.getGrid()[row][col].isAHole()) { // draws a hole
          g2d.setColor(Color.GRAY);
          g2d.fillRect(x, y, cellWidth, cellHeight);
        }
        if (model.getGrid()[row][col].isCardCell()) {
          if (!model.getGrid()[row][col].isEmpty()) { // draws a card
            ICard cardToDraw = ((CardCell) model.getGrid()[row][col]).getCard();
            CardDrawing card = new CardDrawing(cardToDraw, x, y, model);

            IPlayer cardOwner;
            if (model.getCurrentPlayer().getAllPlayerCards().contains(cardToDraw)) {
              cardOwner = model.getCurrentPlayer();
            } else {
              cardOwner = model.otherPlayer();
            }
            card.drawCard(g2d, cardToDraw, cellWidth, cellHeight, cardOwner);
          } else { // draws an empty card cell
            g2d.setColor(Color.ORANGE);
            g2d.fillRect(x, y, cellWidth, cellHeight);
          }
        }
      }
    }

    g2d.setColor(Color.WHITE); // Draw the grid line
    for (int individualRow = 0; individualRow <= rows; individualRow++) {
      g2d.drawLine(0, individualRow * cellHeight, panelWidth, individualRow * cellHeight);
    }
    for (int individualCol = 0; individualCol <= cols; individualCol++) {
      g2d.drawLine(individualCol * cellWidth, 0, individualCol * cellWidth,
              panelHeight);
    }
  }


  @Override
  public void updateView() {
    this.repaint();
  }

  @Override
  public void reset() {
    this.selectedRow = -1;
    this.selectedCol = -1;
  }

  @Override
  public void setInteractive(boolean interactive) {
    this.interactive = interactive;
  }

  /**
   * Represents the mouse listener for the board panel.
   */
  public class BoardListener extends MouseAdapter {

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent event) {
      if (interactive) {
        for (int rowIdx = 0; rowIdx < model.getGrid().length; rowIdx++) {
          for (int colIdx = 0; colIdx < model.getGrid()[0].length; colIdx++) {

            int cardTop = rowIdx * cellHeight;
            int cardBottom = cardTop + cellHeight;

            int cardLeft = colIdx * cellWidth;
            int cardRight = cardLeft + cellWidth;

            if (event.getX() >= cardLeft && event.getX() < cardRight
                    && event.getY() >= cardTop && event.getY() < cardBottom) {
              selectedRow = rowIdx;
              selectedCol = colIdx;
              model.getCurrentPlayer().notifyPlayersChoseAPosn(selectedRow, selectedCol);
            }
          }
        }
      }
    }
  }
}
