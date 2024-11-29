package cs3500.provider.customerCopy.src.cs3500.threetrios.guiview;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

import cs3500.threetrios.model.ICard;
import cs3500.threetrios.model.PlayerColor;
import cs3500.threetrios.model.ReadOnlyThreeTrios;
import cs3500.threetrios.players.IPlayer;

/**
 * Represents a class for drawing just one ICards on the CardPanel.
 */
public class CardDrawing extends Path2D.Double {

  private final ReadOnlyThreeTrios model;
  private final int cardX;
  private final int cardY;
  boolean selected;

  /** The constructor for a CardDrawing.
   *
   * @param card represents the ICard we are trying to draw
   * @param cardX represents the x position of the card where we want to draw it
   * @param cardY represents the y position of the card where we want to draw it
   * @param model represents the model that we are interacting with
   */
  public CardDrawing(ICard card, int cardX, int cardY, ReadOnlyThreeTrios model) {
    this.cardX = cardX;
    this.cardY = cardY;
    this.model = model;
  }

  /**
   * Draws an ICard on the card panel with a certain color, text for values, width and height.
   *
   * @param g2d    represents our 2d graphics
   * @param card   represents the ICard we are trying to draw
   * @param width  represents the width of the ICard on the card panel
   * @param height represents the height of an ICard on the card panel
   * @param player represents which player has this card in their hand or owned card list.
   */
  public void drawCard(Graphics2D g2d, ICard card, int width, int height, IPlayer player) {

    if (player.getPlayerColor().equals(PlayerColor.RED)) {
      g2d.setColor(Color.RED);
    } else if (player.getPlayerColor().equals(PlayerColor.BLUE)) {
      g2d.setColor(Color.BLUE);
    }
    g2d.fillRect(cardX, cardY, width, height);

    g2d.setColor(Color.BLACK);
    g2d.drawRect(cardX, cardY, width, height);

    drawTextOnCard(g2d, card, width, height);

    // draws border around selected card
    if (this.selected
            && player.getPlayerColor().equals(model.getCurrentPlayer().getPlayerColor())) {
      g2d.setColor(Color.BLACK);
      g2d.setStroke(new BasicStroke(7));
      g2d.drawRect(cardX, cardY, width, height);

      // reset stroke width
      g2d.setStroke(new BasicStroke(1));
    }
  }

  private void drawTextOnCard(Graphics2D g2d, ICard card, int width, int height) {
    g2d.setFont(new Font("Arial", Font.PLAIN, 16));

    String north = String.valueOf(card.getNorth());
    String south = String.valueOf(card.getSouth());
    String east = String.valueOf(card.getEast());
    String west = String.valueOf(card.getWest());

    FontMetrics fm = g2d.getFontMetrics();

    int northWidth = fm.stringWidth(north);
    int northX = cardX + (width - northWidth) / 2;
    int northY = cardY + fm.getAscent();

    int southWidth = fm.stringWidth(south);
    int southX = cardX + (width - southWidth) / 2;
    int southY = cardY + height - fm.getDescent();

    int eastWidth = fm.stringWidth(east);
    int eastX = cardX + width - eastWidth - 5; // aligned 5 pixels away from edge
    int eastY = cardY + (height + fm.getAscent()) / 2;

    int westWidth = fm.stringWidth(west);
    int westX = cardX + 5;  // aligned 5 pixels away from edge
    int westY = cardY + (height + fm.getAscent()) / 2;

    g2d.drawString(north, northX, northY);
    g2d.drawString(south, southX, southY);
    g2d.drawString(east, eastX, eastY);
    g2d.drawString(west, westX, westY);
  }
}
