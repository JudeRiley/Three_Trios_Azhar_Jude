package cs3500.threetrios.provider.code.guiview;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import cs3500.threetrios.provider.code.model.Card;
import cs3500.threetrios.provider.code.model.CardNumber;
import cs3500.threetrios.provider.code.model.ICard;
import cs3500.threetrios.code.model.ReadOnlyThreeTrios;
import cs3500.threetrios.provider.code.players.IPlayer;


/**
 * Represents the panel that holds the Three Trios player's ICards as part of their hand and helps
 * the user to view this panel of ICardCells for each of the two players in Three Trios.
 */
public class CardPanel extends JPanel implements IPanel {

  private final ReadOnlyThreeTrios model;
  private final IPlayer player;
  private int cardHeight;
  private int cardWidth;
  private ICard selectedCard;
  private final ICard defaultCard;
  private boolean interactive;

  /**
   * The constructor for the card panel.
   *
   * @param model  represents the game of Three Trios,
   * @param player represents a player in the game of Three Trios that owns this card panel.
   */
  public CardPanel(ReadOnlyThreeTrios model, IPlayer player) {
    this.model = model;
    addMouseListener(new PanelListener());
    this.player = player;
    this.defaultCard = new Card(CardNumber.ONE, CardNumber.ONE, CardNumber.ONE,
            CardNumber.ONE, "non-selected");
    if (this.selectedCard == null) {
      this.selectedCard = defaultCard;
    }
    this.interactive = false;
  }


  @Override
  public void updateView() {
    this.repaint();
  }

  @Override
  public void reset() {
    this.selectedCard = this.defaultCard;
  }

  @Override
  public void setInteractive(boolean interactive) {
    this.interactive = interactive;
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D g2d = (Graphics2D) graphics;

    if (player.getHand().size() <= 1) {
      this.cardHeight = getHeight();
      this.cardWidth = getWidth();
    } else {
      this.cardHeight = getHeight() / this.player.getHand().size();
      this.cardWidth = getWidth();
    }


    for (int cardIdx = 0; cardIdx < this.player.getHand().size(); cardIdx++) {
      ICard cardToDraw = this.player.getHand().get(cardIdx);
      CardDrawing card = new CardDrawing(cardToDraw, 0, (cardIdx * cardHeight), model);

      card.selected = cardToDraw.equals(this.selectedCard);
      card.drawCard(g2d, cardToDraw, cardWidth, cardHeight, player);
    }
  }

  /**
   * Houses the mouse listener abilities for the card panel.
   */
  public class PanelListener extends MouseAdapter {

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent event) {
      if (interactive) {
        for (int cardIdx = 0; cardIdx < player.getHand().size(); cardIdx++) {

          int cardTopY = cardIdx * cardHeight;
          int cardBottomY = cardHeight + cardTopY;

          // Check if the click is inside the bounds of the current card
          if (event.getY() >= cardTopY && event.getY() < cardBottomY) {

            ICard currentSelectedCard = player.getHand().get(cardIdx);


            if (selectedCard.equals(currentSelectedCard)) {
              selectedCard = defaultCard;
              player.notifyPlayerDeselectedCard();
            } else {
              selectedCard = currentSelectedCard;
            }
            if (!selectedCard.equals(defaultCard)) {
              player.notifyPlayersChoseACard(cardIdx);
            }
          }
        }
        repaint();
      }
    }
  }
}




